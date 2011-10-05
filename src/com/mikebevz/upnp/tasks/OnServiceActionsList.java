package com.mikebevz.upnp.tasks;

import org.cybergarage.upnp.ActionList;

/**
 * @author mikebevz
 */
public interface OnServiceActionsList {
public void OnServiceActionsListSuccess(ActionList aList);

public void OnServiceActionListPreExecute();
}
