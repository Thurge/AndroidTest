package com.example.android.opengl;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

public class GLCube {

		private float vertices[] = {
                1.0f,  1.0f, -1.0f,
               -1.0f,  1.0f, -1.0f,
                1.0f, -1.0f, -1.0f,
               -1.0f, -1.0f, -1.0f,
                1.0f,  1.0f,  1.0f,
               -1.0f,  1.0f,  1.0f,
               -1.0f, -1.0f,  1.0f,
                1.0f, -1.0f,  1.0f
                };
		
		private float rgba[] =  {
                1f,  0f,  0f,  1f,
                0f,  1f,  0f,  1f,
                0f,  0f,  1f,  1f,
                1f,  1f,  0f,  1f,
                1f,  0f,  1f,  1f,
                0f,  1f,  1f,  1f,
                1f,  1f,  1f,  1f,
                1f,  0f,  1f,  1f
             };
		
		FloatBuffer vertBuff, colorBuff;
		
		
		private short[] pIndex ={
				  0,2,1, 1,2,3, //front face
				  0,5,4, 5,0,1, //top face
				  4,5,7, 7,5,6, //back face
				  2,7,6, 2,6,3, //bottom face
				  1,3,5, 5,3,6, //right face
				  0,7,2, 4,7,0	//left face
		};
		
		private ShortBuffer pBuff;
		
		public GLCube(){
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
			ByteBuffer cBuff = ByteBuffer.allocateDirect(rgba.length * 4);
			cBuff.order(ByteOrder.nativeOrder());
			colorBuff = cBuff.asFloatBuffer();
			colorBuff.put(rgba);
			colorBuff.position(0);
		}
		
		public void draw(GL10 gl){
			gl.glFrontFace(GL10.GL_CCW);//drawing the triangle clock wise?
			gl.glEnable(GL10.GL_CULL_FACE);
		    gl.glCullFace(GL10.GL_BACK);
			gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
			gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
			
			gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertBuff);
			gl.glColorPointer(4, GL10.GL_FLOAT, 0, colorBuff);
			gl.glDrawElements(GL10.GL_TRIANGLES, pIndex.length, GL10.GL_UNSIGNED_SHORT, pBuff);
			
			//disable what we enabled
			gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
			gl.glDisableClientState(GL10.GL_COLOR_ARRAY);
			gl.glDisable(GL10.GL_CULL_FACE);
		}
		
		public void move(float[] vector)
		{
			for(int i=0; i<vertices.length/3; i++)
			{
				//0,3,6,9 x coordinates
				vertices[i*3] += vector[0];
				
				//1,4,7,10 y coordinates
				vertices[i*3+1] += vector[1];
				
				//2,5,8,11 z coordinates
				vertices[i*3+2] += vector[2];
			}
			
			refreshVectorBuffer();
		}
		
		private void refreshVectorBuffer()
		{
			ByteBuffer  bBuff = ByteBuffer.allocateDirect(vertices.length * 4);
			bBuff.order(ByteOrder.nativeOrder());
			vertBuff = bBuff.asFloatBuffer();
			vertBuff.put(vertices);
			vertBuff.position(0);
		}
		
		public void rotateX(int degrees)
		{
			double angle = degrees * Math.PI/180;
			float y,z = 0;
			for(int i=0; i<vertices.length/3; i++)
			{
				y = vertices[i*3+1];
				z = vertices[i*3+2];
				//0,3,6,9 x coordinates remain the same when rotating about the x axis
				//vertices[i*3] += vector[0];
				
				//1,4,7,10 y coordinates y cosT - zsinT
				//vertices[i*3+1] += vector[1];
				vertices[i*3+1] = (float) (y*Math.cos(angle)-z*Math.sin(angle));
				
				//2,5,8,11 z coordinates ysinT+zcosT
				vertices[i*3+2] = (float) (y*Math.sin(angle)+z*Math.cos(angle));
				
			}
			refreshVectorBuffer();
		}
		
		public void rotateY(int degrees)
		{
//			double angle = degrees * Math.PI/180;
//			float x,z = 0;
//			for(int i=0; i<vertices.length/3; i++)
//			{
//				x = vertices[i*3];
//				z = vertices[i*3+2];
//				//0,3,6,9 x coordinates remain the same when rotating about the x axis
//				//vertices[i*3] += vector[0];
//				
//				//1,4,7,10 y coordinates y cosT - zsinT
//				//vertices[i*3+1] += vector[1];
//				//vertices[i*3+1] = (float) (y*Math.cos(angle)-z*Math.sin(angle));
//				
//				//2,5,8,11 z coordinates ysinT+zcosT
//				//vertices[i*3+2] = (float) (y*Math.sin(angle)+z*Math.cos(angle));
//				
//			}
//			refreshVectorBuffer();
		}
		
		public void rotateZ(int degrees)
		{
			double angle = degrees * Math.PI/180;
			float x,y = 0;
			for(int i=0; i<vertices.length/3; i++)
			{
				x = vertices[i*3];
				y = vertices[i*3+1];
				//0,3,6,9 x coordinates remain the same when rotating about the x axis
				//vertices[i*3] += vector[0];
				
				//1,4,7,10 y coordinates y cosT - zsinT
				//vertices[i*3+1] += vector[1];
				vertices[i*3] = (float) (x*Math.cos(angle)-y*Math.sin(angle));
				
				//2,5,8,11 z coordinates ysinT+zcosT
				vertices[i*3+1] = (float) (x*Math.sin(angle)+y*Math.cos(angle));
				
			}
			refreshVectorBuffer();
		}
}
