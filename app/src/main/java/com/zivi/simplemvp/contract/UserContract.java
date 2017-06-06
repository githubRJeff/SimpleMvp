package com.zivi.simplemvp.contract;

import com.zivi.simplemvplibrary.base.BaseModel;
import com.zivi.simplemvplibrary.base.BasePresenter;
import com.zivi.simplemvplibrary.base.BaseView;

/**
 * Created by zivi on 2017/5/1.
 */

public interface UserContract
{
    interface View extends BaseView
    {
        void loadUserNameSuccess(String username);
        void loadUserNameFailed(String msg);
    }

    interface Model extends BaseModel
    {
        String loadUserName();
    }

    abstract class Presenter extends BasePresenter<Model, View>
    {
        public Presenter(Model model, View view)
        {
            super(model, view);
        }

        public abstract void getUserName();
    }
}
