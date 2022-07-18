package com.poke.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.poke.Enums.PostEnum;
import com.poke.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 56207
* @description 针对表【user】的数据库操作Service
* @createDate 2022-07-18 21:16:37
*/
public interface UserService extends IService<User> {
    void sendCode(String email);

    boolean emailIf(String email);

    boolean codeIf(String code, String email);

    boolean login(String email, String password);

    int updatePasswordByEmail(String password, String email);

    void sendUser(String email);

    String getUsernameByEmail(String email);

    IPage<User> getUserAndDept(IPage<User> page);

    User getUserAndDeptById(Integer id);

    PostEnum decide(String post);
}
