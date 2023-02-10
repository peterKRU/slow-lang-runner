package runner;

public class FileModifier {

	public static String renameSourceToCompiled(String sourceFileName) {

		int index = sourceFileName.lastIndexOf("_source.txt");

		if (index == -1) {

			throw new IllegalArgumentException("Invalid file name format: " + sourceFileName);
		}

		return sourceFileName.substring(0, index) + "_compiled.txt";
	}
}
