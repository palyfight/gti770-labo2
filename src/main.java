
public class main {

	public static void main(String[] args) {
		
		String fileNameTest = "spamdata-test-nolabel.arff";
		String fileNameDev = "spamdata-dev.arff";
		try {
			WekaFileReader wfr = new WekaFileReader(fileNameTest, fileNameDev);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
