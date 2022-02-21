package JoaoVFG.com.github.DesafioNTConsult.Service.Util;

import lombok.NoArgsConstructor;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@NoArgsConstructor
public class DateSum {

    public Date sumMinutes(Date date, int minutes) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, minutes);

        return(calendar.getTime());

    }

}
