/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mikebevz.upnp.mediaserver1;

import org.cybergarage.upnp.Action;

/**
 *
 * @author mikebevz
 */
public class TaskFactory {
    
    public static final String CONTENT_DIR_ACTION_BROWSE = "browse";
    public static final int CONTENT_DIR_ACTION_BROWSE_I = 1;
    
    
    public TaskFactory() {
        
    }
    
    
    public TaskFactoryTask getTaskForAction(Action action) {
        String actionId = action.getName();
        
        switch(name2Int(actionId)) {
            case CONTENT_DIR_ACTION_BROWSE_I:
                return new TaskFactoryTask() {};
                
            default:
                return new TaskFactoryTask() {};
        }
        
    }
    
    private int name2Int(String name) {
        if (name.equals(CONTENT_DIR_ACTION_BROWSE)) {
            return CONTENT_DIR_ACTION_BROWSE_I;
        }
        
        //if (name.equals(CONTENT_DIR_ACTION_BROWSE)) {
        //    return CONTENT_DIR_ACTION_BROWSE_I;
        //}
        
        return 0;
        
    }
}
