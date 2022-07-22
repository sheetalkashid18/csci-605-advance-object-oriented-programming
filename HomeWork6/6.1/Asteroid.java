package hw6;

//Class Asteroid implements Astronomy
public class Asteroid implements Astronomy {
	
	//Fields
	//Name of the Binaries
	String name;
		
	//Density of the Binaries
	double  density;
	    
	//Discoverer name
	String  discoverer;
    
	//Constructor initializes all the fields
    public Asteroid(String name, double density, String discoverer) {
		
		this.name = name;
		this.density = density;
		this.discoverer = discoverer;
	}
    
    //Gets the name of the Asteroid
	public String getName() {
		return name;
	}
	
	//Gets the name of the Asteroid
	public void setName(String name) {
		this.name = name;
	}
	
	//Gets the density of the Asteroid
	public double getDensity() {
		return density;
	}
	
	//Sets the density of the Asteroid
	public void setDensity(double density) {
		this.density = density;
	}
	
	//Gets the Discoverer of the Asteroid
	public String getDiscoverer() {
		return discoverer;
	}
	
	//Sets the Discoverer of the Asteroid
	public void setDiscoverer(String discoverer) {
		this.discoverer = discoverer;
	}
	
	//This method creates a new copy of the object and returns it
	public Asteroid copyMethod() {
		Asteroid temp = new Asteroid(this.name, this.density, this.discoverer);
		return temp;	
	}
	
	//toString method
	public String toString() {
		return  name + "/" + density + "/" + discoverer;
	}  
    
}
