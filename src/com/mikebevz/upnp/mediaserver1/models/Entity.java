/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mikebevz.upnp.mediaserver1.models;

import java.util.Date;

/**
 *
 * @author mikebevz
 */
public interface Entity {
    public void setObjectId(String value);
    public void setDlnaManaged(String value);
    public void setParentID(String value);
    public void setRestricted(String value);
    public void setIcon(String icon);
    public void setCclass(String cclass);
    public void setTitle(String title);
    public void setDescription(String description);
    public void setDate(Date date);
    
    public String getObjectId();
    public String getDlnaManaged();
    public String getParentID();
    public String getRestricted();
    public String getIcon();
    public String getCclass();
    public String getTitle();
    public String getDescription();
    public Date getDate();
    
    
    
}
