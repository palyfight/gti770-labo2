import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.trees.J48;
import weka.core.Instances;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WekaFileReader {

	public WekaFileReader(String test, String dev) throws Exception{
		readFile(test, dev);
	}

	public void readFile(String test, String dev) throws Exception {
		 bayes(test, dev, "EquipeX-plus.txt");
		 j48(test, dev, "EquipeX-moins.txt");
	}
	
	public void j48(String test, String dev, String outputFile) throws Exception{
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
			BufferedReader readerDev = new BufferedReader(new FileReader(dev));
			BufferedReader readerTest = new BufferedReader(new FileReader(test));
			Instances wekaDataDev = new Instances(readerDev);
			Instances wekaDataTest = new Instances(readerTest);
			readerDev.close();
			readerTest.close();
			
			int index = wekaDataDev.numAttributes() - 1;
			wekaDataDev.setClassIndex(index);
			wekaDataTest.setClassIndex(index);
			J48 j48 = new J48();
			j48.setUnpruned(true);
			j48.buildClassifier(wekaDataDev);
			
			for (int i = 0; i < wekaDataTest.numInstances(); i++) { 
				double clsLabel = j48.classifyInstance(wekaDataTest.instance(i)); 
				/*System.out.print("ID: " + wekaDataTest.instance(i).value(0)); 
				System.out.print(", actual: " + wekaDataTest.classAttribute().value((int) wekaDataTest.instance(i).classValue())); 
				System.out.println(", predicted: " + wekaDataTest.classAttribute().value((int) clsLabel));*/
				writer.write(wekaDataTest.classAttribute().value((int) clsLabel));	
				writer.newLine();
				
			}
			writer.flush();
			writer.close();
		} catch (FileNotFoundException e) {
			System.out.println("Can't read file");
		} catch (IOException e) {
			System.out.println("Can't extract data");
		}
	}
	
	public void bayes(String test, String dev, String outputFile) throws Exception{
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
			BufferedReader readerDev = new BufferedReader(new FileReader(dev));
			BufferedReader readerTest = new BufferedReader(new FileReader(test));
			Instances wekaDataDev = new Instances(readerDev);
			Instances wekaDataTest = new Instances(readerTest);
			readerDev.close();
			readerTest.close();
			
			int index = wekaDataDev.numAttributes() - 1;
			wekaDataDev.setClassIndex(index);
			wekaDataTest.setClassIndex(index);
			NaiveBayes bayes = new NaiveBayes();
			bayes.setUseSupervisedDiscretization(true);
			bayes.buildClassifier(wekaDataDev);
			
			for (int i = 0; i < wekaDataTest.numInstances(); i++) { 
				double clsLabel = bayes.classifyInstance(wekaDataTest.instance(i)); 
				/*System.out.print("ID: " + wekaDataTest.instance(i).value(0)); 
				System.out.print(", actual: " + wekaDataTest.classAttribute().value((int) wekaDataTest.instance(i).classValue())); 
				System.out.println(", predicted: " + wekaDataTest.classAttribute().value((int) clsLabel));*/
				writer.write(wekaDataTest.classAttribute().value((int) clsLabel));	
				writer.newLine();
				
			}
			writer.flush();
			writer.close();
		} catch (FileNotFoundException e) {
			System.out.println("Can't read file");
		} catch (IOException e) {
			System.out.println("Can't extract data");
		}
	}
}
