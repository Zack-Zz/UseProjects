package com.github.zack.use.java.base.demo;

import com.github.zack.use.ast.AutoGetter;

/**
 * @author zack
 * @since 2024/12/7
 */
public class Person {

    @AutoGetter
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }
}
