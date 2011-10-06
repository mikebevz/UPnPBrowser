package com.mikebevz.upnp.lal.wrappers;

import com.mikebevz.upnp.lal.Device;
import com.mikebevz.upnp.lal.DeviceList;

public class Wrapper implements ILibraryWrapper {

private ILibraryWrapper concreteWrapper;
public IControlPoint controlPoint;

public Wrapper(ILibraryWrapper concreteWrapper) {
  this.concreteWrapper = concreteWrapper;
}

public Wrapper() {
  this.concreteWrapper  = new CLinkWrapper();
}

public IControlPoint getControlPoint() {
  return this.concreteWrapper.getControlPoint();
}

public DeviceList mapDeviceList(Object deviceList) {
  return null;  //To change body of implemented methods use File | Settings | File Templates.
}

public Device mapDevice(Object dev) {
  return null;  //To change body of implemented methods use File | Settings | File Templates.
}


}
