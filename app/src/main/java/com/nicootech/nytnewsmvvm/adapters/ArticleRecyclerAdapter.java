package com.nicootech.nytnewsmvvm.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.nicootech.nytnewsmvvm.R;
import com.nicootech.nytnewsmvvm.models.Docs;
import org.jetbrains.annotations.NotNull;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class ArticleRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Docs> mDocs;
    private OnArticleListener mOnArticleListener;

    public ArticleRecyclerAdapter(OnArticleListener mOnArticleListener) {
        this.mOnArticleListener = mOnArticleListener;
    }

    @NonNull
    @NotNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_article_list_item, viewGroup,false);
        return new ArticleViewHolder(view, mOnArticleListener);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerView.ViewHolder viewHolder, int i) {
        String imageUrl = "";
        if(mDocs.get(i).getMultimedia().size() >0){
            imageUrl = mDocs.get(i).getMultimedia().get(0).getUrl();
        }
                RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.drawable.ic_nocover);
        Glide.with(viewHolder.itemView.getContext())
                .setDefaultRequestOptions(requestOptions)
                .load(imageUrl)
                .into(((ArticleViewHolder)viewHolder).image);

//        Docs doc = mDocs.get(i);
//        String articleImageUrl = "";
//        if(doc.getMultimedia().size() >0){
//            articleImageUrl = doc.getMultimedia().get(0).getUrl();
//        }
//
//        Glide.with(viewHolder.itemView.getContext())
//                .load(Uri.parse(articleImageUrl))
//                .placeholder(R.drawable.ic_nocover)
//                .into(((ArticleViewHolder)viewHolder).image);

        ((ArticleViewHolder)viewHolder).headline.setText(mDocs.get(i).getHeadline().getMain());
        ((ArticleViewHolder)viewHolder).date.setText(mDocs.get(i).getPub_date().substring(0,mDocs.get(i).getPub_date().indexOf('T')));
    }

    @Override
    public int getItemCount() {
        if(mDocs != null){
            return mDocs.size();
        }
        return 0;

    }

    public void setDocs(List<Docs>docs){
        mDocs = docs;
        notifyDataSetChanged();
    }
}
