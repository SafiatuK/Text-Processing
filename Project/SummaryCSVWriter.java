import java.io.*;
import java.util.*;

public class SummaryCSVWriter {
 public void writeSummary(Map<String, FeatureVector> modelMap, String outputFile) throws IOException {
  BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));

  Set<String> allKeys = new TreeSet<>();
  for (FeatureVector fv : modelMap.values()) {
   allKeys.addAll(fv.getAllFeatures().keySet());
   }

  writer.write("feature");
  for (String model : modelMap.keySet()) {
   writer.write("," + model);
   }
  writer.newLine();

  for (String key : allKeys) {
   writer.write(key);
  for (String model : modelMap.keySet()) {
   writer.write("," + modelMap.get(model).getFeature(key));
   }
  writer.newLine();
  }
 writer.close();
 }
}
