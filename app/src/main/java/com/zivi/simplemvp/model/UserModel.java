package com.zivi.simplemvp.model;

import com.zivi.simplemvp.contract.UserContract;
import com.zivi.simplemvp.model.entity.User;

import javax.inject.Inject;

/**
 * Created by zivi on 2017/5/1.
 */

public class UserModel implements UserContract.Model
{
    @Inject
    public UserModel(){}

    @Override
    public String loadUserName()
    {
        return new User("zivi").getName();
    }
}
