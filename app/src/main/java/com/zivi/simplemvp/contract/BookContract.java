package com.zivi.simplemvp.contract;

import com.zivi.simplemvp.model.entity.Book;
import com.zivi.simplemvp.model.http.HttpResponse;
import com.zivi.simplemvplibrary.base.BaseModel;
import com.zivi.simplemvplibrary.base.BaseView;

import java.util.List;

import rx.Observable;

/**
 * Created by zivi on 2017/8/27.
 */

public interface BookContract
{
    interface BaseBookModel extends BaseModel
    {
        Observable<HttpResponse<List<Book>>> searchBook(String name);
    }

    interface BookView extends BaseView
    {
        void searchSuccess(List<Book> bookList);
        void searchFailed(Exception e);
    }
}
