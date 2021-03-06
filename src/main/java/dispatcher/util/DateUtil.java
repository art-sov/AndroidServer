package dispatcher.util;

import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Sovalov.AV on 07.05.2018.
 */
@Component
public class DateUtil {

    private String date;

    public DateUtil() {
    }

    public DateUtil(String date){
        this.date = date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {

        if (date.equals("current")) {
            SimpleDateFormat formatDate = new SimpleDateFormat("dd.MM.yyyy HH:mm");
            return formatDate.format(new Date()) + ":00";
        }
        return date;
    }

    public String getDateYesterday() {

        Calendar calendar = Calendar.getInstance();

        if (date.equals("current")) {

            Date currentDate = new Date();
            calendar.setTime(currentDate);
            calendar.add(Calendar.DATE, -1);
            currentDate = calendar.getTime();

            SimpleDateFormat formatDate = new SimpleDateFormat("dd.MM.yyyy HH:mm");
            return formatDate.format(currentDate) + ":00";
        }
        else {
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm");
            try {
                calendar.setTime(sdf.parse(date));
                calendar.add(Calendar.DATE, -1);
                date = sdf.format(calendar.getTime());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return date + ":00";
        }
    }
}
