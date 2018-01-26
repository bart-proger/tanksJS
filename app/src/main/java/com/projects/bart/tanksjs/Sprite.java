package com.projects.bart.tanksjs;

/**
 * Created by BART on 12.10.2017.
 */

public class Sprite {

	private Texture texture;
	private Rect region;
	private PointF scale = new PointF(1, 1);

	public Sprite(Texture texture) {
		if (texture.isLoaded()) {
			this.texture = texture;
			this.region = new Rect(0, 0, texture.getWidth(), texture.getHeight());
		}
	}

	public Sprite(Texture texture, Rect region) {
		if (texture.isLoaded()) {
			this.texture = texture;
			this.region = new Rect(region);
		}
	}

	public Texture getTexture() {
		return texture;
	}

	public Rect getRegion() {
		return region;
	}

	public void setScale(float value) {
		scale = new PointF(value, value);
	}

	public PointF getScale() {
		return scale;
	}
}
