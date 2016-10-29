package ejemplos;

import program.Shader;

public class BasicShader extends Shader{

	public BasicShader() {
		super(getVShader(), getFShader());
	}

	
	
	public static String getVShader(){
		return "#version 150 core\n"+

		"in vec3 position;\n"+

		"void main() {\n"+
		"	gl_Position = vec4(position, 1.0);\n"+
		"}\n";
	}

	public static String getFShader(){
		return "#version 150 core\n"+


				"out vec4 fragColor;\n"+

				"void main() {\n"+
				"    fragColor = vec4(1.0, 1.0, 0.0, 1.0);\n"+
				"}\n";
	}
	
}
