/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mikebevz.upnp.tasks;

import android.os.AsyncTask;
import com.mikebevz.upnp.UpnpBrowserApp;
import org.cybergarage.upnp.Device;

/**
 *
 * @author mikebevz
 */
public class GetDeviceTask  extends AsyncTask<Integer, Integer, Device> {
    
    OnDeviceDetails delegate;
    
    private final UpnpBrowserApp app;
    
    public GetDeviceTask(UpnpBrowserApp app) {
        this.app = app;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        this.delegate.OnDeviceDetailsPreExecute();
    }
    
    
    
    @Override
    protected Device doInBackground(Integer... positions) {
        //publishProgress(100);
        Device dev = (Device) app.getDeviceList().get(positions[0]);
        return dev;
    }
    
    @Override
    protected void onPostExecute(Device result) {
        this.delegate.OnDeviceDetailsSuccess(result);
    }

    public void setOnDeviceDetailsHandler(OnDeviceDetails delegate) {
        this.delegate = delegate;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        //this.delegate.OnDeviceDetailsProgressUpdate(values[0]);
    }
    
    
    
}