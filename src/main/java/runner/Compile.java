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
			
			if(updateRequired) {
				updateCompiler();
			}
			
			runCompiler();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void updateCompiler() throws IOException {

		URL website = new URL("https://github.com/USERNAME/REPO/releases/download/v1.0/FILE.jar");

		ReadableByteChannel rbc = Channels.newChannel(website.openStream());
		FileOutputStream fileOutputStream = new FileOutputStream("FILE.jar");
		fileOutputStream.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
		fileOutputStream.close();
		rbc.close();
	}

	private static void runCompiler() throws IOException {

		List<String> command = new ArrayList<>();
		command.add("java");
		command.add("-jar");
		command.add("FILE.jar");
		ProcessBuilder processBuilder = new ProcessBuilder(command);
		Process process = processBuilder.start();
	}
	
	private static void setUpdatePolicy(boolean policy) {
		
		updateRequired = policy;
	}
}
