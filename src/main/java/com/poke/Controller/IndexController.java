package com.poke.Controller;


import com.poke.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {
    @Autowired
   UserService service;

    @GetMapping("/")
    public String main() {
        return "login";
    }

    @GetMapping("/forget")
    public String forget() {
        return "forget";
    }

    @PostMapping("/forget/reset")
    public String reset(String email, String password, String code, Model model) {
        if (service.codeIf(code, email)) {
            service.updatePasswordByEmail(password, email);
            System.out.println(password);
            return "redirect:/login";
        } else {
            model.addAttribute("msg", "验证码错误");
            return "/forget";
        }
    }

    @PostMapping({"/forget/ajaxOfEmail"})
    @ResponseBody
    public String ajaxOfEmail(String email) {
        System.out.println(email);
        if (!service.emailIf(email)) {
           service.sendCode(email);
            return "发送成功";
        } else {
            return "邮箱不存在";
        }
    }
}
