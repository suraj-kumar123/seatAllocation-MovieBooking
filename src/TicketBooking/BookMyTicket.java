package TicketBooking;
import java.io.*;
import java.util.*;

public class BookMyTicket {
	
	
	public static void main(String[] args) {
		 //Scanner scan = new Scanner(System.in);
		try {
			
			TicketDocument ticketBooking = new TicketDocument();
			ReturnFile rf = new ReturnFile();
			Scanner scan = new Scanner(System.in);
			String filePath = scan.nextLine();
			//String filePath = "C:\\Users\\SURAJ KUMAR MESINANE\\Downloads\\ticketFile.txt";
			FileReader fis = new FileReader(filePath);
			BufferedReader br = new BufferedReader(fis);
			String line;
			while((line = br.readLine()) != null) {
				//System.out.println(line);
				int value =ticketBooking.fillReservation(line);
				if(value == -1) {
					System.out.println("The are not enough tickets or not valid input");
				}
				
			}
			rf.outputFile(ticketBooking.finalResult());
			
			br.close();
			scan.close();
			}
		catch(Exception e) {
			System.out.println("error occured");
			e.printStackTrace();
		}
		 
	}
	
	
	

}
