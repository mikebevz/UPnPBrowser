/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mikebevz.upnp.mediaserver1;

import android.app.ProgressDialog;
import android.content.Context;
import com.mikebevz.upnp.mediaserver1.models.Entity;
import com.mikebevz.upnp.mediaserver1.models.Container;
import com.mikebevz.upnp.mediaserver1.models.Item;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.mikebevz.upnp.R;


import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import org.cybergarage.upnp.Device;

/**
 *
 * @author mikebevz
 */
public class ContainerListAdapter extends BaseAdapter implements OnTaskFactory  {

    private List<Entity> containers;
    private LayoutInflater mInflater;
    private final Bitmap icon_container;
    private final Bitmap icon_item;
    public static final String CONTAINER_CCLASS = "object.container";
    public static final String ITEM_CCLASS = "object.item";
    //private final Bitmap icon_music;
    //private final Bitmap icon_mp3;
    
    private final Pattern isContainer;
    private final Pattern isItem;
    private final Context context;
    private final Device device;
    private ProgressDialog dialog;
    private String objectId;
    private String numberReturned;

    
    public ContainerListAdapter(Context context, Device device) {
        //super(context, wrapped, pendingResource);
        this.context = context;
        this.device = device;
        mInflater = LayoutInflater.from(context);

        containers = new ArrayList<Entity>();
        
        
        icon_container = BitmapFactory.decodeResource(context.getResources(), R.drawable.folder);
        icon_item = BitmapFactory.decodeResource(context.getResources(), R.drawable.file);
        //icon_music = BitmapFactory.decodeResource(context.getResources(), R.drawable.file_music);
        //icon_mp3 = BitmapFactory.decodeResource(context.getResources(), R.drawable.file_mp3);
        
        
        isContainer = Pattern.compile(CONTAINER_CCLASS);
        isItem = Pattern.compile(ITEM_CCLASS);
        
        
        BrowseTask task = new BrowseTask(device, TaskFactory.CONTENT_DIRECTORY_SERVICE, TaskFactory.BROWSE_ACTION);
        task.setOnTaskFactoryHandler(this);
        task.execute();
    }

    public ContainerListAdapter(Context context, Device device, String objectId) {
        setObjectId(objectId);
        
        this.context = context;
        this.device = device;
        mInflater = LayoutInflater.from(context);

        containers = new ArrayList<Entity>();
        
        
        icon_container = BitmapFactory.decodeResource(context.getResources(), R.drawable.folder);
        icon_item = BitmapFactory.decodeResource(context.getResources(), R.drawable.file);
        //icon_music = BitmapFactory.decodeResource(context.getResources(), R.drawable.file_music);
        //icon_mp3 = BitmapFactory.decodeResource(context.getResources(), R.drawable.file_mp3);
        
        
        isContainer = Pattern.compile(CONTAINER_CCLASS);
        isItem = Pattern.compile(ITEM_CCLASS);
        
        
        BrowseTask task = new BrowseTask(device, TaskFactory.CONTENT_DIRECTORY_SERVICE, TaskFactory.BROWSE_ACTION);
        task.setOnTaskFactoryHandler(this);
        task.setObjectId(getObjectId());
       
        task.execute();
        
    }
    
    
    
    /**
     * @return the containers
     */
    public List<Entity> getContainers() {
        return containers;
    }

    /**
     * @param containers the containers to set
     */
    public void setContainers(List<Entity> containers) {
        this.containers = containers;
    }

    public int getCount() {
        return this.getContainers().size();
    }

    public Object getItem(int position) {
        return this.getContainers().get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View cView, ViewGroup parent) {

        ViewHolder holder;
        Log.d("Number of elements", String.valueOf(getContainers().size()));
        Log.d("Number returned", getNumberReturned());
        Integer numElements = getContainers().size();
        Integer numReturned = Integer.parseInt(getNumberReturned());
        
        
        if (numElements == position+1) {
            Log.d("List","Let's get some more results");
        }
        
        Log.d("Position", String.valueOf(position));
        
        if (cView == null) {
            cView = mInflater.inflate(R.layout.list_item_icon_text, null);

            holder = new ViewHolder();
            holder.text = (TextView) cView.findViewById(R.id.text);
            holder.description = (TextView) cView.findViewById(R.id.description);
            holder.icon = (ImageView) cView.findViewById(R.id.icon);
            cView.setTag(holder);
        } else {
            holder = (ViewHolder) cView.getTag();
        }
        
        
        
        
        if (isContainer.matcher(this.getContainers().get(position).getCclass()).find()) {
            Container container = (Container) this.getContainers().get(position);    
            holder.text.setText(container.getTitle());
            
            if (!container.getIcon().equals("")) {
                holder.icon.setImageBitmap(getImageBitmap(this.getContainers().get(position).getIcon()));
            } else {
                holder.icon.setImageBitmap(icon_container);
            }

        } else if (isItem.matcher(this.getContainers().get(position).getCclass()).find()) {
            
            Item item = (Item) this.getContainers().get(position);
            holder.text.setText(item.getTitle());
            
            if (!item.getAlbumArtUri().equals("")) {
                holder.icon.setImageBitmap(getImageBitmap(item.getAlbumArtUri()));
            } else {
                holder.icon.setImageBitmap(icon_item);
            }
        }
        
        

        return cView;
    }

    private Bitmap getImageBitmap(String url) {
        Bitmap bm = null;
        try {
            URL aURL = new URL(url);
            URLConnection conn = aURL.openConnection();
            conn.connect();
            InputStream is = conn.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is, 8 * 1024);
            bm = BitmapFactory.decodeStream(bis);
            bis.close();
            is.close();
        } catch (IOException e) {
            Log.e("ImageBitmap", "Error getting bitmap", e);
        }
        return bm;
    }

    public void onTaskFactorySuccess(BrowserTaskResult result) {
        //throw new UnsupportedOperationException("Not supported yet.");
        this.setContainers(result.getEntities());
        this.setNumberReturned(result.getNumberReturned());
        this.notifyDataSetChanged();
        dialog.dismiss();
    }

    public void onTaskFactoryPreExecute() {
        //throw new UnsupportedOperationException("Not supported yet.");
        dialog = ProgressDialog.show(context, "Loading", "It takes some time depending on your WiFi router load", true, true);
    }

    private String getObjectId() {
        return this.objectId;
    }

    private void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    private void setNumberReturned(String numberReturned) {
        this.numberReturned = numberReturned;
    }

    /**
     * @return the numberReturned
     */
    public String getNumberReturned() {
        return numberReturned;
    }

    

    

    static class ViewHolder {

        TextView text;
        TextView description;
        ImageView icon;
    }
}
