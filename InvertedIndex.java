
import java.io.Console;
import jdbm.RecordManager;
import jdbm.RecordManagerFactory;
import jdbm.htree.HTree;
import jdbm.helper.FastIterator;
import java.util.Vector;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;
import java.util.function.Supplier;

public class InvertedIndex {

    private RecordManager recman;
    private HTree hashtable;

    InvertedIndex(String recordmanager, String objectname) throws IOException {
        recman = RecordManagerFactory.createRecordManager(recordmanager);
        long recid = recman.getNamedObject(objectname);

        if (recid != 0) {
            hashtable = HTree.load(recman, recid);
        } else {
            hashtable = HTree.createInstance(recman);
            recman.setNamedObject("ht1", hashtable.getRecid());
        }
    }

    @SuppressWarnings("deprecation")
    public void finalize() throws IOException {
        recman.commit();
        recman.close();
    }

    public void addEntry(String word, int x, int y) throws IOException {
        // Add a "docX Y" entry for the key "word" into hashtable
        // ADD YOUR CODES HERE

        String newValue = "doc" + x + " " + y;

        if (hashtable.get(word) != null) {
            List<String> newList = (List<String>) hashtable.get(word);
            if (!newList.contains(newValue)) {
                newList.add(newValue);
                hashtable.put(word, newList);
            }
        } else {
            List<String> newList = new ArrayList<>();
            newList.add(newValue);
            hashtable.put(word, newList);
        }
    }

    public void delEntry(String word) throws IOException {
        // Delete the word and its list from the hashtable
        // ADD YOUR CODES HERE

        hashtable.remove(word);
    }

    public void printAll() throws IOException {
        // Print all the data in the hashtable
        // ADD YOUR CODES HERE

        FastIterator iter = hashtable.keys();
        String key;

        while ((key = (String) iter.next()) != null) {
            System.out.print(key + " : ");
            List<String> newList = (List<String>) hashtable.get(key);

            for (String word : newList) {
                System.out.print(word);
            }
            System.out.println();
        }
    }

    public void addCrawlResult(String key, Object object) throws IOException {
        Object newObject = object;
        hashtable.put(key, newObject);
    }

    public void printCrawlResult() throws IOException {
        FastIterator iter = hashtable.keys();
        String key;

        while ((key = (String) iter.next()) != null) {
            System.out.print(key + " : ");
            System.out.println(hashtable.get(key));
        }
    }

    public static void main(String[] args) throws IOException {
        try {
            InvertedIndex index = new InvertedIndex("lab1", "ht1");

            index.addEntry("cat", 2, 6);
            index.addEntry("dog", 1, 33);
            System.out.println("First print");
            index.printAll();

            index.addEntry("cat", 8, 3);
            index.addEntry("dog", 6, 73);
            index.addEntry("dog", 8, 83);
            index.addEntry("dog", 10, 5);
            index.addEntry("cat", 11, 106);
            System.out.println("Second print");
            index.printAll();

            index.delEntry("dog");
            System.out.println("Third print");
            index.printAll();
            index.delEntry("dog");
            index.delEntry("cat");
            index.finalize();
        } catch (IOException ex) {
            System.err.println(ex.toString());
        }

        Vector<String> words = new Vector<>();

        Map<String, Integer> keywordFreq = new HashMap<>();
        keywordFreq.put("", 3);

        // Create a Vector for child links
        Vector<String> wordss = new Vector<>();
        words.add("http://example.com/child1");
        words.add("http://example.com/child2");

        // Create a SpiderResult object
        SpiderResult results = new SpiderResult(
                "Example Title",
                "http://example.com",
                "2025-03-08",
                keywordFreq,
                1024,
                wordss
        );

        InvertedIndex index1 = new InvertedIndex("lab2", "ht2");

        index1.addCrawlResult("id1", results);

        index1.printCrawlResult();
    }
}
