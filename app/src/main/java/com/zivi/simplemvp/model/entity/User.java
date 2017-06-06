package com.zivi.simplemvp.model.entity;

/**
 * Created by zivi on 2017/5/1.
 */

public class User
{
    private String name;

    public User(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
}
