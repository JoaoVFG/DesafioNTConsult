package JoaoVFG.com.github.DesafioNTConsult.Service.Util;

import JoaoVFG.com.github.DesafioNTConsult.DTO.StatusVoteDTO;
import org.springframework.web.client.RestTemplate;

public class CheckAbleToVote {
    final String uri = "https://user-info.herokuapp.com/users/";

    public StatusVoteDTO ableToVote(String cpf) {


        RestTemplate restTemplate = new RestTemplate();
        StatusVoteDTO statusVoteDTO = restTemplate.getForObject(uri + cpf, StatusVoteDTO.class);

        return statusVoteDTO;

    }
}
