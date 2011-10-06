package com.mikebevz.upnp.activities;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import com.mikebevz.upnp.UpnpBrowserApp;
import com.mikebevz.upnp.lal.ActionList;
import com.mikebevz.upnp.lal.Device;
import com.mikebevz.upnp.lal.Service;
import com.mikebevz.upnp.lal.ServiceList;


/**
 * @author mikebevz
 */
public class MediaServer2Activity extends Activity {

/**
 * Called when the activity is first created.
 */
@Override
public void onCreate(Bundle icicle) {
  super.onCreate(icicle);

  Bundle bundle = getIntent().getExtras();
  int position = bundle.getInt("device");
  Device device = (Device) ((UpnpBrowserApp) getApplication()).getDeviceList().get(position);
  this.setTitle(device.getFriendlyName());

  ServiceList sList = device.getServiceList();
  for (int i = 0; i < sList.size(); i++) {
    Log.d("Service: ", sList.getService(i).getServiceID());
  }


  Service contentDirectory = device.getService("urn:upnp-org:serviceId:ContentDirectory");

  ActionList list = contentDirectory.getActionList();

  for (int i = 0; i < list.size(); i++) {
    Log.d("Action: ", list.getAction(i).getName());
  }
}
}
