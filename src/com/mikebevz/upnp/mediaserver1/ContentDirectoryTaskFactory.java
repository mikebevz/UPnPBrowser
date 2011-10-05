package com.mikebevz.upnp.mediaserver1;

import android.os.AsyncTask;
import org.cybergarage.upnp.Action;

/**
 * @author mikebevz
 */
class ContentDirectoryTaskFactory {

AsyncTask getTaskForAction(Action action) throws Exception {

  switch (TaskFactory.name2Int(action.getName())) {
    case TaskFactory.BROWSE_ACTION_I:
      //return new BrowseTask();
    default:
      throw new Exception("Action " + action.getName() + " is not supported");

  }

}


}
