package com.cs5520.numad20s_rouniyin.ui.linkcollection;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cs5520.numad20s_rouniyin.LinkItem;
import com.cs5520.numad20s_rouniyin.R;

import java.util.List;

public class LinkListAdapter extends RecyclerView.Adapter<LinkListAdapter.ViewHolder> {
    private int linkItemLayout;
    private List<LinkItem> linkList;

    public LinkListAdapter(int layoutId){
        linkItemLayout = layoutId;
    }

    public void setLinkList(List<LinkItem> links){
        linkList = links;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(
                parent.getContext()).inflate(linkItemLayout, parent, false);
        ViewHolder myViewHolder = new ViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TextView item = holder.item;
        item.setText(linkList.get(position).toString());

    }

    @Override
    public int getItemCount() {
        return linkList== null? 0:linkList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView item;
        ViewHolder(View itemView) {
            super(itemView);
            item = itemView.findViewById(R.id.link_row);
        }
    }
}
