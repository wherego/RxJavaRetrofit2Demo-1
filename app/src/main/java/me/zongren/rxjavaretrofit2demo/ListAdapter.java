package me.zongren.rxjavaretrofit2demo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zongren on 2017/4/17.
 * All right reserved by 正奇晟业（北京）科技有限公司
 */

public class ListAdapter extends RecyclerView.Adapter<ItemViewHolder> {
    public List<ArticleModel> articleList;
    public View.OnClickListener onClickListener;

    public ListAdapter() {
        articleList = new ArrayList<>();
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        ArticleModel model = articleList.get(position);
        holder.titleTextView.setText(model.title);
        holder.subtitleTextView.setText(model.subtitle);
        holder.itemView.setOnClickListener(onClickListener);
    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }
}
