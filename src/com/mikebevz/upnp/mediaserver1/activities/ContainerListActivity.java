/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mikebevz.upnp.mediaserver1.activities;

import com.mikebevz.upnp.mediaserver1.models.Entity;
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
import com.mikebevz.upnp.mediaserver1.BrowseTask;
import com.mikebevz.upnp.mediaserver1.BrowserTaskResult;
import com.mikebevz.upnp.mediaserver1.ContainerListAdapter;
import com.mikebevz.upnp.mediaserver1.OnTaskFactory;
import com.mikebevz.upnp.mediaserver1.TaskFactory;
import com.mikebevz.upnp.mediaserver1.models.Item;
import java.util.List;
import org.cybergarage.upnp.Device;

/**
 *
 * @author mikebevz
 */
public class ContainerListActivity extends Activity implements OnTaskFactory, OnItemClickListener {

    private String objectId;
    private ProgressDialog dialog;
    private Integer deviceNumber;
    private List<Entity> entityList;
    public static final String CONTAINER_STORAGE_FOLDER = "object.container.storageFolder";
    public static final String CONTAINER_PHOTO_ALBUM = "object.container.album.photoAlbum";
    public static final String CONTAINER_MUSIC_ARTIST = "object.container.person.musicArtist";
    public static final String CONTAINER_MUSIC_GENRE = "object.container.genre.musicGenre";
    public static final String CONTAINER_MUSIC_ALBUM = "object.container.album.musicAlbum";
    public static final String ITEM_VIDEO = "object.item.videoItem";
    public static final String ITEM_PHOTO = "object.item.imageItem.photo";
    public static final String ITEM_MUSIC = "object.item.audioItem.musicTrack";
    
    ContainerListAdapter adapter;
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        setContentView(R.layout.media_server_frontpage);

        objectId = getIntent().getExtras().getString("objectId");
        //Log.d("ObjectID", objectId);
        deviceNumber = getIntent().getExtras().getInt("device");
        Device device = ((UpnpBrowserApp) getApplication()).getDeviceList().getDevice(deviceNumber);
        
        adapter = new ContainerListAdapter(this, device, objectId);
        
        ListView cList = (ListView) findViewById(R.id.container_list);
        entityList = adapter.getContainers();
        cList.setAdapter(adapter);
        cList.setOnItemClickListener(this);
        //BrowseTask task = new BrowseTask(device, TaskFactory.CONTENT_DIRECTORY_SERVICE, TaskFactory.BROWSE_ACTION);
        //task.setOnTaskFactoryHandler(this);
        //task.setObjectID(objectId);
        //task.execute();


    }

    public void onTaskFactoryPreExecute() {
        dialog = ProgressDialog.show(this, "", "Loading...", true);
    }

    public void onTaskFactorySuccess(BrowserTaskResult result) {

        this.entityList = result.getEntities();
        //ContainerListAdapter adapter = new ContainerListAdapter(this);
        //adapter.setContainers(entityList);

        
        //ListView cList = (ListView) findViewById(R.id.container_list);
        //cList.setAdapter(adapter);
        //cList.setOnItemClickListener(this);


        dialog.dismiss();
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
            Item item = (Item) entityList.get(position);
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
            Item item = (Item) entityList.get(position);
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
