package com.projects.bart.tanksjs.gui;

import com.projects.bart.tanksjs.Point;
import com.projects.bart.tanksjs.Rect;
import com.projects.bart.tanksjs.Sprite;
import com.projects.bart.tanksjs.Texture;

/**
 * Created by BART on 17.10.2017.
 */

public class Button extends Widget {

	public Button(Texture texture, Rect textureRegion) {
		super(texture, textureRegion);
	}

	public Button(Point position, Texture texture, Rect textureRegion) {
		super(position, texture, textureRegion);
	}

}
