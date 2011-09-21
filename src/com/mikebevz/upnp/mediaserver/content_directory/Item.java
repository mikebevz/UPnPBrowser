/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mikebevz.upnp.mediaserver.content_directory;

/**
 *
 * @author mikebevz
 */
class Item {

    private String objectId;
    private String dlnaManager;
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

    public Item() {
    }

    void setObjectId(String value) {
        this.objectId = value;
    }

    void setDlnaManaged(String value) {
        this.dlnaManager = value;
    }

    void setParentID(String value) {
        this.parentId = value;
    }

    void setRestricted(String value) {
        this.restricted = value;
    }

    /**
     * @return the objectId
     */
    public String getObjectId() {
        return objectId;
    }

    /**
     * @return the dlnaManager
     */
    public String getDlnaManager() {
        return dlnaManager;
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

    void setTitle(String title) {
        this.title = title;
    }

    void setCclass(String cclass) {
        this.cclass = cclass;
    }

    void setIcon(String icon) {
        this.icon = icon;
    }

    void setDescription(String description) {
        this.description = description;
    }

    void setArtist(String value) {
        this.artist = value;
    }

    void setAlbum(String value) {
        this.album = value;
    }

    void setGenre(String value) {
        this.genre = value;
    }

    void setRes(String value) {
        this.res = value;
    }

    void setAlbumArtUri(String value) {
        this.albumArtUri = value;
    }

    void setResSize(String value) {
        this.resSize = value;
    }

    void setResProtocolInfo(String value) {
        this.resProtocolInfo = value;
    }

    void setAlbumArtUriProfileId(String value) {
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
    
}
