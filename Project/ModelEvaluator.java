import java.io.*;

public class ModelEvaluator 
{
 private FeatureExtractor extractor = new FeatureExtractor();

 public void evaluate(String testFile, SpamClassifier classifier) throws IOException {
 BufferedReader reader = new BufferedReader(new FileReader(testFile));
 reader.readLine(); 

 int correct = 0;
 int total = 0;
 int spamCount = 0;
 int nonSpamCount = 0;

 String line;
 while ((line = reader.readLine()) != null) {
  int firstComma = line.indexOf(',');
  if (firstComma == -1) continue;

  String labelStr = line.substring(0, firstComma).trim();
  String emailText = line.substring(firstComma + 1).trim();

  if (emailText.startsWith("\"") && emailText.endsWith("\"")) {
   emailText = emailText.substring(1, emailText.length() - 1);
   }

  boolean actual = labelStr.equals("1");
  Email email = new Email(emailText, actual);
  FeatureVector fv = extractor.extractFeatures(email);
  boolean predicted = classifier.classify(fv);

  if (predicted == actual) correct++;
  if (actual) spamCount++;
   else nonSpamCount++;

    total++;
  }
  reader.close();
  System.out.printf("Accuracy: %.2f%% (%d/%d correct)\n", 100.0 * correct / total, correct, total);
  System.out.println("Test set: " + spamCount + " spam, " + nonSpamCount + " non-spam");
 }
}