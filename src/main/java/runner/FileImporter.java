package runner;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileImporter {

	public static String readFromFile(String fileName) {
		
		String text = "";
		try {
			
			FileReader reader = new FileReader(fileName);

			char[] chars = new char[(int) new File(fileName).length()];
			reader.read(chars);
			text = new String(chars);

			reader.close();
		
		} catch (IOException e) {
			
			System.out.println("FileImporter: An error occurred while reading from the file.");
			e.printStackTrace();
		}

		return text;
	}

}
