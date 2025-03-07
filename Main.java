
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class Main {

    public static void main(String[] args) {
        String url = "http://www.cs.ust.hk/~dlee/4321/";
        Vector<String> extractedText = new Vector<>();

        try {
            // Get Link and words
            Crawler crawl = new Crawler(url);
            spiderResult crawlResult = crawl.extractAll();
            Map<String, Integer> keywordFreq = new HashMap<>();

            // Stem and remove stop words
            StopStem stopStem = new StopStem("stopwords.txt");
            for (String word : crawlResult.keywordFreq.keySet()) {
                if (stopStem.isStopWord(word)) {
                } else {
                    extractedText.add(stopStem.stem(word));
                }
            }

            // extracted words to spider result  
            for (String keyword : extractedText) {
                keywordFreq.put(keyword, keywordFreq.getOrDefault(keyword, 0) + 1);
            }
            crawlResult.keywordFreq = keywordFreq;

            // print out result
            System.out.println(crawlResult.toString());

            // database
            InvertedIndex index = new InvertedIndex("project", "ht1");
        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }
    }
}
