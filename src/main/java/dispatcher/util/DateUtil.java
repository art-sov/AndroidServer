package dispatcher.util;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Sovalov.AV on 07.05.2018.
 */
@Component
public class DateUtil {

    public DateUtil() {
    }

    public String getDate() {
        SimpleDateFormat formatDate = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        return formatDate.format(new Date()) + ":00";
    }

    public String getDateYesterday() {

        Calendar calendar = Calendar.getInstance();
        Date date = new Date();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, -1);
        date = calendar.getTime();

        SimpleDateFormat formatDate = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        return formatDate.format(date) + ":00";
    }
}
