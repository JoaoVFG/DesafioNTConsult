package JoaoVFG.com.github.DesafioNTConsult.Service;

import JoaoVFG.com.github.DesafioNTConsult.DTO.CreateVotoDTO;
import JoaoVFG.com.github.DesafioNTConsult.Entity.Pauta;
import JoaoVFG.com.github.DesafioNTConsult.Entity.Pessoa;
import JoaoVFG.com.github.DesafioNTConsult.Entity.Voto;
import JoaoVFG.com.github.DesafioNTConsult.Repository.VotoRepository;
import JoaoVFG.com.github.DesafioNTConsult.Service.Exception.DataIntegrityException;
import JoaoVFG.com.github.DesafioNTConsult.Service.Util.DateParserUtil;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class VotoService {

    @Autowired
    VotoRepository votoRepository;

    @Autowired
    PessoaService pessoaService;

    @Autowired
    PautaService pautaService;

    DateParserUtil dateParserUtil = new DateParserUtil();

    @SneakyThrows
    public Voto createVoto(CreateVotoDTO createVotoDTO) {
        Voto voto = votoFromDTO(createVotoDTO);

        String dataHoraAtual = new SimpleDateFormat("dd/MM/yyyy' 'HH:mm:ss").format(new Date());

        if(voto.getPauta().getHoraInicio() != null) {
            Date dataHoraVoto =dateParserUtil.conversorData(new SimpleDateFormat("dd/MM/yyyy' 'HH:mm:ss").format(new Date()));

            if (dataHoraVoto.before(voto.getPauta().getHoraEncerramento())) {
                if (alreadyVoted(voto.getPessoa().getId(),voto.getPauta().getId())){
                    throw new DataIntegrityException("PESSOA JA VOTOU NESSA PAUTA");
                } else {
                    voto = votoRepository.save(voto);
                    return voto;
                }

            } else {
                throw new DataIntegrityException("VOTAÇÃO PARA PAUTA ESTA ENCERRADA");
            }

        } else {
            throw new DataIntegrityException("PAUTA NÃO ESTA ABERTA PARA VOTAÇÃO");
        }

    }

    public Boolean alreadyVoted(Integer idPessoa, Integer idPauta) {

        Voto voto = votoRepository.fidnByIdPessoaAndIdVoto(idPessoa, idPauta);
        if(voto != null) {
            return true;
        } else {
            return false;
        }
    }

    private Voto votoFromDTO(CreateVotoDTO createVotoDTO) {
        Voto voto = new Voto();
        voto.setPessoa(pessoaService.findByPessoaId(createVotoDTO.getIdPessoa()));
        voto.setPauta(pautaService.findById(createVotoDTO.getIdPauta()));
        voto.setVoto(createVotoDTO.getVoto());
        voto.setId(null);
        return voto;
    }

}
