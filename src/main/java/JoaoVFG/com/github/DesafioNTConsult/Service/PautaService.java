package JoaoVFG.com.github.DesafioNTConsult.Service;

import JoaoVFG.com.github.DesafioNTConsult.DTO.ResultadoVotacaoConsultaDTO;
import JoaoVFG.com.github.DesafioNTConsult.DTO.ResultadoVotacaoDTO;
import JoaoVFG.com.github.DesafioNTConsult.DTO.StartVotacaoDTO;
import JoaoVFG.com.github.DesafioNTConsult.Entity.Pauta;
import JoaoVFG.com.github.DesafioNTConsult.Repository.PautaRepository;
import JoaoVFG.com.github.DesafioNTConsult.Repository.VotoRepository;
import JoaoVFG.com.github.DesafioNTConsult.Service.Exception.DataIntegrityException;
import JoaoVFG.com.github.DesafioNTConsult.Service.Exception.ObjectNotFoundException;
import JoaoVFG.com.github.DesafioNTConsult.Service.Util.DateParserUtil;
import JoaoVFG.com.github.DesafioNTConsult.Service.Util.DateSum;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PautaService {

    @Autowired
    PautaRepository pautaRepository;

    @Autowired
    VotoRepository votoRepository;

    DateParserUtil dateParserUtil = new DateParserUtil();
    DateSum dateSum = new DateSum();

    public Pauta findById(Integer id) {
        Optional<Pauta> pauta = Optional.ofNullable(pautaRepository.findByPautaId(id));
        return pauta.orElseThrow(() -> new ObjectNotFoundException("NÃO EXISTE PAUTA COM O ID INFORMADO"));
    }

    public Pauta createPauta(String tema) {
        Pauta pauta = new Pauta();
        pauta.setTema(tema);

        return pautaRepository.save(pauta);
    }

    @SneakyThrows
    public Pauta startVotacao(StartVotacaoDTO startVotacaoDTO) {
        Pauta pauta = findById(startVotacaoDTO.getIdPauta());

        if (pauta.getHoraInicio() == null) {

            Integer tempoVotacao;
            String dataHoraInicio = new SimpleDateFormat("dd/MM/yyyy' 'HH:mm:ss").format(new Date());

            if (startVotacaoDTO.getTempoVotacao() == null) tempoVotacao = 1;
            else tempoVotacao = startVotacaoDTO.getTempoVotacao();

            pauta.setHoraInicio(dateParserUtil.conversorData(dataHoraInicio));
            pauta.setHoraEncerramento(dateSum.sumMinutes(pauta.getHoraInicio(), tempoVotacao));

            pauta = pautaRepository.save(pauta);
            return pauta;

        } else {
            throw new DataIntegrityException("PAUTA JÁ FOI ABERTA PARA VOTAÇÃO");
        }
    }

    public ResultadoVotacaoDTO resultadoVotacao(Integer idPauta) {
        Pauta pauta = findById(idPauta);

        if(pauta.getHoraInicio() != null) {
            ResultadoVotacaoDTO resultadoVotacaoDTO = new ResultadoVotacaoDTO();
            resultadoVotacaoDTO.setIdPauta(idPauta);
            resultadoVotacaoDTO.setTema(pautaRepository.findByPautaId(idPauta).getTema());
            resultadoVotacaoDTO.setQuantidadeVotosNao(0);
            resultadoVotacaoDTO.setQuantidadeVotosSim(0);

            List<ResultadoVotacaoConsultaDTO> resultadoVotacaoConsultaDTO = votoRepository.sumQuantidadeVotacao(idPauta);

            for (ResultadoVotacaoConsultaDTO item : resultadoVotacaoConsultaDTO) {
                if (item.getVoto() == "Sim") {
                    resultadoVotacaoDTO.setQuantidadeVotosSim(item.getTotalVotos());
                } else {
                    resultadoVotacaoDTO.setQuantidadeVotosNao(item.getTotalVotos());
                }
            }

            return resultadoVotacaoDTO;
        } else {
            throw new DataIntegrityException("PAUTA NÃO FOI ABERTA PARA VOTAÇÃO");
        }
    }

}
