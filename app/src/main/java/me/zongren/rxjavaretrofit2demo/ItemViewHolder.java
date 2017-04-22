package me.zongren.rxjavaretrofit2demo;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by zongren on 2017/4/17.
 * All right reserved by 正奇晟业（北京）科技有限公司
 */

public class ItemViewHolder extends RecyclerView.ViewHolder {
    public TextView titleTextView;
    public TextView subtitleTextView;

    public ItemViewHolder(View itemView) {
        super(itemView);

        titleTextView = (TextView)itemView.findViewById(R.id.titleTextView);
        subtitleTextView = (TextView)itemView.findViewById(R.id.subtitleTextView);
    }
}
