package com.fux.auth.controller;

import com.fux.auth.Constants;
import com.fux.auth.util.ImageUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.RenderedImage;
import java.io.IOException;
import java.util.Map;

/**
 * 验证码
 * Created by fuxiaoj on 2018/04/13 10:23
 */
@Controller
public class CaptchaController {

    @RequestMapping(value = "captcha")
    public void authImg(HttpServletResponse response, HttpSession session) throws IOException {
        Map<String, Object> map = ImageUtil.generateCodeAndPic();
        session.setAttribute(Constants.AUTH_CODE_SESSION_KEY, map.get("code"));
        ImageIO.write((RenderedImage) map.get("codePic"), "jpeg", response.getOutputStream());
    }
}
