package JoaoVFG.com.github.DesafioNTConsult.Entity;

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

    private String nome;

    private String cpf;

    public Pessoa(Integer id, String nome, String cpf) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
    }


}
