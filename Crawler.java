
import java.net.HttpURLConnection;
import java.util.Vector;
import org.htmlparser.beans.StringBean;
import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import java.util.StringTokenizer;
import org.htmlparser.beans.LinkBean;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Crawler {

    private String url;

    Crawler(String _url) {
        url = _url;
    }

    public Map<String, Integer> extractKeyword() {
        // extract words in url and return them
        // use StringTokenizer to tokenize the result from StringBean
        // ADD YOUR CODES HERE

        Vector<String> words = new Vector<>();
        Map<String, Integer> keywordFreq = new HashMap<>();

        StringBean bean = new StringBean();
        bean.setLinks(false); // Do not include links in extracted text
        bean.setCollapse(true); // Remove extra whitespace
        bean.setReplaceNonBreakingSpaces(true); // Normalize spaces

        bean.setURL(url);

        // Tokenise
        StringTokenizer tokenizer = new StringTokenizer(bean.getStrings());
        while (tokenizer.hasMoreTokens()) {
            words.add(tokenizer.nextToken());
        }

        // keyword: freq
        for (String keyword : words) {
            keywordFreq.put(keyword, keywordFreq.getOrDefault(keyword, 0) + 1);
        }

        return keywordFreq;
    }

    public Vector<String> extractLinks() {
        // extract links in url and return them
        // ADD YOUR CODES HERE
        Vector<String> links = new Vector<>();

        try {
            LinkBean linkBean = new LinkBean();
            linkBean.setURL(url);

            URL[] extractedUrls = linkBean.getLinks();

            for (URL link : extractedUrls) {
                links.add(link.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return links;
    }

    public String extractTitle() {
        String title = "";

        return title;
    }

    public String extractDate() {
        String lastModified = "Unknown";

        return lastModified;
    }

    public int extractPageSize() {
        int pageSize = 0;

        return pageSize;
    }

    public SpiderResult extractAll() {
        String title = extractTitle();
        String link = url;
        String date = extractDate();
        Map<String, Integer> keywordFreq = extractKeyword();
        int pageSize = extractPageSize();
        Vector<String> childLinks = extractLinks();

        SpiderResult newSpiderResult = new SpiderResult(title, link, date, keywordFreq, pageSize, childLinks);

        return newSpiderResult;
    }

    public static void main(String[] args) {
        try {
            Crawler crawler = new Crawler("http://www.cs.ust.hk/~dlee/4321/");

            Map<String, Integer> words = crawler.extractKeyword();

            System.out.println("Words in " + crawler.url + " (size = " + words.size() + ") :");
            for (int i = 0; i < words.size(); i++) {
                if (i < 5 || i > words.size() - 6) {
                    System.out.println(words.get(i));
                } else if (i == 5) {
                    System.out.println("...");
                }
            }
            System.out.println("\n\n");

            Vector<String> links = crawler.extractLinks();
            System.out.println("Links in " + crawler.url + ":");
            for (int i = 0; i < links.size(); i++) {
                System.out.println(links.get(i));
            }
            System.out.println("");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
