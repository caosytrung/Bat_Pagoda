package com.example.trungcaosy.bat_pagoda.presentation.ui.detail.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.trungcaosy.bat_pagoda.R;
import com.example.trungcaosy.bat_pagoda.presentation.ui.main.adapter.ImagePagerAdapter;
import com.example.trungcaosy.bat_pagoda.presentation.ui.main.adapter.RecyclingPagerAdapter;
import com.example.trungcaosy.bat_pagoda.utils.Utils;

import java.util.List;

public class DetailImageAdapter extends RecyclingPagerAdapter {

    private Context context;
    private List<String> imageIdList;

    private int size;
    private boolean isInfiniteLoop;

    public DetailImageAdapter(Context context, List<String> imageIdList) {
        this.context = context;
        this.imageIdList = imageIdList;
        this.size = imageIdList.size();
        isInfiniteLoop = false;
    }

    @Override
    public int getCount() {
        // Infinite loop
        return isInfiniteLoop ? Integer.MAX_VALUE : imageIdList.size();
    }

    /**
     * get really position
     *
     * @param position
     * @return
     */
    private int getPosition(int position) {
        return isInfiniteLoop ? position % size : position;
    }

    @Override
    public View getView(int position, View view, ViewGroup container) {
        DetailImageAdapter.ViewHolder holder;
        if (view == null) {
            holder = new DetailImageAdapter.ViewHolder();
            view = holder.imageView = new ImageView(context);
            ((ImageView) view).setScaleType(ImageView.ScaleType.CENTER_CROP);
         //   view.setTag(holder);
        } else {
            holder = (DetailImageAdapter.ViewHolder) view.getTag();
        }

        if (Utils.isNotStringEmpty(imageIdList.get(position))) {
            Glide.with(context).load(imageIdList.get(position)).error(R.drawable.default_image).
                    placeholder(R.drawable.default_image).into(holder.imageView);
        }
//        holder.imageView.setImageResource(imageIdList.get(getPosition(position)));
        return view;
    }

    private static class ViewHolder {

        ImageView imageView;
    }

    /**
     * @return the isInfiniteLoop
     */
    public boolean isInfiniteLoop() {
        return isInfiniteLoop;
    }

    /**
     * @param isInfiniteLoop the isInfiniteLoop to set
     */
    public DetailImageAdapter setInfiniteLoop(boolean isInfiniteLoop) {
        this.isInfiniteLoop = isInfiniteLoop;
        return this;
    }
}
