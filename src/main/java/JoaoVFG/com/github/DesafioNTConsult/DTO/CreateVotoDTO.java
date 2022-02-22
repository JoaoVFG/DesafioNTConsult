package JoaoVFG.com.github.DesafioNTConsult.DTO;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class CreateVotoDTO {

    private Integer idPauta;

    private Integer idPessoa;

    private String voto;

}
