
package com.ghoomo.ghoomo.ui.utility;

import android.content.Context;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateUtil {

    /**
     * @param context
     * @return
     */
    public static String getDateTime(Context context) {

        String timeSettings = android.provider.Settings.System.getString(
                context.getContentResolver(),
                android.provider.Settings.System.AUTO_TIME);
        if (timeSettings.contentEquals("0")) {
            android.provider.Settings.System.putString(
                    context.getContentResolver(),
                    android.provider.Settings.System.AUTO_TIME, "1");
        }
        Date now = new Date(System.currentTimeMillis());
        Log.d("Date ***** ", now.toString());

        String simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(now);

        context = null;
        System.out.println("simpleDateFormat : " + simpleDateFormat);
        return simpleDateFormat;
    }

    public static String readCurrent12HoursFormatTime(Context context) {

        String timeSettings = android.provider.Settings.System.getString(
                context.getContentResolver(),
                android.provider.Settings.System.AUTO_TIME);
        if (timeSettings.contentEquals("0")) {
            android.provider.Settings.System.putString(
                    context.getContentResolver(),
                    android.provider.Settings.System.AUTO_TIME, "1");
        }
        Date now = new Date(System.currentTimeMillis());
        Log.d("Date ***** ", now.toString());

        Calendar cal = Calendar.getInstance();
        cal.setTimeZone(TimeZone.getTimeZone(UtilConstants.TIME_ZONE));

        SimpleDateFormat dateFormat = new SimpleDateFormat("hh.mm aa");
        String formattedDate = dateFormat.format(now).toString();

        context = null;
        System.out.println(formattedDate);

        return new String(formattedDate);
    }

    /**
     * @return
     */
    public static String getDate() {

        long date = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM, yyyy");
        String dateString = sdf.format(date);
        System.out.println("simpleDateFormat : " + dateString);
        return dateString;
    }

    /**
     * @return
     */
    public static String getDateWithoutYear() {

        long date = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM");
        String dateString = sdf.format(date);
        System.out.println("simpleDateFormat : " + dateString);
        return dateString;
    }

    /**
     * @return
     */
    public static String getRequeryDateTime(Date date) {
        String simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(date);
//        System.out.println("simpleDateFormat : " + simpleDateFormat);
        return simpleDateFormat;
    }

    /**
     * Read date.
     *
     * @return the string
     */
    public static String readDate() {
        Calendar cal = Calendar.getInstance();
        cal.setTimeZone(TimeZone.getTimeZone(UtilConstants.TIME_ZONE));
        StringBuffer date = new StringBuffer();

        if (cal.get(Calendar.DAY_OF_MONTH) < 10) {
            date.append("0" + cal.get(Calendar.DAY_OF_MONTH));
        } else {
            date.append(cal.get(Calendar.DAY_OF_MONTH));
        }
        date.append("/");
        if (cal.get(Calendar.MONTH) < 9) {
            date.append("0" + (cal.get(Calendar.MONTH) + 1));
        } else {
            if (cal.get(Calendar.MONTH) == 9) {
                date.append("10");
            } else {
                date.append(cal.get(Calendar.MONTH) + 1);
            }
        }
        date.append("/");

        date.append(cal.get(Calendar.YEAR));


        date.append(" ");

        return new String(date);
    }

    /**
     * Read time.
     *
     * @return the string
     */
    public static String readCurrent24HoursTime() {
        Calendar cal = Calendar.getInstance();
        cal.setTimeZone(TimeZone.getTimeZone(UtilConstants.TIME_ZONE));
        StringBuffer date = new StringBuffer();

        date.append(cal.get(Calendar.HOUR_OF_DAY));
        date.append(":");
        if (cal.get(Calendar.MINUTE) < 10)
            date.append("0" + cal.get(Calendar.MINUTE));
        else
            date.append(cal.get(Calendar.MINUTE));
//        date.append(":");
//        date.append(cal.get(Calendar.SECOND));

        return new String(date);
    }


    /**
     * *
     */

    public static int getDate(long currentTimeInMillis) {
        Date date = new Date(currentTimeInMillis);

        long time = date.getTime();

        System.out.println("DateUtil.getDate(): time is equal to " + time);

        return 0;

    }

    public static int getDayOfYear(Calendar c) {
        Calendar a = Calendar.getInstance();
        // a.set(c.get(Calendar.YEAR),0,1, 0, 0, 0);
        a.set(Calendar.YEAR, c.get(Calendar.YEAR));
        a.set(Calendar.MONTH, 0);
        a.set(Calendar.DATE, 1);
        a.set(Calendar.HOUR_OF_DAY, 0);
        a.set(Calendar.MINUTE, 0);
        a.set(Calendar.SECOND, 0);
        a.set(Calendar.MILLISECOND, 0);
        long difference = c.getTime().getTime() - a.getTime().getTime();
        return (int) (difference / 86400000) + ((difference % 86400000) > 0 ? 1 : 0);
    }

    public static String julianDateyyddd(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        String dayOfyear = padWithZeroes(3, getDayOfYear(cal));
        System.out.println("DateUtil.julianDateyyddd(): Day Of Year " + dayOfyear);

        // String dayOfyear = padWithZeroes(3, 1);
        return String.valueOf(cal.get(Calendar.YEAR)).substring(3) + dayOfyear + String.valueOf(cal.get(Calendar.HOUR_OF_DAY));
    }

    /**
     * **
     *
     * @param maxLengthWithZeroes
     * @return
     */
    public static String padWithZeroes(int maxLengthWithZeroes, int valueToBePadded) {
        String val = Integer.toString(valueToBePadded);
        int noOfZeroesToBeAdded = maxLengthWithZeroes - val.length();
        StringBuffer paddedData = new StringBuffer();

        for (int i = 0; i < noOfZeroesToBeAdded; i++) {

            paddedData.append("0");
        }

        paddedData.append(val);
        return new String(paddedData);

    }

    public static void main(String arg[]) {
        int time = getDate(System.currentTimeMillis());

        System.out.println("Padded value is equal to  " + julianDateyyddd(new Date()));
    }

    public static long[] getTimeDifference(Date d1, Date d2) {

        long[] result = new long[5];
        Calendar cal = Calendar.getInstance();
        cal.setTimeZone(TimeZone.getTimeZone("GMT"));
        cal.setTime(d1);

        long t1 = cal.getTimeInMillis();
        cal.setTime(d2);

        long diff = Math.abs(cal.getTimeInMillis() - t1);
        final int ONE_DAY = 1000 * 60 * 60 * 24;
//        final int ONE_HOUR = ONE_DAY / 24;
//        final int ONE_MINUTE = ONE_HOUR / 60;
//        final int ONE_SECOND = ONE_MINUTE / 60;

        long d = diff / ONE_DAY;
        result[0] = d;

        return result;
    }


    public static int getDifferenceIndays(long timestamp1, long timestamp2) {
        final int SECONDS = 60;
        final int MINUTES = 60;
        final int HOURS = 24;
        final int MILLIES = 1000;
        long temp;
        if (timestamp1 < timestamp2) {
            temp = timestamp1;
            timestamp1 = timestamp2;
            timestamp2 = temp;
        }
        Calendar startDate = Calendar.getInstance(TimeZone.getDefault());
        Calendar endDate = Calendar.getInstance(TimeZone.getDefault());
        endDate.setTimeInMillis(timestamp1);
        startDate.setTimeInMillis(timestamp2);
        if ((timestamp1 - timestamp2) < 1 * HOURS * MINUTES * SECONDS * MILLIES) {
            int day1 = endDate.get(Calendar.DAY_OF_MONTH);
            int day2 = startDate.get(Calendar.DAY_OF_MONTH);
            if (day1 == day2) {
                return 0;
            } else {
                return 1;
            }
        }
        int diffDays = 0;
        startDate.add(Calendar.DAY_OF_MONTH, diffDays);
        while (startDate.before(endDate)) {
            startDate.add(Calendar.DAY_OF_MONTH, 1);
            diffDays++;
        }
        return diffDays;
    }

}
