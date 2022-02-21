package JoaoVFG.com.github.DesafioNTConsult.Repository;

import JoaoVFG.com.github.DesafioNTConsult.Entity.Pauta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PautaRepository extends JpaRepository<Pauta, Integer> {
    
}
