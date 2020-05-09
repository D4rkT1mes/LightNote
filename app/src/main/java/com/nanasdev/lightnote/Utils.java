package com.nanasdev.lightnote;

public class Utils {
    public static String[] getFilenameStrings(String fileName) {
        String filen = fileName;
        String del = "_";
        return filen.split(del);
    }
}
