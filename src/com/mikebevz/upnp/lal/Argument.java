package com.mikebevz.upnp.lal;


public class Argument {
private String name;
private String value;
private String direction;
private StateVariable relatedStateVariable;
private String relatedStateVariableName;

public String getName() {
  return name;
}

public String getValue() {
  return value;
}

public String getDirection() {
  return direction;
}

public StateVariable getRelatedStateVariable() {
  return relatedStateVariable;
}

public String getRelatedStateVariableName() {
  return relatedStateVariableName;
}
}
