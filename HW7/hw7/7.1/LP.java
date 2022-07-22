package hw7;

public class LP implements Comparable<LP> {
	
	private Integer year;
	private String albumName;
	private String bandname;
	private Float length;
	private Integer totalSongs;
	
	public LP(Integer year, String albumName, String bandName, Float length, Integer totalSongs) {
		this.year = year;
		this.albumName = albumName;
		this.bandname = bandName;
		this.length = length;
		this.totalSongs = totalSongs;
	}
	
	// Returns the string representation of an LP
	public String toString() {
		return " ( " + year + ", " + albumName + ", " + bandname +
				", " + length + ", " + totalSongs + " ) \n";
	}

	@Override
	public int compareTo(LP another) {
		
		// Compare the years of the two LPs
		int compareValue = this.year.compareTo(another.year);
		
		// If the years are same then compare the album names
		if ( compareValue == 0) {
			compareValue = this.albumName.compareTo(another.albumName);
			
			// If the album names are same then compare the band names
			if (compareValue == 0) {
				compareValue = this.bandname.compareTo(another.bandname);
				
				// If the band names are same then compare the lengths
				if (compareValue == 0) {
					compareValue = this.length.compareTo(another.length);
					
					// If the lengths are same then compare the total number of songs
					if (compareValue == 0) {
						compareValue = this.totalSongs.compareTo(another.totalSongs);
					}
				}
			}
		}
		
		return compareValue;
	}
	
	
	
}
