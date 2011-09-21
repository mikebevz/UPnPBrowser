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
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import com.mikebevz.upnp.R;
import com.mikebevz.upnp.UpnpBrowserApp;
import com.mikebevz.upnp.device_browser.ArgumentListAdapter;
import com.mikebevz.upnp.tasks.GetActionArgumentsTask;
import com.mikebevz.upnp.tasks.OnActionArgumentsList;
import org.cybergarage.upnp.Action;
import org.cybergarage.upnp.ArgumentList;

/**
 *
 * @author mikebevz
 */
public class ArgumentListActivity extends Activity implements OnActionArgumentsList, OnItemClickListener {
    
    private ArgumentList aList;
    private ArgumentListAdapter adapter;
    private ProgressDialog dialog;
    private UpnpBrowserApp app;
    private Action action;
    private Integer actionPosition;

    /** Called when the activity is first created.
     * @param icicle 
     */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.list_view);  
        
        app = (UpnpBrowserApp)getApplication(); 
        
        Bundle bundle = getIntent().getExtras();
        actionPosition = bundle.getInt("position");
        
        action = (Action) ((UpnpBrowserApp)getApplication()).getActionList().get(actionPosition);
        
        this.setTitle(action.getName() + " arguments");
        

        GetActionArgumentsTask getArgumentsTask = new GetActionArgumentsTask();
        getArgumentsTask.setOnActionArgumentsListHandler(this);        
        getArgumentsTask.execute(action);
        
        
        ListView listView = (ListView)findViewById(R.id.list_view);
        
        adapter = new ArgumentListAdapter(this);
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
        //Intent intent = new Intent(this, ArgumentDetails)
                
    }



    /**
     * 
     * @param aList
     */
    public void OnActionArgumentsListSuccess(ArgumentList aList) {
        this.aList = aList;
        Log.d("ArgumentsList", String.valueOf(aList.size()));
        //aList.getArgument(0).getActionNode().get
        app.setArgumentList(aList);
        adapter.setArguments(aList);
        adapter.notifyDataSetChanged();
        dialog.dismiss();
    }

    /**
     * 
     */
    public void OnActionArgumentsListPreExecute() {
        dialog = ProgressDialog.show(this, "", "Downloading...", true);
        dialog.setCancelable(true);
    }

    
    
    /**
     * 
     * @param integer
     */
    public void OnActionArgumentsProgressUpdate(Integer integer) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    
    /**
     * 
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.argument_list_menu, menu);
        return true;
    }

    /**
     * 
     * @param featureId
     * @param item
     * @return
     */
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
