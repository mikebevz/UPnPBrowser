package com.mikebevz.upnp.activities.states;

import android.content.Context;
import com.mikebevz.upnp.ActivityState;
import com.mikebevz.upnp.activities.MainActivity;

/**
 * @author mikebevz
 */
public class StateControlPointStopped implements ActivityState {

private final MainActivity mainActivity;

public StateControlPointStopped(Context context) {
  //Context context1 = context;
  this.mainActivity = (MainActivity) context;
}

public void setSettings() {

  mainActivity.devListAdapter.stopControlPoint();

}
}
