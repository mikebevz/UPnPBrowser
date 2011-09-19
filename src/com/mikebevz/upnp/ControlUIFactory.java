/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mikebevz.upnp;

import android.content.Context;
import android.content.Intent;
import com.mikebevz.upnp.uicontrolls.DeviceBrowserUIActivity;
import com.mikebevz.upnp.uicontrolls.MediaServer2Activity;
import com.mikebevz.upnp.uicontrolls.SwitchPowerUIActivity;

/**
 *
 * @author mikebevz
 */
public class ControlUIFactory {
    
    final public static int SWITCH_POWER = 1000;
    final public static String SWITCH_POWER_UPC = "urn:schemas-upnp-org:device:DimmableLight:1";
    final public static int MEDIA_TOMB = 2000;
    final public static String MEDIA_TOMB_UPC = "urn:schemas-upnp-org:device:MediaServer:1";
    final public static int MEDIA_SERVER2 = 3000;
    final public static String MEDIA_SERVER2_UPC = "urn:schemas-upnp-org:device:MediaServer:2";
    
    
    
    
    public Intent getIntent(Context context, String upc) throws RuntimeException {
        
        System.out.println("INPUT: " + upc);
        
        int devId = 0;
        
        if (upc.equals(SWITCH_POWER_UPC)) {
            devId = SWITCH_POWER;
        }
        
        if (upc.equals(MEDIA_TOMB_UPC)) {
            devId = MEDIA_TOMB;
        }
        
        if (upc.equals(MEDIA_SERVER2_UPC)) {
            devId = MEDIA_SERVER2;
        }
        
        
        switch (devId) {
            case SWITCH_POWER:
                return new Intent(context, SwitchPowerUIActivity.class);
            
            //case MEDIA_TOMB:
            //    return new Intent(context, MediaTombActivity.class);
            
            case MEDIA_SERVER2:
                return new Intent(context, MediaServer2Activity.class);    
                
            default:
                return new Intent(context, DeviceBrowserUIActivity.class);
                
        }
        
        
    }
}
