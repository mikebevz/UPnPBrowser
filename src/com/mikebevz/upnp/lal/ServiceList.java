package com.mikebevz.upnp.lal;

import java.util.ArrayList;

public class ServiceList extends ArrayList<Service> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7087550087466291632L;

	public ServiceList() {
	}

	public Service getService(int pos) {

		return get(pos);

	}
}
