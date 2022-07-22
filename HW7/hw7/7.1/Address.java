package hw7;

public class Address implements Comparable<Address> {
	
	private Integer houseNum; // Stores house number
	private String streetName;
	private String city;
	private String state;
	private Integer pinCode;
	
	public Address(int houseNum, String streetName, String city, String state, int pinCode) {
		this.houseNum = houseNum;
		this.streetName = streetName;
		this.city = city;
		this.state = state;
		this.pinCode = pinCode;
	}
	
	// Returns the string representation of an address 
	public String toString() {
		return " ( " + houseNum + ", " + streetName + ", " + city +
				", " + state + ", " + pinCode + " ) \n";
	}
	
	@Override
	public int compareTo(Address another) {
		
		// Compare house numbers of both the addresses
		int compareValue = this.houseNum.compareTo(another.houseNum);
		
		// If house numbers are equal then compare the street names
		if ( compareValue == 0) {
			compareValue = this.streetName.compareTo(another.streetName);
			
			// If street names are same then compare city names
			if (compareValue == 0) {
				compareValue = this.city.compareTo(another.city);
				
				// If city names are same then compare state names
				if (compareValue == 0) {
					compareValue = this.state.compareTo(another.state);
					
					// If state names are same then compare pin codes
					if (compareValue == 0) {
						compareValue = this.pinCode.compareTo(another.pinCode);
					}
				}
			}
		}
		
		return compareValue;
	}

}
