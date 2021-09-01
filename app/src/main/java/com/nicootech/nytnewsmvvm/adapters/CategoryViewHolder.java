package com.nicootech.nytnewsmvvm.adapters;

import android.view.View;
import android.widget.TextView;
import com.nicootech.nytnewsmvvm.R;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView categoryTitle;
    OnArticleListener listener;

    public CategoryViewHolder(@NonNull View itemView, OnArticleListener listener) {
        super(itemView);

        this.listener = listener;
        categoryTitle = itemView.findViewById(R.id.category_title);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        listener.onCategoryClick(categoryTitle.getText().toString());

    }
}
