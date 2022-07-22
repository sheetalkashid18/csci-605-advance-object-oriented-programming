package hw_5;

// Default access level - visible inside the package only
class Planet {
	
	private String name;
	private double density;
	private double orbitalPeriod;
	private int noOfMoons;
	
	// Default access level - visible inside the package only - same for all methods
	Planet() {
		System.out.println("Default constructor");
	}
	
	Planet(String name, double density, double orbitalPeriod, int noOfMoons) {
		this.name = name;
		this.density = density;
		this.orbitalPeriod = orbitalPeriod;
		this.noOfMoons = noOfMoons;
	}
	
	// Inherited from Object class, so, can't reduce the visibility
	public String toString() {
		return name + "/" + density + "/" + orbitalPeriod + "/" + noOfMoons;
	}
	
	// Sets the name of the planet
	void setName(String name) {
		this.name = name;
	}
	
	// Sets the density of the planet
	void setDensity(double density) {
		this.density = density;
	}
	
	// Sets the orbital period of a planet
	void setOrbitalPeriod(double orbitalPeriod) {
		this.orbitalPeriod = orbitalPeriod;
	}
	
	// Sets the numbers of moons for the planet
	void setNumberOfMoons(int noOfMoons) {
		this.noOfMoons = noOfMoons;
	}
	
	// returns name of the planet
	String getName() {
		return name;
	}
	
	// Returns the density of the planet
	double getDensity() {
		return density;
	}
	
	// Returns the orbital period the planet
	double getOrbitalPeriod() {
		return orbitalPeriod;
	}
	
	// Returns the number of moons of the planet
	int getNumberOfMoons() {
		return noOfMoons;
	}
	
	public static void main(String args[])  {
		
		Planet aPlanet = new Planet("Mercury", 5.427, 87.97, 0);
		System.out.println(aPlanet);
		
		aPlanet.setName("Saturn");
		aPlanet.setDensity(0.687);
		aPlanet.setOrbitalPeriod(10759.22);
		aPlanet.setNumberOfMoons(82);
		System.out.println(aPlanet);

		System.out.println("1: " + aPlanet.getName() );
		System.out.println("2: " + aPlanet.getDensity() );
		System.out.println("3: " + aPlanet.getOrbitalPeriod() );
		System.out.println("4: " + aPlanet.getNumberOfMoons() );

	}
	
}


















