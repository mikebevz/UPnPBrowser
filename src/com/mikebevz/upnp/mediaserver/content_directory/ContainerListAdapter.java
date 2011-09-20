/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mikebevz.upnp.mediaserver.content_directory;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.mikebevz.upnp.R;


import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mikebevz
 */
public class ContainerListAdapter extends BaseAdapter {

    private List<Container> containers;
    private LayoutInflater mInflater;
    private final Bitmap icon;

    public ContainerListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);

        containers = new ArrayList<Container>();
        icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.folder);
    }

    /**
     * @return the containers
     */
    public List<Container> getContainers() {
        return containers;
    }

    /**
     * @param containers the containers to set
     */
    public void setContainers(List<Container> containers) {
        this.containers = containers;
    }

    public int getCount() {
        return this.getContainers().size();
    }

    public Object getItem(int position) {
        return this.getContainers().get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View cView, ViewGroup parent) {

        ViewHolder holder;

        if (cView == null) {
            cView = mInflater.inflate(R.layout.list_item_icon_text, null);

            holder = new ViewHolder();
            holder.text = (TextView) cView.findViewById(R.id.text);
            holder.description = (TextView) cView.findViewById(R.id.description);
            holder.icon = (ImageView) cView.findViewById(R.id.icon);
            cView.setTag(holder);
        } else {
            holder = (ViewHolder) cView.getTag();
        }

        holder.text.setText(this.getContainers().get(position).getTitle());
        holder.icon.setImageBitmap(icon);

        if (!this.getContainers().get(position).getIcon().equals("")) {

            holder.icon.setImageBitmap(getImageBitmap(this.getContainers().get(position).getIcon()));
        }
        //holder.description.setText(this.getContainers().get(position).getDescription());

        return cView;
    }

    private Bitmap getImageBitmap(String url) {
        Bitmap bm = null;
        try {
            URL aURL = new URL(url);
            URLConnection conn = aURL.openConnection();
            conn.connect();
            InputStream is = conn.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is, 8*1024);
            bm = BitmapFactory.decodeStream(bis);
            bis.close();
            is.close();
        } catch (IOException e) {
            Log.e("ImageBitmap", "Error getting bitmap", e);
        }
        return bm;
    }

    static class ViewHolder {

        TextView text;
        TextView description;
        ImageView icon;
    }
}
