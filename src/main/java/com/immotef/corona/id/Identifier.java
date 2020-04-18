package com.immotef.corona.id;

public class Identifier {

    public static final String ID_HEADER_NAME = "Authorization";

    public static long getId(String header) {
        return Long.parseLong(header.split(":")[0]);
    }

    public static long getMajor(String header) {
        return getId(header);
    }

    public static long getMinor(String header) {
        return Long.parseLong(header.split(":")[1]);
    }
}
