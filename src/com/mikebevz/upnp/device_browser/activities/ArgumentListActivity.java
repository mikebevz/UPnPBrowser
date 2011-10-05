package com.mikebevz.upnp.device_browser.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import com.mikebevz.upnp.R;
import com.mikebevz.upnp.UpnpBrowserApp;
import com.mikebevz.upnp.device_browser.ArgumentListAdapter;
import com.mikebevz.upnp.tasks.GetActionArgumentsTask;
import com.mikebevz.upnp.tasks.OnActionArgumentsList;
import org.cybergarage.upnp.Action;
import org.cybergarage.upnp.ArgumentList;

/**
 * @author mikebevz
 */
public class ArgumentListActivity extends Activity implements OnActionArgumentsList {

private ArgumentListAdapter adapter;
private ProgressDialog dialog;
private UpnpBrowserApp app;
private Integer actionPosition;

@Override
public void onCreate(Bundle icicle) {
  super.onCreate(icicle);
  setContentView(R.layout.list_view);

  app = (UpnpBrowserApp) getApplication();

  Bundle bundle = getIntent().getExtras();
  actionPosition = bundle.getInt("position");

  Action action = (Action) ((UpnpBrowserApp) getApplication()).getActionList().get(actionPosition);

  this.setTitle(action.getName() + " arguments");


  GetActionArgumentsTask getArgumentsTask = new GetActionArgumentsTask();
  getArgumentsTask.setOnActionArgumentsListHandler(this);
  getArgumentsTask.execute(action);


  ListView listView = (ListView) findViewById(R.id.list_view);

  adapter = new ArgumentListAdapter(this);
  listView.setAdapter(adapter);
}


public void OnActionArgumentsListSuccess(ArgumentList aList) {
  Log.d("ArgumentsList", String.valueOf(aList.size()));
  app.setArgumentList(aList);
  adapter.setArguments(aList);
  adapter.notifyDataSetChanged();
  if (dialog.isShowing()) {
    dialog.dismiss();
  }
}

public void OnActionArgumentsListPreExecute() {
  dialog = ProgressDialog.show(this, "", "Downloading...", true);
  dialog.setCancelable(true);
}

@Override
public boolean onCreateOptionsMenu(Menu menu) {
  MenuInflater inflater = getMenuInflater();
  inflater.inflate(R.menu.argument_list_menu, menu);
  return true;
}

@Override
public boolean onMenuItemSelected(int featureId, MenuItem item) {

  switch (item.getItemId()) {
    case R.id.invoke_action:
      this.invokeAction();
      return true;
    default:
      return true;
  }

}

private void invokeAction() {
  Intent intent = new Intent(this, InvokeActionActivity.class);
  intent.putExtra("position", actionPosition);
  startActivity(intent);
}
}
