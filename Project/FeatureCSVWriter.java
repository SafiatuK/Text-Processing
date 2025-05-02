import java.io.*;
import java.util.*;

public class FeatureCSVWriter {
 public void writeFeatureVectors(List<Email> emails, FeatureExtractor extractor, String outputFile) throws IOException {
  BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
        
  Set<String> allKeys = new TreeSet<>();
  List<FeatureVector> vectors = new ArrayList<>();
  for (Email email : emails) {
   FeatureVector fv = extractor.extractFeatures(email);
   vectors.add(fv);
   allKeys.addAll(fv.getAllFeatures().keySet());
  }

  writer.write("is_spam");
  for (String key : allKeys) {
   writer.write("," + key);
   }
  writer.newLine();
        
  for (int i = 0; i < emails.size(); i++) {
   Email email = emails.get(i);
   FeatureVector fv = vectors.get(i);

   writer.write(email.isSpam() ? "1" : "0");
   for (String key : allKeys) {
    writer.write("," + fv.getFeature(key));
    }
   writer.newLine();
   }

   writer.close();
  }
}