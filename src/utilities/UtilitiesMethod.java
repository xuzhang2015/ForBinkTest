package utilities;

import bean.CouncilProperty;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by zx on 04/07/2018.
 */
public class UtilitiesMethod {
    public static SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
    public static SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MMM/yyyy");
    public static DecimalFormat fnum = new DecimalFormat("##0.00");

    //covert string to long date
    public static Long GetTheDate(String s) {
        try {
            return sdf.parse(s).getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0l;

    }

    //sort the data as ascending order by default, true is ascending, false is descending
    public static void GetOrderByRent(List<CouncilProperty> data, boolean isAsc) {
        if (isAsc) Collections.sort(data, new compareRentOrderByAscoending());
        else Collections.sort(data, new compareRentOrderByDescending());
    }

    //check the file is CSV type
    public static String getMyFileExtension(String fileName) {
        String extension = "";
        int i = fileName.lastIndexOf('.');
        if (i >= 0) {
            extension = fileName.substring(i + 1);
        }
        return extension;
    }

    private static class compareRentOrderByAscoending implements Comparator<CouncilProperty> {

        @Override
        public int compare(CouncilProperty o1, CouncilProperty o2) {
            return Float.compare(o2.getCurrentRent(), o1.getCurrentRent());
        }
    }

    private static class compareRentOrderByDescending implements Comparator<CouncilProperty> {

        @Override
        public int compare(CouncilProperty o1, CouncilProperty o2) {
            return Float.compare(o1.getCurrentRent(), o2.getCurrentRent());
        }
    }
}
