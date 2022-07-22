package hw6;

import java.util.ArrayList;
import java.util.List;

	public class AstronomicalObjectCorrected {
	
	    //List of all the astronomical objects stored 
		List<Object> allComponents = new ArrayList<Object>();
		
		//Astronomical object storage name
		String objectName;
		
		//Total number of objects stored
		int totalObjectsTillNow = 0;
		
		//Total density of the objects added till now
		double totalDensity = 0;
		
		//Average of the densities of the objects added till now
		double avgDensity = 0;
	
		
	//Constructor gives name to the Astronomical object storage
	public AstronomicalObjectCorrected(String string) {
		
		this.objectName = string;	
	}
	
	//Creates copy of the Asstronomical object
	//Adds the copy object of generic type T to a list of all the components in the astronomical object and calculates the average density
	private <T> void add(T AstroObject) {
		
		Object newObj = new Object();
		newObj = ((Astronomy) AstroObject).copyMethod();
		
		this.allComponents.add(newObj);
		totalObjectsTillNow++;
		totalDensity += ((Astronomy) AstroObject).getDensity();
		avgDensity = totalDensity / totalObjectsTillNow;
		
	}

	// Gets all the names of the components added so far
    private <T> String getAllNames() {
		
		String addString = "";
		
		//For all components in the astronomical object storage
		for (int index = 0; index < allComponents.size(); index++ ) {
			
			//gets the name of the component at index
			Object name = allComponents.get(index);
			
			//Appends that component name to the string
			addString += ((Astronomy) name).getName() + ", ";		
		}
		
		//return the new list of names
		return addString;
	}
	
	//toString method
	public String toString() {
		
		String returnString = "";
		
		int index = 0;
		
		for(Object obj : allComponents) {
			
			returnString += index + ": " +  (Astronomy)obj + "\n";
			index++;
			
		}
		
		returnString += "Average Density : " + avgDensity;
		
		return returnString;
	}
	
	//prints the names and the components in the astronomical object class
	public static void print( AstronomicalObjectCorrected aStorageOfAstronomicalObject ) {
            System.out.println(aStorageOfAstronomicalObject.getAllNames());
            System.out.println(aStorageOfAstronomicalObject);
    }
	
	//Test for planets
	public static void testPlanets()        {
			
		    //New Object
            AstronomicalObjectCorrected aStorageOfAstronomicalObject = new AstronomicalObjectCorrected("Milky Way");
            
            //Add a new planet to the astronomical object
            aStorageOfAstronomicalObject.add(new Planet("Mercury", 5.427, 87.97, 0));

            //Creates a new planet
            Planet aPlanet = new Planet("Saturn", 0.687, 10792, 82);
            
            //Adds the planet to the astronomical object
            aStorageOfAstronomicalObject.add(aPlanet);
            
            //Change the aPlanet object
            aPlanet.setName("Earth");
            aPlanet.setDensity(5.514);
            aPlanet.setOrbitalPeriod(365.256363004);
            aPlanet.setNumberOfMoons(1);
            
            //Adds it after changing
            aStorageOfAstronomicalObject.add(aPlanet);
            
            //aStorageOfAstronomicalObject.add(new Binaries("XYZ", 5.427, "Sam", 3));
            
            //Print the Object
            print(aStorageOfAstronomicalObject);
    }
	
	
	//Main method
	public static void main(String args[]) {
		
		//Test planets
		testPlanets();	
	}

}
