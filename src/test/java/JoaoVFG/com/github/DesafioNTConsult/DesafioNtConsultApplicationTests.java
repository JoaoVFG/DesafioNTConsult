package JoaoVFG.com.github.DesafioNTConsult;

import JoaoVFG.com.github.DesafioNTConsult.DTO.*;
import JoaoVFG.com.github.DesafioNTConsult.ENUM.StatusVoteENUM;
import JoaoVFG.com.github.DesafioNTConsult.Entity.Pauta;
import JoaoVFG.com.github.DesafioNTConsult.Entity.Pessoa;
import JoaoVFG.com.github.DesafioNTConsult.Entity.Voto;
import JoaoVFG.com.github.DesafioNTConsult.Repository.PautaRepository;
import JoaoVFG.com.github.DesafioNTConsult.Repository.PessoaRepository;
import JoaoVFG.com.github.DesafioNTConsult.Repository.VotoRepository;
import JoaoVFG.com.github.DesafioNTConsult.Service.Exception.DataIntegrityException;
import JoaoVFG.com.github.DesafioNTConsult.Service.Exception.ObjectNotFoundException;
import JoaoVFG.com.github.DesafioNTConsult.Service.PautaService;
import JoaoVFG.com.github.DesafioNTConsult.Service.PessoaService;
import JoaoVFG.com.github.DesafioNTConsult.Service.Util.CheckAbleToVote;
import JoaoVFG.com.github.DesafioNTConsult.Service.Util.DateParserUtil;
import JoaoVFG.com.github.DesafioNTConsult.Service.Util.DateSum;
import JoaoVFG.com.github.DesafioNTConsult.Service.VotoService;
import lombok.SneakyThrows;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.util.*;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.Assert.assertThrows;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@PropertySource("application-test.properties")
public class DesafioNtConsultApplicationTests {

    @Autowired
    PessoaRepository pessoaRepository;

    @Autowired
    PautaRepository pautaRepository;

    @Autowired
    VotoRepository votoRepository;

    @Autowired
    PessoaService pessoaService;

    @Autowired
    PautaService pautaService;

    @Autowired
    VotoService votoService;


    DateParserUtil dateParserUtil = new DateParserUtil();
    DateSum dateSum = new DateSum();
    CheckAbleToVote checkAbleToVote = new CheckAbleToVote();

    @Test
    public void teste01_repositoryInsertPessoa() {
        Pessoa pessoa = new Pessoa();
        pessoa.setCpf("45567860889");
        pessoa.setNome("Pessoa 1");
        pessoaRepository.save(pessoa);

    }

    @Test
    public void teste02_repositoryInsertAndSearchPessoa() {
        Pessoa pessoa = new Pessoa();
        pessoa.setCpf("11593054807");
        pessoa.setNome("Pessoa A");
        pessoaRepository.save(pessoa);

        assertEquals(pessoaRepository.findById(2).get().getCpf(),"11593054807");

    }

    @SneakyThrows
    @Test
    public void teste03_utilDateConversion() {
        String strDate = "21/02/2022 08:00:00";
        Date date = dateParserUtil.conversorData(strDate);

        assertEquals("Mon Feb 21 08:00:00 BRT 2022",date.toString());

    }

    @SneakyThrows
    @Test
    public void teste04_utilDateSum() {
        String strDate = "21/02/2022 08:00:00";
        Date date = dateParserUtil.conversorData(strDate);
        Date newDate = dateSum.sumMinutes(date, 60);
        assertEquals("Mon Feb 21 09:00:00 BRT 2022",newDate.toString());

    }

    @Test
    public void teste05_repositoryInsertPauta() {
        Pauta pauta = new Pauta();
        pauta.setTema("TEMA DA PAUTA 1");
        pautaRepository.save(pauta);

    }

    @SneakyThrows
    @Test
    public void teste06_repositoryInsertAndSearchPauta() {
        Pauta pauta = new Pauta();
        pauta.setTema("TEMA DA PAUTA 2");
        pauta.setHoraInicio(dateParserUtil.conversorData("21/02/2022 08:00:00"));
        System.out.println(pauta.toString());
        pautaRepository.save(pauta);
        assertEquals(pautaRepository.findById(2).get().getTema(),"TEMA DA PAUTA 2");
    }

    @SneakyThrows
    @Test
    public void teste07_RepositoryInsertSearchAndUpdatePauta() {
        Pauta pauta = new Pauta();
        pauta.setTema("TEMA DA PAUTA 3");
        pauta.setHoraInicio(dateParserUtil.conversorData("21/02/2022 12:00:00"));
        pautaRepository.save(pauta);

        Optional<Pauta> pautaUpdate = pautaRepository.findById(3);
        pautaUpdate.get().setHoraEncerramento(dateSum.sumMinutes(pauta.getHoraInicio(), 90));
        pautaRepository.save(pautaUpdate.get());

        assertEquals(pautaRepository.findById(3).get().getHoraEncerramento().toString(),"2022-02-21 13:30:00.0");
    }

    @Test
    public void teste08_repositoryInsertVotacao() {
        Pessoa pessoa = new Pessoa(null,"TESTE VOTACAO 1","23040181068");
        Pauta pauta = new Pauta();
        pauta.setTema("TEMA DA PAUTA 4");

        pessoaRepository.save(pessoa);
        pautaRepository.save(pauta);

        Voto voto = new Voto();
        voto.setVoto("Sim");
        voto.setPauta(pauta);
        voto.setPessoa(pessoa);

        votoRepository.save(voto);

        assertEquals(votoRepository.findById(1).get().getVoto(),"Sim");

    }

    @Test
    public void teste09_repositoryCountVotos() {
        Pessoa pessoa1 = new Pessoa(null,"TESTE VOTACAO 1","96504710066");
        Pessoa pessoa2 = new Pessoa(null,"TESTE VOTACAO 2","44525136030");
        Pessoa pessoa3 = new Pessoa(null,"TESTE VOTACAO 3","32480358038");

        pessoaRepository.save(pessoa1);
        pessoaRepository.save(pessoa2);
        pessoaRepository.save(pessoa3);

        Pauta pauta = new Pauta();
        pauta.setTema("TEMA DA PAUTA 5");
        pautaRepository.save(pauta);

        Voto voto1 = new Voto(null, pessoa1, pauta, "Sim");
        Voto voto2 = new Voto(null, pessoa2, pauta, "Sim");
        Voto voto3 = new Voto(null, pessoa3, pauta, "N??o");

        votoRepository.save(voto1);
        votoRepository.save(voto2);
        votoRepository.save(voto3);

        List<ResultadoVotacaoConsultaDTO> resultado = votoRepository.sumQuantidadeVotacao(5);
        System.out.println(resultado.get(1).getVoto() + ":" + resultado.get(1).getTotalVotos());

        assertEquals(resultado.get(1).getTotalVotos(), 2);
    }

    @Test
    public void teste10_repositoryFindByCpf() {
        Pessoa pessoa = new Pessoa(null, "TESTE REPOSIT??RIO BUSCA POR CPF","23048922059");
        pessoaRepository.save(pessoa);
        pessoa = pessoaRepository.findByPessoaCpf("23048922059");
        assertEquals(pessoa.getNome(),"TESTE REPOSIT??RIO BUSCA POR CPF");
    }

    @Test
    public void teste11_serviceInsertPessoa() {
        CreatePessoaDTO createPessoaDTO = new CreatePessoaDTO("25449231059","PESSOA SERVICE CREATE");
        Pessoa pessoa = pessoaService.createPessoa(createPessoaDTO);

        assertEquals(pessoa.getCpf(),"25449231059");

    }

    @Test
    public void teste12_serviceBuscaPessoa() {
        CreatePessoaDTO createPessoaDTO = new CreatePessoaDTO("88254199027","PESSOA SERVICE FIND");
        Pessoa pessoa = pessoaService.createPessoa(createPessoaDTO);
        Pessoa pessoaFind = pessoaService.findByPessoaId(9);
        assertEquals(pessoaFind.getNome(),"PESSOA SERVICE FIND");
    }

    @Test
    public  void teste13_serviceInsertRepeatedCpf() {
        CreatePessoaDTO createPessoaDTO = new CreatePessoaDTO("45567860889","PESSOA REPETIDA");
        Exception exception = assertThrows(DataIntegrityException.class, () -> {
            Pessoa pessoa = pessoaService.createPessoa(createPessoaDTO);
        });

        String mensagemErro = "J?? EXISTE UMA PESSOA CADASTRADA COM ESSE CPF";
        String mensagemRecebida = exception.getMessage();
        assertTrue(mensagemRecebida.contains(mensagemErro));
    }

    @Test
    public void teste14_insertAndFindPauta() {
        Pauta pauta = pautaService.createPauta("TESTE INSERE E BUSCA PAUTA");
        Pauta pautaBusca = pautaService.findById(6);
        assertTrue(pautaBusca.equals(pauta));
    }

    @Test
    public void teste15_insertVoto() {
        CreateVotoDTO createVotoDTO = new CreateVotoDTO(6,9,"Sim");
        pautaService.startVotacao(new StartVotacaoDTO(6,10));
        Voto voto = votoService.createVoto(createVotoDTO);
        assertEquals(voto.getVoto(),"Sim");
    }

    @Test
    public void teste16_insertVotoWrongPessoaId() {
        CreateVotoDTO createVotoDTO = new CreateVotoDTO(6,20,"Sim");
        Exception exception = assertThrows(ObjectNotFoundException.class, () -> {
            Voto voto = votoService.createVoto(createVotoDTO);
        });

        String mensagemErro = "N??O EXISTE PESSOA COM O ID INFORMADO";
        String mensagemRecebida = exception.getMessage();
        assertTrue(mensagemRecebida.contains(mensagemErro));

    }

    @Test
    public void teste17_startVotacao() throws ParseException {
        Pauta pautaInsere = pautaService.createPauta("TESTE START VOTACAO");
        StartVotacaoDTO startVotacaoDTO = new StartVotacaoDTO(7,60);
        Pauta pauta = pautaService.startVotacao(startVotacaoDTO);
        Date dateEncerramento = dateSum.sumMinutes(pauta.getHoraInicio(),60);
        assertEquals(pauta.getHoraEncerramento(),dateEncerramento);
    }

    @Test
    public void teste18_startVotacaoWithoutTime() {
        Pauta pautaInsere = pautaService.createPauta("TESTE START VOTACAO SEM TEMPO");
        StartVotacaoDTO startVotacaoDTO = new StartVotacaoDTO(8,null);
        Pauta pauta = pautaService.startVotacao(startVotacaoDTO);
        Date dateEncerramento = dateSum.sumMinutes(pauta.getHoraInicio(),1);
        assertEquals(pauta.getHoraEncerramento(),dateEncerramento);
    }

    @Test
    public void teste19_startVotacaoAlreadyStarted() {
        Pauta pautaInsere = pautaService.createPauta("TESTE START VOTACAO JA INICIADA");
        StartVotacaoDTO startVotacaoDTO = new StartVotacaoDTO(9,null);
        Pauta pauta = pautaService.startVotacao(startVotacaoDTO);
        startVotacaoDTO.setTempoVotacao(5);

        Exception exception = assertThrows(DataIntegrityException.class, () -> {
            Pauta pautaStarted = pautaService.startVotacao(startVotacaoDTO);
        });

        String mensagemErro = "PAUTA J?? FOI ABERTA PARA VOTA????O";
        String mensagemRecebida = exception.getMessage();
        assertTrue(mensagemRecebida.contains(mensagemErro));
    }

    @Test
    public void teste20_votoServiceVotoOnClosedPauta() {
        Pauta pauta = new Pauta();
        pauta.setTema("TESTE PAUTA VOTACAO N??O ABERTA");
        pauta = pautaRepository.save(pauta);
        Integer idPauta = pauta.getId();

        Pessoa pessoa = new Pessoa();
        pessoa.setNome("PESSOA 10 TESTE VOTACAO N??O ABERTA");
        pessoa.setCpf("74690686009");
        pessoa = pessoaRepository.save(pessoa);
        Integer idPessoa = pessoa.getId();


        Exception exception = assertThrows(DataIntegrityException.class, () -> {
            votoService.createVoto(new CreateVotoDTO(idPauta,idPessoa,"Sim"));
        });
        String mensagemErro = "PAUTA N??O ESTA ABERTA PARA VOTA????O";
        String mensagemRecebida = exception.getMessage();
        assertTrue(mensagemRecebida.contains(mensagemErro));
    }

    @SneakyThrows
    @Test
    public void teste21_votoServiceVotoOnPautaEnded() {
        Pauta pauta = new Pauta();
        pauta.setTema("TESTE PAUTA VOTACAO FECHADA");
        pauta.setHoraInicio(dateParserUtil.conversorData("21/02/2022 12:00:00"));
        pauta.setHoraEncerramento(dateParserUtil.conversorData("21/02/2022 13:00:00"));
        pauta = pautaRepository.save(pauta);
        Integer idPauta = pauta.getId();

        Pessoa pessoa = new Pessoa();
        pessoa.setNome("PESSOA 11 TESTE VOTACAO FECHADA");
        pessoa.setCpf("98415902042");
        pessoa = pessoaRepository.save(pessoa);
        Integer idPessoa = pessoa.getId();

        Exception exception = assertThrows(DataIntegrityException.class, () -> {
            votoService.createVoto(new CreateVotoDTO(idPauta,idPessoa,"Sim"));
        });
        String mensagemErro = "VOTA????O PARA PAUTA ESTA ENCERRADA";
        String mensagemRecebida = exception.getMessage();
        assertTrue(mensagemRecebida.contains(mensagemErro));

    }

    @SneakyThrows
    @Test
    public void teste22_repositoryVotoFindPessoaVotoInPauta() {
        Pessoa pessoa = new Pessoa();
        pessoa.setCpf("10090862007");
        pessoa.setNome("TESTE BUSCA VOTO PESSOA EM UMA PAUTA");
        pessoa = pessoaRepository.save(pessoa);

        Pauta pauta = new Pauta();
        pauta.setTema("TESTE PAUTA BUSCA VOTO PESSOA EM UMA PAUTA");
        pauta = pautaRepository.save(pauta);

        Voto voto = new Voto();
        voto.setPessoa(pessoa);
        voto.setPauta(pauta);
        voto.setVoto("Sim");
        voto = votoRepository.save(voto);

        Voto buscaVoto = votoRepository.fidnByIdPessoaAndIdVoto(pessoa.getId(),pauta.getId());
        assertEquals(buscaVoto.getVoto(),"Sim");
    }

    @SneakyThrows
    @Test
    public void teste23_votoServiceVotoAlreadyVoted() {
        Pessoa pessoa = new Pessoa();
        pessoa.setCpf("40085439029");
        pessoa.setNome("TESTE PESSOA JA VOTOU");
        pessoa = pessoaRepository.save(pessoa);

        Pauta pauta = new Pauta();
        pauta.setTema("TESTE PAUTA PESSOA JA VOTOU");
        pauta.setHoraInicio(dateParserUtil.conversorData("22/02/2022 06:00:00"));
        pauta.setHoraEncerramento(dateParserUtil.conversorData("22/02/2023 06:00:00"));
        pauta = pautaRepository.save(pauta);

        Voto voto = new Voto();
        voto.setPessoa(pessoa);
        voto.setPauta(pauta);
        voto.setVoto("Sim");
        voto = votoRepository.save(voto);

        Integer idPauta = pauta.getId();
        Integer idPessoa = pessoa.getId();

        Exception exception = assertThrows(DataIntegrityException.class, () -> {
            votoService.createVoto(new CreateVotoDTO(idPauta,idPessoa,"N??o"));
        });
        String mensagemErro = "PESSOA JA VOTOU NESSA PAUTA";
        String mensagemRecebida = exception.getMessage();
        assertTrue(mensagemRecebida.contains(mensagemErro));
    }

    @SneakyThrows
    @Test
    public void teste24_serviceUtilcheckAbleToVote1() {
        List<StatusVoteENUM> enumListStatus = Arrays.asList(StatusVoteENUM.class.getEnumConstants());
        StatusVoteDTO statusVoteDTO = checkAbleToVote.ableToVote("45567860889");
        assertTrue(enumListStatus.contains(statusVoteDTO.getStatus()));
    }

    @SneakyThrows
    @Test
    public void teste25_serviceUtilcheckAbleToVote2() {
        List<StatusVoteENUM> enumListStatus = Arrays.asList(StatusVoteENUM.class.getEnumConstants());
        StatusVoteDTO statusVoteDTO = checkAbleToVote.ableToVote("11593054807");
        assertTrue(enumListStatus.contains(statusVoteDTO.getStatus()));
    }

    @SneakyThrows
    @Test
    public void teste26_pautaServiceResultadoVotacao(){
        Pessoa pessoa1 = new Pessoa(null,"TESTE VOTACAO 2 - 1","58667131016");
        Pessoa pessoa2 = new Pessoa(null,"TESTE VOTACAO 2 - 2","93461970050");
        Pessoa pessoa3 = new Pessoa(null,"TESTE VOTACAO 2 - 3","35849718028");
        Pessoa pessoa4 = new Pessoa(null,"TESTE VOTACAO 2 - 4","55701873080");
        Pessoa pessoa5 = new Pessoa(null,"TESTE VOTACAO 2 - 5","94732986099");

        pessoaRepository.save(pessoa1);
        pessoaRepository.save(pessoa2);
        pessoaRepository.save(pessoa3);
        pessoaRepository.save(pessoa4);
        pessoaRepository.save(pessoa5);

        Pauta pauta = new Pauta();
        pauta.setTema("TEMA DA PAUTA 5");
        pauta.setHoraInicio(dateParserUtil.conversorData("22/02/2022 06:00:00"));
        pauta = pautaRepository.save(pauta);

        Voto voto1 = new Voto(null, pessoa1, pauta, "Sim");
        Voto voto2 = new Voto(null, pessoa2, pauta, "Sim");
        Voto voto3 = new Voto(null, pessoa3, pauta, "N??o");
        Voto voto4 = new Voto(null, pessoa4, pauta, "Sim");
        Voto voto5 = new Voto(null, pessoa5, pauta, "N??o");
        votoRepository.save(voto1);
        votoRepository.save(voto2);
        votoRepository.save(voto3);
        votoRepository.save(voto4);
        votoRepository.save(voto5);

        ResultadoVotacaoDTO resultadoVotacaoDTO = pautaService.resultadoVotacao(pauta.getId());
        assertEquals(resultadoVotacaoDTO.getQuantidadeVotosSim(),3);
    }

}
