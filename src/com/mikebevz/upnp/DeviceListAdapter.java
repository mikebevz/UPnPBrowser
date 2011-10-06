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
import android.widget.TextView;
import com.mikebevz.upnp.exceptions.UpnpLibraryException;
import com.mikebevz.upnp.exceptions.WifiDisabledException;
import com.mikebevz.upnp.exceptions.WifiNotConnectedException;
import com.mikebevz.upnp.lal.DeviceList;
import com.mikebevz.upnp.lal.device.OnDeviceChange;
import com.mikebevz.upnp.lal.device.OnDeviceNotification;
import com.mikebevz.upnp.lal.wrappers.IControlPoint;



/**
 * @author mikebevz
 */
public class DeviceListAdapter extends BaseAdapter implements OnDeviceChange, OnDeviceNotification {

private final LayoutInflater mInflater;
private DeviceList data;
private final Bitmap diskIcon;
private final Bitmap windowsIcon;
private final Bitmap routerIcon;
private static final String WINDOWS_MEDIA_PLAYER_SHARING = "Windows Media Player Sharing";
private static final String TVMOBILI_MEDIA_SERVER = "TVMOBiLi | MediaServer";
private static final String INTERNET_GATEWAY_DEVICE = "Internet gateway device";
private static final String INTERNET_CONNECTION_SHARING = "Internet Connection Sharing";
private static final String KIES_SYNC_SERVER = "Kies Sync Server";
private static final String INTERNET_GATEWAY_DEVICE_TYPE = "urn:schemas-upnp-org:device:InternetGatewayDevice:1";
private static final String INTERNET_GATEWAY_AP_TYPE = "urn:schemas-wifialliance-org:device:WFADevice:1";
private final Activity context;
private IControlPoint ctrlPoint = null;
private Boolean controlPointStatus = false; // false = stopped, true = running
private UpnpBrowserApp app;
private final ProgressDialog dialog;

/**
 * @param context
 */
public DeviceListAdapter(Activity context) {
  this.context = context;
  mInflater = LayoutInflater.from(context);

  data = new DeviceList();

  diskIcon = BitmapFactory.decodeResource(context.getResources(), R.drawable.disk);
  windowsIcon = BitmapFactory.decodeResource(context.getResources(), R.drawable.windows_device);
  //Bitmap appleIcon = BitmapFactory.decodeResource(context.getResources(), R.drawable.apple_device);
  routerIcon = BitmapFactory.decodeResource(context.getResources(), R.drawable.router_device);
  app = (UpnpBrowserApp) context.getApplication();
  Resources resource = context.getResources();
  dialog = ProgressDialog.show(context, resource.getString(R.string.loading), resource.getString(R.string.loading_message), true, true);

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

private Bitmap getIconForDevice(com.mikebevz.upnp.lal.Device device) {
  //TODO implement different icons
  String type = device.getDeviceType();
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

  return diskIcon; // Default icon
}

public DeviceList getDeviceList() {
  return data;
}

public boolean getControlPointStatus() {
  return controlPointStatus;
}

public void cancel() {
  if (dialog.isShowing()) {
    dialog.dismiss();
  }
}

public void deviceChanged() {
  //Nothing
}


public void deviceAdded(final com.mikebevz.upnp.lal.Device device) {

  Log.d("Device added", device.getFriendlyName());

  Runnable task = new Runnable() {
    public void run() {
      addDevice(device);
    }
  };
  context.runOnUiThread(task);


}

public void deviceDeleted(com.mikebevz.upnp.lal.Device device) {

  Runnable task = new Runnable() {
    public void run() {
      //deleteDevice(device);
    }
  };

  context.runOnUiThread(task);


}

public void deviceNotificationReceived() {
  //NOTHING
}





static class ViewHolder {
  TextView text;
  TextView description;
  ImageView icon;
}

void setDeviceList(DeviceList deviceList) {
  this.data = deviceList;
  app.setDeviceList(data);

}

/**
 * Add device to the list
 *
 * @param dev Device added
 */
void addDevice(com.mikebevz.upnp.lal.Device dev) {
  Log.d("DeviceNotify", "Device Added " + dev.getFriendlyName());
  data.add(dev);
  notifyDataSetChanged();

  if (dialog.isShowing()) {
    dialog.dismiss();
  }

}

/**
 * Delete device from the list
 *
 * @param dev Device to be deleted
 */
void deleteDevice(com.mikebevz.upnp.lal.Device dev) {
  Log.d("DeviceNotify", "Device Deleted " + dev.getFriendlyName());
  data.remove(dev);
  notifyDataSetChanged();
}

/*
public void deviceAdded(final Device device) {

  Runnable task = new Runnable() {
    public void run() {
      //addDevice(device);

    }
  };
  context.runOnUiThread(task);
}

public void deviceRemoved(final Device device) {

  Runnable task = new Runnable() {
    public void run() {
      //deleteDevice(device);
    }
  };

  context.runOnUiThread(task);

}
*/
/*
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

*/

public void startControlPoint() throws WifiNotConnectedException, WifiDisabledException, UpnpLibraryException {
  app = (UpnpBrowserApp) context.getApplication();
  Resources resources = context.getResources();

  if (!app.isWifiEnabled()) {
    throw new WifiDisabledException(resources.getString(R.string.wifi_disabled));
  }

  if (!app.IsWifiConnected()) {
    throw new WifiNotConnectedException(resources.getString(R.string.wifi_isnt_connected));
  }

  if (ctrlPoint == null) {
    ctrlPoint = app.getWrapper().getControlPoint();
    ctrlPoint.setOnDeviceChangeDelegate(this);
    ctrlPoint.setOnDeviceNotificationDelegate(this);

  }

  if (!controlPointStatus) {
    ctrlPoint.start();
    this.context.setProgressBarIndeterminate(true);
    this.context.setProgressBarIndeterminateVisibility(true);

    setDeviceList(ctrlPoint.getDeviceList());

    controlPointStatus = true;
  }

}

public void stopControlPoint() {
  Log.d("ControlPoint", "Stopping ControlPoint");
  if (controlPointStatus) {
    ctrlPoint.stop();
    ctrlPoint.removeOnDeviceChangeDelegate(this);
    ctrlPoint.removeOnDeviceNotificationDelegate(this);
    controlPointStatus = false;
    this.context.setProgressBarIndeterminate(false);
    this.context.setProgressBarIndeterminateVisibility(false);
  }

}
}
