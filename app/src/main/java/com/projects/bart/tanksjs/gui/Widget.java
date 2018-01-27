package com.projects.bart.tanksjs.gui;

import android.view.MotionEvent;

import com.projects.bart.tanksjs.Graphics;
import com.projects.bart.tanksjs.Point;
import com.projects.bart.tanksjs.PointF;
import com.projects.bart.tanksjs.Rect;
import com.projects.bart.tanksjs.Sprite;
import com.projects.bart.tanksjs.Texture;

/**
 * Created by BART on 26.01.2018.
 */

public class Widget {

	public interface OnTouchEventListener {
		void onTouchEvent(MotionEvent event, PointF touch);
	}

	private Sprite background;
	private Rect region;
	private OnTouchEventListener touchEventListener;

	public Widget(Texture texture, Rect textureRegion) {
		this.background = new Sprite(texture, textureRegion);
		this.region = new Rect(0, 0, textureRegion.width, textureRegion.height);
	}

	public Widget(Point position, Texture texture, Rect textureRegion) {
		this.background = new Sprite(texture, textureRegion);
		this.region = new Rect(position.x, position.y, textureRegion.width, textureRegion.height);
	}

	public Rect getRegion() {
		return region;
	}

	public void setPosition(Point position) {
		region.x = position.x;
		region.y = position.y;
	}

	public void setTouchEventListener(OnTouchEventListener touchListener) {
		this.touchEventListener = touchListener;
	}

	public void touchEvent(MotionEvent event, PointF touch) {
		if (touchEventListener != null)
			touchEventListener.onTouchEvent(event, touch);
	}

	public void draw(Graphics graphics) {
		graphics.drawSprite(background, region.x, region.y);
	}
}
