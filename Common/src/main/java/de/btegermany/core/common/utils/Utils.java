package de.btegermany.core.common.utils;

import java.util.HashMap;
import java.util.Map;

public class Utils {
    Map<Class<?>, Object> utilsObjects = new HashMap<>();

    public void register(Object utilsObject) {
        this.utilsObjects.put(utilsObject.getClass(), utilsObject);
    }

    public <T> T get(Class<T> utilsClass) {
        return utilsClass.cast(this.utilsObjects.get(utilsClass));
    }
}
