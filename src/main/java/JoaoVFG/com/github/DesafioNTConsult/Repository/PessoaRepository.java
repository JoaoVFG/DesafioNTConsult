package JoaoVFG.com.github.DesafioNTConsult.Repository;

import JoaoVFG.com.github.DesafioNTConsult.Entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {

}
