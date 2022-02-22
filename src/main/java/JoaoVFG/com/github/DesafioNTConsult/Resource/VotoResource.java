package JoaoVFG.com.github.DesafioNTConsult.Resource;

import JoaoVFG.com.github.DesafioNTConsult.DTO.CreateVotoDTO;
import JoaoVFG.com.github.DesafioNTConsult.Entity.Voto;
import JoaoVFG.com.github.DesafioNTConsult.Service.VotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping(value = "/voto")
public class VotoResource {

    @Autowired
    VotoService votoService;

    @RequestMapping(value = "/buscaid", method = RequestMethod.GET)
    public ResponseEntity<Voto> findById(@PathVariable String idVoto) {
        Voto voto = votoService.findById(Integer.parseInt(idVoto));
        return ResponseEntity.ok().body(voto);
    }

    @RequestMapping(value = "/votar", method = RequestMethod.POST)
    public ResponseEntity<Voto> vote(@RequestBody CreateVotoDTO createVotoDTO) {
        Voto voto = votoService.createVoto(createVotoDTO);
        URI uri = URI.create("/voto" + "/buscaid" + voto.getId());
        return ResponseEntity.created(uri).body(voto);
    }
}
