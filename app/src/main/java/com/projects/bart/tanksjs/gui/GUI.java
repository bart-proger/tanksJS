package com.projects.bart.tanksjs.gui;

import android.view.MotionEvent;

import com.projects.bart.tanksjs.Graphics;
import com.projects.bart.tanksjs.Point;
import com.projects.bart.tanksjs.PointF;

import java.util.ArrayList;

/**
 * Created by BART on 19.10.2017.
 */

public class GUI {

	private ArrayList<Button> buttons = new ArrayList<>();

	public void addButton(Button button) {
		buttons.add(button);
	}

	public void touchEvent(MotionEvent event, PointF touch) {
		for (Button b : buttons) {
			if (b.getRect().contains(touch.toPoint())) {
				b.onClick();
			}
		}
	}

	public void draw(Graphics g) {
		for (Button b : buttons) {
			g.drawSprite(b, b.getRect().x, b.getRect().y);
		}
	}

	public void resize(int width, int height) {

	}

}
