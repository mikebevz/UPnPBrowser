/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mikebevz.upnp.mediaserver.content_directory;

import java.io.ByteArrayInputStream;
import java.util.List;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 *
 * @author mikebevz
 */
public class SaxContentParser implements ContentDirectoryParser {
    
    private String message;
    
    public List<Container> parse() {
        
        SAXParserFactory factory = SAXParserFactory.newInstance();
        
        try {
            SAXParser parser = factory.newSAXParser();
            ContentDirectoryXmlHandler handler = new ContentDirectoryXmlHandler();
            parser.parse(new ByteArrayInputStream(this.message.getBytes()), handler);
            return handler.getContainers(); 
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }
    
}
