package JoaoVFG.com.github.DesafioNTConsult.Service;

import JoaoVFG.com.github.DesafioNTConsult.DTO.CreatePessoaDTO;
import JoaoVFG.com.github.DesafioNTConsult.Entity.Pessoa;
import JoaoVFG.com.github.DesafioNTConsult.Repository.PessoaRepository;
import JoaoVFG.com.github.DesafioNTConsult.Service.Exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PessoaService {

    @Autowired
    PessoaRepository pessoaRepository;

    public Pessoa createPessoa(CreatePessoaDTO createPessoaDTO) {
        Pessoa pessoa = new Pessoa();
        pessoa.setCpf(createPessoaDTO.getCpf());
        pessoa.setNome(createPessoaDTO.getNome());

        pessoa = pessoaRepository.save(pessoa);

        return pessoa;
    }

    public Pessoa findByPessoaId(Integer id) {
        Optional<Pessoa> pessoa = Optional.ofNullable(pessoaRepository.findByPessoaId(id));
        return pessoa.orElseThrow(() -> new ObjectNotFoundException("Não existe Pessoa com o ID informado"));
    }


}
