package com.mikebevz.upnp.lal;

public class Device {

private String friendlyName;
private String location;
private String deviceType;
private String modelName;


public String getFriendlyName() {
    return this.friendlyName;
}


public String getLocation() {
  return this.location;
}

public String getDeviceType() {
  return this.deviceType;
}

public String getModelName() {
  return this.modelName;
}

public void setDeviceType(String deviceType) {
  this.deviceType = deviceType;
}

public void setFriendlyName(String friendlyName) {
  this.friendlyName = friendlyName;
}

public void setLocation(String location) {
  this.location = location;
}

public void setModelName(String modelName) {
  this.modelName = modelName;
}
}
