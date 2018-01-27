package com.projects.bart.tanksjs;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.projects.bart.tanksjs.game.Game;
import com.projects.bart.tanksjs.game.Painter;
import com.projects.bart.tanksjs.gui.Button;
import com.projects.bart.tanksjs.gui.GUI;
import com.projects.bart.tanksjs.gui.Widget;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.ArrayList;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import static android.opengl.GLES20.GL_COLOR_BUFFER_BIT;
import static android.opengl.GLES20.GL_DEPTH_BUFFER_BIT;
import static android.opengl.GLES20.glClear;
import static android.opengl.GLES20.glUniformMatrix4fv;

/**
 * Created by BART on 08.10.2017.
 */

class OpenGLRenderer implements GLSurfaceView.Renderer {

	private Context context;
	private long lastTime, time;

	private Graphics g;
	private Texture texture;
	private Sprite sprite;
	private OrthographicCamera camera;

	Painter painter;
	Game game;
	GUI gui;
	Button btnStart;

//TODO: buttons click
// 376 24 16 16


	public OpenGLRenderer(Context context) {
		this.context = context;
	}

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		g = new Graphics(context);
		texture = new Texture(context, R.drawable.texture);
		sprite = new Sprite(texture, new Rect(376, 24, 16, 16));
		camera = new OrthographicCamera(320, 240);
		camera.setScale(4);

		painter = new Painter(g, texture);
		game = new Game();
		gui = new GUI();

		btnStart = new Button(texture, new Rect(368, 180, 32, 20));
		btnStart.setTouchEventListener(new Widget.OnTouchEventListener() {
			@Override
			public void onTouchEvent(MotionEvent event, PointF touch) {
				game.start();
			}
		});
		gui.addWidget(btnStart);
	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		camera.resize(width, height);
		g.setCamera(camera);

		btnStart.setPosition(new Point(0, (int)((height-btnStart.getRegion().height*4) / camera.getScale())));
	}

	PointF touch = null;

	@Override
	public void onDrawFrame(GL10 gl) {
		lastTime = time;
		time = SystemClock.currentThreadTimeMillis()/*uptimeMillis()*/;
		game.update((float)(time - lastTime), (float)time);
		//gui.update();

		glClear(GL_COLOR_BUFFER_BIT/* | GL_DEPTH_BUFFER_BIT*/);
		game.draw(painter);
		gui.draw(g);

		if (touch != null)
			g.drawSprite(sprite, (int)touch.toPx().x-8, (int)touch.toPx().y-8);
	}

	public boolean onTouchEvent(MotionEvent event) {
		/*PointF*/ touch = new PointF(event.getX() / camera.getScale(), event.getY()  / camera.getScale());

		gui.touchEvent(event, touch);

		return true;
	}
}
