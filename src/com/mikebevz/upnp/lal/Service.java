package com.mikebevz.upnp.lal;


public class Service {


private ActionList actionList;
private String controlURL;
private String serviceID;
private String descriptionURL;
private String eventSubURL;
private String SCPDURL;
private String SID;
private String serviceType;

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
}
