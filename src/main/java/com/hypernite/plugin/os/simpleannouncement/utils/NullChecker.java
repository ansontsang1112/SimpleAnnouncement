package com.hypernite.plugin.os.simpleannouncement.utils;

public class NullChecker {
    public static <T extends Object> boolean isNull(T [] array, int maxIndex) {
        for(int i = 0; i < maxIndex; i++) {
            if(array[i] == null || array[i] == "") {
                return true;
            }
        }

        return false;
    }
}
