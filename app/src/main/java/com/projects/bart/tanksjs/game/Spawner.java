package com.projects.bart.tanksjs.game;

import com.projects.bart.tanksjs.Point;

/**
 * Created by BART on 16.10.2017.
 */

class Spawner extends GameObject {

	private Tank tank;

	private Animation animation;
	private static final int[] frameSequence = new int[] {
			0, 1, 2, 3, 2, 1, 0,
			1, 2, 3, 2, 1,
			2, 3, 2,
			3, 3
	};

	public Spawner(final Point cell) {
		super(cell);
		animation = new Animation(frameSequence.length, 50) {
			@Override
			protected void onStop() {
				if (tank != null) {
					tank.respawnAt(cell);
					tank = null;
				}
			}
		};
	}

	public boolean tryRespawn(Tank tank) {
		if (animation.isStopped()) {
			this.tank = tank;
			tank.setSpawning(true);
			animation.start();
			return true;
		}
		return false;
	}

	@Override
	public void update(float dt) {
		super.update(dt);
		animation.animate(dt);
	}

	@Override
	public void draw(Painter p) {
		super.draw(p);
		if (!animation.isStopped()) {
			p.drawSpawner(cell.x * 8, cell.y * 8, frameSequence[animation.getFrame()]);
		}
	}
}
