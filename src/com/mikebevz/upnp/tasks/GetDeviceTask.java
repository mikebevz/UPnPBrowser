/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mikebevz.upnp.tasks;

import android.os.AsyncTask;
import android.util.Log;
import com.mikebevz.upnp.UpnpBrowserApp;
import org.cybergarage.upnp.Device;

/**
 *
 * @author mikebevz
 */
public class GetDeviceTask  extends AsyncTask<Integer, Integer, Device> {
    
    OnDeviceDetails delegate;
    Exception lastException;
    
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
    protected void onCancelled() {
        super.onCancelled();
        this.delegate.OnDeviceDetailsCancelled(lastException);
    }
    
    
    @Override
    protected Device doInBackground(Integer... positions) {
        //publishProgress(100);
        Device dev = null;
        try {
            dev = (Device) app.getDeviceList().get(positions[0]);
        }catch (Exception e) {
            lastException = e;
        } finally {
            return dev;
        }
        
    }
    
    @Override
    protected void onPostExecute(Device result) {
        if (result == null) {
            this.cancel(true);
        } else {
            this.delegate.OnDeviceDetailsSuccess(result);
        }
    }

    public void setOnDeviceDetailsHandler(OnDeviceDetails delegate) {
        this.delegate = delegate;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        //this.delegate.OnDeviceDetailsProgressUpdate(values[0]);
    }
    
    
    
}