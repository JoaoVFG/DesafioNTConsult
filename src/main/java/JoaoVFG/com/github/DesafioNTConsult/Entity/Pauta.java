package JoaoVFG.com.github.DesafioNTConsult.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@Entity
@EqualsAndHashCode
@ToString
public class Pauta {

    @Id
    private Integer id;

    private String tema;

    @JsonFormat(pattern = "dd/MM/yyyy' 'HH:mm:ss")
    private String HoraInicio;

    @JsonFormat(pattern = "dd/MM/yyyy' 'HH:mm:ss")
    private String HoraEncerramento;
}
