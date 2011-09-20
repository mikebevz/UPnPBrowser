/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mikebevz.upnp.tasks;

import org.cybergarage.upnp.Service;

/**
 *
 * @author mikebevz
 */
public interface OnServiceDetails {

    public void OnServiceDetailsSuccess(Service result);
    public void OnServiceDetailsPreExecute();
}
