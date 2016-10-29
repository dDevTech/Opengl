package ejemplos;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import program.Shader;

public class ShaderReader {

	public static Shader readShader(String vsFile, String fsFile){
		String vs = null;
		String fs = null;
		
		try {
			FileReader fr = new FileReader(vsFile);
			BufferedReader br = new BufferedReader(fr);
			StringBuilder sb = new StringBuilder();
			String linea = br.readLine();
			while(linea != null){
				sb.append(linea + "\n");
				linea = br.readLine();
			}
			vs = sb.toString();
			br.close();
			
			fr = new FileReader(fsFile);
			br = new BufferedReader(fr);
			sb = new StringBuilder();
			linea = br.readLine();
			while(linea != null){
				sb.append(linea + "\n");
				linea = br.readLine();
			}
			fs = sb.toString();
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(vs);
		System.out.println(fs);
		
		
		return new Shader(vs,fs);
	}
	
}

