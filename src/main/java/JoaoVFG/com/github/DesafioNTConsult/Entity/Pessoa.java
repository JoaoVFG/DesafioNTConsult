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
public class Pessoa {

    @Id
    private Integer id;

    @JsonFormat(pattern = "dd/MM/yyyy' 'HH:mm:ss")
    private String nome;

    private String cpf;

    public Pessoa(Integer id, String nome, String cpf) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
    }


}
