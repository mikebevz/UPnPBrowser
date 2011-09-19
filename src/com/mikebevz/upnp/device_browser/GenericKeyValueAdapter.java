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
import java.util.List;

/**
 *
 * @author mikebevz
 */
public class GenericKeyValueAdapter extends BaseAdapter {
    
    private List<String> data;
    private LayoutInflater mInflater;
    
     public GenericKeyValueAdapter(Context context) {
        mInflater = LayoutInflater.from(context);

        data = new ArrayList<String>();
    }
    

    public int getCount() {
        
        return data.size();
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    public Object getItem(int position) {
        return data.get(position);
        
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    public long getItemId(int position) {
        return position;//throw new UnsupportedOperationException("Not supported yet.");
    }

    public View getView(int position, View cView, ViewGroup parent) {

        ViewHolder holder;

        if (cView == null) {
            cView = mInflater.inflate(R.layout.list_item_icon_text, null);

            holder = new ViewHolder();
            holder.text = (TextView) cView.findViewById(R.id.text);
            holder.description = (TextView) cView.findViewById(R.id.description);

            cView.setTag(holder);
        } else {
            holder = (ViewHolder) cView.getTag();
        }


        holder.text.setText(data.get(position));
        

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
    public void setData(List<String> data) {
        this.data = data;
    }

    
}
