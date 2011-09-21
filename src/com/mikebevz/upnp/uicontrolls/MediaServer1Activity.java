/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mikebevz.upnp.uicontrolls;

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
import com.mikebevz.upnp.mediaserver.content_directory.ContainerListAdapter;
import com.mikebevz.upnp.mediaserver.content_directory.Entity;
import com.mikebevz.upnp.mediaserver1.BrowseTask;
import com.mikebevz.upnp.mediaserver1.ContainerListActivity;
import com.mikebevz.upnp.mediaserver1.OnTaskFactory;
import com.mikebevz.upnp.mediaserver1.TaskFactory;
import com.mikebevz.upnp.tasks.GetDeviceTask;
import com.mikebevz.upnp.tasks.OnDeviceDetails;
import java.util.List;
import org.cybergarage.upnp.Device;

/**
 *
 * @author mikebevz
 */
public class MediaServer1Activity extends Activity implements OnDeviceDetails, OnTaskFactory, OnItemClickListener {

    /*
    // Connection Manager Service
    private Action getProtocolInfoAction;
    private Action getCurrentConnectionIDsAction;
    private Action getCurrentConnectionInfoAction;
    // ContentDirectory Service
    private Action action;
    private Action getSortCapabilitiesAction;
    private Action getSystemUpdateIDAction;
    private Action getSearchCapabilitiesAction;
    private ServiceList sList;
     */
    private ProgressDialog dialog;
    private List<Entity> containerList;
    private int deviceNumber;

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
        //dialog = ProgressDialog.show(this, "", "Loading...", true);
    }

    public void OnDeviceDetailsSuccess(Device device) {
        setTitle(device.getFriendlyName());

        BrowseTask task = new BrowseTask(device, TaskFactory.CONTENT_DIRECTORY_SERVICE, TaskFactory.BROWSE_ACTION);
        task.setOnTaskFactoryHandler(this);
        task.execute();

        //dialog.dismiss();
    }

    public void OnDeviceDetailsProgressUpdate(Integer integer) {
    }

    public void onTaskFactoryPreExecute() {
        dialog = ProgressDialog.show(this, "", "Loading...", true);
        dialog.setCancelable(true);
    }

    public void onTaskFactorySuccess(List<Entity> result) {
        this.containerList = result;
        ContainerListAdapter adapter = new ContainerListAdapter(this);
        adapter.setContainers(result);

        ListView cList = (ListView) findViewById(R.id.container_list);
        cList.setAdapter(adapter);
        cList.setOnItemClickListener(this);

        Log.d("Containers: ", String.valueOf(result.size()));
        dialog.dismiss();
    }

    public void onItemClick(AdapterView<?> av, View view, int position, long id) {

        Intent intent = new Intent(this, ContainerListActivity.class);
        intent.putExtra("objectId", containerList.get(position).getObjectId());
        intent.putExtra("device", deviceNumber);
        startActivity(intent);
    }


    public void OnDeviceDetailsCancelled(Exception lastException) {
        Log.e("MediaServer", lastException.getMessage());
        dialog.dismiss();
        //throw new UnsupportedOperationException("Not supported yet.");
    }
}
