package com.mikebevz.upnp.tasks;

import android.os.AsyncTask;
import org.cybergarage.upnp.Action;
import org.cybergarage.upnp.ArgumentList;

/**
 * @author mikebevz
 */
public class GetActionArgumentsTask extends AsyncTask<Action, Integer, ArgumentList> {

private OnActionArgumentsList delegate;

@Override
protected void onPreExecute() {
  super.onPreExecute();
  delegate.OnActionArgumentsListPreExecute();
}


@Override
protected ArgumentList doInBackground(Action... services) {

  return services[0].getArgumentList();
}

@Override
protected void onPostExecute(ArgumentList result) {
  this.delegate.OnActionArgumentsListSuccess(result);
}

public void setOnActionArgumentsListHandler(OnActionArgumentsList delegate) {
  this.delegate = delegate;
}


}
