package com.projects.bart.tanksjs.game;

import com.projects.bart.tanksjs.Graphics;
import com.projects.bart.tanksjs.Point;
import com.projects.bart.tanksjs.Rect;
import com.projects.bart.tanksjs.Sprite;
import com.projects.bart.tanksjs.Texture;

import java.util.ArrayList;

import static com.projects.bart.tanksjs.game.Tank.Type.Enemy;
import static com.projects.bart.tanksjs.game.Tank.Type.Player;

/**
 * Created by BART on 16.10.2017.
 */

class Level {

	public class Cell {
		public static final char
				FREE = '.',
				BRICK = 'b',
				CONCRETE = 'c',
				TREES = 't',
				WATER = 'w',
				ACE = 'a',
				ENEMY_SPAWNER = '*',
				PLAYER_SPAWNER = '1',
				STAFF = '$';

		private char type;
		private int view;
		private GameObject owner;

		public Cell(char type) {
			this.type = type;
		}
	}

	private Cell[][] map;
	private ArrayList<Spawner> enemySpawners = new ArrayList<Spawner>();
	private Spawner playerSpawner;
	private Staff staff;
	private ArrayList<Tank> tanks = new ArrayList<>();
	private Tank player = null;

	public Level(String[] mapData) {
		map = new Cell[mapData.length * 2][mapData[0].length() * 2];
		int j = 0;
		for (String row: mapData) {
			for (int i = 0; i < row.length(); i++) {
				char type = row.charAt(i);
				switch (type)
				{
					case Cell.ENEMY_SPAWNER:
						enemySpawners.add(new Spawner(new Point(i*2, j*2)));
						break;

					case Cell.PLAYER_SPAWNER:
						playerSpawner = new Spawner(new Point(i*2, j*2));
						break;

					case Cell.STAFF:
						staff = new Staff(new Point(i*2, j*2));
						break;
					case Cell.FREE:
						break;
					default:
						this.map[j*2][i*2] = new Cell(type);
						this.map[j*2][i*2+1] = new Cell(type);
						this.map[j*2+1][i*2] = new Cell(type);
						this.map[j*2+1][i*2+1] = new Cell(type);
				}
			}
			j++;
		}
	}

	public void start() {
		tanks.clear();
		tanks.add(new Tank(new Point(0, 0), Player, 0));
		for (int i = 0; i < 5; ++i) {
			tanks.add(new Tank(new Point(0, 0), Enemy, 0));
		}
	}

	public void respawn(Tank tank) {
		switch (tank.getType()) {
			case Player:
				playerSpawner.tryRespawn(tank);
				break;
			case Enemy:
				enemySpawners.get((int)(Math.random() * enemySpawners.size())).tryRespawn(tank);
				break;
		}
	}

	public void update(float dt) {
		playerSpawner.update(dt);
		for (Spawner es: enemySpawners)
			es.update(dt);

		for (Tank tank: tanks) {
			if (tank.isDead() && !tank.isSpawning())
				respawn(tank);

			if (Math.random() * 100 < 3)
				tank.move((int)(Math.random() * 4), dt);
			else
				tank.move(tank.getDir(), dt);
		}
	}

	public void draw(Painter p) {
		int x = 0, y = 0;
		for (Cell[] row: map) {

			for (Cell cell: row) {
				if (cell != null)
					switch (cell.type) {
						case Cell.BRICK:
							p.drawBrick(x, y, cell.view);
							break;
						case Cell.CONCRETE:
							p.drawConcrete(x, y);
							break;
					}
				x += 8;
			}
			x = 0;
			y += 8;
		}

		p.drawStaff(staff.getCell().x * 8, staff.getCell().y * 8, staff.isDestroyed());
		playerSpawner.draw(p);
		for (Spawner es: enemySpawners)
			es.draw(p);

		for (Tank tank: tanks) {
			if (!tank.isDead())
				tank.draw(p);
		}
	}

}
