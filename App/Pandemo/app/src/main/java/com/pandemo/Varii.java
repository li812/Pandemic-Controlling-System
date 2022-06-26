package com.pandemo;

import android.content.Context;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.OkHttpClient;

public class Varii {
    public static String answers[] = new String[35];
    public static int Counter = 1;
    public static int inner = 0;
    public static String videos[];
    public static int sco = 0;
    public static String mn[] = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
    public static String months[] = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    public static String Year[] = {"2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030"};
    public static String URL = "http:172.20.10.2:8000/";
    public static OkHttpClient okHttpClient = UnsafeOkHttpClient.getUnsafeOkHttpClient();
    public static int total = 0;

    public static void p(Context context, String s) {
        Toast.makeText(context, s, Toast.LENGTH_LONG).show();
    }

    public static void p2(Context context, String s) {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
    }

    public static boolean che(String v) {

        if (v.matches("[a-zA-Z ]+$")) {
            return true;
        } else
            return false;
    }

    public static boolean chep(String v) {

        if (v.matches("[0-9]+$")) {
            {
                if (v.length() == 10)
                    return true;
                else
                    return false;

            }
        } else
            return false;
    }

    public static boolean chee(String v) {
        String expression = "^[\\w\\-]([\\.\\w])+[\\w]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = v;
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        return matcher.matches();
    }


}
