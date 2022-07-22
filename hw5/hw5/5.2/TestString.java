package hw_5;

public class TestString {
	Array aArray = new Array();

	private static void error(String errorMessage)	{
		System.out.println("Error: " + errorMessage);
	}
	private void testNull()	{
		// System.out.println(this);
		if ( ! aArray.add(null) )
			error("1: Adding null element");
		if ( ! aArray.contains(null )	)
			error("2: null element not found");
		if ( ! aArray.delete(null) )
			error("3: Deleting null element");
		if ( aArray.delete(null) )
			error("4: Deleting null element");
		// System.out.println(this);
	}
	private void testAdd()	{
//		if ( ! aArray.add("a") )
//			error("Adding element");
		aArray.add("a");
		aArray.add("a");
		aArray.add("a");
		
		aArray.add("a");
		if ( ! aArray.add("a") )
			error("Adding element");
		
		if ( aArray.size() != aArray.getMax() )
			error("Adding size failed");
		if ( aArray.add("a") )
			error("Adding one too many");
		
		System.out.println(aArray);
		
	}
	private void testDelete()	{
		aArray.add(null);
		if ( ! aArray.delete(null) )
			error("Deleting null element");
		if (  aArray.delete(null) )
			error("Deleting non exciting null element");
		if ( ! aArray.delete("a") )
			error("Deleting first element");
		aArray.delete("a");  aArray.delete("a");  aArray.delete("a");
		if ( aArray.delete("a") )
			error("Deleting one too many");
		System.out.println(this);
		
	}
	private void test()	{
		testNull();
		testAdd();
		testDelete();
	}
	public static void main(String args[])	{
		TestString t = new TestString();
		t.test();
	}
}
