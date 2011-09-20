/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mikebevz.upnp.mediaserver1;

import com.mikebevz.upnp.mediaserver.content_directory.Container;
import java.util.List;

/**
 *
 * @author mikebevz
 */
public interface OnTaskFactory {
    public void onTaskFactorySuccess(List<Container> result);
    public void onTaskFactoryPreExecute();
}
