package org.springframework.cloud.netflix.ribbon;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * @author GuoXinYuan
 *
 */
public class XHeaderHolder {
    public static final String X_HEADER = "x_header";

    private static final ThreadLocal<Map<String, String>> X_HEADER_MAP = new ThreadLocal<>();

    public static void put(String key, String value) {
        Map<String, String> map = X_HEADER_MAP.get();
        if (map == null)
            map = new HashMap<>();
        map.put(key, value);
        X_HEADER_MAP.set(map);
    }

    public static void putHeader(HttpServletRequest req) {
        put(X_HEADER, req.getHeader(X_HEADER));
    }

    public static boolean checkMap(Map<String, String> map) {
        if (map != null) {
            String mapCanary = map.get(X_HEADER);
            if (mapCanary != null) {
                Map<String, String> headerMap = X_HEADER_MAP.get();
                if (headerMap != null) {
                    String localCanary = headerMap.get(X_HEADER);
                    return localCanary != null && mapCanary.equals(localCanary);
                }
            }
        }
        return false;
    }

}
