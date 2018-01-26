package com.projects.bart.tanksjs;

import android.opengl.Matrix;

import static android.opengl.GLES20.glViewport;

/**
 * Created by BART on 13.10.2017.
 */

public class OrthographicCamera {

	private PointF position;
	private int halfWidth;
	private int halfHeight;

	private float[] projectionMatrix = new float[16];
	private float[] viewMatrix = new float[16];

	private float scale = 1.0f;

	public OrthographicCamera(int width, int height) {
		resize(width, height);
	}

	public void resize(int width, int height) {
		halfWidth = width / 2;
		halfHeight = height / 2;

		position = new PointF(halfWidth, halfHeight);

		glViewport(0, 0, width, height);

		Matrix.setIdentityM(projectionMatrix, 0);
		Matrix.orthoM(projectionMatrix, 0, 0, width, height, 0, -1, 1);

		Matrix.setIdentityM(viewMatrix, 0);
		Matrix.translateM(viewMatrix, 0, position.x - halfWidth, position.y - halfHeight, 0);
		Matrix.scaleM(viewMatrix, 0, scale, scale, 1);
	}

	public float[] getProjectionViewMatrix() {
		float[] result = new float[16];
		Matrix.multiplyMM(result, 0, projectionMatrix, 0, viewMatrix, 0);
		return result;
	}

	public void setScale(float value) {
		scale = value;
	}
	public float getScale() {
		return scale;
	}
}
