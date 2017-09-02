package com.zivi.simplemvp.model.http;

import com.zivi.simplemvp.MyApplication;
import com.zivi.simplemvp.utils.NetworkUtils;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by zivi on 2017/8/23.
 */

public class ApiManager
{
    private static final int TIME_OUT_LIMIT = 10;
    private Retrofit retrofit;
    private ApiService apiService;

    private ApiManager()
    {
        File fileCache = new File(MyApplication.getApplication().getExternalCacheDir(), "simpleMvp");
        Cache cache = new Cache(fileCache, 100 * 1024 * 1024);
        Interceptor interceptor = new Interceptor()
        {
            @Override
            public Response intercept(Chain chain) throws IOException
            {
                Request request = chain.request();
                if (!NetworkUtils.isNetworkConnected(MyApplication.getApplication()))
                {
                    request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
                }

                Response response = chain.proceed(request);
                if (NetworkUtils.isNetworkConnected(MyApplication.getApplication()))
                {
                    int maxAge = 0;
                    response.newBuilder()
                            .header("Cache-Control", "public, max-age=" + maxAge)
                            .removeHeader("Pragma")
                            .build();
                }
                else
                {
                    int maxStale = 60 * 60 * 24;
                    response.newBuilder()
                            .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                            .removeHeader("Pragma")
                            .build();
                }
                return response;
            }
        };

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(TIME_OUT_LIMIT, TimeUnit.SECONDS)
                .readTimeout(TIME_OUT_LIMIT, TimeUnit.SECONDS)
                .writeTimeout(TIME_OUT_LIMIT, TimeUnit.SECONDS)
                .cache(cache)
                .addInterceptor(interceptor)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl("https://api.douban.com/v2/book/")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }

    private static final class SingleHolder
    {
        static final ApiManager newInstance = new ApiManager();
    }

    public static final ApiManager newInstance()
    {
        return SingleHolder.newInstance;
    }

    public ApiService getApiService()
    {
        if (apiService == null)
        {
            apiService = retrofit.create(ApiService.class);
        }

        return apiService;
    }
}
