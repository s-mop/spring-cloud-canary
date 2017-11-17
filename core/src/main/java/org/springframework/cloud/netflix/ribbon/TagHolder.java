package org.springframework.cloud.netflix.ribbon;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * @author GuoXinYuan
 *
 */
public class TagHolder {
    public static final String X_TAG = "x-tag";

    private static final ThreadLocal<Map<String, String>> X_TAG_MAP = new ThreadLocal<>();

    public static void put(String key, String value) {
        Map<String, String> map = X_TAG_MAP.get();
        if (map == null)
            map = new HashMap<>();
        map.put(key, value);
        X_TAG_MAP.set(map);
    }

    public static void putHeader(HttpServletRequest req) {
        put(X_TAG, req.getHeader(X_TAG));
    }

    public static boolean checkMap(Map<String, String> map) {
        if (map != null) {
            String mapCanary = map.get(X_TAG);
            if (mapCanary != null) {
                Map<String, String> headerMap = X_TAG_MAP.get();
                if (headerMap != null) {
                    String localCanary = headerMap.get(X_TAG);
                    return localCanary != null && mapCanary.equals(localCanary);
                }
            }
        }
        return false;
    }

}
