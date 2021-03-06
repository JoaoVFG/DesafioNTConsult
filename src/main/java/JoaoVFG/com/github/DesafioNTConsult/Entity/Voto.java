package JoaoVFG.com.github.DesafioNTConsult.Entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@EqualsAndHashCode
@ToString
public class Voto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "PESSOA_ID")
    private Pessoa pessoa;

    @ManyToOne
    @JoinColumn(name = "PAUTA_ID")
    private Pauta pauta;

    private String voto;

    public Voto(Integer id, Pessoa pessoa, Pauta pauta, String voto) {
        this.id = id;
        this.pessoa = pessoa;
        this.pauta = pauta;
        this.voto = voto;
    }

}
