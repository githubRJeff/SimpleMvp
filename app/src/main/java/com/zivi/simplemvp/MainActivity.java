package com.zivi.simplemvp;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.zivi.simplemvp.adapter.BookAdapter;
import com.zivi.simplemvp.contract.BookContract;
import com.zivi.simplemvp.di.component.DaggerBookComponent;
import com.zivi.simplemvp.di.module.BookModule;
import com.zivi.simplemvp.model.entity.Book;
import com.zivi.simplemvp.presenter.BookPresenter;
import com.zivi.simplemvplibrary.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity<BookPresenter> implements BookContract.BookView, SearchView.OnQueryTextListener, SwipeRefreshLayout.OnRefreshListener
{
    private Toolbar toolbar;
    private SwipeRefreshLayout refreshLayout;
    private RecyclerView recyclerView;
    private BookAdapter adapter;
    private List<Book> bookList;
    private String queryName = "言叶之庭";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        refreshLayout = (SwipeRefreshLayout) findViewById(R.id.refresh_layout);
        recyclerView = (RecyclerView) findViewById(R.id.rv_book);

        setSupportActionBar(toolbar);

        refreshLayout.setColorSchemeResources(R.color.colorPrimary);

        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(manager);

        bookList = new ArrayList<>();
        adapter = new BookAdapter(this, bookList);
        recyclerView.setAdapter(adapter);

        refreshLayout.setOnRefreshListener(this);

        onRefresh();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);

        searchView.setOnQueryTextListener(this);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onQueryTextSubmit(String query)
    {
        int itemCount = bookList.size();
        bookList.clear();
        adapter.notifyItemRangeRemoved(0, itemCount);

        queryName = query;
        presenter.searchBook(queryName);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText)
    {
        return false;
    }

    @Override
    protected void inject()
    {
        super.inject();
        DaggerBookComponent.builder().bookModule(new BookModule(this)).build().inject(this);
    }

    @Override
    public void showProgress()
    {
        isRefresh(true);
    }

    @Override
    public void hideProgress()
    {
        isRefresh(false);
    }

    @Override
    public void searchSuccess(List<Book> bookList)
    {
        if (!this.bookList.containsAll(bookList))
        {
            this.bookList.addAll(bookList);
            adapter.notifyItemRangeInserted(this.bookList.size(), bookList.size());
        }
    }

    @Override
    public void searchFailed(Exception e)
    {
        Log.e(TAG, e.getMessage());
        Snackbar.make(refreshLayout, "search failed", Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onRefresh()
    {
        presenter.searchBook(queryName);
    }

    private void isRefresh(final boolean isRefresh)
    {
        refreshLayout.post(new Runnable()
        {
            @Override
            public void run()
            {
                refreshLayout.setRefreshing(isRefresh);
            }
        });
    }
}
