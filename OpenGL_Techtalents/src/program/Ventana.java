package program;

import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.*;

public class Ventana {

	// The window handle
	private long window;
	private int ancho;
	private int alto;
	
	private Renderer renderer = new Renderer(){public void setup() {}public void render() {}};
	private String titulo;
	
	public Ventana(int ancho, int alto, String titulo) {
		this.ancho = ancho;
		this.alto = alto;
		this.titulo = titulo;
	}
	
	public Ventana(String titulo) {
		this.titulo = titulo;
	}

	public void run() {
		//System.out.println("Hello LWJGL " + Version.getVersion() + "!");
	
		try {
			init();
			loop();

			// Free the window callbacks and destroy the window
			glfwFreeCallbacks(window);
			glfwDestroyWindow(window);
		} finally {
			// Terminate GLFW and free the error callback
			glfwTerminate();
			glfwSetErrorCallback(null).free();
		}
	}

	private void init() {
		// Setup an error callback. The default implementation
		// will print the error message in System.err.
		GLFWErrorCallback.createPrint(System.err).set();

		// Initialize GLFW. Most GLFW functions will not work before doing this.
		if ( !glfwInit() )
			throw new IllegalStateException("Unable to initialize GLFW");

		// Configure our window
		glfwDefaultWindowHints(); // optional, the current window hints are already the default
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE); // the window will stay hidden after creation
		glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE); // the window will be resizable

		
		long monitor = glfwGetPrimaryMonitor();
		GLFWVidMode vidmode = glfwGetVideoMode(monitor);
		if(ancho==0 || alto==0){
			ancho = vidmode.width();
			alto = vidmode.height();
			window = glfwCreateWindow(ancho, alto, titulo, monitor, NULL);
		}else{
			window = glfwCreateWindow(ancho, alto, titulo, NULL, NULL);
			glfwSetWindowPos(
				window,
				(vidmode.width() - ancho) / 2,
				(vidmode.height() - alto) / 2
			);
		}
		
		
		
		
		if ( window == NULL )
			throw new RuntimeException("Failed to create the GLFW window");

		// Setup a key callback. It will be called every time a key is pressed, repeated or released.
		glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
			if ( key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE )
				glfwSetWindowShouldClose(window, true); // We will detect this in our rendering loop
		});

		// Make the OpenGL context current
		glfwMakeContextCurrent(window);
		// Enable v-sync
		glfwSwapInterval(1);

		// Make the window visible
		glfwShowWindow(window);
	}

	private void loop() {
		// This line is critical for LWJGL's interoperation with GLFW's
		// OpenGL context, or any context that is managed externally.
		// LWJGL detects the context that is current in the current thread,
		// creates the GLCapabilities instance and makes the OpenGL
		// bindings available for use.
		GL.createCapabilities();

		renderer.setup();

		// Run the rendering loop until the user has attempted to close
		// the window or has pressed the ESCAPE key.
		while ( !glfwWindowShouldClose(window) ) {
			renderer.render();
			glfwSwapBuffers(window); 
			glfwPollEvents();
		}
	}

	public Renderer getRenderer() {
		return renderer;
	}

	public void setRenderer(Renderer renderer) {
		this.renderer = renderer;
	}
}