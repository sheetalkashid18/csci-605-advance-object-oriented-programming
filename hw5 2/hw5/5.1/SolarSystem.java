package hw_5;

class SolarSystem {
	
	// Holds the total number of planets a solar system will contain
	private int totalPlanets;
	
	// Holds the number of planets inserted into the solar system so far
	private int soManyPlanets = 0;
	
	// Holds the total density of all the planets
	// inserted so far into the solar system
	private double totalDensity = 0;
	
	// Contains all the planets that a solar system
	// will contain as an array of Planet objects
	private Planet[] planets;
	
	// Holds the average density of all the planets
	// inserted so far into the solar system
	private double avgDensity = 0;
	
	// Constructs a SolarSystem object with the specified 
	// number of totalPlanets
	SolarSystem(int totalPlanets) {
		
		this.totalPlanets = totalPlanets;
		planets = new Planet[totalPlanets];
	}
	
	// Inserts a planet at the specified position in the solar system
	// and updates the fields - soManyPlanets, totalDensity and avgDensity
	void setPlanet(int position, Planet newPlanet) {
		
		planets[position - 1] = newPlanet;
		soManyPlanets += 1;
		totalDensity += newPlanet.getDensity();
		avgDensity = totalDensity / soManyPlanets;
	}
	
	// Returns a String containing the textual representation of
	// a SolarSystem object
	public String toString() {
		
		String toDisplay = "";
		for( int index = 0; index < totalPlanets; index++ ) {
			if ( planets[index] != null ) {
				toDisplay += (index + 1) + ": " + planets[index].toString() + "\n";
			}
		}
		toDisplay += "Average Density: " + avgDensity;
		return toDisplay;
	}
	
	public static void main(String args[])  {
		
		SolarSystem aSolarSystem = new SolarSystem(8);  // sadly Pluto was demoted

		Planet aPlanet = new Planet("Mercury", 5.427, 87.97, 0);
		aSolarSystem.setPlanet(1, new Planet("Mercury", 5.427, 87.97, 0));

		aPlanet.setName("Saturn");
		aPlanet.setDensity(0.687);
		aPlanet.setOrbitalPeriod(10759.22);
		aPlanet.setNumberOfMoons(82);
		aSolarSystem.setPlanet(6, aPlanet);
		/*
        aPlanet.setName("Earth");
        aPlanet.setDensity(5.514);
        aPlanet.setOrbitalPeriod(365.256363004);
        aPlanet.setNumberOfMoons(1);
        aSolarSystem.setPlanet(3, aPlanet);
		 */
		System.out.println(aSolarSystem);
	}
	
}















