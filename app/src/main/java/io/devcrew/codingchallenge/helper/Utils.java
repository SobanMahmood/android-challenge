package io.devcrew.codingchallenge.helper;

import android.support.annotation.NonNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by soban on 19/01/16.
 */
public class Utils {
    /**
     * Read data from input stream and returns as a string. This task being is done synchronously.
     * @param inputStream from where to read
     * @return a string read from provided input stream
     */
    @NonNull
    public static String getStringFrom(InputStream inputStream) {
        StringBuffer dataStringBuffer = new StringBuffer("");
        try {
            InputStreamReader isr = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(isr);
            String readString = bufferedReader.readLine();
            while (readString != null) {
                dataStringBuffer.append(readString);
                readString = bufferedReader.readLine();
            }
            isr.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return dataStringBuffer.toString();
    }

    /**
     * Returns string that has first letter capitalized and all other converted to lower case
     * @param string
     * @return formatted String
     */
    public static String getNameFormattedString(String string) {
        if (string != null && string.length() > 0) {
            StringBuilder formatted = new StringBuilder(string.toLowerCase());
            formatted.setCharAt(0, Character.toUpperCase(formatted.charAt(0)));
            return formatted.toString();
        }
        return "";
    }
}
