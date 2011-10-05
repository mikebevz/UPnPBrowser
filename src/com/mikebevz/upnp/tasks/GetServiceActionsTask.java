package com.mikebevz.upnp.tasks;

import android.os.AsyncTask;
import org.cybergarage.upnp.ActionList;
import org.cybergarage.upnp.Service;

/**
 * @author mikebevz
 */
public class GetServiceActionsTask extends AsyncTask<Service, Integer, ActionList> {

private OnServiceActionsList delegate;

@Override
protected void onPreExecute() {
  super.onPreExecute();
  delegate.OnServiceActionListPreExecute();
}


@Override
protected ActionList doInBackground(Service... services) {

  return services[0].getActionList();
}

@Override
protected void onPostExecute(ActionList result) {
  this.delegate.OnServiceActionsListSuccess(result);
}

public void setOnServiceActionListHandler(OnServiceActionsList delegate) {
  this.delegate = delegate;
}


}
