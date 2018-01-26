package com.projects.bart.tanksjs.game;

import android.util.Log;

import com.projects.bart.tanksjs.Point;

/**
 * Created by BART on 16.10.2017.
 */

class Tank extends GameObject {

	public int getDir() {
		return dir;
	}

	public enum Type { Player, Enemy };
	public static final Point[] DIRECTION = new Point[] {
			new Point(0, -1),
			new Point(-1, 0),
			new Point(0, 1),
			new Point(1, 0)
	};

	public static final int[] HEALTH = { 1, 1, 1, 3 };

	private Point offset = new Point(0, 0);
	private Type type;
	private int rank;
	private int health;
	private int dir = 0;
	private boolean spawning = false;
	private boolean dead = true;
	private Animation animation;

	public Tank(Point cell, Type type, int rank) {
		super(cell);
		this.type = type;
		this.rank = rank;
		this.health = HEALTH[rank];

		animation = new Animation(2, 1, true);
		animation.start();
	}

	@Override
	public void draw(Painter p) {
		if (!dead)
			p.drawTank(cell.x * 8 + offset.x, cell.y * 8 + offset.y, dir, animation.getFrame(), type, rank, health);
	}

	public void move(int dir, float dt) {
		if (this.dir == dir) {
			animation.animate(dt);
			offset.add(DIRECTION[dir]);

			if (Math.abs(offset.x) >= 8 || Math.abs(offset.y) >= 8) {
				offset = new Point(0, 0);
				cell.add(DIRECTION[dir]);
			}
		}
		else {
			this.dir = dir;
			cell.x += Math.round((float)offset.x / 8.0f);
			cell.y += Math.round((float)offset.y / 8.0f);
		}
	}

	public void respawnAt(Point cell) {
		this.cell = new Point(cell);
		dead = false;
		spawning = false;
		if (type == Type.Player) {
			rank = 3;//0;
			dir = 0;
			health = 1;
		}
		else {
			rank = (int)(Math.random() * 4);//random(0, 4);
			dir = 2;
			health = HEALTH[rank];
		}
	}

	public void setSpawning(boolean spawning) {
		this.spawning = spawning;
	}
	public boolean isSpawning() {
		return spawning;
	}

	public boolean isDead() {
		return dead;
	}

	public Type getType() {
		return type;
	}
}
