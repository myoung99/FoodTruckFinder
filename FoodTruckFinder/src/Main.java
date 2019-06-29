

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.HttpURLConnection;

public class Main {
	
	
	private static Organizer or;
	private static String[] temp = null;
	
	
	
	public static void main(String[] args) throws IOException {
		openFile();
		or.getTime();	//get the update time every time it is ran
		or.getAdd(temp, or.getAvailable(temp)); //this calls the methods from the other class that does all of the work 
	}
	
	public static void openFile() {
		StringBuilder result = new StringBuilder();
		or = new Organizer();
		
		try 
		{
			// I used the template given to assemble the try catch
			URL url = new URL("https://data.sfgov.org/resource/jjew-r69b.json");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while((line = rd.readLine()) != null) {
				result.append(line);
				//System.out.println(line);
			}
			
			String str = result.toString();
			//System.out.println(str);		// I printed the string before the replacement
			str = str.replace("\"", " "); // I replaced the quotation marks with spaces so it's easier to read
			//System.out.println(str);		// I printed the string after the replacement to make sure it worked
		
			char t;
			for(int i = 0; i < str.length(); i++) {
				t = str.charAt(i);
				if(t == '}') {
					temp = str.split("}"); //I iterated through the string and split the string into pieces
					//each piece went into the String[] named temp
				}
			}
			
			// printed out the first line of the array and the last line to make sure it was splitting correctly
			//System.out.println("TESTING");
			//System.out.println(temp[0]);
			//System.out.println(temp.length);
			//System.out.println(temp[2997]);			
			rd.close();	//close the json file
		}
		catch(Exception e) { e.getMessage();}
		
	}
	

	
	
}
