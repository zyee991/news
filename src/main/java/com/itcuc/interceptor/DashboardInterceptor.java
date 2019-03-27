package com.itcuc.interceptor;

import com.itcuc.base.entity.Manager;
import com.itcuc.base.service.ManagerService;
import com.itcuc.common.utils.CookieUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zy
 */
public class DashboardInterceptor implements HandlerInterceptor {

    @Autowired
    ManagerService managerService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String info = CookieUtils.getCookie("mninfo");
        if(StringUtils.isNotBlank(info)) {
            Manager manager = managerService.findById(info);
            if(manager != null) {
                request.setAttribute("manager",manager);
                return true;
            }
        }
        response.sendRedirect("/n/login");
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
