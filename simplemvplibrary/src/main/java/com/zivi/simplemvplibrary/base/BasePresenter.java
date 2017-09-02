package com.zivi.simplemvplibrary.base;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by zivi on 2017/4/30.
 */

public class BasePresenter<M extends BaseModel, V extends BaseView> implements IPresenter
{
    protected final String TAG = this.getClass().getSimpleName();
    protected CompositeSubscription compositeSubscription;
    protected M model;
    protected V view;

    public BasePresenter(M model, V view)
    {
        this.model = model;
        this.view = view;
        this.compositeSubscription = new CompositeSubscription();
        onAttach();
    }

    protected void addSubscribe(Subscription subscription)
    {
        if (compositeSubscription == null)
        {
            compositeSubscription = new CompositeSubscription();
        }

        compositeSubscription.add(subscription);
    }

    protected void unSubscribe()
    {
        if (compositeSubscription != null)
        {
            compositeSubscription.unsubscribe();
        }
    }

    /**
     * EventBus or RxBus register here
     */
    @Override
    public void onAttach()
    {

    }

    /**
     * EventBus or RxBus unregister here
     */
    @Override
    public void onDetach()
    {
        unSubscribe();
        this.model = null;
        this.view = null;
        this.compositeSubscription = null;
    }
}
