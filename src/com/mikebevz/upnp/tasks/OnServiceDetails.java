package com.mikebevz.upnp.tasks;


import com.mikebevz.upnp.lal.Service;

/**
 * @author mikebevz
 */
public interface OnServiceDetails {

public void OnServiceDetailsSuccess(Service result);

public void OnServiceDetailsPreExecute();
}
