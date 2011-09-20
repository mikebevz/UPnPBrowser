/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mikebevz.upnp.tasks;

import android.os.AsyncTask;
import org.cybergarage.upnp.Device;
import org.cybergarage.upnp.ServiceList;

/**
 *
 * @author mikebevz
 */
public class GetDeviceServicesTask  extends AsyncTask<Device, Integer, ServiceList> {
    
    OnDeviceServiceList delegate;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        delegate.OnDeviceServiceListPreExecute();
    }
    
    
    
    
    @Override
    protected ServiceList doInBackground(Device... devices) {
        publishProgress(100);
        return devices[0].getServiceList();
    }
    
    
    @Override
    protected void onPostExecute(ServiceList result) {
        this.delegate.OnDeviceServiceListSuccess(result);
    }

    public void setOnDeviceServiceListHandler(OnDeviceServiceList delegate) {
        this.delegate = delegate;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        this.delegate.OnDeviceServiceListProgressUpdate(values[0]);
    }
    
    
    
}
