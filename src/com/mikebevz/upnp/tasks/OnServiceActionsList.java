/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mikebevz.upnp.tasks;

import org.cybergarage.upnp.ActionList;

/**
 *
 * @author mikebevz
 */
public interface OnServiceActionsList {
    public void OnServiceActionsListSuccess(ActionList aList);
    public void OnServiceActionsListProgressUpdate(Integer value);

    public void OnServiceActionListPreExecute();
}
