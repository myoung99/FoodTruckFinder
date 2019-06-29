import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
public class Organizer {
	Calendar cal = Calendar.getInstance();
	//private int hour = cal.get(Calendar.HOUR);
	private int min = cal.get(Calendar.MINUTE);
	private int sec = cal.get(Calendar.SECOND);
	private int day = cal.get(Calendar.DAY_OF_WEEK);
	private int hour1 = cal.get(Calendar.HOUR_OF_DAY);
	private String[] addresses = null;
	
	public void getTime() {
		//update the time
		
		//hour = cal.get(Calendar.HOUR);
		min = cal.get(Calendar.MINUTE);
		sec = cal.get(Calendar.SECOND);
		
		//really only need the day and the hour
		day = cal.get(Calendar.DAY_OF_WEEK)-1; // subtracted one so it matched up with what was in the json file
		hour1 = cal.get(Calendar.HOUR_OF_DAY); // decided to use military time for an easier comparison
		
		//printed out the time at first to double check that it was working correctly 
		
		//System.out.println("HOUR " + hour1);
		//System.out.println("MINUTE " + min);
		//System.out.println("SECONDS " + sec);
		//System.out.println("DAY " + day);
		
		
	}
	
	/*
	 * this method is responsible for taking in the array of strings containing the details of each food truck
	 * it reads through each element in the array
	 * gets the name from each element 
	 * and adds the name into an array list of strings
	 * returns that array list of strings
	 * 
	 * simultaneously it filters out the food trucks that are not open during that time
	 * and it will not add the name of a food truck that is closed
	 */
	public ArrayList<String> getAvailable(String[] arr) {
		int count = 0;
		ArrayList<String> truckNames = new ArrayList<String>();
		
		/*day = 5;
		hour1 = 13;
		* I hard coded in some dates and hours to see some variations in the printing
		*/
		
		for(int i = 0; i < arr.length; i++) {
			if(arr[i].contains(" dayorder : 0") && day == 0 && hour1 > getStart(arr[i]) && hour1 < getEnd(arr[i])) { //use the get start and get end to get methods to get those times for the trucks
				String name = getName(arr[i]); // use the get name method to get the name of each food truck open at that time
				truckNames.add(name);
				count++;
			} else if(arr[i].contains(" dayorder : 1") && day == 1 && hour1 > getStart(arr[i]) && hour1 < getEnd(arr[i])) {
				String name = getName(arr[i]);
				truckNames.add(name);
				count++;
			} else if(arr[i].contains(" dayorder : 2") && day == 2 && hour1 > getStart(arr[i]) && hour1 < getEnd(arr[i])) {
				String name = getName(arr[i]);
				truckNames.add(name);
				count++;
			} else if(arr[i].contains(" dayorder : 3") && day == 3 && hour1 > getStart(arr[i]) && hour1 < getEnd(arr[i])) {
				String name = getName(arr[i]);
				truckNames.add(name);
				count++;
			} else if(arr[i].contains(" dayorder : 4") && day == 4 && hour1 > getStart(arr[i]) && hour1 < getEnd(arr[i])) {
				String name = getName(arr[i]);
				truckNames.add(name);
				count++;
			} else if(arr[i].contains(" dayorder : 5") && day == 5 && hour1 > getStart(arr[i]) && hour1 < getEnd(arr[i])) {
				String name = getName(arr[i]);
				truckNames.add(name);
				count++;
			} else if(arr[i].contains(" dayorder : 6") && day == 6 && hour1 > getStart(arr[i]) && hour1 < getEnd(arr[i])) {
				String name = getName(arr[i]);
				truckNames.add(name);
				count++;
			} 
		}
		
		//sort the array list in alphabetical order 
		Collections.sort(truckNames);
		
		// I printed out the number of food trucks so I could make sure it was being updated correctly as food trucks closed
		//System.out.println(count);
		
		// return array list
		return truckNames;
	}

	/*
	 * This method is responsible for getting the start time from the file (in military time)
	 * it takes the first two characters and concatenates the characters together to form one string
	 * it parses that string into an int and returns the number
	 */
	public int getStart(String string) {
		char d;
		char c;
		int start = string.indexOf(" start24 : ") + 11;
		c = string.charAt(start);
		d = string.charAt(start+1);
		String startStr = String.valueOf(c) + String.valueOf(d);
		try {
			start = Integer.parseInt(startStr);
		}
		catch (NumberFormatException e) {start = 0;}
		
		return start;
	}
	
	/*
	 * This method is responsible for getting the end time from the file (in military time)
	 * it takes the first two characters and concatenates the characters together to form one string
	 * it parses that string into an int and returns the number
	 */
	public int getEnd(String string) {
		int end = string.indexOf(" end24 : ") + 9;
		char c;
		char d;
		c = string.charAt(end);
		d = string.charAt(end+1);
		String endStr = String.valueOf(c) + String.valueOf(d);
		try {
			end = Integer.parseInt(endStr);
		}
		catch (NumberFormatException e) {end = 0;}
		return end;
	}
	
	/*
	 * This method is responsible for getting the addresses of the food trucks in the array list
	 * it iterates through the one dimensional array containing each food trucks' data,
	 * then it checks the current food truck to see if the current name in the array list corresponds to the current food truck
	 * if it does it looks for the index of the lowercase l and adds 10 to get to the start of the address
	 * it then prints until it gets to the comma
	 * 
	 * 
	 * I also have this method printing out the name and the address
	 * this method also prints the new page character (unicode 12) after 10 times
	 */
	public String getAdd(String[] arr, ArrayList<String> aList) {
		char t;
		int count = 0;
		String rtnString = " ";
		ArrayList<String> array = new ArrayList<String>();
		
		for(int i = 0; i < aList.size(); i++) {
			for(int j = 0; j < arr.length; j++) {
				if(arr[j].contains(aList.get(i))) {
					int index = arr[j].indexOf('l') + 10;
					System.out.print(aList.get(i));
					t = arr[j].charAt(index);
					while(t != ',') {
						//System.out.print(arr[j].charAt(index));
						rtnString += t;
						index++;
						t = arr[j].charAt(index);
					}
					//array.add(rtnString);
					System.out.println(rtnString);
					count++;
					if(count == 10) {
						System.out.println(String.valueOf((char)12));
						count = 0;
					}
				}
				rtnString = " ";  //reset the string!
			}
		}
		return rtnString;
	}
	
	/*
	 * This method takes in a string
	 * and returns the name of the food truck mentioned in the string from the array I created
	 */
	public String getName(String str) {
		char t;
		String rtn =  " ";
		int index = str.indexOf(" applicant : ") + 13;
		//System.out.println(" ");
		t = str.charAt(index);
		while(t != 'x') {
			//System.out.print(str.charAt(index));
			rtn += t;
			index++;
			t = str.charAt(index);
		}
		//System.out.println(rtn);
		return rtn;
	}
		

}
