package com.zivi.simplemvplibrary.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.trello.rxlifecycle.components.support.RxFragment;

import javax.inject.Inject;

/**
 * Created by zivi on 2017/8/23.
 */

public class BaseFragment<P extends IPresenter> extends RxFragment
{
    protected final String TAG = this.getClass().getSimpleName();
    @Inject
    protected P presenter;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        inject();
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();

        if (presenter != null)
        {
            presenter.onDetach();
        }

        presenter = null;
    }

    protected void inject(){}
}
