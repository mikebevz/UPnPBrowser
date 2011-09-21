/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mikebevz.upnp.mediaserver1;

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
 * MP3
 * 
 * <DIDL-Lite xmlns="urn:schemas-upnp-org:metadata-1-0/DIDL-Lite/" xmlns:dc="http://purl.org/dc/elements/1.1/" 
 * xmlns:upnp="urn:schemas-upnp-org:metadata-1-0/upnp/" xmlns:sec="http://www.sec.co.kr/" xmlns:dlna="urn:schemas-dlna-org:metadata-1-0/">
 * 
 * <item id="s0-902764585c283cdcd2a0f0647decc397x2464y5z1268234047" dlna:dlnaManaged="0" parentID="0-902764585c283cdcd2a0f0647decc397x2463y3z1" 
 * restricted="1">
 *  <dc:title>If You Love Me (Control-S Remix)</dc:title>
 *  <upnp:class>object.item.audioItem.musicTrack</upnp:class>
 *  <dc:date>2010-03-10T15:14:07Z</dc:date>
 *  <upnp:icon>http://10.0.0.5:30888/__artwork/ae2db52d40dbac95feb1c6f9616cdf15/TVMOBiLi/s0-902764585c283cdcd2a0f0647decc397x2464y5z1268234047</upnp:icon>
 *  <upnp:artist>Chanel</upnp:artist>
 *  <dc:creator>Chanel</dc:creator>
 *  <upnp:album>MOS Presents Addicted to Bass Winter 2009 (Unmixed)</upnp:album>
 *  <upnp:genre>Bass</upnp:genre>
 *  <dc:description>If You Love Me (Control-S Remix)</dc:description>
 *  <res bitrate="40000" duration="00:06:43.000" size="16191378" sampleFrequency="44100" 
 *    protocolInfo="http-get:*:audio/mpeg:DLNA.ORG_PN=MP3;DLNA.ORG_OP=01;DLNA.ORG_CI=0;DLNA.ORG_FLAGS=01500000000000000000000000000000"
 *    >http://10.0.0.5:30888/__contentStream/86fe54f306dc1e5a6dc589e0cac375b0/TVMOBiLi/s0-902764585c283cdcd2a0f0647decc397x2464y5z1268234047.mp3</res>
 *   <upnp:albumArtURI dlna:profileID="JPEG_TN">
 *      http://10.0.0.5:30888/__artwork/ae2db52d40dbac95feb1c6f9616cdf15/TVMOBiLi/s0-902764585c283cdcd2a0f0647decc397x2464y5z1268234047</upnp:albumArtURI>
 * </item>
 * 
 * <item id="s0-902764585c283cdcd2a0f0647decc397x2481y5z1268230914" dlna:dlnaManaged="0" parentID="0-902764585c283cdcd2a0f0647decc397x2463y3z1" restricted="1"><dc:title>Bulla Cake</dc:title><upnp:class>object.item.audioItem.musicTrack</upnp:class><dc:date>2010-03-10T14:21:54Z</dc:date><upnp:icon>http://10.0.0.5:30888/__artwork/0e8cc777a4777d833295cf3f2656f8e4/TVMOBiLi/s0-902764585c283cdcd2a0f0647decc397x2481y5z1268230914</upnp:icon><upnp:artist>Dexplicit</upnp:artist><dc:creator>Dexplicit</dc:creator><upnp:album>MOS Presents Addicted to Bass Winter 2009 (Unmixed)</upnp:album><upnp:genre>Bass</upnp:genre><dc:description>Bulla Cake</dc:description><res bitrate="40000" duration="00:04:39.000" size="11242669" sampleFrequency="44100" protocolInfo="http-get:*:audio/mpeg:DLNA.ORG_PN=MP3;DLNA.ORG_OP=01;DLNA.ORG_CI=0;DLNA.ORG_FLAGS=01500000000000000000000000000000">http://10.0.0.5:30888/__contentStream/a80ca466d238abc87ad8af3307326633/TVMOBiLi/s0-902764585c283cdcd2a0f0647decc397x2481y5z1268230914.mp3</res><upnp:albumArtURI dlna:profileID="JPEG_TN">http://10.0.0.5:30888/__artwork/0e8cc777a4777d833295cf3f2656f8e4/TVMOBiLi/s0-902764585c283cdcd2a0f0647decc397x2481y5z1268230914</upnp:albumArtURI></item><item id="s0-902764585c283cdcd2a0f0647decc397x2496y5z1268233437" dlna:dlnaManaged="0" parentID="0-902764585c283cdcd2a0f0647decc397x2463y3z1" restricted="1"><dc:title>Bad Ass</dc:title><upnp:class>object.item.audioItem.musicTrack</upnp:class><dc:date>2010-03-10T15:03:57Z</dc:date><upnp:icon>http://10.0.0.5:30888/__artwork/653de7f774c3f2140937c8384b5d111d/TVMOBiLi/s0-902764585c283cdcd2a0f0647decc397x2496y5z1268233437</upnp:icon><upnp:artist>Micky Finn &amp; Aphrodite</upnp:artist><dc:creator>Micky Finn &amp; Aphrodite</dc:creator><upnp:album>MOS Presents Addicted to Bass Winter 2009 (Unmixed)</upnp:album><upnp:genre>Bass</upnp:genre><dc:description>Bad Ass</dc:description><res bitrate="40000" duration="00:05:00.000" size="12069230" sampleFrequency="44100" protocolInfo="http-get:*:audio/mpeg:DLNA.ORG_PN=MP3;DLNA.ORG_OP=01;DLNA.ORG_CI=0;DLNA.ORG_FLAGS=01500000000000000000000000000000">http://10.0.0.5:30888/__contentStream/d06831434ba99f92f4f53e56e3f0a017/TVMOBiLi/s0-902764585c283cdcd2a0f0647decc397x2496y5z1268233437.mp3</res><upnp:albumArtURI dlna:profileID="JPEG_TN">http://10.0.0.5:30888/__artwork/653de7f774c3f2140937c8384b5d111d/TVMOBiLi/s0-902764585c283cdcd2a0f0647decc397x2496y5z1268233437
 *   </upnp:albumArtURI>
 *  </item>
 * <item id="s0-902764585c283cdcd2a0f0647decc397x2512y5z1268227857" dlna:dlnaManaged="0" parentID="0-902764585c283cdcd2a0f0647decc397x2463y3z1
 * 
 * 
 * 
 * VIDEO
 * 
 * <DIDL-Lite xmlns="urn:schemas-upnp-org:metadata-1-0/DIDL-Lite/" xmlns:dc="http://purl.org/dc/elements/1.1/" 
 *  xmlns:upnp="urn:schemas-upnp-org:metadata-1-0/upnp/" xmlns:sec="http://www.sec.co.kr/" 
 *  xmlns:dlna="urn:schemas-dlna-org:metadata-1-0/">
 * 
 * <item id="s0-902764585c283cdcd2a0f0647decc397x22777y8z1227254470" dlna:dlnaManaged="0" 
 *  parentID="0-902764585c283cdcd2a0f0647decc397x9147y2z1" restricted="1">
 *  <dc:title>MOV04705.MPG</dc:title>
 *  <upnp:class>object.item.videoItem</upnp:class>
 *  <dc:date>2008-11-21T08:01:10Z</dc:date>
 *  <upnp:artist>Unknown</upnp:artist>
 *  <dc:creator>Unknown</dc:creator>
 *  <upnp:album>turcija</upnp:album>
 *  <upnp:genre>Unknown</upnp:genre>
 *  <dc:description>MOV04705.MPG</dc:description>
 *  <res size="1954013" 
 *      protocolInfo="http-get:*:video/mpeg:DLNA.ORG_PN=MPEG_PS_PAL;DLNA.ORG_OP=01;DLNA.ORG_CI=0;DLNA.ORG_FLAGS=01500000000000000000000000000000">
 *      http://10.0.0.5:30888/__contentStream/47f6d67dfc4ca4db4eaea9108207c0de/TVMOBiLi/s0-902764585c283cdcd2a0f0647decc397x22777y8z1227254470
 *  </res>
 *  <upnp:albumArtURI dlna:profileID="JPEG_TN">
 *      http://10.0.0.5:30888/__artwork/47f6d67dfc4ca4db4eaea9108207c0de/TVMOBiLi/s0-902764585c283cdcd2a0f0647decc397x22777y8z1227254470
 * </upnp:albumArtURI>
 * </item>
 * <item id="s0-902764585c283cdcd2a0f0647decc397x22781y8z1228962340" dlna:dlnaManaged="0" parentID="0-902764585c283cdcd2a0f0647decc397x9147y2z1" restricted="1">
 *  <dc:title>MOV04798.MPG</dc:title>
 * <upnp:class>object.item.videoItem</upnp:class><dc:date>2008-12-11T02:25:40Z</dc:date><upnp:artist>Unknown</upnp:artist><dc:creator>Unknown</dc:creator><upnp:album>turcija</upnp:album><upnp:genre>Unknown</upnp:genre><dc:description>MOV04798.MPG</dc:description><res size="1173077" protocolInfo="http-get:*:video/mpeg:DLNA.ORG_PN=MPEG_PS_PAL;DLNA.ORG_OP=01;DLNA.ORG_CI=0;DLNA.ORG_FLAGS=01500000000000000000000000000000">http://10.0.0.5:30888/__contentStream/fb8d82a324cd212d8c824af932bdf128/TVMOBiLi/s0-902764585c283cdcd2a0f0647decc397x22781y8z1228962340</res><upnp:albumArtURI dlna:profileID="JPEG_TN">http://10.0.0.5:30888/__artwork/fb8d82a324cd212d8c824af932bdf128/TVMOBiLi/s0-902764585c283cdcd2a0f0647decc397x22781y8z1228962340</upnp:albumArtURI></item><item id="s0-902764585c283cdcd2a0f0647decc397x22785y8z1229217136" dlna:dlnaManaged="0" parentID="0-902764585c283cdcd2a0f0647decc397x9147y2z1" restricted="1"><dc:title>MOV04844.MPG</dc:title><upnp:class>object.item.videoItem</upnp:class><dc:date>2008-12-14T01:12:16Z</dc:date><upnp:artist>Unknown</upnp:artist><dc:creator>Unknown</dc:creator><upnp:album>turcija</upnp:album><upnp:genre>Unknown</upnp:genre><dc:description>MOV04844.MPG</dc:description><res size="2217202" protocolInfo="http-get:*:video/mpeg:DLNA.ORG_PN=MPEG_PS_PAL;DLNA.ORG_OP=01;DLNA.ORG_CI=0;DLNA.ORG_FLAGS=01500000000000000000000000000000">http://10.0.0.5:30888/__contentStream/07f3bc384419b8f4e856a09fd587921d/TVMOBiLi/s0-902764585c283cdcd2a0f0647decc397x22785y8z1229217136</res><upnp:albumArtURI dlna:profileID="JPEG_TN">http://10.0.0.5:30888/__artwork/07f3bc384419b8f4e856a09fd587921d/TVMOBiLi/s0-902764585c283cdcd2a0f0647decc397x22785y8z1229217136</upnp:albumArtURI></item><item id="s0-902764585c283cdcd2a0f0647decc397x22789y8z1229290056" dlna:dlnaManaged="0" parentID="0-902764585c283cdcd2a0f0647decc397x9147y2z1" restricted="1"><dc:title>MOV04881.MPG</dc:title><upnp:class>object.item.videoItem</upnp:class><dc:date>2008-12-14T21:27:36Z</dc:date><upnp:artist>Unknown</upnp:artist><dc:creator>Unknown</dc:creator><upnp:album>turcija</upnp:album><upnp:genre>Unknown</upnp:genre><dc:description>MOV04881.MPG</dc:description><res size="5758350" protocolInfo="http-get:*:video/mpeg:DLNA.ORG_PN=MPEG_PS_PAL;DLNA.ORG_OP=01;DLNA.ORG_CI=0;DLNA.ORG_FLAGS=01500000000000000000000000000000">http://10.0.0.5:30888/__contentStream/21ee90d17d331df43ed11636ef53a2f9/TVMOBiLi/s0-902764585c283cdcd2a0f0647decc397x22789y8z1229290056</res><upnp:albumArtURI dlna:profileID="JPEG_TN">http://10.0.0.5:30888/__artwork/21ee90d17d331df43ed11636ef53a2f9/TVMOBiLi/s0-902764585c283cdcd2a0f0647decc397x22789y8z1229290056
 *  </upnp:albumArtURI>
 * </item><item id="s0-902764585c283cdcd2a0f0647de
 * 
 */
/**
 *
 * @author mikebevz
 */
public class ContentDirectoryXmlHandler extends DefaultHandler {

    private List<Container> containers;
    private Container currentContainer;
    private StringBuilder builder;
    private List<Entity> listItems;
    
    // Container element
    final private static String TITLE = "title";
    final private static String CLASS = "class";
    final private static String DATE = "date";
    final private static String DESCRIPTION = "description";
    final private static String WRITE_STATUS = "writeStatus";
    final private static String ICON = "icon";
    final private static String CONTAINER = "container";
    
    // Item element
    final private static String ITEM = "item";
    
    final private static String ARTIST = "artist";
    final private static String ALBUM = "album";
    final private static String GENRE = "genre";
    final private static String RES = "res";
    final private static String ALBUM_ART_URI = "albumArtURI";
    
            
    
    // Container Attributes
    final private static String OBJECT_ID = "id";
    final private static String DLNA_MANAGED = "dlnaManaged";
    final private static String PARENT_ID = "parentID";
    final private static String CHILD_COUNT = "childCount";
    final private static String RESTRICTED = "restricted";
    
    
    //Res Attributes
    final private static String SIZE = "size";
    final private static String PROTOCOL_INFO = "protocolInfo";
    
    //AlbumArtURI Attributes
    final private static String PROFILE_ID = "profileID";
    
    
    
    private Item currentItem;
    private List<Item> items;

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
                listItems.add((Entity)currentContainer);
            }
            builder.setLength(0);
        }
        
        if (this.currentItem != null) {
            if (localName.equalsIgnoreCase(TITLE)) {
                currentItem.setTitle(builder.toString());
            } else if (localName.equalsIgnoreCase(CLASS)) {
                currentItem.setCclass(builder.toString());
                //} else if (localName.equalsIgnoreCase(DATE)) {
                //currentContainer.setDate(builder.toString());
            } else if (localName.equalsIgnoreCase(DESCRIPTION)) {
                currentItem.setDescription(builder.toString());
            } else if (localName.equalsIgnoreCase(ICON)) {
                currentItem.setIcon(builder.toString());
            } else if (localName.equalsIgnoreCase(ARTIST)) {
                currentItem.setArtist(builder.toString());
            } else if (localName.equalsIgnoreCase(ALBUM)) {
                currentItem.setAlbum(builder.toString());
            } else if (localName.equalsIgnoreCase(GENRE)) {
                currentItem.setGenre(builder.toString());
            } else if (localName.equalsIgnoreCase(RES)) {
                currentItem.setRes(builder.toString());
            } else if (localName.equalsIgnoreCase(ALBUM_ART_URI)) {
                currentItem.setAlbumArtUri(builder.toString());
            } else if (localName.equalsIgnoreCase(ITEM)) {
                items.add(currentItem);
                listItems.add((Entity)currentItem);
            }
            builder.setLength(0);
        }
    }

    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
        containers = new ArrayList<Container>();
        items = new ArrayList<Item>();
        listItems = new ArrayList<Entity>();
        builder = new StringBuilder();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);

        if (localName.equalsIgnoreCase(ITEM)) {
            this.currentItem = new Item();
            int length = attributes.getLength();
            
            for (int i = 0; i < length; i++) {
                String name = attributes.getLocalName(i);

                if (name.equalsIgnoreCase(OBJECT_ID)) {
                    this.currentItem.setObjectId(attributes.getValue(i));
                }

                if (name.equalsIgnoreCase(DLNA_MANAGED)) {
                    this.currentItem.setDlnaManaged(attributes.getValue(i));
                }

                if (name.equalsIgnoreCase(PARENT_ID)) {
                    this.currentItem.setParentID(attributes.getValue(i));
                }

                if (name.equalsIgnoreCase(RESTRICTED)) {
                    this.currentItem.setRestricted(attributes.getValue(i));
                }
            }
        }
        
        if (localName.equalsIgnoreCase(RES)) {
            int length = attributes.getLength();
            for (int i = 0; i < length; i++) {
                String name = attributes.getLocalName(i);
                
                if (name.equalsIgnoreCase(SIZE)) {
                    this.currentItem.setResSize(attributes.getValue(i));
                }
                
                if (name.equalsIgnoreCase(PROTOCOL_INFO)) {
                    this.currentItem.setResProtocolInfo(attributes.getValue(i));
                }
            }
        }
        
        if (localName.equalsIgnoreCase(ALBUM_ART_URI)) {
            int length = attributes.getLength();
            for (int i = 0; i < length; i++) {
                String name = attributes.getLocalName(i);
                
                if (name.equalsIgnoreCase(PROFILE_ID)) {
                    this.currentItem.setAlbumArtUriProfileId(attributes.getValue(i));
                }
                
            }
        }
        

        if (localName.equalsIgnoreCase(CONTAINER)) {
            this.currentContainer = new Container();
            int length = attributes.getLength();
            for (int i = 0; i < length; i++) {
                String name = attributes.getLocalName(i);

                if (name.equalsIgnoreCase(OBJECT_ID)) {
                    this.currentContainer.setObjectId(attributes.getValue(i));
                }

                if (name.equalsIgnoreCase(DLNA_MANAGED)) {
                    this.currentContainer.setDlnaManaged(attributes.getValue(i));
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

    List<Entity> getContainers() {
        return this.listItems;
    }
    
    
}
