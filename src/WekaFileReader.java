import weka.core.Instances;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class WekaFileReader {

	public WekaFileReader(String path){
		readFile(path);
	}

	public void readFile(String path) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(path));
			Instances wekaData = new Instances(reader);
			
			//while(wekaData.)
		
		} catch (FileNotFoundException e) {
			System.out.println("Can't read file");
		} catch (IOException e) {
			System.out.println("Can't extract data");
		}
		
	}
}
