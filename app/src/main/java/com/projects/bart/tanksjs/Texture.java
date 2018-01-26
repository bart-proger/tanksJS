package com.projects.bart.tanksjs;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import static android.opengl.GLES20.GL_BLEND;
import static android.opengl.GLES20.GL_CLAMP_TO_EDGE;
import static android.opengl.GLES20.GL_LINEAR;
import static android.opengl.GLES20.GL_NEAREST;
import static android.opengl.GLES20.GL_ONE_MINUS_SRC_ALPHA;
import static android.opengl.GLES20.GL_SRC_ALPHA;
import static android.opengl.GLES20.GL_TEXTURE0;
import static android.opengl.GLES20.GL_TEXTURE_2D;
import static android.opengl.GLES20.GL_TEXTURE_MAG_FILTER;
import static android.opengl.GLES20.GL_TEXTURE_MIN_FILTER;
import static android.opengl.GLES20.GL_TEXTURE_WRAP_S;
import static android.opengl.GLES20.GL_TEXTURE_WRAP_T;
import static android.opengl.GLES20.glActiveTexture;
import static android.opengl.GLES20.glBindTexture;
import static android.opengl.GLES20.glBlendFunc;
import static android.opengl.GLES20.glDeleteTextures;
import static android.opengl.GLES20.glEnable;
import static android.opengl.GLES20.glGenTextures;
import static android.opengl.GLES20.glTexParameteri;
import static android.opengl.GLUtils.texImage2D;


/**
 * Created by BART on 12.10.2017.
 */

public class Texture {

	private int id = 0;
	private int width = 0;
	private int height = 0;

	public Texture(Context context, int resourceId) {
		loadFromResource(context, resourceId);
	}

	public void loadFromResource(Context context, int resourceId) {
		final int[] textureIds = new int[1];
		glGenTextures(1, textureIds, 0);
		if (textureIds[0] == 0) {
			return;
		}

		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inScaled = false;

		final Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), resourceId, options);

		if (bitmap == null) {
			glDeleteTextures(1, textureIds, 0);
			return;
		}

		glActiveTexture(GL_TEXTURE0);
		glBindTexture(GL_TEXTURE_2D, textureIds[0]);

		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		glEnable(GL_BLEND);

		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_CLAMP_TO_EDGE);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP_TO_EDGE);

		texImage2D(GL_TEXTURE_2D, 0, bitmap, 0);

		glBindTexture(GL_TEXTURE_2D, 0);

		id = textureIds[0];
		width = bitmap.getWidth();
		height = bitmap.getHeight();

		bitmap.recycle();
	}

	public void free() {
		if (id != 0) {
			glDeleteTextures(1, new int[]{id}, 0);
			id = 0;
			width = 0;
			height = 0;
		}
	}

	public boolean isLoaded() {
		return (id != 0);
	}
	public int getId() {
		return id;
	}
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
}
