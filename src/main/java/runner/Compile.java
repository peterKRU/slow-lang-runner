package runner;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;
import java.util.List;

public class Compile extends Procedure {

	private static boolean updateRequired = false;

	@Override
	public void execute() {

		try {
			
			if (updateRequired) {
				
				updateCompiler();
			}
			
			runCompiler();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void updateCompiler() throws IOException {

		ConfigManager configManager = new ConfigManager();
		String jarUrl = configManager.getConfigValue("slow-lang-compiler:wip");
		URL downloadUrl = new URL(jarUrl);

		ReadableByteChannel readableByteChannel = Channels.newChannel(downloadUrl.openStream());
		FileOutputStream fileOutputStream = new FileOutputStream("slow-lang-compiler.jar");
		fileOutputStream.getChannel().transferFrom(readableByteChannel, 0, Long.MAX_VALUE);
		fileOutputStream.close();
		readableByteChannel.close();
	}

	private static void runCompiler() throws IOException {

		List<String> command = new ArrayList<>();
		command.add("java");
		command.add("-jar");
		command.add("slow-lang-compiler.jar");
		ProcessBuilder processBuilder = new ProcessBuilder(command);
		
		@SuppressWarnings("unused")
		Process process = processBuilder.start();
	}

	public void setUpdateRequired(boolean value) {

		updateRequired = value;
	}
}
