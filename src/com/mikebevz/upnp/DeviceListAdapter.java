/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mikebevz.upnp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import org.cybergarage.upnp.Device;
import org.cybergarage.upnp.DeviceList;

/**
 *
 * @author mikebevz
 */
public class DeviceListAdapter extends BaseAdapter {
    
    private LayoutInflater mInflater;
    
    private DeviceList data;
    
    public DeviceListAdapter(Context context) {
        
        mInflater = LayoutInflater.from(context);
        
        data = new DeviceList();
        //Device dev1 = new Device();
        //dev1.setFriendlyName("Test1");
        //dev1.setLocation("http://location.com");
        //data.add(dev1);
    }

    public int getCount() {
        return data.size();
    }

    public Object getItem(int pos) {
        return data.get(pos);
    }

    public long getItemId(int pos) {
        return pos;
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
        
        holder.text.setText(data.getDevice(position).getFriendlyName());
        holder.description.setText(data.getDevice(position).getLocation());
        
        
        return cView;
    }
    
    static class ViewHolder {
        TextView text;
        TextView description;
        ImageView icon;
    }
    
    
    public void setDeviceList(DeviceList deviceList) {
        this.data = deviceList;
    }
    
    public void addDevice(Device dev) {
        data.add(dev);
        notifyDataSetChanged();
        
    }
    
    public void deleteDevice(Device dev){
        this.data.remove(dev);
        this.notifyDataSetChanged();
    }
}
