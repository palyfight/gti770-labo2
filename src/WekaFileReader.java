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
		 j48(valid, "EquipeX-moins.txt");
	}
	
	public void j48(String validate, String outputFile) throws Exception{
		double good = 0;
		double count = 0;
		double actual, pred = 0;
		
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
			
			BufferedReader readerValidate = new BufferedReader(new FileReader(validate));
			Instances wekaDataValidate = new Instances(readerValidate);
			readerValidate.close();

			int predictInstance = wekaDataValidate.numAttributes() - 2;
			wekaDataValidate.setClassIndex(predictInstance);
			
			for (int i = 0; i < wekaDataValidate.numInstances(); i++) { 
				double clsLabel = j48Model.classifyInstance(wekaDataValidate.instance(i)); 
				//System.out.print("ID: " + wekaDataTest.instance(i).value(0));
				wekaDataValidate.setClassIndex(predictInstance+1);
				actual = Integer.parseInt(wekaDataValidate.classAttribute().value((int) wekaDataValidate.instance(i).classValue()));
				//System.out.print(", actual: " + wekaDataValidate.classAttribute().value((int) wekaDataValidate.instance(i).classValue())); 
				wekaDataValidate.setClassIndex(predictInstance);
				pred = Integer.parseInt(wekaDataValidate.classAttribute().value((int) clsLabel));
				//System.out.println(", predicted: " + wekaDataValidate.classAttribute().value((int) clsLabel));
				if(actual == pred){
					good++;
				}
				writer.write(wekaDataValidate.classAttribute().value((int) clsLabel));	
				writer.newLine();
				count++;
			}
			System.out.println(good/count);
			writer.flush();
			writer.close();
		} catch (FileNotFoundException e) {
			System.out.println("Can't read file");
		} catch (IOException e) {
			System.out.println("Can't extract data");
		}
	}
	
	public void bayes(String valid, String outputFile) throws Exception{
		double good = 0;
		double count = 0;
		double actual, pred = 0;
		
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
			
			BufferedReader readerValidate = new BufferedReader(new FileReader(valid));
			Instances wekaDataValidate = new Instances(readerValidate);
			readerValidate.close();

			int predictInstance = wekaDataValidate.numAttributes() - 2;
			wekaDataValidate.setClassIndex(predictInstance);
			
			for (int i = 0; i < wekaDataValidate.numInstances(); i++) { 
				double clsLabel = bayesModel.classifyInstance(wekaDataValidate.instance(i)); 
				/*System.out.print("ID: " + wekaDataValidate.instance(i).value(0));*/
				wekaDataValidate.setClassIndex(predictInstance+1);
				actual = Integer.parseInt(wekaDataValidate.classAttribute().value((int) wekaDataValidate.instance(i).classValue()));
				//System.out.print(", actual: " + wekaDataValidate.classAttribute().value((int) wekaDataValidate.instance(i).classValue())); 
				wekaDataValidate.setClassIndex(predictInstance);
				pred = Integer.parseInt(wekaDataValidate.classAttribute().value((int) clsLabel));
				//System.out.println(", predicted: " + wekaDataValidate.classAttribute().value((int) clsLabel));
				if(actual == pred){
					good++;
				}
				writer.write(wekaDataValidate.classAttribute().value((int) clsLabel));	
				writer.newLine();
				count++;
			}
			System.out.println(good/count);
			writer.flush();
			writer.close();
		} catch (FileNotFoundException e) {
			System.out.println("Can't read file");
		} catch (IOException e) {
			System.out.println("Can't extract data");
		}
	}
}
