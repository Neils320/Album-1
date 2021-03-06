package com.album.ui.adapter;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.album.Album;
import com.album.AlbumConfig;
import com.album.R;
import com.album.model.FinderModel;
import com.album.ui.widget.AlbumImageView;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * by y on 15/08/2017.
 */

public class ListPopupWindowAdapter extends BaseAdapter {
    private List<FinderModel> list = null;
    private AlbumConfig albumConfig = null;

    public ListPopupWindowAdapter(List<FinderModel> finderModel) {
        this.list = finderModel;
        albumConfig = Album.getInstance().getConfig();
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_finder, parent, false);
        }
        FrameLayout frameLayout = (FrameLayout) convertView.findViewById(R.id.iv_album_finder_icon);
        AppCompatTextView appCompatTextView = (AppCompatTextView) convertView.findViewById(R.id.tv_album_finder_name);
        appCompatTextView.setTextColor(ContextCompat.getColor(parent.getContext(), albumConfig.getAlbumListPopupItemTextColor()));
        ImageView imageView;
        if (albumConfig.isFrescoImageLoader()) {
            imageView = new SimpleDraweeView(frameLayout.getContext());
        } else {
            imageView = new AlbumImageView(frameLayout.getContext());
        }
        frameLayout.addView(imageView);
        if (list != null && list.get(position) != null) {
            FinderModel finderModel = list.get(position);
            appCompatTextView.setText(String.format("%s(%s)", finderModel.getDirName(), String.valueOf(finderModel.getCount())));
            Album.getInstance().getAlbumImageLoader().displayAlbumThumbnails(imageView, finderModel);
        }
        return convertView;
    }

    public FinderModel getFinder(int position) {
        if (list != null) {
            return list.get(position);
        }
        return null;
    }
}
