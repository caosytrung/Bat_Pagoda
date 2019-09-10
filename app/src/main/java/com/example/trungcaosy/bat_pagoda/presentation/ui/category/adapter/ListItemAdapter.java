package com.example.trungcaosy.bat_pagoda.presentation.ui.category.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.trungcaosy.bat_pagoda.R;
import com.example.trungcaosy.bat_pagoda.data.response.NodeData;
import com.example.trungcaosy.bat_pagoda.presentation.callback_click.RecyclerViewClick;
import com.example.trungcaosy.bat_pagoda.presentation.ui.category.model.MyModel;
import com.example.trungcaosy.bat_pagoda.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListItemAdapter extends RecyclerView.Adapter<ListItemAdapter.ViewHoder> {
    private List<NodeData> nodeDataList;
    private Context context;
    private RecyclerViewClick<NodeData> recyclerViewClick;

    public ListItemAdapter(Context context, List<NodeData> nodeDataList, RecyclerViewClick<NodeData> recyclerViewClick){
        this.context = context;
        this.nodeDataList = nodeDataList;
        this.recyclerViewClick = recyclerViewClick;
    }

    @NonNull
    @Override
    public ListItemAdapter.ViewHoder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        return new ViewHoder(inflater.inflate(R.layout.item_model_view, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ListItemAdapter.ViewHoder viewHoder, int i) {
        NodeData nodeData = nodeDataList.get(i);
        viewHoder.tvDisplay.setText(nodeData.name);
        viewHoder.lnContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerViewClick.onClick(view, nodeDataList.get(i));
            }
        });

        if (Utils.isNotStringEmpty(nodeData.imageUrl)){
            Glide.with(viewHoder.tvDisplay.getContext()).load(nodeData.imageUrl).
                    placeholder(R.drawable.default_image).
                    into(viewHoder.ivThumbnail);
        }
    }

    @Override
    public int getItemCount() {
        return nodeDataList.size();
    }

    public class ViewHoder extends RecyclerView.ViewHolder {
        @BindView(R.id.lnContainer)
        LinearLayout lnContainer;

        @BindView(R.id.ivThumbnail)
        ImageView ivThumbnail;

        @BindView(R.id.tvDisplay)
        TextView tvDisplay;

        public ViewHoder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
