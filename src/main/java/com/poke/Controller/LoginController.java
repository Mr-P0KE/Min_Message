package com.poke.Controller;


import com.poke.service.UserService;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/login")
@Controller
public class LoginController {
    @Autowired
    UserService userService;

    @GetMapping
    public String login() {
        return "login";
    }

    @PostMapping("/In")
    public String loginIn(String email, String password, Model model, HttpSession session) {
        String regx = "^[a-zA-Z\\d_-]+@[a-zA-Z\\d_-]+(\\.[a-zA-Z\\d_-]+)+$";
        if (ObjectUtils.isEmpty(email) && ObjectUtils.isEmpty(password)) {
            model.addAttribute("msg2", "输入不能为空");
            return "login";
        } else if (!email.matches(regx)) {
            model.addAttribute("msg2", "邮箱格式格式错误");
            return "login";
        } else if (userService.login(email, password)) {
            userService.sendUser(email);
            session.setAttribute("email", email);
            return "redirect:/main";
        } else {
            model.addAttribute("msg2", "密码错误");
            return "login";
        }
    }
}
