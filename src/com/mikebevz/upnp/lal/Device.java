package com.mikebevz.upnp.lal;



import com.mikebevz.upnp.lal.wrappers.ILibraryWrapper;
import com.mikebevz.upnp.lal.wrappers.Wrapper;

import java.io.File;
import java.net.InetAddress;

public class Device {

private ILibraryWrapper wrapper;

private String friendlyName;
private String location;
private String deviceType;
private String modelName;
private File descriptionFile;
private String descriptionFilePath;
private long elapsedTime;
private InetAddress[] HTTPBindAddress;
private int HTTPPort;
private int leaseTime;
private Object userData;
private String URLBase;
private String UPC;
private String UDN;
private long timeStamp;
private int SSDPPort;
private String SSDPIPv6MulticastAddress;
private String SSDPIPv4MulticastAddress;
private InetAddress[] SSDPBindAddress;
private int SSDPAnnounceCount;
private String serialNumber;
private String presentationURL;
private String multicastIPv6Address;
private String multicastIPv4Address;
private String modelURL;
private String modelNumber;
private String modelDescription;
private String manufactureURL;
private String manufacture;
private ServiceList serviceList;
private String interfaceAddress;


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

public void setDescriptionFile(File descriptionFile) {
  this.descriptionFile = descriptionFile;
}

public void setDescriptionFilePath(String descriptionFilePath) {
  this.descriptionFilePath = descriptionFilePath;
}

public void setElapsedTime(long elapsedTime) {
  this.elapsedTime = elapsedTime;
}


public void setHTTPBindAddress(InetAddress[] HTTPBindAddress) {
  this.HTTPBindAddress = HTTPBindAddress;
}

public void setHTTPPort(int HTTPPort) {
  this.HTTPPort = HTTPPort;
}

public void setLeaseTime(int leaseTime) {
  this.leaseTime = leaseTime;
}


public void setUserData(Object userData) {
  this.userData = userData;
}

public void setURLBase(String URLBase) {
  this.URLBase = URLBase;
}

public void setUPC(String UPC) {
  this.UPC = UPC;
}

public void setUDN(String UDN) {
  this.UDN = UDN;
}

public void setTimeStamp(long timeStamp) {
  this.timeStamp = timeStamp;
}


public void setSSDPPort(int SSDPPort) {
  this.SSDPPort = SSDPPort;
}


public void setSSDPIPv6MulticastAddress(String SSDPIPv6MulticastAddress) {
  this.SSDPIPv6MulticastAddress = SSDPIPv6MulticastAddress;
}

public void setSSDPIPv4MulticastAddress(String SSDPIPv4MulticastAddress) {
  this.SSDPIPv4MulticastAddress = SSDPIPv4MulticastAddress;
}

public void setSSDPBindAddress(InetAddress[] SSDPBindAddress) {
  this.SSDPBindAddress = SSDPBindAddress;
}

public void setSSDPAnnounceCount(int SSDPAnnounceCount) {
  this.SSDPAnnounceCount = SSDPAnnounceCount;
}


public void setSerialNumber(String serialNumber) {
  this.serialNumber = serialNumber;
}

public void setPresentationURL(String presentationURL) {
  this.presentationURL = presentationURL;
}

public void setMulticastIPv6Address(String multicastIPv6Address) {
  this.multicastIPv6Address = multicastIPv6Address;
}

public void setMulticastIPv4Address(String multicastIPv4Address) {
  this.multicastIPv4Address = multicastIPv4Address;
}

public void setModelURL(String modelURL) {
  this.modelURL = modelURL;
}

public void setModelNumber(String modelNumber) {
  this.modelNumber = modelNumber;
}

public void setModelDescription(String modelDescription) {
  this.modelDescription = modelDescription;
}

public void setManufactureURL(String manufactureURL) {
  this.manufactureURL = manufactureURL;
}

public void setManufacture(String manufacture) {
  this.manufacture = manufacture;
}

public com.mikebevz.upnp.lal.Action getAction(String setLoadLevelTarget) {
  return null;  //To change body of created methods use File | Settings | File Templates.
}

public StateVariable getStateVariable(String loadLevelTarget) {
   return null;

}

public ServiceList getServiceList() {
  //TODO Return list of services
  return null;
}

public File getDescriptionFile() {
  return descriptionFile;
}

public String getDescriptionFilePath() {
  return descriptionFilePath;
}

public long getElapsedTime() {
  return elapsedTime;
}

public InetAddress[] getHTTPBindAddress() {
  return HTTPBindAddress;
}

public int getHTTPPort() {
  return HTTPPort;
}

public int getLeaseTime() {
  return leaseTime;
}

public Object getUserData() {
  return userData;
}

public String getURLBase() {
  return URLBase;
}

public String getUPC() {
  return UPC;
}

public String getUDN() {
  return UDN;
}

public long getTimeStamp() {
  return timeStamp;
}

public int getSSDPPort() {
  return SSDPPort;
}

public String getSSDPIPv6MulticastAddress() {
  return SSDPIPv6MulticastAddress;
}

public String getSSDPIPv4MulticastAddress() {
  return SSDPIPv4MulticastAddress;
}

public InetAddress[] getSSDPBindAddress() {
  return SSDPBindAddress;
}

public int getSSDPAnnounceCount() {
  return SSDPAnnounceCount;
}

public String getSerialNumber() {
  return serialNumber;
}

public String getPresentationURL() {
  return presentationURL;
}

public String getMulticastIPv6Address() {
  return multicastIPv6Address;
}

public String getMulticastIPv4Address() {
  return multicastIPv4Address;
}

public String getModelURL() {
  return modelURL;
}

public String getModelNumber() {
  return modelNumber;
}

public String getModelDescription() {
  return modelDescription;
}

public String getManufactureURL() {
  return manufactureURL;
}

public String getManufacture() {
  return manufacture;
}

public Service getService(String s) {
  return null;  //To change body of created methods use File | Settings | File Templates.
}

public String getInterfaceAddress() {
  return interfaceAddress;
}

public void setServiceList(ServiceList serviceList) {
  this.serviceList = serviceList;
}

public void setInterfaceAddress(String interfaceAddress) {
  this.interfaceAddress = interfaceAddress;
}

public void setWrapper(Wrapper wrapper) {
  this.wrapper = wrapper;
}
}
