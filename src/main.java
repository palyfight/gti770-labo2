
public class main {

	public static void main(String[] args) {
		
		String fileNameTest = "spamdata-test-nolabel.arff";
		try {
			WekaFileReader wfr = new WekaFileReader(fileNameTest);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
