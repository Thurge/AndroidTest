/*
 * Copyright (C) 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.opengl;



import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLSurfaceView.Renderer;
import android.opengl.GLU;


public class MyGLRenderer implements Renderer {

	//private GLTriangleEx tri;
	private GLCube cube;

	
	
	public MyGLRenderer(){
		//tri = new GLTriangleEx();
		cube = new GLCube();
	}
	
	
	public void onSurfaceCreated(GL10 gl, EGLConfig eglConfig) {
		gl.glClearColor(mRed, mGreen, mBlue, 1.f);
		gl.glClearDepthf(1f);
		//gl.glRotatef(.090f, 1, 0, 0);
	
	}
	
	public void onDrawFrame(GL10 gl) {
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		gl.glClearColor(mRed, mGreen, mBlue, 1.f);
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();
		GLU.gluLookAt(gl, 0, 0, -5, 0, 0, 0, 0, 2, 0);
		
		//long time = SystemClock.uptimeMillis() %4000L;
		//float angle = .090f * ((int) time);
		
		//gl.glRotatef(angle, 1, 0, 2);
		//gl.glRotatef(angle, mAxisX, mAxisY, mAxisZ);
		
		//tri.draw(gl);
//		float[] vector = {
//				(float) Math.sin(time),0,0 	
//		};
		//cube.move(vector);
		cube.rotateX(1);
		cube.draw(gl);
	}

	public void onSurfaceChanged(GL10 gl, int width, int height) {
		gl.glViewport(0,0,width,height);
		//ratio depnding on our orientation
		float ratio = (((float)width )/ ((float) height));
		gl.glMatrixMode(GL10.GL_PROJECTION);
		gl.glLoadIdentity();
		//creates our viewing area
		//tells left value, right value, bottom and top
		gl.glFrustumf(-ratio, ratio, -1, 1, 1, 25);
	}
		
	public void setColor(float[] newColor) {
		switch(newColor.length){
			case 4: //do nothing but fall through
			case 3: mBlue	=	newColor[2];
			case 2: mGreen 	= 	newColor[1];
			case 1: mRed 	= 	newColor[0];
		}
    }
	
//	public void setAxisOfRotation(float x, float y, float z){
//		mAxisX = x;
//		mAxisY = y;
//		mAxisZ = z;
//	}

    private float mRed;
    private float mGreen;
    private float mBlue;
    
//	private float mAxisX=0;
//	private float mAxisY=1;
//	private float mAxisZ=0;
}
