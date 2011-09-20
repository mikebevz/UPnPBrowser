/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mikebevz.upnp.tasks;

import org.cybergarage.upnp.ArgumentList;

/**
 *
 * @author mikebevz
 */
public interface OnActionArgumentsList {

    public void OnActionArgumentsListSuccess(ArgumentList result);

    public void OnActionArgumentsProgressUpdate(Integer integer);

    public void OnActionArgumentsListPreExecute();
}
