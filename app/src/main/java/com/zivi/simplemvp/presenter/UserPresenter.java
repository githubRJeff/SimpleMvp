package com.zivi.simplemvp.presenter;

import android.text.TextUtils;

import com.zivi.simplemvp.contract.UserContract;

import javax.inject.Inject;

/**
 * Created by zivi on 2017/5/1.
 */

public class UserPresenter extends UserContract.Presenter
{
    @Inject
    public UserPresenter(UserContract.Model model, UserContract.View view)
    {
        super(model, view);
    }

    @Override
    public void getUserName()
    {
        if (!TextUtils.isEmpty(model.loadUserName()))
        {
            view.loadUserNameSuccess(model.loadUserName());
        }
        else
        {
            view.loadUserNameFailed("load failed");
        }
    }
}
