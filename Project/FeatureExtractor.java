import java.util.*;

public class FeatureExtractor 
{
 public FeatureVector extractFeatures(Email email) {
 FeatureVector vector = new FeatureVector();
 String text = email.getText().toLowerCase();
 String[] words = text.split("\\s+");

 vector.addFeature("word_count", words.length);
 vector.addFeature("char_count", text.length());

 Set<String> uniqueWords = new HashSet<>(Arrays.asList(words));
 vector.addFeature("unique_word_count", uniqueWords.size());

 double avgLength = 0;
 int numberCount = 0;
 int linkCount = 0;
 int spamWordCount = 0;

 List<String> spamWords = List.of("free", "win", "winner", "money", "offer", "click", "buy");

 for (String word : words) {
  avgLength += word.length();
  if (word.matches(".*\\d.*")) numberCount++;
   if (word.contains("http") || word.contains("www")) linkCount++;
    if (spamWords.contains(word)) spamWordCount++;
    }

 if (words.length > 0) {
  avgLength /= words.length;
  }

 vector.addFeature("avg_word_length", avgLength);
 vector.addFeature("number_word_count", numberCount);
 vector.addFeature("link_count", linkCount);
 vector.addFeature("spam_word_count", spamWordCount);
 return vector;
 }
}