package com.zivi.simplemvp.di.module;

import com.zivi.simplemvp.contract.BookContract;
import com.zivi.simplemvp.model.BookModel;

import dagger.Module;
import dagger.Provides;

/**
 * Created by zivi on 2017/8/27.
 */
@Module
public class BookModule
{
    private BookContract.BookView bookView;

    public BookModule(BookContract.BookView bookView)
    {
        this.bookView = bookView;
    }

    @Provides
    public BookContract.BookView provideBookView()
    {
        return this.bookView;
    }

    @Provides
    public BookContract.BaseBookModel provideBookModel(BookModel bookModel)
    {
        return bookModel;
    }
}
