public class SpamClassifier 
{
 private FeatureVector spamModel;
 private FeatureVector nonSpamModel;
 private DistanceCalculator calculator = new DistanceCalculator();

 public SpamClassifier(FeatureVector spamModel, FeatureVector nonSpamModel) {
  this.spamModel = spamModel;
  this.nonSpamModel = nonSpamModel;
 }

 public boolean classify(FeatureVector input) {
  double distToSpam = calculator.computeDistance(input, spamModel);
  double distToNonSpam = calculator.computeDistance(input, nonSpamModel);
  return distToSpam < distToNonSpam; // Closer = more similar
  }
}