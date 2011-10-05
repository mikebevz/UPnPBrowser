package com.mikebevz.upnp.tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import com.mikebevz.upnp.UpnpBrowserApp;
import org.cybergarage.upnp.Device;

/**
 * @author mikebevz
 */
public class GetDeviceTask extends AsyncTask<Integer, Integer, Device> {

private OnDeviceDetails delegate;
private Exception lastException;

private final UpnpBrowserApp app;

public GetDeviceTask(Context context) {
  this.app = (UpnpBrowserApp) context.getApplicationContext();

}

@Override
protected void onPreExecute() {
  super.onPreExecute();
  delegate.OnDeviceDetailsPreExecute();
}

@Override
protected void onCancelled() {
  this.delegate.OnDeviceDetailsCancelled(lastException);
  super.onCancelled();
}


@Override
protected Device doInBackground(Integer... positions) {
  Device dev = null;
  try {
    dev = (Device) app.getDeviceList().get(positions[0]);
    Log.d("DeviceList", String.valueOf(app.getDeviceList().size()));
    return dev;
  } catch (Exception e) {
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