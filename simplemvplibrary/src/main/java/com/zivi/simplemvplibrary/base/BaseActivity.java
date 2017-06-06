package com.zivi.simplemvplibrary.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import javax.inject.Inject;

/**
 * Created by zivi on 2017/5/1.
 */

public class BaseActivity<P extends IPresenter> extends RxAppCompatActivity
{
    protected final String TAG = this.getClass().getSimpleName();
    @Inject
    protected P presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        inject();
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();

        if (presenter != null)
        {
            presenter.onDetach();
        }
        presenter = null;
    }

    protected void inject()
    {

    }
}
