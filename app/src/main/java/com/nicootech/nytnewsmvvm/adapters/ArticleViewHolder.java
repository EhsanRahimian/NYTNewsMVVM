package com.nicootech.nytnewsmvvm.adapters;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.nicootech.nytnewsmvvm.R;
import com.nicootech.nytnewsmvvm.models.Docs;

import org.jetbrains.annotations.NotNull;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

public class ArticleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView headline, date;
    AppCompatImageView image;
    OnArticleListener onArticleListener;

    public ArticleViewHolder(@NonNull @NotNull View itemView, OnArticleListener onArticleListener) {
        super(itemView);

        this.onArticleListener = onArticleListener;

        headline = itemView.findViewById(R.id.article_headline);
        date = itemView.findViewById(R.id.article_date);
        image = itemView.findViewById(R.id.article_image);

        itemView.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        onArticleListener.onArticleClick(getAbsoluteAdapterPosition());



    }
}
