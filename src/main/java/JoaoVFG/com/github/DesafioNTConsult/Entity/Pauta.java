package JoaoVFG.com.github.DesafioNTConsult.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@EqualsAndHashCode
@ToString
public class Pauta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String tema;

    @JsonFormat(pattern = "dd/MM/yyyy' 'HH:mm:ss")
    private Date horaInicio;

    @JsonFormat(pattern = "dd/MM/yyyy' 'HH:mm:ss")
    private Date horaEncerramento;

}
