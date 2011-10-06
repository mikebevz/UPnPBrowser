package com.mikebevz.upnp.lal;


import org.cybergarage.upnp.ServiceStateTable;
import org.cybergarage.upnp.event.SubscriberList;
import org.cybergarage.xml.Node;

public class Service {


private ActionList actionList;
private String controlURL;
private String serviceID;
private String descriptionURL;
private String eventSubURL;
private String SCPDURL;
private String SID;
private String serviceType;
private Device device;
private byte[] SCPDData;
private Node serviceNode;

private ServiceStateTable serviceStateTable;
private SubscriberList subscriberList;
private long timeout;
private Object userData;

public ActionList getActionList() {
  return actionList;
}

public String getControlURL() {
  return controlURL;
}

public String getServiceID() {
  return serviceID;
}

public Action getAction(String actionName) {
  return null;  //To change body of created methods use File | Settings | File Templates.
}

public String getDescriptionURL() {
  return descriptionURL;
}

public String getEventSubURL() {
  return eventSubURL;
}

public String getSCPDURL() {
  return SCPDURL;
}

public String getSID() {
  return SID;
}

public String getServiceType() {
  return serviceType;
}

public void setActionList(ActionList actionList) {
  //To change body of created methods use File | Settings | File Templates.
}


public void setControlURL(String controlURL) {
  this.controlURL = controlURL;
}


public void setDescriptionURL(String descriptionURL) {
  this.descriptionURL = descriptionURL;
}

public void setDevice(Device device) {
  this.device = device;
}

public void setSCPDData(byte[] SCPDData) {
  this.SCPDData = SCPDData;
}


public void setSCPDURL(String SCPDURL) {
  this.SCPDURL = SCPDURL;
}

public void setServiceID(String serviceID) {
  this.serviceID = serviceID;
}

public void setServiceNode(Node serviceNode) {
  this.serviceNode = serviceNode;
}

public void setServiceStateTable(ServiceStateTable serviceStateTable) {
  this.serviceStateTable = serviceStateTable;
}

public void setServiceType(String serviceType) {
  this.serviceType = serviceType;
}


public void setSID(String SID) {
  this.SID = SID;
}

public void setSubscriberList(SubscriberList subscriberList) {
  this.subscriberList = subscriberList;
}

public void setTimeout(long timeout) {
  this.timeout = timeout;
}


public void setUserData(Object userData) {
  this.userData = userData;
}
}
