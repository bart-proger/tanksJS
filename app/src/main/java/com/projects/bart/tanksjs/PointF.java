package com.projects.bart.tanksjs;

import android.content.res.Resources;

/**
 * Created by BART on 12.10.2017.
 */

public class PointF {

	private static final float DENSITY = Resources.getSystem().getDisplayMetrics().density;

	public float x;
	public float y;

	public PointF(Point source) {
		this.x = source.x;
		this.y = source.y;
	}

	public PointF(float x, float y) {
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

	public PointF toPx() {
		return new PointF(x * DENSITY, y * DENSITY);
	}

	public PointF toDp() {
		return new PointF(x / DENSITY, y / DENSITY);
	}

	public Point toPoint() {
		return new Point((int)x, (int)y);
	}

}
