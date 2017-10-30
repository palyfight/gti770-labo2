
public class main {
	/*
	 * Takes a Weka file has an entry argument and two text files for output
	 * args[0] = Weka file format .arff
	 * args[1] = Output predictions file for best algorithm
	 * args[2] = Output predictions file for worst algorithm
	 */
	public static void main(String[] args) {
		
		String fileNameTest = "spamdata-test-nolabel.arff";
		try {
			WekaFileReader wfr = new WekaFileReader(fileNameTest);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
