package com.mikebevz.upnp.lal;

import java.util.ArrayList;

public class DeviceList extends ArrayList<Device> {


  public DeviceList() {
  }

  public Device getDevice(int pos) {

    return get(pos);

  }
}
