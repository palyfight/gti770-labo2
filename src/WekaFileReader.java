import weka.classifiers.Classifier;
import weka.core.Instances;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WekaFileReader {
	private Classifier bayesModel;
	private Classifier j48Model;
	
	public WekaFileReader(String valid) throws Exception{
		
		bayesModel = (Classifier) weka.core.SerializationHelper.read("bayes.model");
		j48Model = (Classifier) weka.core.SerializationHelper.read("treeJ48.model");
		readFile(valid);
	}

	public void readFile(String valid) throws Exception {
		 bayes(valid, "EquipeX-plus.txt");
		 //j48(valid, "EquipeX-moins.txt");
	}
	
	/*public void j48(String test, String dev, String outputFile) throws Exception{
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
				//System.out.print("ID: " + wekaDataTest.instance(i).value(0)); 
				//System.out.print(", actual: " + wekaDataTest.classAttribute().value((int) wekaDataTest.instance(i).classValue())); 
				//System.out.println(", predicted: " + wekaDataTest.classAttribute().value((int) clsLabel));
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
	}*/
	
	public void bayes(String valid, String outputFile) throws Exception{
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
			
			BufferedReader readerValidate = new BufferedReader(new FileReader(valid));
			Instances wekaDataValidate = new Instances(readerValidate);
			readerValidate.close();
			
			int predictInstance = 57;
			double value = bayesModel.classifyInstance(wekaDataValidate.instance(predictInstance));
			String prediction = wekaDataValidate.classAttribute().value((int)value);
			System.out.println("The predicted value of instance "+
                    Integer.toString(predictInstance)+
                    ": "+prediction); 
			
			for (int i = 0; i < wekaDataValidate.numInstances(); i++) { 
				double clsLabel = bayesModel.classifyInstance(wekaDataValidate.instance(i)); 
				/*System.out.print("ID: " + wekaDataTest.instance(i).value(0)); 
				System.out.print(", actual: " + wekaDataTest.classAttribute().value((int) wekaDataTest.instance(i).classValue())); 
				System.out.println(", predicted: " + wekaDataTest.classAttribute().value((int) clsLabel));*/
				writer.write(wekaDataValidate.classAttribute().value((int) clsLabel));	
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
