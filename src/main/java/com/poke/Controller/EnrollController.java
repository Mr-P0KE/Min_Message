package com.poke.Controller;


import  com.poke.service.UserService;
import com.poke.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/enroll")
public class EnrollController {
    @Autowired
   UserService userService;

    @PostMapping("/sendCode")
    @ResponseBody
    public String sendCode(@RequestParam("email") String email) {
        String regx = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
        if (ObjectUtils.isEmpty(email)) {
            return "请输入邮箱";
        } else if (!email.matches(regx)) {
            return "邮箱格式错误,请重新输入";
        } else if (userService.emailIf(email)) {
            return "邮箱已被使用";
        } else {
            userService.sendCode(email);
            return "发送成功，请注意查收";
        }
    }

    @PostMapping("/")
    public String enroll(User user, @RequestParam("code") String code, Model model) {
        System.out.println(code);
        if (!userService.emailIf(user.getEmail())) {
            model.addAttribute("msg", "邮箱已被注册");
            return "login";
        } else if (!ObjectUtils.isEmpty(user.getUsername()) && !ObjectUtils.isEmpty(user.getPassword()) && !ObjectUtils.isEmpty(user.getEmail())) {
            if (userService.codeIf(code, user.getEmail())) {
                userService.save(user);
                return "/login/In";
            } else {
                model.addAttribute("msg", "验证码错误");
                return "login";
            }
        } else {
            model.addAttribute("msg", "内容不能为空");
            return "login";
        }
    }

    @PostMapping("/ajaxEmail")
    @ResponseBody
    public boolean ajaxEmail(String email) {
        System.out.println(email);
        boolean b = userService.emailIf(email);
        return b;
    }
}
