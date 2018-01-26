package com.projects.bart.tanksjs;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.Matrix;
import android.util.Log;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import static android.opengl.GLES20.GL_BLEND;
import static android.opengl.GLES20.GL_COLOR_BUFFER_BIT;
import static android.opengl.GLES20.GL_DEPTH_TEST;
import static android.opengl.GLES20.GL_FLOAT;
import static android.opengl.GLES20.GL_FRAGMENT_SHADER;
import static android.opengl.GLES20.GL_ONE;
import static android.opengl.GLES20.GL_TEXTURE0;
import static android.opengl.GLES20.GL_TEXTURE_2D;
import static android.opengl.GLES20.GL_TRIANGLE_STRIP;
import static android.opengl.GLES20.GL_VERTEX_SHADER;
import static android.opengl.GLES20.glActiveTexture;
import static android.opengl.GLES20.glBindTexture;
import static android.opengl.GLES20.glBlendFunc;
import static android.opengl.GLES20.glClear;
import static android.opengl.GLES20.glClearColor;
import static android.opengl.GLES20.glDisable;
import static android.opengl.GLES20.glDrawArrays;
import static android.opengl.GLES20.glEnable;
import static android.opengl.GLES20.glEnableVertexAttribArray;
import static android.opengl.GLES20.glGetAttribLocation;
import static android.opengl.GLES20.glGetUniformLocation;
import static android.opengl.GLES20.glUniform1i;
import static android.opengl.GLES20.glUniformMatrix3fv;
import static android.opengl.GLES20.glUniformMatrix4fv;
import static android.opengl.GLES20.glUseProgram;
import static android.opengl.GLES20.glVertexAttribPointer;

/**
 * Created by BART on 12.10.2017.
 */

public class Graphics {

	private static final int POSITION_COUNT = 3;
	private static final int TEXTURE_COUNT = 2;
	private static final int STRIDE = (POSITION_COUNT + TEXTURE_COUNT) * 4;

	private FloatBuffer vertexData;
	private int programId;

	private int aPositionLocation;
	private int aTextureLocation;
	private int uTextureUnitLocation;
	private int uProjectionViewMatrixLocation;
	private int uModelMatrixLocation;
	private int uTextureMatrixLocation;

	public Graphics(Context context) {
		glClearColor(0f, 0f, 0f, 1f);
		//glEnable(GL_DEPTH_TEST);
		glDisable(GL_DEPTH_TEST);
		glEnable(GL_BLEND);
		glBlendFunc(GL_ONE, GL_ONE);

		int vertexShaderId = ShaderUtils.createShader(context, GL_VERTEX_SHADER, R.raw.vertex_shader);
		int fragmentShaderId = ShaderUtils.createShader(context, GL_FRAGMENT_SHADER, R.raw.fragment_shader);
		programId = ShaderUtils.createProgram(vertexShaderId, fragmentShaderId);
		glUseProgram(programId);

		aPositionLocation = glGetAttribLocation(programId, "a_Position");
		aTextureLocation = glGetAttribLocation(programId, "a_Texture");
		uTextureUnitLocation = glGetUniformLocation(programId, "u_TextureUnit");
		uProjectionViewMatrixLocation = glGetUniformLocation(programId, "u_ProjectionViewMatrix");
		uModelMatrixLocation = glGetUniformLocation(programId, "u_ModelMatrix");
		uTextureMatrixLocation = glGetUniformLocation(programId, "u_TextureMatrix");

		float[] vertices = {
				// position - texture
				0, 1, 0,	0, 1,
				0, 0, 0,	0, 0,
				1, 1, 0,	1, 1,
				1, 0, 0,	1, 0
		};
		vertexData = ByteBuffer
				.allocateDirect(vertices.length * 4)
				.order(ByteOrder.nativeOrder())
				.asFloatBuffer();
		vertexData.put(vertices);

		vertexData.position(0);
		glVertexAttribPointer(aPositionLocation, POSITION_COUNT, GL_FLOAT, false, STRIDE, vertexData);
		glEnableVertexAttribArray(aPositionLocation);

		vertexData.position(POSITION_COUNT);
		glVertexAttribPointer(aTextureLocation, TEXTURE_COUNT, GL_FLOAT, false, STRIDE, vertexData);
		glEnableVertexAttribArray(aTextureLocation);
	}

	public void drawSprite(Sprite sprite, int x, int y) {
		float[] modelMatrix = new float[16];
		Matrix.setIdentityM(modelMatrix, 0);
		Matrix.translateM(modelMatrix, 0, x, y, 0);
		Matrix.scaleM(modelMatrix, 0, sprite.getRegion().width * sprite.getScale().x, sprite.getRegion().height * sprite.getScale().y, 1);

		glUniformMatrix4fv(uModelMatrixLocation, 1, false, modelMatrix, 0);

		float sx = (float) sprite.getRegion().width / (float) sprite.getTexture().getWidth(),
				sy = (float) sprite.getRegion().height / (float) sprite.getTexture().getHeight(),
				tx = (float) sprite.getRegion().x / (float) sprite.getTexture().getWidth(),
				ty = (float) sprite.getRegion().y / (float) sprite.getTexture().getHeight();

		Log.d("-DBG-", String.format("sx=%.3f sy=%.3f tx=%.3f ty=%.3f", sx, sy, tx, ty));

		float[] textureMatrix = new float[16];/* {
				sx,	0,	0,
				0,	sy,	0,
				tx,	ty,	1.0f
		};*/

		Matrix.setIdentityM(textureMatrix, 0);
		Matrix.translateM(textureMatrix, 0, (float) sprite.getRegion().x / (float) sprite.getTexture().getWidth(),
											(float) sprite.getRegion().y / (float) sprite.getTexture().getHeight(), 0);
		Matrix.scaleM(textureMatrix, 0, (float) sprite.getRegion().width / (float) sprite.getTexture().getWidth(),
										(float) sprite.getRegion().height / (float) sprite.getTexture().getHeight(), 1);

		glUniformMatrix4fv(uTextureMatrixLocation, 1, false, textureMatrix, 0);
/*
		glActiveTexture(GL_TEXTURE0);
		glBindTexture(GL_TEXTURE_2D, sprite.getTexture().getId());
		glUniform1i(uTextureUnitLocation, 0);
*/
		glBindTexture(GL_TEXTURE_2D, sprite.getTexture().getId());
		glDrawArrays(GL_TRIANGLE_STRIP, 0, 4);
	}

	public void setCamera(OrthographicCamera camera) {
		glUniformMatrix4fv(uProjectionViewMatrixLocation, 1, false, camera.getProjectionViewMatrix(), 0);
	}

	public void drawTextureRegion(Texture texture, Rect region, int x, int y) {
		float[] modelMatrix = new float[16];
		Matrix.setIdentityM(modelMatrix, 0);
		Matrix.translateM(modelMatrix, 0, x, y, 0);
		Matrix.scaleM(modelMatrix, 0, region.width, region.height, 1);

		glUniformMatrix4fv(uModelMatrixLocation, 1, false, modelMatrix, 0);

		float   sx = (float) region.width / (float) texture.getWidth(),
				sy = (float) region.height / (float) texture.getHeight(),
				tx = (float) region.x / (float) texture.getWidth(),
				ty = (float) region.y / (float) texture.getHeight();

		Log.d("-DBG-", String.format("sx=%.3f sy=%.3f tx=%.3f ty=%.3f", sx, sy, tx, ty));

		float[] textureMatrix = new float[16];/* {
				sx,	0,	0,
				0,	sy,	0,
				tx,	ty,	1.0f
		};*/

		Matrix.setIdentityM(textureMatrix, 0);
		Matrix.translateM(textureMatrix, 0, (float) region.x / (float) texture.getWidth(),
				(float) region.y / (float) texture.getHeight(), 0);
		Matrix.scaleM(textureMatrix, 0, (float) region.width / (float) texture.getWidth(),
				(float) region.height / (float) texture.getHeight(), 1);

		glUniformMatrix4fv(uTextureMatrixLocation, 1, false, textureMatrix, 0);
/*
		glActiveTexture(GL_TEXTURE0);
		glBindTexture(GL_TEXTURE_2D, texture.getId());
		glUniform1i(uTextureUnitLocation, 0);
*/
		glBindTexture(GL_TEXTURE_2D, texture.getId());
		glDrawArrays(GL_TRIANGLE_STRIP, 0, 4);
	}
}
