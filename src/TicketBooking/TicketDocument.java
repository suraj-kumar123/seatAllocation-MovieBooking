package TicketBooking;
import java.util.*;


public class TicketDocument {
	
	int TotalSeatsLeft = 200;
	ArrayList<Character> seatingarr = new ArrayList<Character>();
	int[] seatArr = new int[] {0,0,0,0,0,0,0,0,0,0};
	Map<String, ArrayList<String>> returnData = new HashMap<>();
	
	public TicketDocument(){
		seatingarr.add('J');
		seatingarr.add('I');
		seatingarr.add('H');
		seatingarr.add('G');
		seatingarr.add('F');
		seatingarr.add('E');
		seatingarr.add('D');
		seatingarr.add('C');
		seatingarr.add('B');
		seatingarr.add('A');
	}
	
	public int fillReservation(String reservation) {
		
		String tempVal = reservation;
		String[] orderValue = tempVal.split(" ");
		String resNum = orderValue[0];
		int numberOfTickets = Integer.valueOf(orderValue[1]);
		if(numberOfTickets < 0 || numberOfTickets > TotalSeatsLeft) {
			return -1;
		}
		else {
			if(numberOfTickets > 20) {
				int val = 0;
				while(numberOfTickets > 20) {
					val = assignseats(resNum, 20);
					numberOfTickets-=20;
				}
				if(numberOfTickets > 0) {
					val = assignseats(resNum, numberOfTickets);
				}
			}
			else {
				assignseats(resNum, numberOfTickets);
			}
			TotalSeatsLeft-=numberOfTickets;
		}
		
		return 0;
	}
	
	public int assignseats(String resNum, int seatsRequested) {
		String reserveNum = resNum;
		int seatsReq = seatsRequested;
		boolean flag = false;
		//see if we can place them together
		for(int i = 0; i<seatArr.length; i++) {
			if(20 - seatArr[i] >= seatsReq) {
				assignSeat(reserveNum, seatingarr.get(i), seatsReq);
				flag = true;
				return 0;
			}
		}
		//to assign seats separately at different places
		if(flag == false) {
			if(seatsRequested <= TotalSeatsLeft) {
				String tempStor = "";
				ArrayList<String> separateSeats = new ArrayList<>();
				for(int i = 0; i<seatArr.length; i++) {
					if(seatArr[i] < 20) {
						char s = seatingarr.get(i);
						int value = seatArr[i];
						while(value < 20 && seatsRequested>0) {
							value++;
							tempStor= tempStor+s+value;
							seatsRequested--;
							if(seatsRequested != 0) {
								tempStor+=",";
							}
						}
						if(value == 20) {
							seatArr[i] = 20;
						}
						else {
							if(value+3>=20) {
								seatArr[i] = 20;
							}
							else {
								seatArr[i] = value+3;
								TotalSeatsLeft-=3;
							}
						}
					}
				}
				separateSeats.add(tempStor);
				returnData.put(reserveNum, separateSeats);
				
			}
		}
		
		return 0;
	}
	
	public void assignSeat(String reserveNum, char key, int value) {
		String number = reserveNum;
		char key1 = key;
		int seats = value;
		
		String seatsBooked = "";
		while(seats>0) {
			int index = seatingarr.indexOf(key1);
			int tempVal = seatArr[index] + 1;
			seatsBooked= seatsBooked + key1 + tempVal;
			seatArr[index] = tempVal;
			//mapdata.replace(key1, tempVal);
			seats--;
			if(seats != 0) {
				seatsBooked += ",";
			}
		}
		bufferSeats(key1);
		if(returnData.containsKey(number)) {
			ArrayList<String> s = returnData.get(number);
			s.add(seatsBooked.trim());
		}
		else {
			if(seatsBooked.length() > 0) {
				ArrayList<String> s = new ArrayList<String>();
				s.add(seatsBooked);
				returnData.put(number,s);
			}
			
		}
	}
	
	public void bufferSeats(char keyValue) {
		int index = seatingarr.indexOf(keyValue);
		int currentVal = seatArr[index];
		if(20 - currentVal <= 3) {
			seatArr[index] = 20;
			TotalSeatsLeft = TotalSeatsLeft-20 + currentVal;
		}
		else {
			int updateVal = currentVal + 3;
			seatArr[index] = updateVal;
			TotalSeatsLeft-=3;
		}
	}
	
	public Map<String, ArrayList<String>> finalResult(){
		return returnData;
	}
	

}
