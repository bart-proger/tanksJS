package com.projects.bart.tanksjs.gui;

import com.projects.bart.tanksjs.Point;
import com.projects.bart.tanksjs.Rect;
import com.projects.bart.tanksjs.Sprite;
import com.projects.bart.tanksjs.Texture;

/**
 * Created by BART on 26.01.2018.
 */

public class Widget extends Sprite {

	public interface OnTouchListener {
		void onTouch();
	}

	private Rect rect;
	private OnTouchListener touchListener;

	public Widget(Texture texture, Rect textureRegion) {
		super(texture, textureRegion);
		this.rect = new Rect(0, 0, textureRegion.width, textureRegion.height);
	}

	public Widget(Point position, Texture texture, Rect textureRegion) {
		super(texture, textureRegion);
		this.rect = new Rect(position.x, position.y, textureRegion.width, textureRegion.height);
	}

	public Rect getRect() {
		return rect;
	}

	public void setPosition(Point position) {
		rect.x = position.x;
		rect.y = position.y;
	}

	public void setTouchListener(OnTouchListener touchListener) {
		this.touchListener = touchListener;
	}

	public void onTouch()
	{
		if (touchListener != null)
			touchListener.onTouch();
	}
}
