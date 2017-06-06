package com.zivi.simplemvp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.zivi.simplemvp.contract.UserContract;
import com.zivi.simplemvp.di.component.DaggerUserComponent;
import com.zivi.simplemvp.di.module.UserModule;
import com.zivi.simplemvp.presenter.UserPresenter;
import com.zivi.simplemvplibrary.base.BaseActivity;

public class MainActivity extends BaseActivity<UserPresenter> implements UserContract.View
{
    private TextView textView;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.text);
        button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                presenter.getUserName();
            }
        });
    }

    @Override
    protected void inject()
    {
        super.inject();
        DaggerUserComponent.builder().userModule(new UserModule(this)).build().inject(this);
    }

    @Override
    public void showProgress()
    {

    }

    @Override
    public void hideProgress()
    {

    }

    @Override
    public void loadUserNameSuccess(String username)
    {
        textView.setText(username);
    }

    @Override
    public void loadUserNameFailed(String msg)
    {
        textView.setText(msg);
    }
}
