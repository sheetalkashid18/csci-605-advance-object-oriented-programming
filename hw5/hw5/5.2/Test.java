package hw_5;

public class Test {
	Array aArray	 	= new Array();
	Flexible aFlexible 	= new Flexible();
	Integer one 		= Integer.valueOf(1);
	Integer two 		= Integer.valueOf(2);
	Integer three 		= Integer.valueOf(3);

	private void testAddArray()	{
		
		aArray.add(one);
		aArray.add(two);
		
		// Check to see if multiple null objects can be added
		aArray.add(null);
		aArray.add(null);
		
		if (!aArray.add(one)) {
			System.out.println("Error");
		}
		
		if (!aArray.add(null)) {
			System.out.println("Error");
		}
		
		// Check to see if objects more than the max limit can be added
		if (!aArray.add(two)) {
			System.out.println("Error");
		}
		
		System.out.println(aArray);
	}
	private void testAddFlexible()	{
		
		// Check to see if objects more than the 
		// max limit can be stored in a Flexible object
		aFlexible.add(one);
		aFlexible.add(one);
		aFlexible.add(two);
		aFlexible.add(two);
		aFlexible.add(three);
		aFlexible.add(one);
		aFlexible.add(one);
		aFlexible.add(two);
		aFlexible.add(two);
		aFlexible.add(three);
		
		System.out.println(aFlexible.getMax());
		
		aFlexible.add(null);
		aFlexible.delete(two);
		System.out.println(aFlexible);
		
	}
	private void test()	{
		testAddArray();
		testAddFlexible();
	}
	public static void main(String args[])	{
		new Test().test();
	}
}
