package com.projects.bart.tanksjs.gui;

import android.view.MotionEvent;

import com.projects.bart.tanksjs.Graphics;
import com.projects.bart.tanksjs.Point;
import com.projects.bart.tanksjs.PointF;

import java.util.ArrayList;

/**
 * Created by BART on 19.10.2017.
 */

//TODO: обрабатывать все event's

public class GUI {

	private ArrayList<Widget> widgets = new ArrayList<>();

	public void addWidget(Widget widget) {
		widgets.add(widget);
	}

	public void touchEvent(MotionEvent event, PointF touch) {
		for (Widget w : widgets) {
			if (w.getRegion().contains(touch.toPoint())) {
				w.touchEvent(event, touch);
			}
		}
	}

	public void draw(Graphics g) {
		for (Widget w : widgets) {
			w.draw(g);
		}
	}

	public void resize(int width, int height) {

	}

}
