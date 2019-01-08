package com.neuedu.util;

import javax.servlet.http.Cookie;
import java.util.HashMap;
import java.util.Map;

public class CookieUtil {
    public static Map<String,Cookie> getCookie(Cookie[] cookies){
        Map<String,Cookie> map = new HashMap<>();
        if (cookies != null){
            for (Cookie c:cookies
            ) {
                map.put(c.getName(),c);
            }
        }
        return map;
    }
}
