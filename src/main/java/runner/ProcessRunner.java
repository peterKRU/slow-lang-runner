package runner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class ProcessRunner {

	public static void compile(String fileName) throws IOException {

		runJarFile(ConstantsTable.COMPILER_EXECUTABLE_NAME, fileName);
	}

	public static void run(String fileName) throws IOException {

		runJarFile(ConstantsTable.EXECUTION_ENGINE_EXECUTABLE_NAME, fileName);
	}

	public static void compileAndRun(String fileName) throws IOException {

		compile(fileName);
		run(FileModifier.renameSourceToCompiled(fileName));
	}

	public static void runDefaultTest(String fileName) throws IOException {

		run(Commands.TEST);
	}

	public static void runJarFile(String jarFilePath, String input) {

		try {

			ProcessBuilder builder = new ProcessBuilder("java", "-jar", jarFilePath);
			builder.redirectErrorStream(true);
			Process process = builder.start();

			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			OutputStreamWriter writer = new OutputStreamWriter(process.getOutputStream());

			writer.write(input + System.lineSeparator());
			writer.flush();

			String line;

			while ((line = reader.readLine()) != null) {

				System.out.println(line);
			}

			reader.close();
			writer.close();

		} catch (IOException e) {

			e.printStackTrace();
		}
	}
}
