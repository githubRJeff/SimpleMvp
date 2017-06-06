package com.zivi.simplemvp.di.module;

import com.zivi.simplemvp.contract.UserContract;
import com.zivi.simplemvp.model.UserModel;

import dagger.Module;
import dagger.Provides;

/**
 * Created by zivi on 2017/5/1.
 */
@Module
public class UserModule
{
    private UserContract.View view;

    public UserModule(UserContract.View view)
    {
        this.view = view;
    }

    @Provides
    public UserContract.View provideUserView()
    {
        return this.view;
    }

    @Provides
    public UserContract.Model provideUserModel(UserModel model)
    {
        return model;
    }
}
