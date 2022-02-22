package JoaoVFG.com.github.DesafioNTConsult.DTO;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class ResultadoVotacaoDTO {

    Integer idPauta;

    String tema;

    Integer quantidadeVotosSim;

    Integer quantidadeVotosNao;

}
