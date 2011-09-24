/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mikebevz.upnp.device_browser;

import android.app.Activity;
import android.app.ProgressDialog;

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
import com.mikebevz.upnp.UpnpBrowserApp;
import com.mikebevz.upnp.tasks.GetServiceTask;
import com.mikebevz.upnp.tasks.OnServiceDetails;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.cybergarage.upnp.Device;
import org.cybergarage.upnp.Service;

/**
 *
 * @author mikebevz
 */
public class ServiceDetailsAdapter extends BaseAdapter implements OnServiceDetails {

    private List<String> data;
    private LayoutInflater mInflater;
    private final Bitmap icon;
    private Device device;
    private final Activity context;
    private ProgressDialog dialog;
    private Service service;



    public ServiceDetailsAdapter(Context context, int position) {
        this.context = (Activity)context;
        mInflater = LayoutInflater.from(context);
        
        data = new ArrayList<String>();
        icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.info);
        
        GetServiceTask task = new GetServiceTask((UpnpBrowserApp)context.getApplicationContext());
        task.setOnServiceDetailsHandler(this);
        task.execute(position);
        
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
     * @param position
     * @return
     */
    public Object getItem(int position) {
        return data.get(position);

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
    public void setData(List<String> data) {
        this.data = data;
    }

    
       /**
     * 
     * @param result
     */
    public void OnServiceDetailsSuccess(Service result) {
        service = result;
        List<String> serviceNameList = Arrays.asList(service.getServiceID().split(":"));
        Collections.reverse(serviceNameList);
        context.setTitle("Service: " + serviceNameList.toArray()[0].toString());
        
        ArrayList<String> properties = new ArrayList<String>();
        properties.add("ControlURL : " +service.getControlURL());
        properties.add("DescriptionURL : " +service.getDescriptionURL());
        properties.add("EventSubURL : " +service.getEventSubURL());
        properties.add("SCPDURL : " +service.getSCPDURL());
        properties.add("SID : " +service.getSID());
        properties.add("ServiceID : " +service.getServiceID());
        properties.add("ServiceType : " +service.getServiceType());
        
        setData(properties);
        notifyDataSetChanged();
        dialog.dismiss();
    }

    /**
     * 
     */
    public void OnServiceDetailsPreExecute() {
        dialog = ProgressDialog.show(context, "", context.getResources().getString(R.string.loading), true);
    }

   
}
