package JoaoVFG.com.github.DesafioNTConsult.Resource;

import JoaoVFG.com.github.DesafioNTConsult.DTO.ResultadoVotacaoDTO;
import JoaoVFG.com.github.DesafioNTConsult.DTO.StartVotacaoDTO;
import JoaoVFG.com.github.DesafioNTConsult.Entity.Pauta;
import JoaoVFG.com.github.DesafioNTConsult.Service.PautaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping(value = "/pauta")
public class PautaResource {

    @Autowired
    PautaService pautaService;

    @RequestMapping(value = "/buscaid", method = RequestMethod.GET)
    public ResponseEntity<Pauta> findById(@PathVariable String idPauta) {
        Pauta pauta = pautaService.findById(Integer.parseInt(idPauta));
        return ResponseEntity.ok().body(pauta);
    }

    @RequestMapping(value = "/inserepauta", method = RequestMethod.POST)
    public ResponseEntity<Pauta> createPauta(@RequestBody String temaPauta) {
        Pauta pauta = pautaService.createPauta(temaPauta);
        URI uri = URI.create("/pauta" + "/buscaid" + pauta.getId());
        return ResponseEntity.created(uri).body(pauta);
    }

    @RequestMapping(value = "/iniciavotacao", method = RequestMethod.PUT)
    public ResponseEntity<Pauta> startVotacao(@RequestBody StartVotacaoDTO startVotacaoDTO) {
        Pauta pauta = pautaService.startVotacao(startVotacaoDTO);
        return ResponseEntity.ok().body(pauta);
    }

    @RequestMapping(value = "/resultadovotacao", method = RequestMethod.GET)
    public ResponseEntity<ResultadoVotacaoDTO> resultadoVotacao(@PathVariable String idPauta) {
        ResultadoVotacaoDTO resultadoVotacaoDTO = pautaService.resultadoVotacao(Integer.parseInt(idPauta));
        return ResponseEntity.ok().body(resultadoVotacaoDTO);
    }
}
