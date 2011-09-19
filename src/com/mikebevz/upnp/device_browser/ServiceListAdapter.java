/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mikebevz.upnp.device_browser;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.mikebevz.upnp.R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.cybergarage.upnp.Service;

/**
 *
 * @author mikebevz
 */
public class ServiceListAdapter extends BaseAdapter {
    
    private List<Service> services;

    private LayoutInflater mInflater;
    
    public ServiceListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        
        services = new ArrayList<Service>();
    }

    /**
     * @return the services
     */
    public List<Service> getServices() {
        return services;
    }

    /**
     * @param services the services to set
     */
    public void setServices(List<Service> service) {
        this.services = service;
    }

    public int getCount() {
        return this.getServices().size();
    }

    public Object getItem(int position) {
        return this.getServices().get(position);
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
        
        List<String> list = Arrays.asList(this.getServices().get(position).getServiceID().split(":"));
        Collections.reverse(list);
        
        holder.text.setText(list.toArray()[0].toString());
        holder.description.setText(this.getServices().get(position).getServiceID());
        
        return cView;
    }
    
    static class ViewHolder {
        TextView text;
        TextView description;
        ImageView icon;
    }
    
}
