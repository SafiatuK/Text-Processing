import java.util.*;

public class FeatureVector 
{
 private Map<String, Double> features = new HashMap<>(); 

 public void addFeature(String name, double value) {
  features.put(name, value); 
  }

 public double getFeature(String name) {
   return features.getOrDefault(name, 0.0);
  }

 public Map<String, Double> getAllFeatures() {
  return features; 
  }

 public String toString() {
  return features.toString(); 
  }
}