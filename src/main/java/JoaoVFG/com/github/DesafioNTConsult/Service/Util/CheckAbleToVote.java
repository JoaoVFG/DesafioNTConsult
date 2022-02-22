package JoaoVFG.com.github.DesafioNTConsult.Service.Util;

import JoaoVFG.com.github.DesafioNTConsult.DTO.StatusVoteDTO;
import JoaoVFG.com.github.DesafioNTConsult.ENUM.StatusVoteENUM;
import org.springframework.web.client.RestTemplate;

public class CheckAbleToVote {
    final String uri = "https://user-info.herokuapp.com/users/";

    public Boolean ableToVote(String cpf) {


        RestTemplate restTemplate = new RestTemplate();
        StatusVoteDTO statusVoteDTO = restTemplate.getForObject(uri + cpf, StatusVoteDTO.class);

        if (statusVoteDTO.getStatus() == StatusVoteENUM.ABLE_TO_VOTE) {
            return true;
        } else {
            return false;
        }


    }
}
