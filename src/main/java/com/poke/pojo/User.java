package com.poke.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;

import com.poke.Enums.PostEnum;
import lombok.Data;

/**
 * 
 * @TableName user
 */
@TableName(value ="user")
@Data
public class User implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    private String username;

    /**
     * 
     */
    private String password;

    /**
     * 
     */
    private String email;

    /**
     * 
     */
    private Integer dId;

    /**
     * 
     */


    @TableField(exist = false)
    private Department department;

    private PostEnum post;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}