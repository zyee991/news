package com.itcuc.common.utils;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * Classname IpAddressUtil
 * <p>获取ip地址</p>
 *
 * @author z
 * @date 2018/7/10
 */
public class IpAddressUtil {

    private final static String UNKOOWN = "unKnown";

    /**
     * 获取Ip地址
     * @param request
     * @return
     */
    public static String getIpAdrress(HttpServletRequest request) {
        String xip = request.getHeader("X-Real-IP");
        String xfor = request.getHeader("X-Forwarded-For");
        if(StringUtils.isNotEmpty(xfor) && !UNKOOWN.equalsIgnoreCase(xfor)){
            //多次反向代理后会有多个ip值，第一个ip才是真实ip
            int index = xfor.indexOf(",");
            if(index != -1){
                return xfor.substring(0,index);
            }else{
                return xfor;
            }
        }
        xfor = xip;
        if(StringUtils.isNotEmpty(xfor) && !UNKOOWN.equalsIgnoreCase(xfor)){
            return xfor;
        }
        if (StringUtils.isBlank(xfor) || UNKOOWN.equalsIgnoreCase(xfor)) {
            xfor = request.getHeader("Proxy-Client-IP");
        }
        if (StringUtils.isBlank(xfor) || UNKOOWN.equalsIgnoreCase(xfor)) {
            xfor = request.getHeader("WL-Proxy-Client-IP");
        }
        if (StringUtils.isBlank(xfor) || UNKOOWN.equalsIgnoreCase(xfor)) {
            xfor = request.getHeader("HTTP_CLIENT_IP");
        }
        if (StringUtils.isBlank(xfor) || UNKOOWN.equalsIgnoreCase(xfor)) {
            xfor = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (StringUtils.isBlank(xfor) || UNKOOWN.equalsIgnoreCase(xfor)) {
            xfor = request.getRemoteAddr();
        }
        return xfor;
    }
}
