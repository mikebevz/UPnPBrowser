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
import com.mikebevz.upnp.mediaserver1.ContainerListAdapter;
import com.mikebevz.upnp.mediaserver1.models.Entity;
import com.mikebevz.upnp.tasks.GetDeviceTask;
import com.mikebevz.upnp.tasks.OnDeviceDetails;
import org.cybergarage.upnp.Device;

import java.util.List;

/**
 * @author mikebevz
 */
public class MediaServer1Activity extends Activity implements OnDeviceDetails, OnItemClickListener {

private ProgressDialog dialog;
private int deviceNumber;
private ContainerListAdapter adapter;

@Override
public void onCreate(Bundle icicle) {
  super.onCreate(icicle);
  setContentView(R.layout.media_server_frontpage);

  Bundle bundle = getIntent().getExtras();
  deviceNumber = bundle.getInt("device");

  GetDeviceTask task = new GetDeviceTask(getApplication());
  task.setOnDeviceDetailsHandler(this);
  task.execute(deviceNumber);
}

public void OnDeviceDetailsPreExecute() {
}

public void OnDeviceDetailsSuccess(Device device) {
  setTitle(device.getFriendlyName());


  adapter = new ContainerListAdapter(this, device);
  List<Entity> containerList = adapter.getContainers();
  ListView cList = (ListView) findViewById(R.id.container_list);
  cList.setAdapter(adapter);
  cList.setOnItemClickListener(this);

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
