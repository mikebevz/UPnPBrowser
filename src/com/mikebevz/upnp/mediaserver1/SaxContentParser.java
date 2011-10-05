package com.mikebevz.upnp.mediaserver1;

import com.mikebevz.upnp.mediaserver1.models.Entity;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author mikebevz
 */
public class SaxContentParser implements ContentDirectoryParser {

private String message;

public List<Entity> parse() throws RuntimeException {

  SAXParserFactory factory = SAXParserFactory.newInstance();

  try {
    SAXParser parser = factory.newSAXParser();
    ContentDirectoryXmlHandler handler = new ContentDirectoryXmlHandler();
    if (this.message != null) {
      parser.parse(new ByteArrayInputStream(this.message.getBytes()), handler);
      return handler.getContainers();
    } else {
      return new ArrayList<Entity>();
    }

  } catch (Exception e) {
    throw new RuntimeException(e);
  }
}

public String getMessage() {
  return message;
}

public void setMessage(String message) {
  this.message = message;
}
}
