package com.itcuc.common.utils;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Classname CookieUtils
 * <p></p>
 *
 * @author z
 * @date 2018/7/10
 */
@Slf4j
public class CookieUtils {

    public static Cookie[] getCookies() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        return request.getCookies();
    }

    public static void showCookie() {
        Cookie[] c = getCookies();
        if(c != null) {
            for (int i = 0; i < c.length; i++) {
                log.info("一条cookie____  name: " + c[i].getName() + "  || value: " + c[i].getValue());
            }
        }
    }

    public static void saveCookie(Cookie cookie) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletResponse response = attributes.getResponse();
        response.addCookie(cookie);
    }

    /**
     * 添加cookie
     *
     * @param name
     * @param object
     */
    public static void addCookie(String name, Object object) {
        try {

            String v = URLEncoder.encode(new Gson().toJson(object), "UTF-8");

            Cookie cookie = new Cookie(name, v);
            cookie.setPath("/");
            // 设置保存cookie最大时长
            cookie.setMaxAge(3600);
            saveCookie(cookie);

        } catch (Exception e) {
            log.info(e.getMessage());
            log.info(" -------添加cookie 失败！--------" + e.getMessage());
        }
    }

    /**
     * 获取cookie
     *
     * @param name
     * @param clazz
     * @return
     */
    public static <T> T getCookie(String name, Class<T> clazz) {
        try {

            Cookie[] cookies = getCookies();
            String v = null;
            if(cookies != null) {
                for (int i = 0; i < cookies.length; i++) {
                    if ((name).equalsIgnoreCase(cookies[i].getName())) {
                        v = cookies[i].getValue();
                        v = URLDecoder.decode(v, "UTF-8");
                    }
                }
            }
            if (v != null) {
                return new Gson().fromJson(v, clazz);
            }
        } catch (Exception e) {
            log.info(e.getMessage());
            log.info("------获取 class Cookie 失败----- " + e.getMessage());
        }
        return null;
    }

    /**
     * 获取cookie
     *
     * @param name
     * @return
     */
    public static String getCookie(String name) {
        try {

            Cookie[] cookies = getCookies();
            if(cookies != null) {
                for (int i = 0; i < cookies.length; i++) {
                    if ((name).equalsIgnoreCase(cookies[i].getName())) {
                        String v = cookies[i].getValue();
                        v = URLDecoder.decode(v, "UTF-8");
                        return new Gson().fromJson(v, String.class);
                    }
                }
            }
        } catch (Exception e) {
            log.info(e.getMessage());
            log.info(" --------获取String cookie 失败--------   " + e.getMessage());
        }
        return null;
    }

    /**
     * 删除cookie
     *
     * @param name
     */
    public static void removeCookie(String name) {
        try {

            Cookie[] cookies = getCookies();
            if(cookies != null) {
                for (int i = 0; i < cookies.length; i++) {
                    if ((name).equalsIgnoreCase(cookies[i].getName())) {

                        Cookie cookie = new Cookie(name, "");
                        cookie.setPath("/");
                        // 设置保存cookie最大时长为0，即使其失效
                        cookie.setMaxAge(0);
                        saveCookie(cookie);
                    }
                }
            }

        } catch (Exception e) {
            log.info(e.getMessage());
            log.info(" -------删除cookie失败！--------" + e.getMessage());
        }
    }
}
