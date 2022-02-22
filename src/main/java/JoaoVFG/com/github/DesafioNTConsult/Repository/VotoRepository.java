package JoaoVFG.com.github.DesafioNTConsult.Repository;

import JoaoVFG.com.github.DesafioNTConsult.DTO.ResultadoVotacaoDTO;
import JoaoVFG.com.github.DesafioNTConsult.Entity.Voto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VotoRepository extends JpaRepository<Voto, Integer> {

    @Query("SELECT voto.voto as voto, count(*) as totalVotos FROM Voto voto WHERE voto.pauta.id = :idBusca GROUP BY voto.voto")
    List<ResultadoVotacaoDTO> sumQuantidadeVotacao(@Param("idBusca") Integer id);

    @Query("SELECT voto FROM Voto voto WHERE voto.pessoa.id = :idPessoa AND voto.pauta.id = :idPauta")
    Voto fidnByIdPessoaAndIdVoto(@Param("idPessoa") Integer idPessoa,@Param("idPauta") Integer idPauta);
}
