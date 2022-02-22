package JoaoVFG.com.github.DesafioNTConsult.Repository;

import JoaoVFG.com.github.DesafioNTConsult.Entity.Pauta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PautaRepository extends JpaRepository<Pauta, Integer> {

    @Transactional(readOnly = true)
    @Query("SELECT pauta FROM Pauta pauta WHERE pauta.id = :id")
    public Pauta findByPautaId(@Param("id") Integer id);
    
}
