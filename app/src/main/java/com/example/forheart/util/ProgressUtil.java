package com.example.forheart.util;

/**
 * util class to handle workout progress
 */
public class ProgressUtil {

    public static int moderate = 300;

    public static int vigorous = 175;

    public static void setModerate(int moderate) {
        ProgressUtil.moderate = moderate;
    }

    public static void setVigorous(int vigorous) {
        ProgressUtil.vigorous = vigorous;
    }

    public static int getModerate() {
        return moderate;
    }

    public static int getVigorous() {
        return vigorous;
    }

    public static void init(String type) {
        if (type.equals("Heavy")) {
            setModerate(420);
            setVigorous(250);
        }
        else if (type.equals("Light")) {
            setModerate(180);
            setVigorous(100);
        }
        else {
            setModerate(300);
            setVigorous(175);
        }
    }

    public static float transVig(int vigTime) {
        return vigTime * moderate / vigorous;
    }
}
