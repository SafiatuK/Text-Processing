import java.io.*;
import java.util.*;

public class DatasetSplitter
{
 public void split(String inputFile, String trainFile, String testFile, double trainRatio) throws IOException {
  List<String> lines = new ArrayList<>();
  BufferedReader reader = new BufferedReader(new FileReader(inputFile));
  String header = reader.readLine(); 
  String line;

  while ((line = reader.readLine()) != null) {
   lines.add(line);
  }
  reader.close();

  Collections.shuffle(lines); 
  int trainSize = (int) (lines.size() * trainRatio);

  BufferedWriter trainWriter = new BufferedWriter(new FileWriter(trainFile));
  BufferedWriter testWriter = new BufferedWriter(new FileWriter(testFile));

  trainWriter.write(header + "\n");
  testWriter.write(header + "\n");

  for (int i = 0; i < lines.size(); i++) {
   if (i < trainSize) {
    trainWriter.write(lines.get(i) + "\n");
   } else {
      testWriter.write(lines.get(i) + "\n");
     }
  }

  trainWriter.close();
  testWriter.close();
 }
}