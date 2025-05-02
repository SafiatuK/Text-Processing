import java.io.*;
import java.util.*;

public class ModelTrainer 
{
 private FeatureExtractor extractor = new FeatureExtractor();

 public Map<String, FeatureVector> train(String trainFile) throws IOException {
  BufferedReader reader = new BufferedReader(new FileReader(trainFile));
  reader.readLine(); 

  List<FeatureVector> spamFeatures = new ArrayList<>();
  List<FeatureVector> nonSpamFeatures = new ArrayList<>();
  String line;
  while ((line = reader.readLine()) != null) {
   int firstComma = line.indexOf(',');
    if (firstComma == -1) continue;

  String labelStr = line.substring(0, firstComma).trim();
  String emailText = line.substring(firstComma + 1).trim();

  if (emailText.startsWith("\"") && emailText.endsWith("\"")) {
   emailText = emailText.substring(1, emailText.length() - 1);
  }

  boolean isSpam = labelStr.equals("1");
  Email email = new Email(emailText, isSpam);
  FeatureVector fv = extractor.extractFeatures(email);

  if (isSpam) spamFeatures.add(fv);
    else nonSpamFeatures.add(fv);
  }       
  reader.close();

  FeatureVector spamModel = averageVector(spamFeatures);
  FeatureVector nonSpamModel = averageVector(nonSpamFeatures);

  Map<String, FeatureVector> result = new HashMap<>();
  result.put("spam", spamModel);
  result.put("nonspam", nonSpamModel);
  return result;
 }

 private FeatureVector averageVector(List<FeatureVector> list) {
  FeatureVector avg = new FeatureVector();
  if (list.isEmpty()) return avg;

  Map<String, Double> sum = new HashMap<>();
  for (FeatureVector fv : list) {
   for (Map.Entry<String, Double> entry : fv.getAllFeatures().entrySet()) {
    sum.put(entry.getKey(), sum.getOrDefault(entry.getKey(), 0.0) + entry.getValue());
   }
  }

  for (String key : sum.keySet()) {
   avg.addFeature(key, sum.get(key) / list.size());
  }
 return avg;
 }
}