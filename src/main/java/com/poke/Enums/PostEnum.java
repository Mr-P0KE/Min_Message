package com.poke.Enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Data;
import lombok.Getter;


@Getter
public enum PostEnum {
    emp(0, "员工"),
    comp(1, "主管"),
    boss(2, "老板");

    @EnumValue
    private Integer post;
    private String name;

    private PostEnum(Integer post, String postName) {
        this.post = post;
        this.name = postName;
    }


}
