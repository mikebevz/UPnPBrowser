/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mikebevz.upnp.mediaserver1;

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
import java.util.regex.Pattern;

/**
 *
 * @author mikebevz
 */
public class ContainerListAdapter extends BaseAdapter {

    private List<Entity> containers;
    private LayoutInflater mInflater;
    private final Bitmap icon_container;
    private final Bitmap icon_item;
    public static final String CONTAINER_CCLASS = "object.container";
    public static final String ITEM_CCLASS = "object.item";
    //private final Bitmap icon_music;
    //private final Bitmap icon_mp3;
    
    private final Pattern isContainer;
    private final Pattern isItem;
    

    public ContainerListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);

        containers = new ArrayList<Entity>();
        
        icon_container = BitmapFactory.decodeResource(context.getResources(), R.drawable.folder);
        icon_item = BitmapFactory.decodeResource(context.getResources(), R.drawable.file);
        //icon_music = BitmapFactory.decodeResource(context.getResources(), R.drawable.file_music);
        //icon_mp3 = BitmapFactory.decodeResource(context.getResources(), R.drawable.file_mp3);
        
        
        isContainer = Pattern.compile(CONTAINER_CCLASS);
        isItem = Pattern.compile(ITEM_CCLASS);
        
    }
    /**
     * @return the containers
     */
    public List<Entity> getContainers() {
        return containers;
    }

    /**
     * @param containers the containers to set
     */
    public void setContainers(List<Entity> containers) {
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
        
        
        
        
        if (isContainer.matcher(this.getContainers().get(position).getCclass()).find()) {
            Container container = (Container) this.getContainers().get(position);    
            holder.text.setText(container.getTitle());
            
            if (!container.getIcon().equals("")) {
                holder.icon.setImageBitmap(getImageBitmap(this.getContainers().get(position).getIcon()));
            } else {
                holder.icon.setImageBitmap(icon_container);
            }

        } else if (isItem.matcher(this.getContainers().get(position).getCclass()).find()) {
            
            Item item = (Item) this.getContainers().get(position);
            holder.text.setText(item.getTitle());
            
            if (!item.getAlbumArtUri().equals("")) {
                holder.icon.setImageBitmap(getImageBitmap(item.getAlbumArtUri()));
            } else {
                holder.icon.setImageBitmap(icon_item);
            }
        }
        
        

        return cView;
    }

    private Bitmap getImageBitmap(String url) {
        Bitmap bm = null;
        try {
            URL aURL = new URL(url);
            URLConnection conn = aURL.openConnection();
            conn.connect();
            InputStream is = conn.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is, 8 * 1024);
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
