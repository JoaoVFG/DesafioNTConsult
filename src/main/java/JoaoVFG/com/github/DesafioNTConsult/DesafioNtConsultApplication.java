package JoaoVFG.com.github.DesafioNTConsult;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;

@SpringBootApplication
public class DesafioNtConsultApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesafioNtConsultApplication.class, args);

		//OBS: Só setei a timezone manualmente para a JVM Não consegui corrigir a timezone em minha IDE
		//Todas as inserções estavam indo com horário incorreto
		//Sei que esse tipo de configuração deve ser realizada preferencialmente do lado do Banco
		TimeZone.setDefault(TimeZone.getTimeZone("GMT-6:00"));
	}

}
