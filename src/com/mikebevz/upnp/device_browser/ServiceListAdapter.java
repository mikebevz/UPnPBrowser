package com.mikebevz.upnp.device_browser;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.mikebevz.upnp.R;
import org.cybergarage.upnp.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author mikebevz
 */
public class ServiceListAdapter extends BaseAdapter {

private List<Service> services;

private final LayoutInflater mInflater;
private final Bitmap icon;

public ServiceListAdapter(Context context) {
  mInflater = LayoutInflater.from(context);

  services = new ArrayList<Service>();
  icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.service);
}

List<Service> getServices() {
  return services;
}

public void setServices(List<Service> services) {
  this.services = services;
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
    holder.text = (TextView) cView.findViewById(R.id.text);
    holder.description = (TextView) cView.findViewById(R.id.description);
    holder.icon = (ImageView) cView.findViewById(R.id.icon);

    cView.setTag(holder);
  } else {
    holder = (ViewHolder) cView.getTag();
  }

  List<String> list = Arrays.asList(this.getServices().get(position).getServiceID().split(":"));
  Collections.reverse(list);

  holder.text.setText(list.toArray()[0].toString());
  holder.description.setText(this.getServices().get(position).getServiceID());
  holder.icon.setImageBitmap(icon);

  return cView;
}

static class ViewHolder {
  TextView text;
  TextView description;
  ImageView icon;
}
}
