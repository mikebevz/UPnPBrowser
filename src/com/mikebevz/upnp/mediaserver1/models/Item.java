package com.mikebevz.upnp.mediaserver1.models;

import java.util.Date;

/**
 * @author mikebevz
 */
public class Item implements Entity {

private String objectId;
private String dlnaManaged;
private String parentId;
private String restricted;
private String title;
private String cclass;
private String icon;
private String description;
private String artist;
private String album;
private String genre;
private String res;
private String albumArtUri;
private String resSize;
private String resProtocolInfo;
private String albumArtUriProfileId;
private Date date;

public Item() {
}

public void setObjectId(String value) {
  this.objectId = value;
}

public void setDlnaManaged(String value) {
  this.dlnaManaged = value;
}

public void setParentID(String value) {
  this.parentId = value;
}

public void setRestricted(String value) {
  this.restricted = value;
}

/**
 * @return the objectId
 */
public String getObjectId() {
  return objectId;
}

/**
 * @return the dlnaManaged
 */
public String getDlnaManager() {
  return dlnaManaged;
}

/**
 * @return the parentId
 */
public String getParentId() {
  return parentId;
}

/**
 * @return the restricted
 */
public String getRestricted() {
  return restricted;
}

public void setTitle(String title) {
  this.title = title;
}

public void setCclass(String cclass) {
  this.cclass = cclass;
}

public void setIcon(String icon) {
  this.icon = icon;
}

public void setDescription(String description) {
  this.description = description;
}

public void setArtist(String value) {
  this.artist = value;
}

public void setAlbum(String value) {
  this.album = value;
}

public void setGenre(String value) {
  this.genre = value;
}

public void setRes(String value) {
  this.res = value;
}

public void setAlbumArtUri(String value) {
  this.albumArtUri = value;
}

public void setResSize(String value) {
  this.resSize = value;
}

public void setResProtocolInfo(String value) {
  this.resProtocolInfo = value;
}

public void setAlbumArtUriProfileId(String value) {
  this.albumArtUriProfileId = value;
}

/**
 * @return the title
 */
public String getTitle() {
  return title;
}

/**
 * @return the cclass
 */
public String getCclass() {
  return cclass;
}

/**
 * @return the icon
 */
public String getIcon() {
  return icon;
}

/**
 * @return the description
 */
public String getDescription() {
  return description;
}

/**
 * @return the artist
 */
public String getArtist() {
  return artist;
}

/**
 * @return the album
 */
public String getAlbum() {
  return album;
}

/**
 * @return the genre
 */
public String getGenre() {
  return genre;
}

/**
 * @return the res
 */
public String getRes() {
  return res;
}

/**
 * @return the albumArtUri
 */
public String getAlbumArtUri() {
  return albumArtUri;
}

/**
 * @return the resSize
 */
public String getResSize() {
  return resSize;
}

/**
 * @return the resProtocolInfo
 */
public String getResProtocolInfo() {
  return resProtocolInfo;
}

/**
 * @return the albumArtUriProfileId
 */
public String getAlbumArtUriProfileId() {
  return albumArtUriProfileId;
}

public void setDate(Date date) {
  this.date = date;
}

public String getDlnaManaged() {
  return this.dlnaManaged;
}

public String getParentID() {
  return this.parentId;
}

public Date getDate() {
  return this.date;
}

}
