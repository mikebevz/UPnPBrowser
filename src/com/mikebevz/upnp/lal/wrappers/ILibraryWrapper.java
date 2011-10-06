package com.mikebevz.upnp.lal.wrappers;

import com.mikebevz.upnp.lal.Device;
import com.mikebevz.upnp.lal.DeviceList;

/**
 *
 * @param <DL> DeviceList input type
 * @param <D> Device input type
 */
public interface ILibraryWrapper<DL, D> {
IControlPoint getControlPoint();

public DeviceList mapDeviceList(DL deviceList) ;
public Device mapDevice(D dev);

}
