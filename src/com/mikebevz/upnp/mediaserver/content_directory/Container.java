/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mikebevz.upnp.mediaserver.content_directory;

import java.util.Date;

/**
 *
 * @author mikebevz
 */
public class Container {
    
    private String title;
    private String cclass;
    private String storageUsed;
    private Date date;
    private String description;
    private String writeStatus;
    private String icon;
 
    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the cclass
     */
    public String getCclass() {
        return cclass;
    }

    /**
     * @param cclass the cclass to set
     */
    public void setCclass(String cclass) {
        this.cclass = cclass;
    }

    /**
     * @return the storageUsed
     */
    public String getStorageUsed() {
        return storageUsed;
    }

    /**
     * @param storageUsed the storageUsed to set
     */
    public void setStorageUsed(String storageUsed) {
        this.storageUsed = storageUsed;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the writeStatus
     */
    public String getWriteStatus() {
        return writeStatus;
    }

    /**
     * @param writeStatus the writeStatus to set
     */
    public void setWriteStatus(String writeStatus) {
        this.writeStatus = writeStatus;
    }

    /**
     * @return the icon
     */
    public String getIcon() {
        return icon;
    }

    /**
     * @param icon the icon to set
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }
    
            
}
