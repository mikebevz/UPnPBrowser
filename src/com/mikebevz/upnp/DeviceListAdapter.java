/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mikebevz.upnp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import org.cybergarage.upnp.ControlPoint;
import org.cybergarage.upnp.Device;
import org.cybergarage.upnp.DeviceList;
import org.cybergarage.upnp.device.DeviceChangeListener;
import org.cybergarage.upnp.device.NotifyListener;
import org.cybergarage.upnp.ssdp.SSDPPacket;

/**
 *
 * @author mikebevz
 */
public class DeviceListAdapter extends BaseAdapter implements DeviceChangeListener, NotifyListener {

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
    private final Activity context;
    ControlPoint ctrlPoint = null;
    Boolean controlPointStatus = false; // false = stopped, true = running
    ListView devicesList;
    UpnpBrowserApp app;
    private final ProgressDialog dialog;
    private Resources resources;

    /**
     * 
     * @param context
     */
    public DeviceListAdapter(Activity context) {
        this.context = context;

        mInflater = LayoutInflater.from(context);

        data = new DeviceList();

        diskIcon = BitmapFactory.decodeResource(context.getResources(), R.drawable.disk);
        windowsIcon = BitmapFactory.decodeResource(context.getResources(), R.drawable.windows_device);
        appleIcon = BitmapFactory.decodeResource(context.getResources(), R.drawable.apple_device);
        routerIcon = BitmapFactory.decodeResource(context.getResources(), R.drawable.router_device);

        app = (UpnpBrowserApp) context.getApplication();

        Resources resource = context.getResources();

        dialog = ProgressDialog.show(context, resource.getString(R.string.loading), resource.getString(R.string.loading_message), true, true);

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
            holder.text = (TextView) cView.findViewById(R.id.text);
            holder.description = (TextView) cView.findViewById(R.id.description);
            holder.icon = (ImageView) cView.findViewById(R.id.icon);

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

    public DeviceList getDeviceList() {
        return data;
    }

    public boolean getControlPointStatus() {
        return controlPointStatus;
    }

    public void cancel() {

        dialog.dismiss();
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
        app.setDeviceList(data);

    }

    /**
     * 
     * @param dev
     */
    public void addDevice(Device dev) {
        Log.d("DeviceNotify", "Device Added " + dev.getFriendlyName());
        data.add(dev);
        notifyDataSetChanged();

        dialog.dismiss();

    }

    /**
     * 
     * @param dev
     */
    public void deleteDevice(Device dev) {
        Log.d("DeviceNotify", "Device Deleted " + dev.getFriendlyName());
        data.remove(dev);
        notifyDataSetChanged();
    }

    /**
     * 
     * @param device
     */
    public void deviceAdded(final Device device) {

        Runnable task = new Runnable() {

            public void run() {

                addDevice(device);

            }
        };

        context.runOnUiThread(task);

    }

    /**
     * 
     * @param device
     */
    public void deviceRemoved(final Device device) {

        Runnable task = new Runnable() {

            public void run() {

                deleteDevice(device);
            }
        };

        context.runOnUiThread(task);

    }

    /**
     * 
     * @param ssdpp
     */
    public void deviceNotifyReceived(final SSDPPacket ssdpp) {

        Runnable task = new Runnable() {

            public void run() {

                setDeviceList(ctrlPoint.getDeviceList());
                Log.d("DeviceNotify", "Device Notify Received: " + ssdpp.getUSN());
                notifyDataSetChanged();
            }
        };

        context.runOnUiThread(task);

    }

    public void startControlPoint() throws WifiNotConnectedException, WifiDisabledException {
        app = (UpnpBrowserApp) context.getApplication();
        resources = context.getResources();

        if (!app.isWifiEnabled()) {
            throw new WifiDisabledException(resources.getString(R.string.wifi_disabled));
        }
        
        if (!app.IsWifiConnected()) {
            throw new WifiNotConnectedException(resources.getString(R.string.wifi_isnt_connected));
        } 

        //Log.d("ControlPoint", "Start ControlPoint");
        if (ctrlPoint == null) {
            Log.d("ControlPoint", "Start - Create New ControlPoint");
            ctrlPoint = new ControlPoint();
            ctrlPoint.addNotifyListener(this);
            ctrlPoint.addDeviceChangeListener(this);

        }

        if (controlPointStatus == false) {
            Log.d("ControlPoint", "Start - Starting ControlPoint");
            ctrlPoint.start();

            this.context.setProgressBarIndeterminate(true);
            this.context.setProgressBarIndeterminateVisibility(true);

            setDeviceList(ctrlPoint.getDeviceList());

            controlPointStatus = true;
        }
        //}
    }

    public void resumeControlPoint() throws Exception {
        Log.d("ControlPoint", "Resuming ControlPoint");
        this.startControlPoint();
        ctrlPoint.removeExpiredDevices();

        /**
         * 
        if (!app.IsWifiConnected()) {
        throw new Exception(context.getResources().getString(R.string.wifi_isnt_connected));
        } else {
        if (controlPointStatus == false) {
        Log.d("ControlPoint", "Resume - Start ControlPoint");
        ctrlPoint.start();
        ctrlPoint.addNotifyListener(this);
        ctrlPoint.addDeviceChangeListener(this);
        ctrlPoint.removeExpiredDevices();
        setDeviceList(ctrlPoint.getDeviceList());
        
        this.context.setProgressBarIndeterminate(true);
        this.context.setProgressBarIndeterminateVisibility(true);
        
        controlPointStatus = true;
        }
        }
         */
    }

    public void stopControlPoint() {
        Log.d("ControlPoint", "Stopping ControlPoint");
        if (controlPointStatus == true) {
            ctrlPoint.stop();
            ctrlPoint.removeNotifyListener(this);
            ctrlPoint.removeDeviceChangeListener(this);
            controlPointStatus = false;
            this.context.setProgressBarIndeterminate(false);
            this.context.setProgressBarIndeterminateVisibility(false);
        }

    }
}
