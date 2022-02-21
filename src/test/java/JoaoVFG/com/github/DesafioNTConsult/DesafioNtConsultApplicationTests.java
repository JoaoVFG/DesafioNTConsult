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

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DesafioNtConsultApplicationTests {

    @Autowired
    PessoaRepository pessoaRepository;


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

}
