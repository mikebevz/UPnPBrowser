package com.mikebevz.upnp.activities;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import com.mikebevz.upnp.UpnpBrowserApp;
import org.cybergarage.upnp.ActionList;
import org.cybergarage.upnp.Device;
import org.cybergarage.upnp.Service;
import org.cybergarage.upnp.ServiceList;

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
  // ToDo add your GUI initialization code here

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
