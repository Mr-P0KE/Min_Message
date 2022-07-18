package com.poke.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.poke.Enums.PostEnum;
import com.poke.Utils.CodeUtils;
import com.poke.Utils.RedisConstants;
import com.poke.pojo.User;
import com.poke.service.UserService;
import com.poke.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
* @author 56207
* @description 针对表【user】的数据库操作Service实现
* @createDate 2022-07-18 21:16:37
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    JavaMailSender sender;
    @Autowired
    UserMapper userMapper;


    public void sendCode(String email) {
        String code = CodeUtils.code();
        System.out.println("验证码为" + code);
        SimpleMailMessage message = CodeUtils.sendEmail(code, email);
        sender.send(message);
        redisTemplate.opsForValue().set("login:code:" + email, code, RedisConstants.LOGIN_CODE_TTL, TimeUnit.MINUTES);
    }

    public void sendUser(String email) {
        redisTemplate.opsForValue().set("login:user" + email, email, RedisConstants.LOGIN_USER_TTL, TimeUnit.HOURS);
    }

    public boolean emailIf(String email) {
        int i = userMapper.countByEmail(email);
        return i == 0;
    }

    public int updatePasswordByEmail(String password, String email) {
        int i = userMapper.updatePasswordByEmail(password, email);
        return i;
    }

    public boolean codeIf(String code, String email) {
        if (this.redisTemplate.hasKey("login:code:" + email)) {
            String codeRedis = (String)redisTemplate.opsForValue().get("login:code:" + email);
            System.out.println(codeRedis);
            return codeRedis.equals(code);
        } else {
            return false;
        }
    }

    public boolean login(String email, String password) {
        int i = userMapper.countByEmailAndPassword(email, password);
        return i != 0;
    }

    public String getUsernameByEmail(String email) {
        List<User> list = userMapper.selectByEmail(email);
        String username = ((User)list.get(0)).getUsername();
        return username;
    }

    public IPage<User> getUserAndDept(IPage<User> page) {
        IPage<User> result = userMapper.getUserAndDeptByDId(page);
        return result;
    }

    public User getUserAndDeptById(Integer id) {
        User user = userMapper.getUserAndDept(id);
        return user;
    }

    public PostEnum decide(String post) {
        PostEnum p;
        if (post.equals("emp")) {
            p = PostEnum.emp;
        } else if (post.equals("comp")) {
            p = PostEnum.comp;
        } else {
            p = PostEnum.boss;
        }

        return p;
    }
}




