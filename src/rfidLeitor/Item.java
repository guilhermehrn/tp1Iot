package rfidLeitor;

public class Item {
	Item(String _id, int _count) {
		id		= _id;
		count 	= _count;
	}
	
	Item(String str) {
		
		String fields[] = str.split(", ");
		
		id      = fields[0].substring(4);
		last    = Long.parseLong(fields[1].substring(5));
		rssi    = Float.parseFloat(fields[2].substring(5));
		speed   = Float.parseFloat(fields[3].substring(6));
		reads   = Float.parseFloat(fields[4].substring(6));
		antenna = fields[5].substring(4,5);
		distanceEst = 0;
		distanceReal = 0;
	}
	
	String id      = "";
	long   last    = 0;
	float  rssi    = 0;
	float  speed   = 0;
	float  reads   = 0;
	String antenna = "";
	float distanceEst = 0;
	float distanceReal = 0;
	
	int count = 0;
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getId() {
		return this.id;
	}
	
	public void addToCount(int count) {
		this.count += count;
	}
	
	public int getCount() {
		return this.count;
	}
}