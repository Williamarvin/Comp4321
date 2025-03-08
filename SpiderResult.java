import java.io.Serializable;
import java.util.Map;
import java.util.Vector;

public class SpiderResult implements Serializable {
    private static final long serialVersionUID = 1L; // Ensures version compatibility during serialization

    String title;
    String url;
    String date;
    public Map<String, Integer> keywordFreq;
    int pageSize;
    Vector<String> childLink;

    public SpiderResult(String title, String url, String date, Map<String, Integer> keywordFreq, int pageSize, Vector<String> childLink) {
        this.title = title;
        this.url = url;
        this.date = date;
        this.keywordFreq = keywordFreq;
        this.pageSize = pageSize;
        this.childLink = childLink;
    }

    @Override
    public String toString() {
        return "Title: " + title + "\n"
                + "URL: " + url + "\n"
                + "Date: " + date + "\n"
                + "Page Size: " + pageSize + " bytes\n"
                + "Keywords: " + keywordFreq + "\n"
                + "Child Links: " + childLink;
    }

    public static void main(String[] args) {
        // Crawler crawl = new Crawler("http://www.cs.ust.hk/~dlee/4321/");
    }
}
