/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mikebevz.upnp.exceptions;

/**
 *
 * @author mikebevz
 */
public class WifiDisabledException extends Exception {

    public WifiDisabledException(String wifi_disabled) {
        super(wifi_disabled);
    }
    
}
