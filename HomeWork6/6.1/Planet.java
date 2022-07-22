package hw6;

//Class Planet implements Astronomy

public class Planet implements Astronomy {
	
	//Field names
	//Name of the planet
	String name;
	
	//Density of the planet
    double density;
    
    //Orbital Period of the planet
    double orbitalPeriod;
    
    //Total No of moons of a planet  
    int numberOfMoons;
    
    //Constructor of planet class initializes all the fields
	public Planet(String string, double d, double e, int i) {
		
		this.name = string;
		this.density = d;
		this.orbitalPeriod = e;
		this.numberOfMoons = i;
		
	}
	
	//Gets the name of the planet
	public String getName() {
		return name;
	}
	
	//Sets the name of the planet
	public void setName(String name) {
		this.name = name;
	}
	
	//Gets the density of the planet
	public double getDensity() {
		return density;
	}
	
	//Sets the density of the planet
	public void setDensity(double density) {
		this.density = density;
	}
	
	//Gets the orbital period of the planet
	public double getOrbitalPeriod() {
		return orbitalPeriod;
	}
	
	//Sets the orbital period of the planet
	public void setOrbitalPeriod(double orbitalPeriod) {
		this.orbitalPeriod = orbitalPeriod;
	}
	
	//Gets the number of moons of the planet
	public int getNumberOfMoons() {
		return numberOfMoons;
	}
	
	//Sets the number of moons of the planet
	public void setNumberOfMoons(int numberOfMoons) {
		this.numberOfMoons = numberOfMoons;
	}
	
	//This method creates a new copy of the object and returns it
	public Planet copyMethod() {
		Planet temp = new Planet(this.name, this.density, this.orbitalPeriod, this.numberOfMoons);
		return temp;
	}
	
	//toString method
	public String toString() {
		return name + "/" + density + "/" + orbitalPeriod + "/" + numberOfMoons ;
	}

	
}
