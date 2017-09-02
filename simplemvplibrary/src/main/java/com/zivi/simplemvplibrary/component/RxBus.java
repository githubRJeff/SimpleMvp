package com.zivi.simplemvplibrary.component;

import java.util.HashMap;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by zivi on 2017/4/29.
 */

public class RxBus
{
    private static volatile RxBus instance;
    private final Subject<Object, Object> subject;
    private HashMap<String, CompositeSubscription> subscriptionMap;

    public RxBus()
    {
        subject = new SerializedSubject<>(PublishSubject.create());
    }

    public static RxBus getInstance()
    {
        if (instance == null)
        {
            synchronized (RxBus.class)
            {
                if (instance == null)
                {
                    instance = new RxBus();
                }
            }
        }

        return instance;
    }

    /**
     * 发送事件
     * @param object
     */
    public void post(Object object)
    {
        subject.onNext(object);
    }

    /**
     * 返回指定类型的Observable实例
     * @param type
     * @param <T>
     * @return
     */
    public <T> Observable<T> toObservable(final Class<T> type)
    {
        return subject.ofType(type);
    }

    /**
     * 是否已有订阅者订阅
     * @return
     */
    public boolean hasObservable()
    {
        return subject.hasObservers();
    }

    /**
     * 默认的订阅方法
     * @param type
     * @param next
     * @param error
     * @param <T>
     * @return
     */
    public <T> Subscription onSubscribe(Class<T> type, Action1<T> next, Action1<Throwable> error)
    {
        return toObservable(type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(next, error);
    }

    /**
     * 保存订阅后的subscription
     * @param object
     * @param subscription
     */
    public void addSubscription(Object object, Subscription subscription)
    {
        if (subscriptionMap == null)
        {
            subscriptionMap = new HashMap<>();
        }

        String key = object.getClass().getName();
        if (subscriptionMap.get(key) != null)
        {
            subscriptionMap.get(key).add(subscription);
        }
        else
        {
            CompositeSubscription compositeSubscription = new CompositeSubscription();
            compositeSubscription.add(subscription);
            subscriptionMap.put(key, compositeSubscription);
        }
    }

    /**
     * 取消订阅
     * @param object
     */
    public void unSubscribe(Object object)
    {
        if (subscriptionMap == null)
        {
            return;
        }

        String key = object.getClass().getName();
        if (!subscriptionMap.containsKey(key))
        {
            return;
        }

        if (subscriptionMap.get(key) != null)
        {
            subscriptionMap.get(key).unsubscribe();
        }

        subscriptionMap.remove(key);
    }
}
