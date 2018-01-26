package com.projects.bart.tanksjs.gui;

import com.projects.bart.tanksjs.Point;
import com.projects.bart.tanksjs.Rect;
import com.projects.bart.tanksjs.Sprite;
import com.projects.bart.tanksjs.Texture;

/**
 * Created by BART on 17.10.2017.
 */

public class Button extends Sprite {

	private Rect rect;

	public Button(Texture texture, Rect textureRegion) {
		super(texture, textureRegion);
		this.rect = new Rect(0, 0, textureRegion.width, textureRegion.height);
	}

	public Button(Point position, Texture texture, Rect textureRegion) {
		super(texture, textureRegion);
		this.rect = new Rect(position.x, position.y, textureRegion.width, textureRegion.height);
	}

	public void onClick() {
	}

	public Rect getRect() {
		return rect;
	}

	public void setPosition(Point position) {
		rect.x = position.x;
		rect.y = position.y;
	}
}
