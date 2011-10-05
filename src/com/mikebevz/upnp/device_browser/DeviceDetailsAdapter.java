package com.mikebevz.upnp.device_browser;

import android.app.ProgressDialog;
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
import com.mikebevz.upnp.tasks.GetDeviceTask;
import com.mikebevz.upnp.tasks.OnDeviceDetails;
import org.cybergarage.upnp.Device;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mikebevz
 */
public class DeviceDetailsAdapter extends BaseAdapter implements OnDeviceDetails {

private List<String> data;
private final LayoutInflater mInflater;
private final Bitmap icon;
private final Context context;
private ProgressDialog dialog;


public DeviceDetailsAdapter(Context context, int position) {
  this.context = context;
  mInflater = LayoutInflater.from(context);

  data = new ArrayList<String>();
  icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.info);

  GetDeviceTask task = new GetDeviceTask(context);
  task.setOnDeviceDetailsHandler(this);
  task.execute(position);

}

/**
 * @return
 */
public int getCount() {

  return data.size();

}

/**
 * @param position
 * @return
 */
public Object getItem(int position) {
  return data.get(position);

}

/**
 * @param position
 * @return
 */
public long getItemId(int position) {
  return position;
}

/**
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


  holder.text.setText(data.get(position));
  holder.icon.setImageBitmap(icon);

  return cView;
}

static class ViewHolder {

  TextView text;
  TextView description;
  ImageView icon;
}

/**
 * @return the data
 */
public List<String> getData() {
  return data;
}

/**
 * @param data the data to set
 */
void setData(List<String> data) {
  this.data = data;
}

public void OnDeviceDetailsSuccess(Device device) {


  ArrayList<String> properties = new ArrayList<String>();
  properties.add("FrendlyName : " + device.getFriendlyName());
  properties.add("DeviceType : " + device.getDeviceType());
  properties.add("InterfaceAddress : " + device.getInterfaceAddress());
  properties.add("Location : " + device.getLocation());
  properties.add("Manufacture : " + device.getManufacture());
  properties.add("LocatManufactureURL : " + device.getManufactureURL());
  properties.add("ModelDescription : " + device.getModelDescription());
  properties.add("ModelName : " + device.getModelName());
  properties.add("ModelNumber : " + device.getModelNumber());
  properties.add("ModelURL : " + device.getModelURL());
  properties.add("IP (v4) : " + device.getMulticastIPv4Address());
  properties.add("IP (v6) : " + device.getMulticastIPv6Address());
  properties.add("PresentationURL : " + device.getPresentationURL());
  properties.add("SSDPIPv4MulticastAddress : " + device.getSSDPIPv4MulticastAddress());
  properties.add("SSDPIPv6MulticastAddress : " + device.getSSDPIPv6MulticastAddress());
  properties.add("SerialNumber : " + device.getSerialNumber());
  properties.add("UDN : " + device.getUDN());
  properties.add("UPC : " + device.getUPC());
  properties.add("URLBase : " + device.getURLBase());
  properties.add("LeaseTime: " + device.getLeaseTime());
  properties.add("ElapsedTime: " + device.getElapsedTime());
  properties.add("DescriptionFilePath: " + device.getDescriptionFilePath());


  this.setData(properties);
  this.notifyDataSetChanged();
  if (dialog.isShowing()) {
    dialog.dismiss();
  }

}

public void OnDeviceDetailsPreExecute() {
  dialog = ProgressDialog.show(context, context.getResources().getString(R.string.loading),
                                context.getResources().getString(R.string.loading_message), true, true);
}

public void OnDeviceDetailsCancelled(Exception e) {
  Log.e("DeviceBrowser", e.getMessage());
  if (dialog.isShowing()) {
    dialog.dismiss();
  }
}
}
