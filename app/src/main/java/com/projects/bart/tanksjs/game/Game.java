package com.projects.bart.tanksjs.game;

import com.projects.bart.tanksjs.Graphics;
import com.projects.bart.tanksjs.Point;

import java.util.ArrayList;

/**
 * Created by BART on 16.10.2017.
 */

public class Game {

	private Level level;

	public Game() {
		level = new Level(new String[] {
				"*.bb*bb.*",
				".........",
				".........",
				".bb...cc.",
				".........",
				"c....b...",
				".........",
				"...ccc...",
				"..1c$c..."
		});
	}

	public void start()
	{
		level.start();
	}

	public void update(float dt, float time) {
		level.update(dt);
	}

	public void draw(Painter p) {
		level.draw(p);
	}
}
