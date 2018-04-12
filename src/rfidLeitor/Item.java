package rfidLeitor;

public class Item {
	Item(String str) {
		String fields[] = str.split(", ");
		id = fields[0].substring(4);
		last = Long.parseLong(fields[1].substring(5));
		rssi = Float.parseFloat(fields[2].substring(5));
		speed = Float.parseFloat(fields[3].substring(6));
		reads = Float.parseFloat(fields[4].substring(6));
	}
	
	String id = "";
	long last = 0;
	float rssi = 0;
	float speed = 0;
	float reads = 0;
}