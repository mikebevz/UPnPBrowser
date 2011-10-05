package com.mikebevz.upnp;

import android.app.Application;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import org.cybergarage.upnp.ActionList;
import org.cybergarage.upnp.ArgumentList;
import org.cybergarage.upnp.DeviceList;
import org.cybergarage.upnp.ServiceList;

/**
 * @author mikebevz
 */
public class UpnpBrowserApp extends Application {

private DeviceList deviceList;
private ActionList actionList;
private ServiceList serviceList;
private ArgumentList argumentList;

private Boolean contentBrowser = false;
private ConnectivityManager connManager;
private NetworkInfo mWifi;
private boolean debug;


/**
 * @param list
 */
public void setDeviceList(DeviceList list) {
  this.deviceList = list;

}

/**
 * @return
 */
public DeviceList getDeviceList() {
  return deviceList;
}

/**
 * @param actionList
 */
public void setActionList(ActionList actionList) {
  this.actionList = actionList;
}

/**
 * @return
 */
public ActionList getActionList() {
  return actionList;
}

/**
 * @param sList
 */
public void setServiceList(ServiceList sList) {
  this.serviceList = sList;
}

/**
 * @return
 */
public ServiceList getServiceList() {
  return this.serviceList;
}

/**
 * @return
 */
public Boolean IsWifiConnected() {

  connManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
  mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

  return mWifi.isConnected();
}

/**
 * @param aList
 */
public void setArgumentList(ArgumentList aList) {
  this.argumentList = aList;
}

/**
 * @return
 */
public ArgumentList getArgumentList() {
  return this.argumentList;
}

/**
 * @return the contentBrowser
 */
public Boolean getContentBrowser() {
  return contentBrowser;
}

/**
 * @param contentBrowser the contentBrowser to set
 */
public void setContentBrowser(Boolean contentBrowser) {
  this.contentBrowser = contentBrowser;
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


}
