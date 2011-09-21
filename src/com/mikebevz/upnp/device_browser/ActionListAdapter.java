/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
    private final Bitmap icon;

    /**
     * 
     * @param context
     */
    public ActionListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);

        actions = new ArrayList<Action>();
        icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.action);
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

    /**
     * 
     * @return
     */
    public int getCount() {
        return this.getActions().size();
    }

    /**
     * 
     * @param position
     * @return
     */
    public Object getItem(int position) {
        return this.getActions().get(position);
    }

    /**
     * 
     * @param position
     * @return
     */
    public long getItemId(int position) {
        return position;
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
            holder.icon = (ImageView)cView.findViewById(R.id.icon);

            cView.setTag(holder);
        } else {
            holder = (ViewHolder) cView.getTag();
        }

        //List<String> list = Arrays.asList(this.getActions().get(position).getName().split(":"));
        //Collections.reverse(list);

        holder.text.setText(this.getActions().get(position).getName());
        holder.description.setText("Input args: " + String.valueOf(this.getActions().get(position).getInputArgumentList().size())
                                   +" / Output args: " + String.valueOf(this.getActions().get(position).getOutputArgumentList().size()));
        holder.icon.setImageBitmap(icon);

        return cView;
    }

    static class ViewHolder {

        TextView text;
        TextView description;
        ImageView icon;
    }
}
