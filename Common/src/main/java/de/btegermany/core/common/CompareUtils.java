package de.btegermany.core.common;

import lombok.experimental.UtilityClass;

@UtilityClass
public class CompareUtils {

    public static boolean startsWithIgnoreCase(String str1, String str2) {
        return str1.toLowerCase().startsWith(str2.toLowerCase());
    }

}
