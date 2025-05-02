import java.util.*;
import java.io.*;
public class MainTest
{
 public static void main(String[] args) {
  String inputFile = "data/spam_or_not_spam.csv";
  String trainFile = "data/train.csv";
  String testFile = "data/test.csv";
  String featuresOutput = "data/features.csv";
  String summaryOutput = "data/summary.csv";

 try {
  DatasetSplitter splitter = new DatasetSplitter();
  splitter.split(inputFile, trainFile, testFile, 0.8);

  ModelTrainer trainer = new ModelTrainer();
  Map<String, FeatureVector> models = trainer.train(trainFile);

  SpamClassifier classifier = new SpamClassifier(models.get("spam"), models.get("nonspam"));

  ModelEvaluator evaluator = new ModelEvaluator();
  evaluator.evaluate(testFile, classifier);

  List<Email> allEmails = new ArrayList<>();
  BufferedReader reader = new BufferedReader(new FileReader(trainFile));
  reader.readLine();
  String line;
  while ((line = reader.readLine()) != null) {
   int comma = line.indexOf(',');
   if (comma == -1) continue;
   String label = line.substring(0, comma);
   String msg = line.substring(comma + 1).trim();
   if (msg.startsWith("\"") && msg.endsWith("\"")) {
    msg = msg.substring(1, msg.length() - 1);
    }
   boolean isSpam = label.equals("1");
   allEmails.add(new Email(msg, isSpam));
  }
  reader.close();

  FeatureExtractor extractor = new FeatureExtractor();
  FeatureCSVWriter featureWriter = new FeatureCSVWriter();
  featureWriter.writeFeatureVectors(allEmails, extractor, featuresOutput);

  SummaryCSVWriter summaryWriter = new SummaryCSVWriter();
  summaryWriter.writeSummary(models, summaryOutput);

  System.out.println("Feature and summary CSVs written successfully.");

  } 
  catch (Exception e) {
    e.printStackTrace();
   }
  }
}