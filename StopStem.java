import java.io.*;
import java.util.HashSet;

public class StopStem
{
	private Porter porter;
	private HashSet<String> stopWords;
	public boolean isStopWord(String str)
	{
		return stopWords.contains(str);	
	}
	public StopStem(String file) throws IOException 
	{
		super();
		porter = new Porter();
		stopWords = new HashSet<String>();
				
		// use BufferedReader to extract the stopwords in stopwords.txt (path passed as parameter str)
		// add them to HashSet<String> stopWords
		// MODIFY THE BELOW CODE AND ADD YOUR CODES HERE
		
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Trim the line to remove extra spaces and add it to the stopWords set
                line = line.trim();
                if (!line.isEmpty()) {  // Ensure we don't add empty lines
                    stopWords.add(line);
                }
            }
        } catch (IOException e) {
            // Handle exception here (optional, for example: print or rethrow)
            throw new IOException("Error reading the stopwords file", e);
        }
	}
	public String stem(String str)
	{
		return porter.stripAffixes(str);
	}
	public static void main(String[] arg) throws IOException 
	{
		StopStem stopStem = new StopStem("stopwords.txt");
		String input="";
		try{
			do
			{
				System.out.print("Please enter a single English word: ");
				BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
				input = in.readLine();
				if(input.length()>0)
				{	
					if (stopStem.isStopWord(input))
						System.out.println("It should be stopped");
					else
			   			System.out.println("The stem of it is \"" + stopStem.stem(input)+"\"");
				}
			}
			while(input.length()>0);
		}
		catch(IOException ioe)
		{
			System.err.println(ioe.toString());
		}
	}
}
