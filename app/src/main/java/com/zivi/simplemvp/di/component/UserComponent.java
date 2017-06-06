package com.zivi.simplemvp.di.component;

import com.zivi.simplemvp.MainActivity;
import com.zivi.simplemvp.di.module.UserModule;

import dagger.Component;

/**
 * Created by zivi on 2017/5/1.
 */
@Component(modules = UserModule.class)
public interface UserComponent
{
    void inject(MainActivity activity);
}
