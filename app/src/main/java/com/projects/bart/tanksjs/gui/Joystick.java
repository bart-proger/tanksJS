package com.projects.bart.tanksjs.gui;

import android.view.MotionEvent;

import com.projects.bart.tanksjs.Point;
import com.projects.bart.tanksjs.PointF;

/**
 * Created by BART on 19.10.2017.
 */

public class Joystick {

	public enum Direction { UP, LEFT, DOWN, RIGHT };

	public class MoveStickEvent {
		public Direction direction;
		public PointF vector;
	}

	public interface OnMoveStickListener {
		void onMoveStick(MoveStickEvent event);
	}

	OnMoveStickListener moveStickListener = null;

	public void setMoveStickListener(OnMoveStickListener listener) {
		moveStickListener = listener;
	}

	public void touchEvent(MotionEvent event, PointF touch) {

	}

}
