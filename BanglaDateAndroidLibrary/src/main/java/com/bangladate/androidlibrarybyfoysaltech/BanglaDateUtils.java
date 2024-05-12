package com.bangladate.androidlibrarybyfoysaltech;

import android.os.Handler;
import android.os.Looper;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class BanglaDateUtils {

    private static final long UPDATE_INTERVAL = 1000;
    private static final Date currentDate = new Date();
    private static final int BANGLA_YEAR_OFFSET = 593;
    private static final String[] BANGLA_MONTHS = {"বৈশাখ", "জ্যৈষ্ঠ", "আষাঢ়", "শ্রাবণ", "ভাদ্র", "আশ্বিন", "কার্তিক", "অগ্রহায়ণ", "পৌষ", "মাঘ", "ফাল্গুন", "চৈত্র"};
    private static final String[] BANGLA_WEEKDAYS = {"রবিবার", "সোমবার", "মঙ্গলবার", "বুধবার", "বৃহস্পতিবার", "শুক্রবার", "শনিবার"};

    private static boolean isLeapYear(int year) {
        return (year % 100 == 0) ? (year % 400 == 0) : (year % 4 == 0);
    }

    private static int getBanglaDay(int day, int month, int year) {
        int banglaDay;
        switch (month) {
            case 0:
                banglaDay = (day < 15) ? (day + 16) : (day - 14);
                break;
            case 1:
                banglaDay = (day < 14) ? (day + 17) : (day - 13);
                break;
            case 2:
                banglaDay = isLeapYear(year) ? ((day < 15) ? (day + 16) : (day - 14)) : ((day < 15) ? (day + 15) : (day - 14));
                break;
            case 3:
                banglaDay = (day < 14) ? (day + 17) : (day - 13);
                break;
            case 4:
                banglaDay = (day < 15) ? (day + 17) : (day - 14);
                break;
            case 5:
            case 6:
                banglaDay = (day < 16) ? (day + 16) : (day - 15);
                break;
            default:
                banglaDay = (day < 17) ? (day + 15) : (day - 16);
                break;
        }
        return banglaDay;
    }

    private static int getBanglaMonth(int day, int month) {
        int result;
        if ((month == 4 && day > 14) || (month == 5 && day < 15)) {
            result = 1;
        } else if ((month == 5 && day > 14) || (month == 6 && day < 16)) {
            result = 2;
        } else if ((month == 6 && day > 15) || (month == 7 && day < 16)) {
            result = 3;
        } else if ((month == 7 && day > 15) || (month == 8 && day < 16)) {
            result = 4;
        } else if ((month == 8 && day > 15) || (month == 9 && day < 17)) {
            result = 5;
        } else if ((month == 9 && day > 16) || (month == 10 && day < 16)) {
            result = 6;
        } else if ((month == 10 && day > 15) || (month == 11 && day < 16)) {
            result = 7;
        } else if ((month == 11 && day > 15) || (month == 0 && day < 15)) {
            result = 8;
        } else if ((month == 0 && day > 14) || (month == 1 && day < 14)) {
            result = 9;
        } else if ((month == 1 && day > 13) || (month == 2 && day < 15)) {
            result = 10;
        } else if ((month == 2 && day > 14) || (month == 3 && day < 14)) {
            result = 11;
        } else {
            result = 0;
        }
        return result;
    }

    private static String getBanglaWeekDayName(int dayOfWeek) {
        if (dayOfWeek >= 0 && dayOfWeek < BANGLA_WEEKDAYS.length) {
            return BANGLA_WEEKDAYS[dayOfWeek];
        } else {
            return "Invalid Day";
        }
    }

    public static String getWeekDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        return getBanglaWeekDayName(dayOfWeek - 1);
    }

    private static String formatBanglaDate(int day, int month) {
        return String.format(Locale.getDefault(), "%d - %s", day, BANGLA_MONTHS[month]);
    }


    public static String getBanglaDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        int banglaDay = getBanglaDay(day, month, year);
        int banglaMonth = getBanglaMonth(day, month);
        return formatBanglaDate(banglaDay, banglaMonth);
    }

    public static String getBanglaSeason(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        int banglaMonth = getBanglaMonth(day, month);
        String banglaSeason = getBanglaSeason(banglaMonth);
        return banglaSeason;
    }

    private static String getBanglaSeason(int month) {
        String[] banglaSeasons = {"গ্রীষ্মকাল", "বর্ষাকাল", "শরৎকাল", "হেমন্তকাল", "শীতকাল", "বসন্তকাল"};
        String season;
        if (month == 0 || month == 1 || month == 2) {
            season = banglaSeasons[0]; // গ্রীষ্মকাল
        } else if (month == 2 || month == 3) {
            season = banglaSeasons[1]; // বর্ষাকাল
        } else if (month == 4 || month == 5) {
            season = banglaSeasons[2]; // শরৎকাল
        } else if (month == 6 || month == 7) {
            season = banglaSeasons[3]; // হেমন্তকাল
        } else if (month == 8 || month == 9) {
            season = banglaSeasons[4]; // শীতকাল
        } else {
            season = banglaSeasons[5]; // বসন্তকাল
        }
        return season;
    }

    /*private static String getBanglaSeason(int month) {
        String[] banglaSeasons = {"গ্রীষ্মকাল", "বর্ষাকাল", "শরৎকাল", "হেমন্তকাল", "শীতকাল", "বসন্তকাল"};
        switch (month) {
            case 0:  // বৈশাখ
            case 1:  // জ্যৈষ্ঠ
            case 2:  // আষাঢ়
                return banglaSeasons[0]; // গ্রীষ্মকাল
            case 3:  // আষাঢ়
            case 4:  // শ্রাবণ
                return banglaSeasons[1]; // বর্ষাকাল
            case 5:  // ভাদ্র
            case 6:  // আশ্বিন
                return banglaSeasons[2]; // শরৎকাল
            case 7:  // কার্তিক
            case 8:  // অগ্রহায়ণ
                return banglaSeasons[3]; // হেমন্তকাল
            case 9:  // পৌষ
            case 10: // মাঘ
                return banglaSeasons[4]; // শীতকাল
            case 11: // ফাল্গুন
            default: // চৈত্র
                return banglaSeasons[5]; // বসন্তকাল
        }
    }*/


    public static String getBanglaDayOnly(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        int banglaDay = getBanglaDay(day, month, year);
        return formatDayOnly(banglaDay);
    }

    private static String formatDayOnly(int day) {
        return String.format(Locale.getDefault(), "%d", day);
    }

    public static String getBanglaMonthOnly(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int banglaMonth = getBanglaMonth(day, month);
        return formatBanglaMonthOnly(banglaMonth);
    }

    private static String formatBanglaMonthOnly(int month) {
        return String.format(Locale.getDefault(), "%s", BANGLA_MONTHS[month]);
    }

    private static String formatBanglaYear(int year) {
        int banglaYear = year - BANGLA_YEAR_OFFSET;
        if (Calendar.getInstance().get(Calendar.MONTH) < 2 || (Calendar.getInstance().get(Calendar.MONTH) == 2 && Calendar.getInstance().get(Calendar.DAY_OF_MONTH) < 14)) {
            banglaYear--;
        }
        return String.valueOf(banglaYear);
    }

    public static String englisttoBng(String data) {
        String[] englishDigits = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        String[] bengaliDigits = {"০", "১", "২", "৩", "৪", "৫", "৬", "৭", "৮", "৯"};
        for (int i = 0; i < englishDigits.length; i++) {
            data = data.replace(englishDigits[i], bengaliDigits[i]);
        }
        return data;
    }

    public static String getBanglafullDate() {
        String banglaDate = getBanglaDate(currentDate);
        int year = Calendar.getInstance().get(Calendar.YEAR);
        String banglaYear = formatBanglaYear(year);
        return englisttoBng(banglaDate + " - " + banglaYear);
    }

    public static String getBanglaDay() {
        String banglaDate = getBanglaDayOnly(currentDate);
        return englisttoBng(banglaDate);
    }

    public static String getBanglaMonth() {
        String banglaDate = getBanglaMonthOnly(currentDate);
        return banglaDate;
    }

    public static String getBanglaSeason() {
        String banglaDate = getBanglaSeason(currentDate);
        return banglaDate;
    }

    public static String getEnglishDate() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd - MMMM - yyyy | EEEE", new Locale("bn"));
        String bengaliDate = dateFormat.format(calendar.getTime());
        return bengaliDate;
    }

    public static void startUpdatingTime(TextView textView, boolean periodTrueOrfalse, boolean MorningOrafternoon) {
        final Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                Calendar calendar = Calendar.getInstance();
                int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
                int hour = (hourOfDay % 12 == 0) ? 12 : hourOfDay % 12;
                String period = (hourOfDay < 12) ? "পূর্বাহ্ণ" : "অপরাহ্ণ";
                int minute = calendar.get(Calendar.MINUTE);
                int second = calendar.get(Calendar.SECOND);
                if (periodTrueOrfalse) {
                    String banglaTime = convertToBangla(hour) + ":" + convertToBangla(minute) + ":" + convertToBangla(second) + " " + period;
                    textView.setText(banglaTime);
                } else if (MorningOrafternoon) {
                    String banglaTime = convertToBangla(hour) + ":" + convertToBangla(minute) + ":" + convertToBangla(second) + " " + getTimePeriod();
                    textView.setText(banglaTime);
                } else {
                    String banglaTime = convertToBangla(hour) + ":" + convertToBangla(minute) + ":" + convertToBangla(second);
                    textView.setText(banglaTime);
                }
                handler.postDelayed(this, UPDATE_INTERVAL);
            }
        });
    }

    public static String getTimePeriod() {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        if (hour >= 5 && hour < 12) {
            return "সকাল"; // Morning
        } else if (hour >= 12 && hour < 18) {
            return "দুপুর"; // Afternoon
        } else {
            return "রাত"; // Night
        }
    }

    private static String convertToBangla(int time) {
        String[] banglaDigits = {"০", "১", "২", "৩", "৪", "৫", "৬", "৭", "৮", "৯"};
        String timeStr = String.format(Locale.getDefault(), "%02d", time);
        StringBuilder banglaTime = new StringBuilder();
        for (char digit : timeStr.toCharArray()) {
            if (Character.isDigit(digit)) {
                banglaTime.append(banglaDigits[Character.getNumericValue(digit)]);
            } else {
                banglaTime.append(digit);
            }
        }
        return banglaTime.toString();
    }


}
