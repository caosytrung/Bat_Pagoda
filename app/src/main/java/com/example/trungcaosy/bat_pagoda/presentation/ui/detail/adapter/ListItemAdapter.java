package com.example.trungcaosy.bat_pagoda.presentation.ui.detail.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.trungcaosy.bat_pagoda.R;
import com.example.trungcaosy.bat_pagoda.presentation.ui.detail.model.MyModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

public class ListItemAdapter extends RecyclerView.Adapter<ListItemAdapter.ViewHoder> {
    private List<MyModel> myModels;
    private Context context;

    public ListItemAdapter(Context context){
        this.context = context;
        myModels = new ArrayList<>();
        myModels.add(new MyModel());
        myModels.add(new MyModel());
        myModels.add(new MyModel());
        myModels.add(new MyModel());
        myModels.add(new MyModel());

    }

    @NonNull
    @Override
    public ListItemAdapter.ViewHoder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        return new ViewHoder(inflater.inflate(R.layout.item_model_view, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ListItemAdapter.ViewHoder viewHoder, int i) {

    }

    @Override
    public int getItemCount() {
        return myModels.size();
    }

    public class ViewHoder extends RecyclerView.ViewHolder {
        public ViewHoder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
