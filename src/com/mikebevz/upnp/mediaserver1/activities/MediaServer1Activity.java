/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mikebevz.upnp.mediaserver1.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import com.mikebevz.upnp.R;
import com.mikebevz.upnp.UpnpBrowserApp;
import com.mikebevz.upnp.mediaserver1.models.Entity;
import com.mikebevz.upnp.mediaserver1.ContainerListAdapter;
import com.mikebevz.upnp.tasks.GetDeviceTask;
import com.mikebevz.upnp.tasks.OnDeviceDetails;
import java.util.List;
import org.cybergarage.upnp.Device;

/**
 *
 * @author mikebevz
 */
public class MediaServer1Activity extends Activity implements OnDeviceDetails,  OnItemClickListener {

    private ProgressDialog dialog;
    private List<Entity> containerList;
    private int deviceNumber;
    ContainerListAdapter adapter;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.media_server_frontpage);

        Bundle bundle = getIntent().getExtras();
        deviceNumber = bundle.getInt("device");

        GetDeviceTask task = new GetDeviceTask((UpnpBrowserApp) getApplication());
        task.setOnDeviceDetailsHandler(this);
        task.execute(deviceNumber);
    }

    public void OnDeviceDetailsPreExecute() {
        //dialog = ProgressDialog.show(this, "", getResources().getString(R.string.loading), true);
    }

    public void OnDeviceDetailsSuccess(Device device) {
        setTitle(device.getFriendlyName());
        
        
        adapter = new ContainerListAdapter(this, device);
        containerList = adapter.getContainers();
        ListView cList = (ListView) findViewById(R.id.container_list);
        cList.setAdapter(adapter);
        cList.setOnItemClickListener(this);
        
    }

    public void OnDeviceDetailsProgressUpdate(Integer integer) {
    }

   

    public void onItemClick(AdapterView<?> av, View view, int position, long id) {

        Intent intent = new Intent(this, ContainerListActivity.class);
        intent.putExtra("objectId", adapter.getContainers().get(position).getObjectId());
        intent.putExtra("device", deviceNumber);
        startActivity(intent);
    }


    public void OnDeviceDetailsCancelled(Exception lastException) {
        Log.e("MediaServer", lastException.getMessage());
        if (dialog.isShowing()) {
            dialog.dismiss();
        }
    }
}
