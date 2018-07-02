package com.dongfang.monitor.filter;

import com.dongfang.monitor.model.monitor.User;
import com.dongfang.monitor.utils.GlobalConstant;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class CustomFormAuthenticationFilter extends FormAuthenticationFilter {

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpSession session = httpServletRequest.getSession();
        String validateCode = (String) session.getAttribute("verifyCode");
        String randomcode = httpServletRequest.getParameter("verify");
        if (randomcode != null && validateCode != null && !randomcode.equals(validateCode)) {
            httpServletRequest.setAttribute("shiroLoginFailure", "kaptchaValidateFailed");
            return true;
        }
        return super.onAccessDenied(request, response);

    }

    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
        User user = (User) subject.getPrincipal();
        HttpServletRequest httpServletRequest = WebUtils.toHttp(request);
        HttpSession session = httpServletRequest.getSession();
        session.setAttribute(GlobalConstant.USER_SESSION_KEY, user);
        return super.onLoginSuccess(token, subject, request, response);

    }
}
