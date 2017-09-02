package com.zivi.simplemvp.model;

import com.zivi.simplemvp.contract.BookContract;
import com.zivi.simplemvp.model.entity.Book;
import com.zivi.simplemvp.model.http.ApiManager;
import com.zivi.simplemvp.model.http.HttpResponse;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by zivi on 2017/8/27.
 */

public class BookModel implements BookContract.BaseBookModel
{
    @Inject
    public BookModel(){}

    @Override
    public Observable<HttpResponse<List<Book>>> searchBook(String name)
    {
        return ApiManager.newInstance().getApiService().searchBook(name);
    }
}
