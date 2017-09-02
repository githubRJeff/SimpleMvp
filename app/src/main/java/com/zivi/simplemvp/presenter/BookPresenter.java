package com.zivi.simplemvp.presenter;

import android.util.Log;

import com.zivi.simplemvp.contract.BookContract;
import com.zivi.simplemvp.model.entity.Book;
import com.zivi.simplemvp.model.http.HttpResponse;
import com.zivi.simplemvplibrary.base.BasePresenter;

import java.util.List;

import javax.inject.Inject;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by zivi on 2017/8/27.
 */

public class BookPresenter extends BasePresenter<BookContract.BaseBookModel, BookContract.BookView>
{
    @Inject
    public BookPresenter(BookContract.BaseBookModel model, BookContract.BookView view)
    {
        super(model, view);
    }

    public void searchBook(String name)
    {
        view.showProgress();
        compositeSubscription.add(model.searchBook(name)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Observer<HttpResponse<List<Book>>>()
                            {
                                @Override
                                public void onCompleted()
                                {
                                    view.hideProgress();
                                }

                                @Override
                                public void onError(Throwable e)
                                {
                                    view.hideProgress();
                                    view.searchFailed((Exception) e);
                                }

                                @Override
                                public void onNext(HttpResponse<List<Book>> listHttpResponse)
                                {
                                    view.searchSuccess(listHttpResponse.getBooks());
                                }
                            }));
    }
}
