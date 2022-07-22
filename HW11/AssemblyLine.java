package hw11;

public class AssemblyLine extends Thread {
	
	// The total number of stations given in the question
	static int NO_OF_STATIONS = 4;
	
	// The total number of cars that need to be manufactured
	static int totalCarsToBeProduced = 3;
	
	// Holds the ID of each station
	private int station_id;
	
	// Contains objects equal to the number of stations
	static Object all[] = new Object[NO_OF_STATIONS];
	
	// Objects for synchronization
	private Object first;
    private Object second;
    
    // Hold the references of the threads for every station
    static AssemblyLine stations[] = new AssemblyLine[NO_OF_STATIONS];
    
    static {
		for ( int index = 0; index < NO_OF_STATIONS; index ++ )
			all[index] = new Object();
	}
	
    // Constructor for the Assembly Line object
	public AssemblyLine(int station_id, Object first, Object second) {
		this.station_id = station_id;
		this.first = first;
		this.second = second;
	}
	
	public void run() {
		while (totalCarsToBeProduced != 0) {
			try { sleep(300); } catch ( InterruptedException e ) { }
			synchronized (first) {
				synchronized (second) {
					goToStation(station_id);
					second.notify();
				}
				try {
					first.wait();
				} catch ( Exception e ) { }
			}
		}
		
	}
	
	// Prints the station work for the particular station ID
	public void goToStation(int station_id) {
		switch (station_id) {
		case 1:
			station1();
			break;
		case 2:
			station2();
			break;
		case 3:
			station3();
			break;
		case 4:
			station4();
			break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + station_id);
		}
	}
	
	// Prints the work done at station 1
	public static void station1() {
		System.out.println("Station 1: Mounts tires on rims and send 4 tires to station 2");
	}
	
	// Prints the work done at station 2
	public static void station2() {
		System.out.println("\tStation 2: Mounts tires on car and sends the car to station  3");
	}
	
	// Prints the work done at station 3
	public static void station3() {
		System.out.println("\t\tStation 3: Put engine in car and sends the car to station 4");
	}
	
	// Prints the work done at station 4
	public static void station4() {
		
		System.out.println("\t\t\tStation 4: Add doors to car and sends the car out the factory");
		totalCarsToBeProduced--;
		
		// Stop the execution of threads when the car production is completed
		if(totalCarsToBeProduced == 0) {
			for(int index = 0; index < stations.length; index++) {
				stations[index].interrupt();
			}
		}
	}
	
	public static void main(String args[]) {
		  
		for(int i = 0; i < NO_OF_STATIONS; i++)
		{
			// If the thread is not the last thread to be created
			if(i != stations.length - 1) {
				stations[i] = new AssemblyLine(i+1, all[i], all[i+1]);
			}
			
			// If the thread is the last thread to be created
			else {
				stations[i] = new AssemblyLine(i+1, all[i], all[0]);
			}
			
			// Start the execution of the thread
			stations[i].start();
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
