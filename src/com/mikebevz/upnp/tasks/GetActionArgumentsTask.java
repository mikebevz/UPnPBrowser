/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mikebevz.upnp.tasks;

import android.os.AsyncTask;
import org.cybergarage.upnp.Action;
import org.cybergarage.upnp.ActionList;
import org.cybergarage.upnp.ArgumentList;

/**
 *
 * @author mikebevz
 */
public class GetActionArgumentsTask  extends AsyncTask<Action, Integer, ArgumentList> {
    
    OnActionArgumentsList delegate;
    
    
    @Override
    protected ArgumentList doInBackground(Action... services) {
        
        return services[0].getArgumentList();
    }
    
    @Override
    protected void onPostExecute(ArgumentList result) {
        this.delegate.OnActionArgumentsListSuccess(result);
    }

    public void setOnActionArgumentsListHandler(OnActionArgumentsList delegate) {
        this.delegate = delegate;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        this.delegate.OnActionArgumentsProgressUpdate(values[0]);
    }
}
