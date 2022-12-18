package parking;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.*;

public class Car implements Serializable {
	// class release version:
	private static final long serialVersionUID = 1L;
	// areas with prompts:
	String serialNum;
	static final String P_serialNum = "Serial Number";
	String owner;
	static final String P_owner = "owner";
	String startDate;
	static final String P_startDate = "startDate";
	String endTime;
	static final String P_endTime = "endTime";
	String endDate;
	static final String P_endDate = "endDate";
	String startTime;
	static final String P_startTime = "startTime";
	double price;
	static final String P_price = "Price";

	// validation methods:
	static Boolean validSerialNum(String str) 
    {
       return str.matches("^BY[0-9]{4}[A-Z]{2}-[1-7]$");
	}

    static Boolean validOwner(String str) 
    {
        return str.matches("^[A-Za-z-]+ [A-Za-z-]+ [A-Za-z-]+$");
    }

	static Boolean validTime(String str) {
        try {
            LocalTime.parse(str);
            return true;
        } catch (DateTimeParseException | NullPointerException e) {
            return false;
        }
	}

    private static Boolean validDate(String str)
    {
        try {
            DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
            df.setLenient(false);
            df.parse(str);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

	public static boolean nextRead(Scanner fin, PrintStream out) {
		return nextRead(P_serialNum, fin, out);
	}

	static boolean nextRead(final String prompt, Scanner fin, PrintStream out) {
		out.print(prompt);
		out.print(": ");
		return fin.hasNextLine();
	}

	public static final String ownerDel = ",";

	public static Car read(Scanner fin, PrintStream out) throws IOException,
			NumberFormatException {
		String str;
		Car car = new Car();
		car.serialNum = fin.nextLine().trim();
		if (!Car.validSerialNum(car.serialNum)) {
			throw new IOException("Invalid Serial Number: " + car.serialNum);
		}
		if (!nextRead(P_owner, fin, out)) {
			return null;
		}
		car.owner = fin.nextLine();
        if (!Car.validOwner(car.owner)) {
			throw new IOException("Invalid Owner: " + car.owner);
		}
		if (!nextRead(P_startDate, fin, out)) {
			return null;
		}
		car.startDate = fin.nextLine();
        if (!Car.validDate(car.startDate)) {
			throw new IOException("Start date: " + car.startDate);
		}
		if (!nextRead(P_startTime, fin, out)) {
			return null;
		}
		car.startTime = fin.nextLine();
		if (!Car.validTime(car.startTime)) {
			throw new IOException("Invalid start time: " + car.startTime);
		}
		if (!nextRead(P_endDate, fin, out)) {
			return null;
		}
		car.endDate = fin.nextLine();
        if (!Car.validDate(car.endDate)) {
			throw new IOException("End date: " + car.startDate);
		}
		if (!nextRead(P_endTime, fin, out)) {
			return null;
		}
		car.endTime = fin.nextLine();
        if (!Car.validTime(car.endTime)) {
			throw new IOException("Invalid end time: " + car.endTime);
		}
		if (!nextRead(P_price, fin, out)) {
			return null;
		}
		str = fin.nextLine();
		car.price = Double.parseDouble(str);
		return car;
	}

	public Car() {
	}

	public static final String areaDel = "\n";

	public String toString() {
		return new String(
			serialNum + areaDel + 
			owner + areaDel + 
			startDate + areaDel + 
			endTime + areaDel + 
			endDate + areaDel + 
			startTime + areaDel + 
			price
			);
	}
}
