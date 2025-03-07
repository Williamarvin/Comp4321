import java.util.Vector;
import IRUtilities.*;
import java.io.*;
import java.util.HashSet;

public class Main {
  public static void main(String[] args) {
    // System.out.println("Hello World");

    String url = "http://www.cs.ust.hk/~dlee/4321/";
    Vector<String> extractedText = new Vector<>();

    try {
        // Get Link and words
        Crawler crawl = new Crawler(url);
        spiderResult crawlResult = crawl.extractAll();

        System.out.println(crawlResult);
        
        // System.out.println("Link List: ");
        // System.out.println(linkList);
        // System.out.println("Word Lists: ");
        // System.out.println(wordList);

        // Stem and remove stop words
		StopStem stopStem = new StopStem("stopwords.txt");
        for(String word : wordList){
            if (stopStem.isStopWord(word)){}
            else
                extractedText.add(stopStem.stem(word));
        }

        // System.out.println(extractedText);

        InvertedIndex index = new InvertedIndex("project", "ht1");
    } 
    catch (Exception e) {
        System.out.println("Error:" + e.getMessage());
    }
  }
}