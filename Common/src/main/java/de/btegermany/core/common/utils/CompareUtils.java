package de.btegermany.core.common.utils;

public class CompareUtils {

    public boolean startsWithIgnoreCase(String str, String with) {
        return str.toLowerCase().startsWith(with.toLowerCase());
    }

}
