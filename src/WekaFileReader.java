import weka.classifiers.Evaluation;
import weka.classifiers.meta.FilteredClassifier;
import weka.classifiers.trees.J48;
import weka.core.Instances;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class WekaFileReader {

	public WekaFileReader(String test, String dev) throws Exception{
		readFile(test, dev);
	}

	public void readFile(String test, String dev) throws Exception {
		try {
			BufferedReader readerTest = new BufferedReader(new FileReader(test));
			BufferedReader readerDev = new BufferedReader(new FileReader(dev));
			Instances wekaDataTest = new Instances(readerTest);
			Instances wekaDataDev = new Instances(readerDev);

			J48 j48 = new J48();
			j48.setUnpruned(false);
			FilteredClassifier fc = new FilteredClassifier();
			fc.setClassifier(j48);
			fc.buildClassifier(wekaDataTest);

			Evaluation eval = new Evaluation(wekaDataTest);
			eval.evaluateModel(j48, wekaDataDev);
			System.out.println(eval.toSummaryString("\nResults\n======\n", false));

		} catch (FileNotFoundException e) {
			System.out.println("Can't read file");
		} catch (IOException e) {
			System.out.println("Can't extract data");
		}

	}
}

/*import weka.classifiers.trees.J48;
import weka.core.Instances;

public class Main {

    public static void main(String[] args) throws Exception
    {
        //load training instances
        Instances test=...

        //build a J48 decision tree
        J48 model=new J48(); 
        model.buildClassifier(test);

        //decide which instance you want to predict
        int s1=2;

        //get the predicted probabilities 
        double[] prediction=model.distributionForInstance(test.get(s1));

        //output predictions
        for(int i=0; i<prediction.length; i=i+1)
        {
            System.out.println("Probability of class "+
                                test.classAttribute().value(i)+
                               " : "+Double.toString(prediction[i]));
        }

    }

}*/
