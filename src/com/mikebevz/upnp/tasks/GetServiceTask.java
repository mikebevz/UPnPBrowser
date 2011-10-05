package com.mikebevz.upnp.tasks;

import android.os.AsyncTask;
import com.mikebevz.upnp.UpnpBrowserApp;
import org.cybergarage.upnp.Service;

/**
 * @author mikebevz
 */
public class GetServiceTask extends AsyncTask<Integer, Integer, Service> {

private OnServiceDetails delegate;

private final UpnpBrowserApp app;

public GetServiceTask(UpnpBrowserApp app) {
  this.app = app;
}

@Override
protected void onPreExecute() {
  super.onPreExecute();
  this.delegate.OnServiceDetailsPreExecute();
}


@Override
protected Service doInBackground(Integer... positions) {
  //publishProgress(100);

  return (Service) app.getServiceList().get(positions[0]);
}

@Override
protected void onPostExecute(Service result) {
  this.delegate.OnServiceDetailsSuccess(result);
}

public void setOnDeviceDetailsHandler(OnServiceDetails delegate) {
  this.delegate = delegate;
}

@Override
protected void onProgressUpdate(Integer... values) {
  //this.delegate.OnDeviceDetailsProgressUpdate(values[0]);
}

public void setOnServiceDetailsHandler(OnServiceDetails delegate) {
  this.delegate = delegate;
}


}
