package com.mikebevz.upnp.tasks;

import com.mikebevz.upnp.lal.ActionList;



/**
 * @author mikebevz
 */
public interface OnServiceActionsList {
public void OnServiceActionsListSuccess(ActionList aList);

public void OnServiceActionListPreExecute();
}
