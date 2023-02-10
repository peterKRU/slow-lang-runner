package runner;

import java.io.IOException;
import java.util.Scanner;

import static runner.Commands.*;

public class Runner {

	public static void main(String[] args) throws IOException {
		
		Scanner scanner = new Scanner(System.in);
		
		while (true) {

			log(Messages.DEFAULT_STATIC_MESSAGE);

			String input = scanner.nextLine();

			if (input.equals(Commands.EXIT)) {

				break;
			}
			
			String[] splitInput = input.split(" ");
			String command = splitInput[0];
			String fileName = splitInput[1];

			switch (command) {

				case COMPILE -> {
					
					log(Messages.COMPILE);
					ProcessRunner.compile(fileName);
				}
				case RUN -> {
					
					log(Messages.RUN);
					ProcessRunner.run(fileName);
				}
				case COMPILE_AND_RUN -> {
					
					log(Messages.COMPILE);
					log(Messages.RUN);
					ProcessRunner.compileAndRun(fileName);
				}
				case TEST -> {
					
					log(Messages.TEST);
				}
				default -> {
					
					log(Messages.NOT_RECOGNIZED);
				}

			}

		}

		scanner.close();
	}

	public static void log(String message) {

		System.out.println(message);
	}
}
