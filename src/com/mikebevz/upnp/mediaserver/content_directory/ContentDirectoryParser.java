/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mikebevz.upnp.mediaserver.content_directory;

import java.util.List;

/**
 *
 * @author mikebevz
 */
public interface ContentDirectoryParser  {
    List<Container> parse();
}
