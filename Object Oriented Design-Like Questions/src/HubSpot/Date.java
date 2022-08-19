package HubSpot;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Date {
    int year;
    int month;
    int day;

    public static final Set<Integer> bigMonth = new HashSet<>(Arrays.asList(1, 3, 5, 7, 8, 10, 12));
    public static final Set<Integer> smallMonth = new HashSet<>(Arrays.asList(4, 6, 9, 11));

    public Date(String date) {
        String[] dateParts = date.split("-");
        this.year = Integer.parseInt(dateParts[0]);
        this.month = Integer.parseInt(dateParts[1]);
        this.day = Integer.parseInt(dateParts[2]);
    }

    public boolean isNextDay(Date other) {
        int nextDay = day + 1;
        int nextMonth = month;
        int nextYear = year;
        if (month == 2 && nextDay == 29) {
            nextMonth = 3;
            nextDay = 1;
        } else if (smallMonth.contains(month) && day == 31) {
            nextMonth++;
            nextDay = 1;
        } else if (bigMonth.contains(month) && day == 32) {
            nextMonth++;
            nextDay = 1;
        }
        if (nextMonth == 13) {
            nextMonth = 1;
            nextYear++;
        }
        return nextDay == other.day && nextMonth == other.month && nextYear == other.year;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Date)) {
            return false;
        }
        Date other = (Date)o;
        return this.year == other.year && this.month == other.month && this.day == other.day;
    }

    @Override
    public int hashCode() {
        return year * 10000 + month * 100 + day;
    }

    @Override
    public String toString() {
        String monthStr = month < 10 ? ("0" + month) : ("" + month);
        String dayStr = day < 10 ? ("0" + day) : ("" + day);
        return year + "-" + monthStr + "-" + dayStr;
    }
}

