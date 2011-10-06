package com.mikebevz.upnp.lal;

import java.util.ArrayList;

public class ServiceList extends ArrayList<Service> {


  public ServiceList() {
  }

  public Service getService(int pos) {

    return get(pos);

  }
}
