package com.mikebevz.upnp.lal.device;

import com.mikebevz.upnp.lal.Device;

public interface OnDeviceChange {
  public void deviceChanged();

void deviceAdded(Device device);

void deviceDeleted(Device device);
}
