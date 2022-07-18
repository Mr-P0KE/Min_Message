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
        message.setSubject("��ӭ��ʹ��ʱ�з�װ��˾��Ա����ϵͳ");
        message.setText("����������֤�루" + code + "��������������ˣ�����֤����Ч������ӣ���������ʹ��");
        message.setTo(email);
        message.setFrom("3137668254@qq.com(MR_POKE)");
        System.out.println(message);
        return message;
    }
}
