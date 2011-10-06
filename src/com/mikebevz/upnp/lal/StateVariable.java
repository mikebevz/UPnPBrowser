package com.mikebevz.upnp.lal;


import org.cybergarage.upnp.AllowedValueList;
import org.cybergarage.upnp.AllowedValueRange;

public class StateVariable {
private String value;
private UPnPStatus queryStatus;
private String dataType;
private AllowedValueList allowedValueList;
private AllowedValueRange allowedValueRange;

public boolean postQuerylAction() {
  return false;  //To change body of created methods use File | Settings | File Templates.
}

public String getValue() {
  return value;
}

public UPnPStatus getQueryStatus() {
  return queryStatus;
}

public String getDataType() {
  return dataType;
}

public AllowedValueList getAllowedValueList() {
  return allowedValueList;
}

public AllowedValueRange getAllowedValueRange() {
  return allowedValueRange;
}
}
