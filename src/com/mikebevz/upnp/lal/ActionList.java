package com.mikebevz.upnp.lal;

import java.util.ArrayList;
import java.util.List;

public class ActionList extends ArrayList<Action> {


private List<Action> actions;

public ActionList() {
  }

  public Action getAction(int pos) {

    return get(pos);

  }

}
