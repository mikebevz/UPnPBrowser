/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mikebevz.upnp.device_browser.activities;

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
import com.mikebevz.upnp.device_browser.ActionListAdapter;
import com.mikebevz.upnp.tasks.GetServiceActionsTask;
import com.mikebevz.upnp.tasks.OnServiceActionsList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.cybergarage.upnp.ActionList;
import org.cybergarage.upnp.Service;

/**
 *
 * @author mikebevz
 */
public class ActionListActivity extends Activity implements OnServiceActionsList, OnItemClickListener {

    private ActionList sList;
    private ActionListAdapter adapter;
    private ProgressDialog dialog;

    /** Called when the activity is first created.
     * @param icicle 
     */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.list_view);

        Bundle bundle = getIntent().getExtras();
        int position = bundle.getInt("position");
        Service service = (Service) ((UpnpBrowserApp) getApplication()).getServiceList().get(position);

        List<String> list = Arrays.asList(service.getServiceID().split(":"));
        Collections.reverse(list);

        this.setTitle(getApplication().getResources().getString(R.string.actions_at) + list.toArray()[0].toString());
        
        GetServiceActionsTask getActionsTask = new GetServiceActionsTask();
        getActionsTask.setOnServiceActionListHandler(this);
        getActionsTask.execute(service);


        ListView listView = (ListView) findViewById(R.id.list_view);
        adapter = new ActionListAdapter(this);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);

    }

    /**
     * 
     * @param av
     * @param view
     * @param position
     * @param id
     */
    public void onItemClick(AdapterView<?> av, View view, int position, long id) {

        Intent intent = new Intent(this, ArgumentListActivity.class);
        intent.putExtra("position", position);
        startActivity(intent);
    }

    /**
     * 
     * @param aList
     */
    public void OnServiceActionsListSuccess(ActionList aList) {
        this.sList = aList;
        
        ((UpnpBrowserApp) getApplication()).setActionList(aList);
        Log.d("ServiceList", String.valueOf(sList.size()));
        adapter.setActions(sList);
        adapter.notifyDataSetChanged();
        if (dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    /**
     * 
     */
    public void OnServiceActionListPreExecute() {
        
        dialog = ProgressDialog.show(this, "", getResources().getString(R.string.loading), true);
        dialog.setCancelable(true);
        //dialog.setCancelMessage("Cannot load data. Try again");
    }

    /**
     * 
     * @param value
     */
    public void OnServiceActionsListProgressUpdate(Integer value) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }
}
