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
	
	/*
	 * Reads the Weka file and creates prediction files based on the Naive Bayes
	 * algorithm and the J48 Tree algorithm
	 * valid = Weka file name.
	 */
	public WekaFileReader(String valid) throws Exception{
		
		bayesModel = (Classifier) weka.core.SerializationHelper.read("bayes.model");
		j48Model = (Classifier) weka.core.SerializationHelper.read("treeJ48.model");
		readFile(valid);
	}

	public void readFile(String valid) throws Exception {
		 bayes(valid, "EquipeX-plus.txt");
		 j48(valid, "EquipeX-moins.txt");
	}
	
	/*
	 * Using the weka methods, it evaluates the read weka file to output prediction based
	 * on the model used by the j48Model variable
	 * validate = Weka File name
	 * outputFile = output file name
	 */
	public void j48(String validate, String outputFile) throws Exception{
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
			
			BufferedReader readerValidate = new BufferedReader(new FileReader(validate));
			Instances wekaDataValidate = new Instances(readerValidate);
			readerValidate.close();
			
			int index = wekaDataValidate.numAttributes() - 1;
			wekaDataValidate.setClassIndex(index);
			
			for (int i = 0; i < wekaDataValidate.numInstances(); i++) { 
				double clsLabel = j48Model.classifyInstance(wekaDataValidate.instance(i)); 
				System.out.print("ID: " + wekaDataTest.instance(i).value(0)); 
				System.out.print(", actual: " + wekaDataTest.classAttribute().value((int) wekaDataTest.instance(i).classValue())); 
				System.out.println(", predicted: " + wekaDataTest.classAttribute().value((int) clsLabel));
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
	
	/*
	 * Using the weka methods, it evaluates the read weka file to output prediction based
	 * on the model used by the bayesModel variable
	 * validate = Weka File name
	 * outputFile = output file name
	 */
	public void bayes(String valid, String outputFile) throws Exception{
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
			
			BufferedReader readerValidate = new BufferedReader(new FileReader(valid));
			Instances wekaDataValidate = new Instances(readerValidate);
			readerValidate.close();

			int predictInstance = wekaDataValidate.numAttributes() - 1;
			wekaDataValidate.setClassIndex(predictInstance);
			
			for (int i = 0; i < wekaDataValidate.numInstances(); i++) { 
				double clsLabel = bayesModel.classifyInstance(wekaDataValidate.instance(i)); 
				System.out.print("ID: " + wekaDataValidate.instance(i).value(0)); 
				System.out.print(", actual: " + wekaDataValidate.classAttribute().value((int) wekaDataValidate.instance(i).classValue())); 
				System.out.println(", predicted: " + wekaDataValidate.classAttribute().value((int) clsLabel));
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
