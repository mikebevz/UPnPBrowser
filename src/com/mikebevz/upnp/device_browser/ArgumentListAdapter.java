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
import org.cybergarage.upnp.Argument;

/**
 *
 * @author mikebevz
 */
public class ArgumentListAdapter extends BaseAdapter {

    private List<Argument> argument;
    private LayoutInflater mInflater;
    private final Bitmap iconin;
    private final Bitmap iconout;

    public ArgumentListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);

        argument = new ArrayList<Argument>();
        iconin = BitmapFactory.decodeResource(context.getResources(), R.drawable.argin);
        iconout = BitmapFactory.decodeResource(context.getResources(), R.drawable.argout);
        
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
            holder.icon = (ImageView)cView.findViewById(R.id.icon);
            
            cView.setTag(holder);
        } else {
            holder = (ViewHolder) cView.getTag();
        }

        //List<String> list = Arrays.asList(this.getArguments().get(position).getName().split(":"));
        //Collections.reverse(list);

        holder.text.setText(this.getArguments().get(position).getDirection()+": " 
                            + this.getArguments().get(position).getName()
                            + " : " + this.getArguments().get(position).getRelatedStateVariable().getDataType()
                        );
        holder.description.setText("Related State Variable: " 
                + this.getArguments().get(position).getRelatedStateVariableName()
                //+ "\n Allowed Range: "+this.getArguments().get(position).getRelatedStateVariable().getAllowedValueRange().toString()
                //+ "\n Allowed Values: "+this.getArguments().get(position).getRelatedStateVariable().getAllowedValueList().toString()
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
