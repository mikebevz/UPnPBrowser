package com.mikebevz.upnp.mediaserver1;

import com.mikebevz.upnp.mediaserver1.models.Entity;

import java.util.List;

/**
 * @author mikebevz
 */
public class BrowserTaskResult {
private String result;
private String numberReturned;
private String totalMatches;
private String updateID;
private List<Entity> entities;

public String getResult() {
  return result;
}

public void setResult(String result) {
  this.result = result;
}

public String getNumberReturned() {
  return numberReturned;
}

public void setNumberReturned(String numberReturned) {
  this.numberReturned = numberReturned;
}

public String getTotalMatches() {
  return totalMatches;
}

public void setTotalMatches(String totalMatches) {
  this.totalMatches = totalMatches;
}

public String getUpdateID() {
  return updateID;
}

public void setUpdateID(String updateID) {
  this.updateID = updateID;
}

void setEntities(List<Entity> entities) {
  this.entities = entities;
}

public List<Entity> getEntities() {
  return entities;
}
}
