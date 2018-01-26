package com.projects.bart.tanksjs.game;

import com.projects.bart.tanksjs.Point;

/**
 * Created by BART on 16.10.2017.
 */

class GameObject {

	protected Point cell;

	public GameObject(Point cell) {
		this.cell = new Point(cell);
	}

	public void onIntersect(GameObject obj) {}
	public void update(float dt) {}
	public void draw(Painter p) {}

	public Point getCell() {
		return cell;
	}
}
