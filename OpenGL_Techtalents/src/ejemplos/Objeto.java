package ejemplos;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;


@SuppressWarnings("unused")
public class Objeto {

	//float[]vertices=generarPoligono(0,0,4,(float) 0.5);
	private int shaderProgram;
	private int vao;
	
	public Objeto(){
		
		int vao = glGenVertexArrays();
		glBindVertexArray(vao);
		
		float[] vertices = new float[]{
				-0.6f,-0.6f,0f,
				-0.6f,+0.6f,0f, 
				+0.6f,+0.6f,0f,
				
				-0.6f,-0.6f,0f,
				+0.6f,-0.6f,0f, 
				+0.6f,+0.6f,0f
		};
		
		
		
		FloatBuffer buffer = BufferUtils.createFloatBuffer(3 * 6/*vertices.length*/);
		
		buffer.put(vertices);
		buffer.flip();
		
		int vbo = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, vbo);
		glBufferData(GL_ARRAY_BUFFER, buffer, GL_STATIC_DRAW);
		
		shaderProgram = ShaderReader.readShader("res//Shader//basic.vs", "res//Shader//basic.fs").getProgram();
		
		glUseProgram(shaderProgram);

		int posAttrib = glGetAttribLocation(shaderProgram, "position");
		glEnableVertexAttribArray(posAttrib);
		glVertexAttribPointer(posAttrib, 3, GL_FLOAT, false, 3 * 4, 0);
		
	}
	
	public void render(){
		glUseProgram(shaderProgram);
		glDrawArrays(GL_TRIANGLES, 0,6);//vertices.length
	}
	public float[] generarPoligono(int centroX,int centroY,int numLados,float radio){
		
		int contador=0;
		float[]vertices = new float[numLados*9];
		int grados=360/numLados;
		double radianes=Math.toRadians(grados);
		for(int i=1;i<=numLados;i++){
			double x =centroX+Math.cos(radianes*(i)*radio);
			vertices[contador]=(float) x;
			contador++;
			double y =centroY+Math.sin(radianes*(i)*radio);
			vertices[contador]=(float) y;
			contador++;
			double z =0;
			vertices[contador]=(float) z;
			contador++;
			
			vertices[contador]=(float)centroX;
			contador++;
			vertices[contador]=(float) centroY;
			contador++;
			vertices[contador]=0;//z
			contador++;
			
			 x =centroX+Math.cos(radianes*(i)+radianes*radio);
			vertices[contador]=(float) x;
			contador++;
			 y =centroY+Math.sin((radianes)*(i)+radianes*radio);
			vertices[contador]=(float) y;
			contador++;
			 z =0;
			vertices[contador]=(float) z;
			contador++;
			
			
		}
		return vertices;
		
	}
	
}
