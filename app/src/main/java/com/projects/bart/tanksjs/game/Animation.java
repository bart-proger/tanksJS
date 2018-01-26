package com.projects.bart.tanksjs.game;

/**
 * Created by BART on 16.10.2017.
 */

public class Animation {

	private int frame = -1;
	private int frameCount;
	private float time = 0;
	private float delay;
	private boolean looped = false;

	public Animation(int frameCount, float frameDelay) {
		this.frameCount = frameCount;
		this.delay = frameDelay;
	}

	public Animation(int frameCount, float frameDelay, boolean looped) {
		this(frameCount, frameDelay);
		this.looped = looped;
	}

	public void animate(float dt) {
		if (frame > -1 && (time += dt) > delay) {
			time = 0;
			if (!looped && stopCondition()) {
				stop();
				return;
			}
			frame = (frame + 1) % frameCount;
		}
	}

	public void start() {
		frame = 0;
	}

	public void stop() {
		onStop();
		frame = -1;
	}

	public boolean isStopped() {
		return frame == -1;
	}

	public boolean stopCondition() {
		return frame == frameCount-1;
	}

	public int getFrame() {
		return frame;
	}

	protected void onStop() {
	}

}
