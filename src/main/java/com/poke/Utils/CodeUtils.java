package com.poke.Utils;

import java.util.UUID;
import org.springframework.mail.SimpleMailMessage;

public class CodeUtils {


    public static String code() {
        String code = String.valueOf(UUID.randomUUID()).substring(0, 6);
        return code;
    }

    public static SimpleMailMessage sendEmail(String code, String email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("欢迎您使用时尚服装公司人员管理系统");
        message.setText("这是您的验证码（" + code + "），请勿告诉他人，此验证码有效期五分钟，请您尽快使用");
        message.setTo(email);
        message.setFrom("3137668254@qq.com(MR_POKE)");
        System.out.println(message);
        return message;
    }
}
