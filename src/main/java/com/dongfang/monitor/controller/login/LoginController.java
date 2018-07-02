package com.dongfang.monitor.controller.login;

import com.dongfang.monitor.model.monitor.User;
import com.dongfang.monitor.service.PermissionService;
import com.dongfang.monitor.utils.GlobalConstant;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Controller
public class LoginController {

    @Autowired
    DefaultKaptcha defaultKaptcha;

    @Autowired
    PermissionService permissionService;

    /**
     * 跳登录页
     * @param httpSession
     * @return
     */
    @GetMapping("/login")
    public String toLogin(HttpSession httpSession){
        return "login";
    }

    /**
     * 验证码
     * @param httpServletRequest
     * @param httpServletResponse
     * @param httpSession
     * @throws IOException
     */
    @GetMapping("/verifyCode")
    public void verifyCode(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, HttpSession httpSession) throws IOException {
        byte[] captchaChallengeAsJpeg = null;
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
        try {

            String createText = defaultKaptcha.createText();
            httpSession.setAttribute("verifyCode", createText);

            BufferedImage challenge = defaultKaptcha.createImage(createText);
            ImageIO.write(challenge, "jpg", jpegOutputStream);
        } catch (IllegalArgumentException e) {
            httpServletResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
        httpServletResponse.setHeader("Cache-Control", "no-store");
        httpServletResponse.setHeader("Pragma", "no-cache");
        httpServletResponse.setDateHeader("Expires", 0);
        httpServletResponse.setContentType("image/jpeg");
        ServletOutputStream responseOutputStream =
                httpServletResponse.getOutputStream();
        responseOutputStream.write(captchaChallengeAsJpeg);
        responseOutputStream.flush();
        responseOutputStream.close();
    }

    @PostMapping("/login")
    public String login(HttpServletRequest request, Model model){
        String exception = (String) request.getAttribute("shiroLoginFailure");
        System.out.println("exception=" + exception);
        String msg = "";
        if (exception != null) {
            if (UnknownAccountException.class.getName().equals(exception)) {
                msg = "账号不存在：";
            } else if (IncorrectCredentialsException.class.getName().equals(exception)) {
                msg = "密码不正确：";
            } else if ("kaptchaValidateFailed".equals(exception)) {
                msg = "验证码错误";
            } else {
                msg = exception;
            }
        }
        model.addAttribute("msg",msg);

        return "/login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession httpSession){
        httpSession.removeAttribute(GlobalConstant.USER_SESSION_KEY);
        return "/login";
    }

    @GetMapping("/index")
    public String index(HttpSession httpSession,Model model){
        User user = (User) httpSession.getAttribute(GlobalConstant.USER_SESSION_KEY);
        model.addAttribute("permissionList",permissionService.findUserPromission(user));
        return "index";
    }



}
