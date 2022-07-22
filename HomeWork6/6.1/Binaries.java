package hw6;

//Class Binaries implements Astronomy

public class Binaries implements Astronomy {
	
	//Field names
	//Name of the Binaries
	String name;
	
	//Density of the Binaries
    double  density;
    
    //Discoverer name
    String  discoverer;
    
    //satellites
    int satellites;
    
    //Constructor initializes all the fields
	public Binaries(String name, double density, String discoverer, int satellites) {
		this.name = name;
		this.density = density;
		this.discoverer = discoverer;
		this.satellites = satellites;
	}
	
	//Gets the name of the Binary
	public String getName() {
		return name;
	}
	
	//Sets the name of the Binary
	public void setName(String name) {
		this.name = name;
	}
	
	//Gets the density of the Binary
	public double getDensity() {
		return density;
	}
	
	//Sets the density of the Binary
	public void setDensity(double density) {
		this.density = density;
	}
	
	//Gets the Discoverer of the Binary
	public String getDiscoverer() {
		return discoverer;
	}
	
	//Sets the Discoverer of the Binary
	public void setDiscoverer(String discoverer) {
		this.discoverer = discoverer;
	}
	
	//Gets the number of satellites of the Binary
	public int getSatellites() {
		return satellites;
	}
	
	//Sets the number of satellites of the Binary
	public void setSatellites(int satellites) {
		this.satellites = satellites;
	}
	
	//This method creates a new copy of the object and returns it
	public Binaries copyMethod() {
		Binaries temp = new Binaries(this.name, this.density, this.discoverer, this.satellites);
		return temp;
		
	}
	
	//toString method
	public String toString() {
		return name + "/" + density + "/" + discoverer + "/" + satellites;
	}
    
    

}
