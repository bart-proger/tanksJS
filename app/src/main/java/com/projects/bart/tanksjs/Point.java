package com.projects.bart.tanksjs;

import android.content.res.Resources;

/**
 * Created by BART on 12.10.2017.
 */

public class Point {

	private static final float DENSITY = Resources.getSystem().getDisplayMetrics().density;

	public int x;
	public int y;

	public Point(Point source) {
		this.x = source.x;
		this.y = source.y;
	}

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void add(Point a) {
		this.x += a.x;
		this.y += a.y;
	}

	public void sub(Point a) {
		this.x -= a.x;
		this.y -= a.y;
	}

	public Point toPx() {
		return new Point((int)(x * DENSITY), (int)(y * DENSITY));
	}

	public Point toDp() {
		return new Point((int)(x / DENSITY), (int)(y / DENSITY));
	}
}
