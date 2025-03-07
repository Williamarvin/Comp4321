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
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class spiderResult {
    String title;
    String url;
    String date;
    Map<String, Integer> keywordFreq = new HashMap<>();
    int pageSize;
    Vector<String> childLink;
}

public class Crawler
{
	private String url;

	Crawler(String _url)
	{
		url = _url;
	}
	public Map<String, Integer> extractKeyword() throws ParserException
	{
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

	public Vector<String> extractLinks() throws ParserException
	{
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

	public String extractTitle() throws ParserException {
        String title = "";
        Parser parser = new Parser(url);
        NodeList nodeList = parser.extractAllNodesThatMatch(new NodeClassFilter(TitleTag.class));

        if (nodeList.size() > 0) {
            TitleTag titleTag = (TitleTag) nodeList.elementAt(0);
            title = titleTag.getTitle();
        }

        return title;
	}

	public String extractDate() throws ParserException{
		String lastModified = "Unknown";

        try {
            URL urlObj = new URL(url);
            URLConnection connection = urlObj.openConnection();

            if (connection instanceof HttpURLConnection) {
                HttpURLConnection httpConnection = (HttpURLConnection) connection;
                long date = httpConnection.getLastModified();

                if (date != 0) {
                    lastModified = new java.util.Date(date).toString();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lastModified;
	}

	public String extractPageSize() throws ParserException{
		String pageSize = "Unknown";

        try {
            URL urlObj = new URL(url);
            URLConnection connection = urlObj.openConnection();
            int size = connection.getContentLength();

            if (size != -1) {
                pageSize = size + " bytes";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return pageSize;
	}

	
	public static void main (String[] args)
	{
		try
		{
			Crawler crawler = new Crawler("http://www.cs.ust.hk/~dlee/4321/");

			Vector<String> words = crawler.extractKeyword();		
			
			System.out.println("Words in "+crawler.url+" (size = "+words.size()+") :");
			for(int i = 0; i < words.size(); i++)
				if(i<5 || i>words.size()-6){
					System.out.println(words.get(i));
				} else if(i==5){
					System.out.println("...");
				}
			System.out.println("\n\n");
			

	
			Vector<String> links = crawler.extractLinks();
			System.out.println("Links in "+crawler.url+":");
			for(int i = 0; i < links.size(); i++)		
				System.out.println(links.get(i));
			System.out.println("");
			
		}
		catch (ParserException e)
            	{
                	e.printStackTrace ();
            	}

	}
}

	
