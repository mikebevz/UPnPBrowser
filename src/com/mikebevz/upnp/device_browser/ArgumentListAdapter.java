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
import org.cybergarage.upnp.Argument;

/**
 *
 * @author mikebevz
 */
public class ArgumentListAdapter extends BaseAdapter {

    private List<Argument> argument;
    private LayoutInflater mInflater;

    public ArgumentListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);

        argument = new ArrayList<Argument>();
    }

    /**
     * @return the argument
     */
    public List<Argument> getArguments() {
        return argument;
    }

    /**
     * @param argument the argument to set
     */
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

            cView.setTag(holder);
        } else {
            holder = (ViewHolder) cView.getTag();
        }

        //List<String> list = Arrays.asList(this.getArguments().get(position).getName().split(":"));
        //Collections.reverse(list);

        holder.text.setText(this.getArguments().get(position).getDirection()+": " 
                            + this.getArguments().get(position).getName()
                            + ":" + this.getArguments().get(position).getRelatedStateVariable().getDataType()
                        );
        holder.description.setText("Rel State Var: " 
                + this.getArguments().get(position).getRelatedStateVariableName()
                //+ "\n Allowed Range: "+this.getArguments().get(position).getRelatedStateVariable().getAllowedValueRange().toString()
                //+ "\n Allowed Values: "+this.getArguments().get(position).getRelatedStateVariable().getAllowedValueList().toString()
                );

        return cView;
    }

    static class ViewHolder {

        TextView text;
        TextView description;
        ImageView icon;
    }
}
