/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mikebevz.upnp.mediaserver1;

import java.util.List;

/**
 *
 * @author mikebevz
 */
public interface OnTaskFactory {
    public void onTaskFactorySuccess(List<Entity> result);
    public void onTaskFactoryPreExecute();
}
