package runner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class ProcessRunner {
	
	public static void compile(String fileName) throws IOException {
		
		List<String> command = buildJavaJarCommand(ConstantsTable.COMPILER_EXECUTABLE_NAME);
		ProcessBuilder processBuilder = new ProcessBuilder(command);
		processBuilder.redirectErrorStream(true);
		Process process = processBuilder.start();
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
		
        String line;
        
        while ((line = reader.readLine()) != null) {
            
        	System.out.println(line);        
        }
        
        reader.close();
	}
	
	public static void run(String fileName) throws IOException {

		List<String> command = buildJavaJarCommand(ConstantsTable.EXECUTION_ENGINE_EXECUTABLE_NAME);
		ProcessBuilder processBuilder = new ProcessBuilder(command);
		processBuilder.redirectErrorStream(true);
		Process process = processBuilder.start();
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
		
        String line;
        
        while ((line = reader.readLine()) != null) {
            
        	System.out.println(line);        
        }
        
        reader.close();		
	}
	
	public static void compileAndRun(String fileName) throws IOException {
		
		compile(fileName);
		run(fileName);
	}
	
	public static void runDefaultTest(String fileName) {
		
	}
	
	private static List<String> buildJavaJarCommand(String jarName) {
		
		return List.of("java", "-jar", jarName);
	}
}
