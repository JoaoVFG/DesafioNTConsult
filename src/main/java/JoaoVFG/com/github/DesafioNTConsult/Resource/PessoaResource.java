package JoaoVFG.com.github.DesafioNTConsult.Resource;

import JoaoVFG.com.github.DesafioNTConsult.DTO.CreatePessoaDTO;
import JoaoVFG.com.github.DesafioNTConsult.Entity.Pessoa;
import JoaoVFG.com.github.DesafioNTConsult.Service.PessoaService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping(value = "/pessoa")
public class PessoaResource {

    @Autowired
    PessoaService pessoaService;

    @ApiOperation("Busca uma pessoa Por Id")
    @RequestMapping(value = "/buscaid/id/{idPessoa}", method = RequestMethod.GET)
    public ResponseEntity<Pessoa> findById(@PathVariable String idPessoa) {
        Pessoa pessoa = pessoaService.findByPessoaId(Integer.parseInt(idPessoa));
        return ResponseEntity.ok().body(pessoa);
    }

    @ApiOperation("Insere uma nova Pessoa")
    @RequestMapping(value = "/inserepessoa", method = RequestMethod.POST)
    public ResponseEntity<Pessoa> createPessoa(@RequestBody CreatePessoaDTO createPessoaDTO) {
        Pessoa pessoa = pessoaService.createPessoa(createPessoaDTO);
        URI uri = URI.create("/pessoa" + "/buscaid" + pessoa.getId());
        return ResponseEntity.created(uri).body(pessoa);
    }

}
