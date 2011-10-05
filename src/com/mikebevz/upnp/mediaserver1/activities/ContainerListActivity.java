package com.mikebevz.upnp.mediaserver1.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import com.mikebevz.upnp.R;
import com.mikebevz.upnp.UpnpBrowserApp;
import com.mikebevz.upnp.mediaserver1.ContainerListAdapter;
import com.mikebevz.upnp.mediaserver1.models.Item;
import org.cybergarage.upnp.Device;

/**
 * @author mikebevz
 */
public class ContainerListActivity extends Activity implements OnItemClickListener {

private ProgressDialog dialog;
private Integer deviceNumber;
private static final String CONTAINER_STORAGE_FOLDER = "object.container.storageFolder";
private static final String CONTAINER_PHOTO_ALBUM = "object.container.album.photoAlbum";
private static final String CONTAINER_MUSIC_ARTIST = "object.container.person.musicArtist";
private static final String CONTAINER_MUSIC_GENRE = "object.container.genre.musicGenre";
private static final String CONTAINER_MUSIC_ALBUM = "object.container.album.musicAlbum";
private static final String ITEM_VIDEO = "object.item.videoItem";
public static final String ITEM_PHOTO = "object.item.imageItem.photo";
private static final String ITEM_MUSIC = "object.item.audioItem.musicTrack";

private ContainerListAdapter adapter;

/**
 * Called when the activity is first created.
 */
@Override
public void onCreate(Bundle icicle) {
  super.onCreate(icicle);

  setContentView(R.layout.media_server_frontpage);

  String objectId = getIntent().getExtras().getString("objectId");
  //Log.d("ObjectID", objectId);
  deviceNumber = getIntent().getExtras().getInt("device");
  Device device = ((UpnpBrowserApp) getApplication()).getDeviceList().getDevice(deviceNumber);

  adapter = new ContainerListAdapter(this, device, objectId);

  ListView cList = (ListView) findViewById(R.id.container_list);
  //List<Entity> entityList = adapter.getContainers();

  cList.setOnItemClickListener(this);
  //cList.setFooterDividersEnabled(true);

  //TextView tv = new TextView(this);
  //tv.setText(R.string.loading);
  //cList.addFooterView(tv);
  //cList.setTranscriptMode(ALWAYS_SC);
  cList.setItemsCanFocus(false);
  cList.setSmoothScrollbarEnabled(true);
  cList.setAdapter(adapter);


}


public void onItemClick(AdapterView<?> av, View view, int position, long id) {

  String cclass = adapter.getContainers().get(position).getCclass();
  Log.d("CClass", cclass);


  if (cclass.matches(CONTAINER_STORAGE_FOLDER) || cclass.matches(CONTAINER_PHOTO_ALBUM)
        || cclass.matches(CONTAINER_MUSIC_ALBUM) || cclass.matches(CONTAINER_MUSIC_ARTIST)
        || cclass.matches(CONTAINER_MUSIC_GENRE)) {
    Intent intent = new Intent(this, ContainerListActivity.class);
    intent.putExtra("objectId", adapter.getContainers().get(position).getObjectId());
    intent.putExtra("device", deviceNumber);
    startActivity(intent);
  }


  if (cclass.matches(ITEM_MUSIC)) {
    Item item = (Item) adapter.getContainers().get(position);
    Intent intent = new Intent(android.content.Intent.ACTION_VIEW);
    Uri data = Uri.parse(item.getRes());
    intent.setDataAndType(data, "audio/mp3");

    try {
      startActivity(intent);
    } catch (ActivityNotFoundException e) {
      Log.e("PlayMP3", e.getMessage());
    }
  }

  if (cclass.matches(ITEM_VIDEO)) {
    Item item = (Item) adapter.getContainers().get(position);
    Intent intent = new Intent(android.content.Intent.ACTION_VIEW);
    Uri data = Uri.parse(item.getRes());
    intent.setDataAndType(data, "video/*");

    try {
      startActivity(intent);
    } catch (ActivityNotFoundException e) {
      Log.e("PlayVideo", e.getMessage());
    }
  }

}


}
