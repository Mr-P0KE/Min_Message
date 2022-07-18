package com.poke.Controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.poke.service.UserService;
import com.poke.pojo.User;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {
    @Autowired
    UserService userService;

    @GetMapping("/main")
    public String getData(@RequestParam(value = "pn",defaultValue = "1") Integer pn, Model model, HttpSession session) {
        String email = (String)session.getAttribute("email");
        model.addAttribute("username", userService.getUsernameByEmail(email));
        Page<User> userPage = new Page(pn, 10);
        IPage<User> page = userService.getUserAndDept(userPage);
        model.addAttribute("page", page);
        return "main";
    }

    @DeleteMapping("/user/{id}")
    public String deleteUser(@PathVariable("id") Integer id, Integer pn) {
        userService.removeById(id);
        return "redirect:/main?pn=" + pn;
    }

    @GetMapping("/user/{id}")
    @ResponseBody
    public User getUser(@PathVariable("id") Integer id, Model model) {
        User user = userService.getUserAndDeptById(id);
        System.out.println(user);
        return user;
    }

    @PutMapping("/user/{id}")
    @ResponseBody
    public String UpdateUser(String name, Integer id, String post, Integer did) {
        System.out.println("ssssssss");
        User u = new User();
        u.setUsername(name);
        u.setPost(userService.decide(post));
        u.setId(id);
        u.setDId(did);
        System.out.println(u);
        userService.updateById(u);
        return "success";
    }

    @PostMapping("/user")
    @ResponseBody
    public String add(String name, String email, String post, Integer did, Model model) {
        System.out.println(name + " email: " + email + "  post: " + post + " dId: " + did);
        User u = new User();
        u.setUsername(name);
        u.setPost(userService.decide(post));
        u.setEmail(email);
        u.setDId(did);
        u.setPassword("000000");
        userService.save(u);
        model.addAttribute("msg", "添加成功");
        return "success";
    }

    @DeleteMapping("/users")
    @ResponseBody
    public String deleteUsers(@RequestParam("ids") String ids) {
        if(ids.contains("-")){
            String[] s = ids.split("-");
            List<Integer> list = new ArrayList<>();
            for(String ss : s){
                list.add(Integer.parseInt(ss));
            }
           userService.removeBatchByIds(list);
        }else{
            userService.removeById(Integer.parseInt(ids));
        }
        return "success";
    }
}

