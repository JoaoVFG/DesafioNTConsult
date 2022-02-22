package JoaoVFG.com.github.DesafioNTConsult.Service;

import JoaoVFG.com.github.DesafioNTConsult.DTO.CreateVotoDTO;
import JoaoVFG.com.github.DesafioNTConsult.Entity.Pauta;
import JoaoVFG.com.github.DesafioNTConsult.Entity.Pessoa;
import JoaoVFG.com.github.DesafioNTConsult.Entity.Voto;
import JoaoVFG.com.github.DesafioNTConsult.Repository.VotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VotoService {

    @Autowired
    VotoRepository votoRepository;

    @Autowired
    PessoaService pessoaService;

    @Autowired
    PautaService pautaService;

    public Voto createVoto(CreateVotoDTO createVotoDTO) {
        Voto voto = votoFromDTO(createVotoDTO);
        voto = votoRepository.save(voto);
        return voto;
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
