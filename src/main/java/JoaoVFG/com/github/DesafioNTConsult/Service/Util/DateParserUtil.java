package JoaoVFG.com.github.DesafioNTConsult.Service.Util;

import lombok.NoArgsConstructor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@NoArgsConstructor
public class DateParserUtil {
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy' 'HH:mm:ss");

    public Date conversorData(String dataString) throws ParseException {
        Date dataConvertida;

        try {
            dataConvertida = formatter.parse(dataString);
        } catch (ParseException e) {
            throw new JoaoVFG.com.github.DesafioNTConsult.Service.Exception.ParseException("Erro ao Converter a data");
        }
        return dataConvertida;
    }

}
