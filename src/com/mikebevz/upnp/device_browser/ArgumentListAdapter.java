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
import com.mikebevz.upnp.lal.Argument;


import java.util.ArrayList;
import java.util.List;

/**
 * @author mikebevz
 */
public class ArgumentListAdapter extends BaseAdapter {

private List<Argument> argument;
private final LayoutInflater mInflater;
private final Bitmap iconin;
private final Bitmap iconout;

public ArgumentListAdapter(Context context) {
  mInflater = LayoutInflater.from(context);

  argument = new ArrayList<Argument>();
  iconin = BitmapFactory.decodeResource(context.getResources(), R.drawable.argin);
  iconout = BitmapFactory.decodeResource(context.getResources(), R.drawable.argout);

}

List<Argument> getArguments() {
  return argument;
}

public void setArguments(List<Argument> actions) {
  this.argument = actions;
}

public int getCount() {
  return this.getArguments().size();
}

public Object getItem(int position) {
  return this.getArguments().get(position);
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

  holder.text.setText(this.getArguments().get(position).getDirection() + ": "
                        + this.getArguments().get(position).getName()
                        + " : " + this.getArguments().get(position).getRelatedStateVariable().getDataType()
  );
  holder.description.setText("Related State Variable: "
                               + this.getArguments().get(position).getRelatedStateVariableName()
  );
  if (this.getArguments().get(position).getDirection().equals("in")) {
    holder.icon.setImageBitmap(iconin);
  } else {
    holder.icon.setImageBitmap(iconout);
  }


  return cView;
}

static class ViewHolder {

  TextView text;
  TextView description;
  ImageView icon;
}
}
