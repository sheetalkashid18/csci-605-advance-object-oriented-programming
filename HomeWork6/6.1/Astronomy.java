package hw6;

//An interface Astronomy
public interface Astronomy {
	
	 //Get name of the Astronomical Object
	 String getName();
	 
	 //Set name of the Astronomical Object
	 void setName(String name);
	 
	 //Get density of the Astronomical Object
	 double getDensity();
	 
	 //Set density of the Astronomical Object
	 void setDensity(double density);
	 
	//This method creates a new copy of the object and returns it
	 Astronomy copyMethod();

}
