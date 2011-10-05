package com.mikebevz.upnp.mediaserver1;

import android.os.AsyncTask;
import org.cybergarage.upnp.Action;
import org.cybergarage.upnp.Service;

/**
 * @author mikebevz
 */
class TaskFactory {

public static final String CONTENT_DIRECTORY_SERVICE = "urn:upnp-org:serviceId:ContentDirectory";
public static final Integer CONTENT_DIRECTORY_SERVICE_I = 100;
public static final String CONNECTION_MANAGER_SERVICE = "urn:upnp-org:serviceId:ConnectionManager";
public static final Integer CONNECTION_MANAGER_SERVICE_I = 110;
public static final String MEDIA_RECEIVER_REGISTRAR_SERVICE = "urn:microsoft.com:service:X_MS_MediaReceiverRegistrarSCPD";
public static final Integer MEDIA_RECEIVER_REGISTRAR_SERVICE_I = 120;
public static final String MS_CONTENT_DIRECTORY_SERVICE = "urn:schemas-microsoft-com:service:MSContentDirectory";
public static final Integer MS_CONTENT_DIRECTORY_SERVICE_I = 130;
// ContentDirectory Service Actions
public static final String BROWSE_ACTION = "Browse";
public static final int BROWSE_ACTION_I = 1;
private static final String GET_SEARCH_CAPABILITIES_ACTION = "GetSearchCapabilities";
private static final int GET_SEARCH_CAPABILITIES_ACTION_I = 2;
private static final String GET_SORT_CAPABILITIES_ACTION = "GetSortCapabilities";
private static final int GET_SORT_CAPABILITIES_ACTION_I = 3;
private static final String GET_SYSTEM_UPDATE_ID_ACTION = "GetSystemUpdateID";
private static final int GET_SYSTEM_UPDATE_ID_ACTION_I = 4;
private static final String SEARCH_ACTION = "Search";
private static final int SEARCH_ACTION_I = 5;

public TaskFactory() {
}

public AsyncTask getTaskForAction(Action action) throws Exception {
  String actionId = action.getName();
  Service service = action.getService();

  if (service.getServiceType().equals(CONTENT_DIRECTORY_SERVICE)) {
    ContentDirectoryTaskFactory factory = new ContentDirectoryTaskFactory();
    return factory.getTaskForAction(action);
  }
  // Default TODO: Investigate
  ContentDirectoryTaskFactory factory = new ContentDirectoryTaskFactory();
  return factory.getTaskForAction(action);

}

public static int name2Int(String name) {

  if (name.equals(BROWSE_ACTION)) {
    return BROWSE_ACTION_I;
  }

  if (name.equals(GET_SEARCH_CAPABILITIES_ACTION)) {
    return GET_SEARCH_CAPABILITIES_ACTION_I;
  }

  if (name.equals(GET_SORT_CAPABILITIES_ACTION)) {
    return GET_SORT_CAPABILITIES_ACTION_I;
  }

  if (name.equals(GET_SYSTEM_UPDATE_ID_ACTION)) {
    return GET_SYSTEM_UPDATE_ID_ACTION_I;
  }

  if (name.equals(SEARCH_ACTION)) {
    return SEARCH_ACTION_I;
  }

  return 0;

}
}
