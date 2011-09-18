/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mikebevz.upnp.mediaserver.content_directory;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.mikebevz.upnp.R;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mikebevz
 */
public class ContainerListAdapter extends BaseAdapter {
    
    private List<Container> containers;

    private LayoutInflater mInflater;
    
    public ContainerListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        
        containers = new ArrayList<Container>();
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
            holder.text = (TextView)cView.findViewById(R.id.text);
            holder.description = (TextView)cView.findViewById(R.id.description);
            
            cView.setTag(holder);
        } else {
            holder = (ViewHolder) cView.getTag();
        }
        
        holder.text.setText(this.getContainers().get(position).getTitle());
        //holder.description.setText(this.getContainers().get(position).getDescription());
        
        return cView;
    }
    
    static class ViewHolder {
        TextView text;
        TextView description;
        ImageView icon;
    }
    
}
