import java.util.*;

public class DistanceCalculator 
{
 public double computeDistance(FeatureVector a, FeatureVector b) {
  double sum = 0.0;
  Set<String> keys = new HashSet<>();
  keys.addAll(a.getAllFeatures().keySet());
  keys.addAll(b.getAllFeatures().keySet()); 

  for (String key : keys) {
   double diff = a.getFeature(key) - b.getFeature(key);
   sum += diff * diff;
   }
  return Math.sqrt(sum); // Euclidean distance
 }
}