/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mikebevz.upnp.mediaserver1;

import android.os.AsyncTask;
import com.mikebevz.upnp.mediaserver.content_directory.Container;
import com.mikebevz.upnp.mediaserver.content_directory.SaxContentParser;
import java.util.List;
import org.cybergarage.upnp.Action;

/**
 *
 * @author mikebevz
 */
public class BrowseTask extends AsyncTask<Action, Void, List<Container>> implements TaskFactoryTask {

    private OnTaskFactory delegate;

    @Override
    protected List<Container> doInBackground(Action... actions) {
        //throw new UnsupportedOperationException("Not supported yet.");
        Action action = actions[0];//device.getService(TaskFactory.CONTENT_DIRECTORY_SERVICE).getAction(TaskFactory.BROWSE_ACTION);
        action.setArgumentValue("ObjectID", "0");
        action.setArgumentValue("BrowseFlag", "BrowseDirectChildren");
        action.setArgumentValue("Filter", "*");
        action.setArgumentValue("StartingIndex", "0");
        action.setArgumentValue("RequestedCount", "10");
        action.setArgumentValue("SortCriteria", "");

        SaxContentParser parser = new SaxContentParser();

        if (action.postControlAction() == true) {
            //ArgumentList outArgList = action.getOutputArgumentList();

            String result = action.getArgument("Result").getValue();
            //String numberReturned = action.getArgument("NumberReturned").getValue();
            //String totalMatches = action.getArgument("TotalMatches").getValue();
            //String updateID = action.getArgument("UpdateID").getValue();

            parser.setMessage(result);


        }

        
        List<Container> containers = parser.parse();

        return containers;
    }

    @Override
    protected void onPostExecute(List<Container> result) {
        super.onPostExecute(result);
        this.delegate.onTaskFactorySuccess(result);
    }

    public void setOnTaskFactoryHandler(OnTaskFactory delegate) {
        this.delegate = delegate;
    }
}
