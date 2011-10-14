package com.mikebevz.upnp.lal;


import android.util.SparseArray;
import org.apache.http.Header;


public class Action {

private String name;
private ArgumentList argumentList;
private UPnPStatus controlStatus;
private Service service;
private ArgumentList outputArgumentList;
private SparseArray<?> inputArgumentList;

public String getName() {
  return name;
}

public ArgumentList getArgumentList() {
  return argumentList;
}

public void setArgumentValue(String newLoadlevelTarget, int value) {
  //TODO implement
  //To change body of created methods use File | Settings | File Templates.
}

public boolean postControlAction() {
  //TODO implement
  return false;  //To change body of created methods use File | Settings | File Templates.
}

public UPnPStatus getControlStatus() {
  return controlStatus;
}

public Header getArgument(String result) {
  return null;  //To change body of created methods use File | Settings | File Templates.
}

public void setArgumentValue(String objectID, String objectId) {


}

public Service getService() {
  return service;
}

public ArgumentList getOutputArgumentList() {
  return outputArgumentList;
}


public SparseArray<?> getInputArgumentList() {
  return inputArgumentList;
}
}
