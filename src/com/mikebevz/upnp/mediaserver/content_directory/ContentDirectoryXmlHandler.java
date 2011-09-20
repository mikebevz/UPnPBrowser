/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mikebevz.upnp.mediaserver.content_directory;

import android.util.Log;
import java.util.ArrayList;
import java.util.List;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


//<DIDL-Lite xmlns="urn:schemas-upnp-org:metadata-1-0/DIDL-Lite/" xmlns:dc="http://purl.org/dc/elements/1.1/" 
// xmlns:upnp="urn:schemas-upnp-org:metadata-1-0/upnp/" xmlns:sec="http://www.sec.co.kr/" 
// xmlns:dlna="urn:schemas-dlna-org:metadata-1-0/">
// <container id="0-902764585c283cdcd2a0f0647decc397x2y2z1" 
// dlna:dlnaManaged="0" parentID="0" childCount="1" restricted="1">
//   <dc:title>My Content</dc:title>
//   <upnp:class>object.container.storageFolder</upnp:class>
//   <dc:date>1970-01-01T00:00:01Z</dc:date>
//   <dc:description>My Content</dc:description>
//   <upnp:writeStatus>NOT_WRITABLE</upnp:writeStatus>
//   <upnp:icon>http://10.0.0.5:30888/__artwork/2c389b73c490bbc83b414cb68ee6bb2e/TVMOBiLi/0-902764585c283cdcd2a0f0647decc397x2y2z1</upnp:icon>
// </container>
// <container id="0-902764585c283cdcd2a0f0647decc397x3y2z1" dlna:dlnaManaged="0" parentID="0" childCount="1" restricted="1">
//   <dc:title>My Friends Content</dc:title>
//   <upnp:class>object.container.storageFolder</upnp:class>
//   <dc:date>1970-01-01T00:00:01Z</dc:date>
//   <dc:description>My Friends Content</dc:description>
//   <upnp:writeStatus>NOT_WRITABLE</upnp:writeStatus>
//   <upnp:icon>http://10.0.0.5:30888/__artwork/139884225dfeb716c797f66f1e766540/TVMOBiLi/0-902764585c283cdcd2a0f0647decc397x3y2z1</upnp:icon>
//</container>
//</DIDL-Lite>

/**
 *
 * @author mikebevz
 */
public class ContentDirectoryXmlHandler extends DefaultHandler {
    
    private List<Container> containers;
    private Container currentContainer;
    private StringBuilder builder;
    
    final private static String TITLE = "title";
    final private static String CLASS = "class";
    final private static String DATE = "date";
    final private static String DESCRIPTION = "description";
    final private static String WRITE_STATUS = "writeStatus";
    final private static String ICON = "icon";
    final private static String CONTAINER = "container";
    
    // Container Attributes
    final private static String OBJECT_ID = "id";
    final private static String DLNA_MANAGED = "dlnaManaged";
    final private static String PARENT_ID = "parentID";
    final private static String CHILD_COUNT = "childCount";
    final private static String RESTRICTED = "restricted";
    
            
    
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        builder.append(ch, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
        
        if (this.currentContainer != null) {
            if (localName.equalsIgnoreCase(TITLE)) {
                currentContainer.setTitle(builder.toString());
            } else if (localName.equalsIgnoreCase(CLASS)) {
                currentContainer.setCclass(builder.toString());
            //} else if (localName.equalsIgnoreCase(DATE)) {
                //currentContainer.setDate(builder.toString());
            } else if (localName.equalsIgnoreCase(DESCRIPTION)) {
                currentContainer.setDescription(builder.toString());
            } else if (localName.equalsIgnoreCase(WRITE_STATUS)) {
                currentContainer.setWriteStatus(builder.toString());
            } else if (localName.equalsIgnoreCase(ICON)) {
                currentContainer.setIcon(builder.toString());
            } else if (localName.equalsIgnoreCase(CONTAINER)) {
                //currentContainer.setObjectId(builder.toString());
                
                containers.add(currentContainer);
            } 
            builder.setLength(0);
        }
    }

    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
        containers = new ArrayList<Container>();
        builder = new StringBuilder();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        
        
        
        if (localName.equalsIgnoreCase(CONTAINER)) {
            this.currentContainer = new Container();
            int length = attributes.getLength();
            //Log.d("XML", localName+ " : "+ String.valueOf(attributes.getLength()));
            for (int i=0;i<length;i++) {
                String name = attributes.getLocalName(i);
                
                if (name.equalsIgnoreCase(OBJECT_ID)) {
                    this.currentContainer.setObjectId(attributes.getValue(i));
                }
                
                if (name.equalsIgnoreCase(DLNA_MANAGED)) {
                    this.currentContainer.setDlnaManager(attributes.getValue(i));
                }
                
                if (name.equalsIgnoreCase(PARENT_ID)) {
                    this.currentContainer.setParentID(attributes.getValue(i));
                }
                
                if (name.equalsIgnoreCase(CHILD_COUNT)) {
                    this.currentContainer.setChildCount(attributes.getValue(i));
                }
                
                if (name.equalsIgnoreCase(RESTRICTED)) {
                    this.currentContainer.setRestricted(attributes.getValue(i));
                }
                
            }
        }
    }

    List<Container> getContainers() {
        return this.containers;
    }
    
    
    
    
    
    
}
