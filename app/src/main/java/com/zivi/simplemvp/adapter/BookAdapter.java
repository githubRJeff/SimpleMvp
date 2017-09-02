package com.zivi.simplemvp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zivi.simplemvp.R;
import com.zivi.simplemvp.model.entity.Book;

import java.util.List;

/**
 * Created by zivi on 2017/8/27.
 */

public class BookAdapter extends RecyclerView.Adapter
{
    private Context context;
    private List<Book> bookList;

    public BookAdapter(Context context, List<Book> bookList)
    {
        this.context = context;
        this.bookList = bookList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(context).inflate(R.layout.item_book, parent, false);
        return new BookItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position)
    {
        if (holder instanceof BookItemViewHolder)
        {
            Book book = bookList.get(position);

            if (book != null)
            {
                Glide.with(context).load(book.getImage()).crossFade().into(((BookItemViewHolder) holder).coverImageView);
                ((BookItemViewHolder) holder).titleTextView.setText(book.getTitle() + (TextUtils.isEmpty(book.getAlt_title()) ? "" : " (" + book.getAlt_title() + ")"));

                StringBuilder sb = new StringBuilder();
                for (String author : book.getAuthor())
                {
                    sb.append(author).append(" ");
                }
                ((BookItemViewHolder) holder).authorTextView.setText(sb.toString());
                ((BookItemViewHolder) holder).summaryTextView.setText(book.getSummary().replace("\n", ""));
            }
        }
    }

    @Override
    public int getItemCount()
    {
        return bookList.size();
    }

    public class BookItemViewHolder extends RecyclerView.ViewHolder
    {
        ImageView coverImageView;
        TextView titleTextView;
        TextView authorTextView;
        TextView summaryTextView;

        public BookItemViewHolder(View itemView)
        {
            super(itemView);
            coverImageView = (ImageView) itemView.findViewById(R.id.iv_cover);
            titleTextView = (TextView) itemView.findViewById(R.id.tv_title);
            authorTextView = (TextView) itemView.findViewById(R.id.tv_author);
            summaryTextView = (TextView) itemView.findViewById(R.id.tv_summary);
        }
    }
}
