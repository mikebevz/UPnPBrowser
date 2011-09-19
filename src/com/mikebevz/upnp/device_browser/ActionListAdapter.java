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
import org.cybergarage.upnp.Action;

/**
 *
 * @author mikebevz
 */
public class ActionListAdapter extends BaseAdapter {

    private List<Action> actions;
    private LayoutInflater mInflater;

    public ActionListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);

        actions = new ArrayList<Action>();
    }

    /**
     * @return the actions
     */
    public List<Action> getActions() {
        return actions;
    }

    /**
     * @param actions the actions to set
     */
    public void setActions(List<Action> actions) {
        this.actions = actions;
    }

    public int getCount() {
        return this.getActions().size();
    }

    public Object getItem(int position) {
        return this.getActions().get(position);
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

            cView.setTag(holder);
        } else {
            holder = (ViewHolder) cView.getTag();
        }

        //List<String> list = Arrays.asList(this.getActions().get(position).getName().split(":"));
        //Collections.reverse(list);

        holder.text.setText(this.getActions().get(position).getName());
        holder.description.setText("Input args: " + String.valueOf(this.getActions().get(position).getInputArgumentList().size()));

        return cView;
    }

    static class ViewHolder {

        TextView text;
        TextView description;
        ImageView icon;
    }
}
