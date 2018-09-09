package com.techexchange.mobileapps.application1;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.AbsListView.LayoutParams;

import java.util.ArrayList;

public class GridViewAdapter extends BaseAdapter {
    private ArrayList<ImageView> mImages = null;
    private int colWidth;
    private int colHeight;
    private Context context;

    public GridViewAdapter(ArrayList<ImageView> mImages, int colWidth, int colHeight, Context context) {
        this.mImages = mImages;
        this.colWidth = colWidth;
        this.colHeight = colHeight;
        this.context = context;
    }

    @Override
    public int getCount() {
        return mImages.size();
    }

    @Override
    public Object getItem(int position) {return (Object) mImages.get(position);}

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView image;

        if(convertView == null)
           image = mImages.get(position);
        else
            image = (ImageView) convertView;

        LayoutParams params = new LayoutParams(colWidth, colHeight);
        image.setLayoutParams(params);

        return image;
    }
}
