package it.univpm.mobile_programming_project.utils;

import android.annotation.SuppressLint;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Helper {

    private static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static boolean isEmailValid(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }

    public static Date fromStringToDate(String dateString ) {
        return fromStringToDate(dateString, "dd/MM/yyyy");
    }

    @SuppressLint("SimpleDateFormat")
    public static Date fromStringToDate(String dateString, String dateFormat ) {
        try {
            SimpleDateFormat dataFormat = new SimpleDateFormat(dateFormat);
            return dataFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Date fromStringToDateTime(String dateString, String oraString) {
        return fromStringToDate(dateString + " " + oraString, "dd/MM/yyyy HH:mm");
    }

    @SuppressLint("SimpleDateFormat")
    public static String formatDateToString(Date data) {
        if(data == null) return "-";
        return new SimpleDateFormat("dd/MM/yyyy HH:mm").format(data);
    }

    public static String getDateFormat() {
        return "%02d/%02d/%04d";
    }

    public static String getTimeFormat() {
        return "%02d:%02d";
    }


}
