package com.zivi.simplemvp.di.component;

import com.zivi.simplemvp.MainActivity;
import com.zivi.simplemvp.di.module.BookModule;

import dagger.Component;

/**
 * Created by zivi on 2017/8/27.
 */
@Component(modules = BookModule.class)
public interface BookComponent
{
    void inject(MainActivity mainActivity);
}
