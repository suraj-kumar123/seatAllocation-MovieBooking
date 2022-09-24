package TicketBooking;
import java.util.*;
import java.io.*;
import java.nio.file.Paths;
import java.nio.file.Path;

public class ReturnFile {
	
	public ReturnFile() {
		
	}
	
	public void outputFile(Map<String, ArrayList<String>> returnData) {
		BufferedWriter writer;
		try {
			Map<String, ArrayList<String>> reqData = returnData;
			writer = new BufferedWriter(new FileWriter("output.txt"));
			int counter = 0;
			int counter1 = 0;
			while(reqData.size() > counter) {
				String reqStr = "R00";
				counter1++;
				reqStr +=counter1;
				if(reqData.get(reqStr) != null) {
					if(reqData.get(reqStr).size() > 0) {
						ArrayList<String> tempStr = reqData.get(reqStr);
						String strVal = "";
						for(int i=0; i<tempStr.size(); i++) {
							if(i < tempStr.size()-1) {
								strVal = strVal + tempStr.get(i) + ",";
							}
							else {
								if(i == tempStr.size()-1) {
									strVal = strVal + tempStr.get(i);
								}
							}
						}
						String str = "";
						if(strVal != null && strVal.length() > 0 ) {
							str = reqStr + " " + strVal;
							writer.write(str+"\n");
						}
						
						
						counter++;
					}
					else {
						counter1++;
					}
				}
			}
			writer.close();
			
		}catch(Exception e) {
			System.out.println("Some issue occured");
		}
		Path path = Paths.get("output.txt");
		System.out.println(path.toAbsolutePath());
	}

}
