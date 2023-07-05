package com.example.datacollection;

import android.os.Build;

public class util {

    public static String getCharacter(int i)
    {String character=new String();
        if (i == 1)
        {
            character = "ا";
        }
        else if (i == 2)
        {
            character = "ب";
        }
        else if (i == 3)
        {
            character = "پ";
        }
        else if (i == 4)
        {
            character = "ت";
        }
        else if (i == 5)
        {
            character = "ٹ";
        }
        else if (i == 6)
        {
            character = "ث";
        }
        else if (i == 7)
        {
            character = "ج";
        }
        else if (i == 8)
        {
            character = "چ";
        }
        else if (i == 9)
        {
            character = "ح";
        }
        else if (i == 10)
        {
            character = "خ";
        }
        else if (i == 11)
        {
            character = "د";
        }
        else if (i == 12)
        {
            character = "ڈ";
        }
        else if (i == 13)
        {
            character = "ذ";
        }
        else if (i == 14)
        {
            character = "ر";
        }
        else if (i == 15)
        {
            character = "ڑ";
        }
        else if (i == 16)
        {
            character = "ز";
        }
        else if (i == 17)
        {
            character = "ژ";
        }
        else if (i == 18)
        {
            character = "س";
        }
        else if (i == 19)
        {
            character = "ش";
        }
        else if (i == 20)
        {
            character = "ص";
        }
        else if (i == 21)
        {
            character = "ض";
        }
        else if (i == 22)
        {
            character = "ط";
        }
        else if (i == 23)
        {
            character = "ظ";
        }
        else if (i == 24)
        {
            character = "ع";
        }
        else if (i == 25)
        {
            character = "غ";
        }
        else if (i == 26)
        {
            character = "ف";
        }
        else if (i == 27)
        {
            character = "ق";
        }
        else if (i == 28)
        {
            character = "ک";
        }
        else if (i == 29)
        {
            character = "گ";
        }
        else if (i == 30)
        {
            character = "ل";
        }
        else if (i == 31)
        {
            character = "م";
        }
        else if (i == 32)
        {
            character = "ن";
        }
        else if (i == 33)
        {
            character = "و";
        }
        else if (i == 34)
        {
            character = "ہ";
        }
        else if (i == 35)
        {
            character = "ء";
        }
        else if (i == 36)
        {
            character = "ی";
        }
        else if (i == 37)
        {
            character = "ے";
        }
        return character;
    }



    public static String getDeviceName() {
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        if (model.toLowerCase().startsWith(manufacturer.toLowerCase())) {
            return capitalize(model);
        } else {
            return capitalize(manufacturer) + " " + model;
        }
    }


    private static String capitalize(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        char first = s.charAt(0);
        if (Character.isUpperCase(first)) {
            return s;
        } else {
            return Character.toUpperCase(first) + s.substring(1);
        }
    }
}
