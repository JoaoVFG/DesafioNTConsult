package JoaoVFG.com.github.DesafioNTConsult.Repository;

import JoaoVFG.com.github.DesafioNTConsult.Entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {

    @Transactional(readOnly = true)
    @Query("SELECT pessoa FROM Pessoa pessoa WHERE pessoa.id = :id")
    public Pessoa findByPessoaId(@Param("id") Integer id);

    @Transactional(readOnly = true)
    @Query("SELECT pessoa FROM Pessoa pessoa WHERE pessoa.cpf = :cpf")
    public Pessoa findByPessoaCpf(@Param("cpf") String cpf);
}
