/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mikebevz.upnp.mediaserver.content_directory;

import java.util.ArrayList;
import java.util.List;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

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
        }
    }

    List<Container> getContainers() {
        return this.containers;
    }
    
    
    
    
    
    
}
