package com.mikebevz.upnp.lal;

import java.util.ArrayList;

public class ArgumentList extends ArrayList<Argument> {


  public ArgumentList() {
  }

  public Argument getArgument(int pos) {

    return get(pos);

  }
}
