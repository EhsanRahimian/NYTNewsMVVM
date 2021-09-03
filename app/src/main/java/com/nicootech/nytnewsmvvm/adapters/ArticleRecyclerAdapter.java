package com.nicootech.nytnewsmvvm.adapters;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.nicootech.nytnewsmvvm.R;
import com.nicootech.nytnewsmvvm.models.Docs;
import com.nicootech.nytnewsmvvm.utils.Constants;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class ArticleRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int ARTICLE_TYPE = 1;
    private static final int LOADING_TYPE = 2;
    private static final int CATEGORY_TYPE = 3;

    private List<Docs> mDocs;
    private OnArticleListener mOnArticleListener;

    public ArticleRecyclerAdapter(OnArticleListener onArticleListener) {
        mOnArticleListener = onArticleListener;
        mDocs = new ArrayList<>();
    }

    @NonNull
    @NotNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup viewGroup, int i) {

        View view = null;

        switch (i){
            case ARTICLE_TYPE:{
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_article_list_item, viewGroup,false);
                return new ArticleViewHolder(view, mOnArticleListener);
            }
            case CATEGORY_TYPE:{
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_category_list_item, viewGroup,false);
                return new CategoryViewHolder(view, mOnArticleListener);
            }
            case LOADING_TYPE:{
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_loading_list_item, viewGroup,false);
                return new LoadingViewHolder(view);
            }
            default:{
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_article_list_item, viewGroup,false);
                return new ArticleViewHolder(view, mOnArticleListener);
            }

        }

    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerView.ViewHolder viewHolder, int i) {

        int itemViewType = getItemViewType(i);

        if(itemViewType == ARTICLE_TYPE){

        String imageUrl = "";
        if(mDocs.get(i).getMultimedia().size() >0){
            imageUrl = mDocs.get(i).getMultimedia().get(1).getUrl();
        }
        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.drawable.ic_nocover);
        Glide.with(viewHolder.itemView.getContext())
                .setDefaultRequestOptions(requestOptions)
                .load(imageUrl)
                .into(((ArticleViewHolder)viewHolder).image);
        //////////////////////////////////////////////////////////////////


//            Docs doc = mDocs.get(i);
//            String articleImageUrl = "";
//            if(doc.getMultimedia().size() > 0){
//                articleImageUrl = doc.getMultimedia().get(0).getUrl();
//            }
//            RequestOptions options = new RequestOptions()
//                    .placeholder(R.drawable.ic_launcher_background);
//
//            Glide.with(viewHolder.itemView.getContext())
//                    .setDefaultRequestOptions(options)
//                    .load(Uri.parse(articleImageUrl))
//                    .placeholder(R.drawable.ic_nocover)
//                    .into(((ArticleViewHolder)viewHolder).image);

            ((ArticleViewHolder)viewHolder).headline.setText(mDocs.get(i).getHeadline().getMain());
            ((ArticleViewHolder)viewHolder).date.setText(mDocs.get(i).getPub_date().substring(0,mDocs.get(i).getPub_date().indexOf('T')));

        }
        else if(itemViewType == CATEGORY_TYPE){
            ((CategoryViewHolder)viewHolder).categoryTitle.setText(mDocs.get(i).getPub_date());
        }

    }

    @Override
    public int getItemViewType(int position) {
        if(Objects.equals(mDocs.get(position).get_id(), "")){
            return CATEGORY_TYPE;
        }
        else if(mDocs.get(position).getPub_date().equals("LOADING...")){
            return LOADING_TYPE;
        }
        /////
        else if(position == mDocs.size()-1
                && position != 0
                && !mDocs.get(position).getPub_date().equals("EXHAUSTED...")
        /////
        ){
            return LOADING_TYPE;
         }

        else{
            return ARTICLE_TYPE;
        }
    }
    @Override
    public int getItemCount() {
        if(mDocs != null){
            return mDocs.size();
        }
        return 0;

    }

    public void displayLoading(){
        if(!isLoading()){
            Docs newDoc = new Docs();
            newDoc.setPub_date("LOADING...");
            List<Docs>loadingList = new ArrayList<>();
            loadingList.add(newDoc);
            mDocs = loadingList;
            notifyDataSetChanged();
        }
    }

    private boolean isLoading(){
        if(mDocs != null){
            if(mDocs.size()>0){
                if(mDocs.get(mDocs.size()-1).getPub_date().equals("LOADING...")){
                    return true;
                }
            }
        }
        return false;
    }

    public void setDocs(List<Docs>docs){
        mDocs = docs;
        notifyDataSetChanged();
    }

    public void displaySearchCategories(){
        List<Docs> categories = new ArrayList<>();
        for(int i = 0; i < Constants.DEFAULT_SEARCH_CATEGORIES.length; i++){
            Docs doc = new Docs();
            doc.setPub_date(Constants.DEFAULT_SEARCH_CATEGORIES[i]);
            doc.set_id("");
            categories.add(doc);
        }
        mDocs = categories;
        notifyDataSetChanged();
    }
    ////////////////////////////////////////////////////////////////////////
    public Docs getSelectedArticle(int position){
        if(mDocs != null){
            if(mDocs.size() > 0){
                return mDocs.get(position);
            }
        }
        return null;
    }

}