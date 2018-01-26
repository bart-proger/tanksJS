package com.projects.bart.tanksjs.game;

import com.projects.bart.tanksjs.Point;

/**
 * Created by BART on 16.10.2017.
 */

public class Staff extends GameObject {

	private boolean destroyed = false;

	public Staff(Point cell) {
		super(cell);
	}

	public boolean isDestroyed() {
		return destroyed;
	}

}
