package JoaoVFG.com.github.DesafioNTConsult.Service;

import JoaoVFG.com.github.DesafioNTConsult.Entity.Pauta;
import JoaoVFG.com.github.DesafioNTConsult.Repository.PautaRepository;
import JoaoVFG.com.github.DesafioNTConsult.Service.Exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PautaService {

    @Autowired
    PautaRepository pautaRepository;

    public Pauta findById(Integer id) {
        Optional<Pauta> pauta = Optional.ofNullable(pautaRepository.findByPautaId(id));
        return pauta.orElseThrow(() -> new ObjectNotFoundException("NÃO EXISTE PAUTA COM O ID INFORMADO"));
    }

    public Pauta createPauta(String tema) {
        Pauta pauta = new Pauta();
        pauta.setTema(tema);

        return pautaRepository.save(pauta);
    }

}
