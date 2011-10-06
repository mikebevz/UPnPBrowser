package com.mikebevz.upnp.lal.wrappers;

import com.mikebevz.upnp.lal.DeviceList;
import com.mikebevz.upnp.lal.device.OnDeviceChange;
import com.mikebevz.upnp.lal.device.OnDeviceNotification;

import java.util.List;

public interface IControlPoint<D> {

void start();
void stop();
DeviceList getDeviceList();


void removeOnDeviceChangeDelegate(OnDeviceChange delegate);

void removeOnDeviceNotificationDelegate(OnDeviceNotification delegate);

void setDeviceChangeDelegates(List<OnDeviceChange> deviceChangeDelegates);

void setNotificationDelegates(List<OnDeviceNotification> deviceNotificationDelegates);

void addDevice(D dev);

void removeDevice(D dev);

void setOnDeviceChangeDelegate(OnDeviceChange delegate);

void setOnDeviceNotificationDelegate(OnDeviceNotification delegate);
}