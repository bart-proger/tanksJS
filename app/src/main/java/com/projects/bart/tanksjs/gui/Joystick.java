package com.projects.bart.tanksjs.gui;

import android.view.MotionEvent;

import com.projects.bart.tanksjs.Point;
import com.projects.bart.tanksjs.PointF;
import com.projects.bart.tanksjs.Rect;
import com.projects.bart.tanksjs.Texture;

/**
 * Created by BART on 19.10.2017.
 */

public class Joystick extends Widget {

	public enum Direction { UP, LEFT, DOWN, RIGHT };

	public final class MoveStickEvent {
		public MoveStickEvent(Direction direction, PointF vector) {
			this.direction = direction;
			this.vector = vector;
		}
		public Direction direction;
		public PointF vector;
	}

	public interface OnMoveStickListener {
		void onMoveStick(MoveStickEvent event);
	}

	PointF from = null, to = null;

	public Joystick(Texture texture, Rect textureRegion) {
		super(texture, textureRegion);

		setTouchEventListener(new OnTouchEventListener() {
			@Override
			public void onTouchEvent(MotionEvent event, PointF touch) {
				switch (event.getAction()) {
					case MotionEvent.ACTION_DOWN:
						from = new PointF(touch);
						break;
					case MotionEvent.ACTION_UP:
						from = null;
						to = null;
						break;
					case MotionEvent.ACTION_MOVE:
						to = new PointF(touch);
						break;
				}
				if (moveStickListener != null && from != null && to != null) {
					PointF vec = new PointF(to);
					vec.sub(from);
					Direction dir;
					if (Math.abs(vec.x) > Math.abs(vec.y)) {
						dir = vec.x <= 0 ? Direction.LEFT : Direction.RIGHT;
					}
					else {
						dir = vec.y <= 0 ? Direction.UP : Direction.DOWN;
					}
					moveStickListener.onMoveStick(new MoveStickEvent(dir, vec));
				}
			}
		});
	}

	OnMoveStickListener moveStickListener = null;

	public void setMoveStickListener(OnMoveStickListener listener) {
		moveStickListener = listener;
	}

	public void touchEvent(MotionEvent event, PointF touch) {

	}

}
