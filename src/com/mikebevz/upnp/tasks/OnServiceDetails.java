package com.mikebevz.upnp.tasks;

import org.cybergarage.upnp.Service;

/**
 * @author mikebevz
 */
public interface OnServiceDetails {

public void OnServiceDetailsSuccess(Service result);

public void OnServiceDetailsPreExecute();
}
