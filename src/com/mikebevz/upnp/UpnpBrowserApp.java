package com.mikebevz.upnp;

import android.app.Application;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.mikebevz.upnp.lal.wrappers.ILibraryWrapper;
import com.mikebevz.upnp.lal.wrappers.Wrapper;
import org.cybergarage.upnp.ActionList;
import org.cybergarage.upnp.ArgumentList;
import org.cybergarage.upnp.DeviceList;
import org.cybergarage.upnp.ServiceList;

public class UpnpBrowserApp extends Application {

private DeviceList deviceList;
private ActionList actionList;
private ServiceList serviceList;
private ArgumentList argumentList;

private ConnectivityManager connManager;
private NetworkInfo mWifi;
private boolean debug;
private Boolean contentBrowser = false;

private ILibraryWrapper upnpWrapper = new Wrapper();


public void setDeviceList(DeviceList list) {
  this.deviceList = list;

}

public DeviceList getDeviceList() {
  return deviceList;
}

public void setActionList(ActionList actionList) {
  this.actionList = actionList;
}

public ActionList getActionList() {
  return actionList;
}

public void setServiceList(ServiceList sList) {
  this.serviceList = sList;
}

public ServiceList getServiceList() {
  return this.serviceList;
}

public Boolean IsWifiConnected() {

  connManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
  mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

  return mWifi.isConnected();
}


public void setArgumentList(ArgumentList aList) {
  this.argumentList = aList;
}


public ArgumentList getArgumentList() {
  return this.argumentList;
}


public Boolean getContentBrowser() {

  return contentBrowser;
}

public boolean isDebug() {
  debug = true;
  return debug;
}

public boolean isWifiEnabled() {
  connManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
  mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

  return mWifi.isAvailable();
}

public void setDeviceList(com.mikebevz.upnp.lal.DeviceList data) {
  com.mikebevz.upnp.lal.DeviceList deviceListLal = data;
}

public ILibraryWrapper getWrapper() {
  return this.upnpWrapper;
}
}
