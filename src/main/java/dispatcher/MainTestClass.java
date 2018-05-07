package dispatcher;

import dispatcher.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Sovalov.AV on 11.04.2018.
 */
public class MainTestClass {

    @Autowired
    private static DateUtil dateUtil;

    public static void main(String[] args) throws ParseException {

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy'_'HH:mm");
        String i_date = null;
        String i_date_today = null;
        String i_date_pr = null;
        String i_date_today_pr = null;
        String i_date_today2 = null;
        String i_date_1 = null;
        String i_date2 = null;
        String M = null;
        String s = null;
        String err = "";
        long l_date1 = 0;
        long l_date2 = 0;
        int n_null;
        boolean check;

        Calendar sys_date = Calendar.getInstance();
        Date sys_d1 = new Date(sys_date.getTime().getTime());
        System.out.println("sys_d1: " + sys_d1);

        i_date = formatter.format(sys_d1);
        System.out.println("i_date: " + i_date);
        l_date1 = formatter.parse(i_date).getTime(); //long
        System.out.println("l_date1: " + l_date1);

        Calendar sys_date2 = Calendar.getInstance();
        sys_date2.add(sys_date2.DAY_OF_MONTH, - 1);
        Date sys_d2 = new Date(sys_date2.getTime().getTime());

        System.out.println("sys_d2: " + sys_d2);

        i_date2 = formatter.format(sys_d2);//String
        System.out.println("i_date2: " + i_date2);

        l_date2 = formatter.parse(i_date2).getTime(); //long
        System.out.println("l_date2: " + l_date2);

        Date sys_d = new Date(sys_date.getTime().getTime());
        i_date_today = formatter.format(sys_d);
        System.out.println("i_date_today: " + i_date_today);
        System.out.println();

        i_date_today_pr = i_date_today.substring(0, 10);
        System.out.println("i_date_today_pr: " + i_date_today_pr);

        Timestamp timestamp1 = new Timestamp(l_date1);
        Timestamp timestamp2 = new Timestamp(l_date2);

        System.out.println(timestamp1);
        System.out.println(timestamp2);

        System.out.println("Timestamp: " + new Timestamp(l_date1));

        System.out.println("nanos: " + timestamp1.getNanos());

        System.out.println("=============================================================");


//        Calendar currentCalendar = Calendar.getInstance();
//        currentCalendar.set(Calendar.SECOND, 0);
        SimpleDateFormat formatDate  = new SimpleDateFormat("dd-MM-yyyy HH:mm");

        Date currentDate = new Date();
        String dateStr = formatDate.format(currentDate) + ":00";
        System.out.println("dateStr: " + dateStr);

        System.out.println("=============================================================");



    }
}
