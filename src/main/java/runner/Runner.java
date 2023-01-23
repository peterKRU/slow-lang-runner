package runner;

public class Runner {

	public static void main(String[] args) {
		
		System.out.println("Runner running...");
		
		Compile compile = new Compile();
		compile.setUpdateRequired(false);
		compile.execute();
		Run run = new Run();
		run.setUpdateRequired(false);
		run.execute();
		
		String consoleOutput = FileImporter.readFromFile("consoleOutput.txt");
		System.out.println("EE console output: " + consoleOutput);
	}

}
