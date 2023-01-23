package runner;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;
import java.util.List;

public class Run extends Procedure {
	
	private static boolean updateRequired = false;

	@Override
	public void execute() {
		
		try {

			if (updateRequired) {
				updateExecutionEngine();
			}

			runExecutionEngine();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void updateExecutionEngine() throws IOException {
		
		ConfigManager configManager = new ConfigManager();
		String jarUrl = configManager.getConfigValue("slow-lang-execution-engine:wip");
		URL downloadUrl = new URL(jarUrl);

		ReadableByteChannel readableByteChannel = Channels.newChannel(downloadUrl.openStream());
		FileOutputStream fileOutputStream = new FileOutputStream("slow-lang-execution-engine.jar");
		fileOutputStream.getChannel().transferFrom(readableByteChannel, 0, Long.MAX_VALUE);
		fileOutputStream.close();
		readableByteChannel.close();
	}

	private static void runExecutionEngine() throws IOException {

		List<String> command = new ArrayList<>();
		command.add("java");
		command.add("-jar");
		command.add("slow-lang-execution-engine.jar");
		ProcessBuilder processBuilder = new ProcessBuilder(command);
		
		@SuppressWarnings("unused")
		Process process = processBuilder.start();
	}

	public void setUpdateRequired(boolean value) {

		updateRequired = value;
	}
	
}
