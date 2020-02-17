package com.khoa.datetimepicker;

import android.content.Context;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;

public class SelectableScrolling extends RecyclerView.OnScrollListener {
    private int firstPosition = 0;
    public static final int primaryPosition = 2;
    private int adapterPosition;
    private RecyclerView.SmoothScroller smoothScroller;
    private Context context;
    private RecyclerView mRecyclerView;
    private OnItemChangeListener listener;

    public SelectableScrolling(RecyclerView mRecyclerView, OnItemChangeListener listener) {
        this.context = mRecyclerView.getContext();
        this.listener = listener;
        this.mRecyclerView = mRecyclerView;
        smoothScroller = new
                LinearSmoothScroller(context) {
                    @Override protected int getVerticalSnapPreference() {
                        return LinearSmoothScroller.SNAP_TO_START;
                    }
                };
    }


    @Override
    public void onScrolled(@NonNull RecyclerView mRecyclerView, int dx, int dy) {
        super.onScrolled(mRecyclerView, dx, dy);

        boolean scrollUp = dy > 0;
        View topView = mRecyclerView.getChildAt(0);
        int distance = Math.abs(topView.getTop());
        if(distance >= 50 && distance<=80){
            if(scrollUp) {
                setSelectedItem(primaryPosition + 1);
            } else {
                setSelectedItem(primaryPosition);
            }
        }
    }

    public void setSelectedItem(int position){
        MyViewHolder viewHolder = (MyViewHolder) mRecyclerView.getChildViewHolder(mRecyclerView.getChildAt(position));
        viewHolder.setSelected();

        adapterPosition = viewHolder.getAdapterPosition();
        if(listener!=null){
            listener.onItemChanged(adapterPosition);
        }

        firstPosition = viewHolder.getAdapterPosition()-2;

        MyViewHolder viewHolder1 = (MyViewHolder) mRecyclerView.getChildViewHolder(mRecyclerView.getChildAt(position-1));
        viewHolder1.setNormal();

        MyViewHolder viewHolder2 = (MyViewHolder) mRecyclerView.getChildViewHolder(mRecyclerView.getChildAt(position-2));
        viewHolder2.setNormal();

        MyViewHolder viewHolder3 = (MyViewHolder) mRecyclerView.getChildViewHolder(mRecyclerView.getChildAt(position+1));
        viewHolder3.setNormal();

        MyViewHolder viewHolder4 = (MyViewHolder) mRecyclerView.getChildViewHolder(mRecyclerView.getChildAt(position+2));
        viewHolder4.setNormal();
    }

    @Override
    public void onScrollStateChanged(@NonNull RecyclerView mRecyclerView, int newState) {
        super.onScrollStateChanged(mRecyclerView, newState);
        if(newState == RecyclerView.SCROLL_STATE_IDLE){
            smoothScroller.setTargetPosition(firstPosition);
            mRecyclerView.getLayoutManager().startSmoothScroll(smoothScroller);
        }
    }

    public int getSelectedAdapterPosition(){
        return adapterPosition;
    }
}
