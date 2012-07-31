package com.example.android.opengl;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

public class GLTriangleEx {

		private float vertices[] = {
				0f, 1f,  //point 0 
				1f, -1,  //point 1
				-1f, -1, //point 2
		};
		
		private float rgbaVals[] = {
				1, 1, 0, .5f, //r	 g 	 b	 a on first vert
				.25f, 0, .85f, 1, // second vert
				0, 1, 1, 1			//third vert
		};
		
		FloatBuffer vertBuff, colorBuff;
		
		
		private short[] pIndex = {0,1,2};
		
		private ShortBuffer pBuff;
		
		public GLTriangleEx(){
			//how many verts, 4 bytes in a float
			ByteBuffer  bBuff = ByteBuffer.allocateDirect(vertices.length * 4);
			bBuff.order(ByteOrder.nativeOrder());
			vertBuff = bBuff.asFloatBuffer();
			vertBuff.put(vertices);
			vertBuff.position(0);
			
			//how many indices * short size (2 bytes)
			ByteBuffer pbBuff = ByteBuffer.allocateDirect(pIndex.length * 2);
			pbBuff.order(ByteOrder.nativeOrder());
			pBuff = pbBuff.asShortBuffer();
			pBuff.put(pIndex);
			pBuff.position(0);
			
			//how many indices * short size (2 bytes)
			ByteBuffer cBuff = ByteBuffer.allocateDirect(rgbaVals.length * 4);
			cBuff.order(ByteOrder.nativeOrder());
			colorBuff = cBuff.asFloatBuffer();
			colorBuff.put(rgbaVals);
			colorBuff.position(0);
			
		}
		
		public void draw(GL10 gl){
			gl.glFrontFace(GL10.GL_CW);//drawing the triangle clock wise?
			gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
			
			gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
			
			gl.glVertexPointer(2, GL10.GL_FLOAT, 0, vertBuff);
			gl.glColorPointer(4, GL10.GL_FLOAT, 0, colorBuff);
			gl.glDrawElements(GL10.GL_TRIANGLES, pIndex.length, GL10.GL_UNSIGNED_SHORT, pBuff);
			
			gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
			gl.glDisableClientState(GL10.GL_COLOR_ARRAY);
		}
}
