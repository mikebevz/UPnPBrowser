/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mikebevz.upnp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
    private Bitmap diskIcon;
    private final Bitmap windowsIcon;
    private final Bitmap appleIcon;
    private final Bitmap routerIcon;
    private static final String WINDOWS_MEDIA_PLAYER_SHARING = "Windows Media Player Sharing";
    private static final String TVMOBILI_MEDIA_SERVER = "TVMOBiLi | MediaServer";
    private static final String INTERNET_GATEWAY_DEVICE = "Internet gateway device";
    private static final String INTERNET_CONNECTION_SHARING = "Internet Connection Sharing";
    private static final String KIES_SYNC_SERVER = "Kies Sync Server";
    
    private static final String INTERNET_GATEWAY_DEVICE_TYPE = "urn:schemas-upnp-org:device:InternetGatewayDevice:1";
    private static final String INTERNET_GATEWAY_AP_TYPE = "urn:schemas-wifialliance-org:device:WFADevice:1";
    
    
    
    /**
     * 
     * @param context
     */
    public DeviceListAdapter(Context context) {
        
        mInflater = LayoutInflater.from(context);
        
        data = new DeviceList();
        diskIcon = BitmapFactory.decodeResource(context.getResources(), R.drawable.disk);
        windowsIcon = BitmapFactory.decodeResource(context.getResources(), R.drawable.windows_device);
        appleIcon = BitmapFactory.decodeResource(context.getResources(), R.drawable.apple_device);
        routerIcon = BitmapFactory.decodeResource(context.getResources(), R.drawable.router_device);
        
        
    }

    /**
     * 
     * @return
     */
    public int getCount() {
        return data.size();
    }

    /**
     * 
     * @param pos
     * @return
     */
    public Object getItem(int pos) {
        return data.get(pos);
    }

    /**
     * 
     * @param pos
     * @return
     */
    public long getItemId(int pos) {
        return pos;
    }

    /**
     * 
     * @param position
     * @param cView
     * @param parent
     * @return
     */
    public View getView(int position, View cView, ViewGroup parent) {
        
        ViewHolder holder;
        
        if (cView == null) {
            cView = mInflater.inflate(R.layout.list_item_icon_text, null);
            
            holder = new ViewHolder();
            holder.text = (TextView)cView.findViewById(R.id.text);
            holder.description = (TextView)cView.findViewById(R.id.description);
            holder.icon = (ImageView)cView.findViewById(R.id.icon);
            
            cView.setTag(holder);
        } else {
            holder = (ViewHolder) cView.getTag();
        }
        
        holder.text.setText(data.getDevice(position).getFriendlyName());
        holder.description.setText(data.getDevice(position).getLocation());
        
        holder.icon.setImageBitmap(getIconForDevice(data.getDevice(position)));
        
        return cView;
    }
    
    private Bitmap getIconForDevice(Device device) {
        //TODO implement different icons 
        String type = device.getDeviceType();
        //String location = device.getLocation();
        String modelName = device.getModelName();
        
        if (modelName.equalsIgnoreCase(WINDOWS_MEDIA_PLAYER_SHARING)) {
            return windowsIcon;
        }
        
        if (modelName.equalsIgnoreCase(TVMOBILI_MEDIA_SERVER)) {
            //TODO make icon for TVMobili Devices
        }
        
        if (modelName.equalsIgnoreCase(KIES_SYNC_SERVER)) {
            //TODO make icon for Sync Servers
        }
        
        if (modelName.equalsIgnoreCase(INTERNET_GATEWAY_DEVICE) 
            || modelName.equalsIgnoreCase(INTERNET_CONNECTION_SHARING)
            || type.equalsIgnoreCase(INTERNET_GATEWAY_DEVICE_TYPE)
            || type.equalsIgnoreCase(INTERNET_GATEWAY_AP_TYPE)) {
            return routerIcon;
        }
        
        return diskIcon; // Default
    }
    
    static class ViewHolder {
        TextView text;
        TextView description;
        ImageView icon;
    }
    
    
    /**
     * 
     * @param deviceList
     */
    public void setDeviceList(DeviceList deviceList) {
        this.data = deviceList;
    }
    
    /**
     * 
     * @param dev
     */
    public void addDevice(Device dev) {
        data.add(dev);
        notifyDataSetChanged();
        
    }
    
    /**
     * 
     * @param dev
     */
    public void deleteDevice(Device dev){
        this.data.remove(dev);
        this.notifyDataSetChanged();
    }
}
