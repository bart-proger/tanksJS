package com.projects.bart.tanksjs.game;

import com.projects.bart.tanksjs.Graphics;
import com.projects.bart.tanksjs.Rect;
import com.projects.bart.tanksjs.Texture;

/**
 * Created by BART on 16.10.2017.
 */

public class Painter {

	private Graphics g;
	private Texture texture;

	public Painter(Graphics graphics, Texture texture) {
		this.g = graphics;
		this.texture = texture;
	}

	public void drawBrick(int x, int y, int view) {
		g.drawTextureRegion(texture, new Rect(256 + view * 8, 64, 8, 8), x, y);
	}

	public void drawConcrete(int x, int y) {
		g.drawTextureRegion(texture, new Rect(256, 72, 8, 8), x, y);
	}

	public void drawTank(int x, int y, int angle, int frame, Tank.Type type, int rank, int health) {
		int sx = 0,
			sy = 0;

		if (type == Tank.Type.Enemy) {
			sx = 128;
			sy = 64;

			if (health == 2) {
				sx = 0;
				sy = 128+64;
			}
			else if (health == 3) {
				sx = 0;
			}
		}

		sx += angle * 32 + frame * 16;
		sy += rank * 16;

		g.drawTextureRegion(texture, new Rect(sx, sy, 16, 16), x, y);
	}

	public void drawStaff(int x, int y, boolean destroyed) {
		if (destroyed)
			g.drawTextureRegion(texture, new Rect(304+16, 32, 16, 16), x, y);
		else
			g.drawTextureRegion(texture, new Rect(304, 32, 16, 16), x, y);
	}

	public void drawSpawner(int x, int y, int frame) {
		g.drawTextureRegion(texture, new Rect(256 + frame * 16, 96, 16, 16), x, y);
	}
/*
	this.drawBullet = function(x, y, angle)
	{
		g.drawTextureRegion(this.texture, 321 + angle * 8, 100, 8, 8, x, y, SIZE/2, SIZE/2);
	};

	this.drawExplosion = function(x, y, frame, source)
	{
		var sx = 256,
				s = 1,
				offset = 0;

		if (frame < 3)
		{
			sx += frame * 16;
		}
		else
		{
			sx += 16 * 3 + (frame - 3) * 32;
			s = 2;
			offset = -SIZE / 2;
		}
		g.drawTextureRegion(this.texture, sx, 128, 16*s, 16*s, x + offset, y + offset, SIZE*s, SIZE*s);
	};

	this.drawSpawner = function(x, y, frame)
	{
		g.drawTextureRegion(this.texture, 256 + frame * 16, 96, 16, 16, x * SIZE, y * SIZE, SIZE, SIZE);
	};

	this.drawBonus = function(x, y, typeIndex)
	{
		g.drawTextureRegion(this.texture, 256 + typeIndex * 16, 112, 16, 16, x, y, SIZE, SIZE);
	};

	this.drawProtection = function(x, y, frame)
	{
		g.drawTextureRegion(this.texture, 256 + frame * 16, 144, 16, 16, x, y, SIZE, SIZE);
	};

	this.drawStaff = function(x, y, destroyed)
	{
		if (destroyed === true)
			g.drawTextureRegion(this.texture, 304+16, 32, 16, 16, x, y, SIZE, SIZE);
		else
			g.drawTextureRegion(this.texture, 304, 32, 16, 16, x, y, SIZE, SIZE);

	};
*/
}
