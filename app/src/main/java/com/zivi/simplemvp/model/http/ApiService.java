package com.zivi.simplemvp.model.http;

import com.zivi.simplemvp.model.entity.Book;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by zivi on 2017/8/23.
 */

public interface ApiService
{
    @GET("search")
    Observable<HttpResponse<List<Book>>> searchBook(@Query("q") String name);
}
