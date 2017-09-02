package com.zivi.simplemvp.model.http;

/**
 * Created by zivi on 2017/8/27.
 */

public class HttpResponse<T>
{
    private String msg;
    private int code;
    private int count;
    private int start;
    private int total;
    private T books;

    public String getMsg()
    {
        return msg;
    }

    public void setMsg(String msg)
    {
        this.msg = msg;
    }

    public int getCode()
    {
        return code;
    }

    public void setCode(int code)
    {
        this.code = code;
    }

    public int getCount()
    {
        return count;
    }

    public void setCount(int count)
    {
        this.count = count;
    }

    public int getStart()
    {
        return start;
    }

    public void setStart(int start)
    {
        this.start = start;
    }

    public int getTotal()
    {
        return total;
    }

    public void setTotal(int total)
    {
        this.total = total;
    }

    public T getBooks()
    {
        return books;
    }

    public void setBooks(T books)
    {
        this.books = books;
    }
}
