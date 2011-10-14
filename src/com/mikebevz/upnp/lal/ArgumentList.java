package com.mikebevz.upnp.lal;

import java.util.ArrayList;

public class ArgumentList extends ArrayList<Argument> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3298540884442287175L;

	public ArgumentList() {
	}

	public Argument getArgument(int pos) {

		return get(pos);

	}
}
