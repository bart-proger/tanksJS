package com.projects.bart.tanksjs;

/**
 * Created by BART on 12.10.2017.
 */

public class Rect {

	public int x;
	public int y;
	public int width;
	public int height;

	public Rect(Rect source) {
		this.x = source.x;
		this.y = source.y;
		this.width = source.width;
		this.height = source.height;
	}

	public Rect(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public boolean contains(Point point) {
		return (point.x >= x && point.y >= y && point.x <= x + width && point.y <= y + height);
	}
}
