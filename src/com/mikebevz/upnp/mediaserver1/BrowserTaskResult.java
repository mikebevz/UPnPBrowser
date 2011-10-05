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

/**
 * @return the result
 */
public String getResult() {
  return result;
}

/**
 * @param result the result to set
 */
public void setResult(String result) {
  this.result = result;
}

/**
 * @return the numberReturned
 */
public String getNumberReturned() {
  return numberReturned;
}

/**
 * @param numberReturned the numberReturned to set
 */
public void setNumberReturned(String numberReturned) {
  this.numberReturned = numberReturned;
}

/**
 * @return the totalMatches
 */
public String getTotalMatches() {
  return totalMatches;
}

/**
 * @param totalMatches the totalMatches to set
 */
public void setTotalMatches(String totalMatches) {
  this.totalMatches = totalMatches;
}

/**
 * @return the updateID
 */
public String getUpdateID() {
  return updateID;
}

/**
 * @param updateID the updateID to set
 */
public void setUpdateID(String updateID) {
  this.updateID = updateID;
}

void setEntities(List<Entity> entities) {
  this.entities = entities;
}

/**
 * @return the updateID
 */
public List<Entity> getEntities() {
  return entities;
}
}
