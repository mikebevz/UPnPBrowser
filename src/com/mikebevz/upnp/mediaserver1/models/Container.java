package com.mikebevz.upnp.mediaserver1.models;

import java.util.Date;

/**
 * @author mikebevz
 */
public class Container implements Entity {

private String objectId;
private String title;
private String cclass;
private String storageUsed;
private Date date;
private String description;
private String writeStatus;
private String icon;
private String dlnaManaged;
private String parentId;
private String childCount;
private String restricted;

public String getTitle() {
  return title;
}

public void setTitle(String title) {
  this.title = title;
}

public String getCclass() {
  return cclass;
}

public void setCclass(String cclass) {
  this.cclass = cclass;
}

public String getStorageUsed() {
  return storageUsed;
}

public void setStorageUsed(String storageUsed) {
  this.storageUsed = storageUsed;
}

public Date getDate() {
  return date;
}

public void setDate(Date date) {
  this.date = date;
}

public String getDescription() {
  return description;
}

public void setDescription(String description) {
  this.description = description;
}

public String getWriteStatus() {
  return writeStatus;
}

public void setWriteStatus(String writeStatus) {
  this.writeStatus = writeStatus;
}

public String getIcon() {
  return icon;
}

public void setIcon(String icon) {
  this.icon = icon;
}

public String getObjectId() {
  return objectId;
}

public void setObjectId(String objectId) {
  this.objectId = objectId;
}

public void setDlnaManaged(String value) {
  this.dlnaManaged = value;
}

public void setParentID(String value) {
  this.parentId = value;
}

public void setChildCount(String value) {
  this.childCount = value;
}

public void setRestricted(String value) {
  this.restricted = value;
}

public String getDlnaManager() {
  return dlnaManaged;
}

public String getParentId() {
  return parentId;
}

public String getChildCount() {
  return childCount;
}

public String getRestricted() {
  return restricted;
}

public String getDlnaManaged() {
  return this.dlnaManaged;
}

public String getParentID() {
  return this.parentId;
}
}
