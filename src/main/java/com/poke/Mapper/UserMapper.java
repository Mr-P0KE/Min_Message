package com.poke.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.poke.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author 56207
* @description 针对表【user】的数据库操作Mapper
* @createDate 2022-07-18 21:16:37
* @Entity com.poke.pojo.User
*/
public interface UserMapper extends BaseMapper<User> {

    int countByEmail(@Param("email") String email);

    int countByEmailAndPassword(@Param("email") String email, @Param("password") String password);

    int updatePasswordByEmail(@Param("password") String password, @Param("email") String email);

    List<User> selectByEmail(@Param("email") String email);

    IPage<User> getUserAndDeptByDId(IPage<User> page);

    User getUserAndDept(Integer id);
}




