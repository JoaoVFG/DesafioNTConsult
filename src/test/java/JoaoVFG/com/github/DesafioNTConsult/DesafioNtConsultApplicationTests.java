package JoaoVFG.com.github.DesafioNTConsult;

import JoaoVFG.com.github.DesafioNTConsult.Entity.Pauta;
import JoaoVFG.com.github.DesafioNTConsult.Entity.Pessoa;
import JoaoVFG.com.github.DesafioNTConsult.Repository.PautaRepository;
import JoaoVFG.com.github.DesafioNTConsult.Repository.PessoaRepository;
import JoaoVFG.com.github.DesafioNTConsult.Service.Util.DateParserUtil;
import JoaoVFG.com.github.DesafioNTConsult.Service.Util.DateSum;
import lombok.SneakyThrows;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DesafioNtConsultApplicationTests {

    @Autowired
    PessoaRepository pessoaRepository;

    @Autowired
    PautaRepository pautaRepository;


    DateParserUtil dateParserUtil = new DateParserUtil();
    DateSum dateSum = new DateSum();

    @Test
    public void teste01_insertPessoa() {
        Pessoa pessoa = new Pessoa();
        pessoa.setCpf("45567860889");
        pessoa.setNome("Pessoa 1");
        pessoaRepository.save(pessoa);

    }

    @Test
    public void teste02_insertAndSearchPessoa() {
        Pessoa pessoa = new Pessoa();
        pessoa.setCpf("11593054807");
        pessoa.setNome("Pessoa A");
        pessoaRepository.save(pessoa);

        assertEquals(pessoaRepository.findById(2).get().getCpf(),"11593054807");

    }

    @SneakyThrows
    @Test
    public void teste03_dateConversion() {
        String strDate = "21/02/2022 08:00:00";
        Date date = dateParserUtil.conversorData(strDate);

        assertEquals("Mon Feb 21 08:00:00 BRT 2022",date.toString());

    }

    @SneakyThrows
    @Test
    public void teste04_dateSum() {
        String strDate = "21/02/2022 08:00:00";
        Date date = dateParserUtil.conversorData(strDate);
        Date newDate = dateSum.sumMinutes(date, 60);
        assertEquals("Mon Feb 21 09:00:00 BRT 2022",newDate.toString());

    }

    @Test
    public void teste05_insertPauta() {
        Pauta pauta = new Pauta();
        pauta.setTema("TEMA DA PAUTA 1");
        pautaRepository.save(pauta);

    }

    @SneakyThrows
    @Test
    public void teste06_insertAndSearchPauta() {
        Pauta pauta = new Pauta();
        pauta.setTema("TEMA DA PAUTA 2");
        pauta.setHoraInicio(dateParserUtil.conversorData("21/02/2022 08:00:00"));
        System.out.println(pauta.toString());
        pautaRepository.save(pauta);
        assertEquals(pautaRepository.findById(2).get().getTema(),"TEMA DA PAUTA 2");
    }

    @SneakyThrows
    @Test
    public void teste07_insertSearchAndUpdatePauta() {
        Pauta pauta = new Pauta();
        pauta.setTema("TEMA DA PAUTA 3");
        pauta.setHoraInicio(dateParserUtil.conversorData("21/02/2022 12:00:00"));
        pautaRepository.save(pauta);

        Optional<Pauta> pautaUpdate = pautaRepository.findById(3);
        pautaUpdate.get().setHoraEncerramento(dateSum.sumMinutes(pauta.getHoraInicio(), 90));
        pautaRepository.save(pautaUpdate.get());

        assertEquals(pautaRepository.findById(3).get().getHoraEncerramento().toString(),"2022-02-21 13:30:00.0");
    }

}
