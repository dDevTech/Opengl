package ejemplos;

import static org.lwjgl.opengl.GL11.*;

import program.Renderer;
import program.Ventana;

public class Triangulo extends Ventana implements Renderer{

	private Objeto objeto;

	public Triangulo(int ancho, int alto, String titulo) {
		super(ancho,alto,titulo);
		setRenderer(this);
	}

	public static void main(String[] args){
		new Triangulo(800,800, "Triangulo").run();
	}

	@Override
	public void setup() {
		glClearColor(1f, 0f, 0f, 1f);
		objeto = new Objeto();
	}

	@Override
	public void render() {
		glClear(GL_COLOR_BUFFER_BIT);
		objeto.render();
	}
	
}
