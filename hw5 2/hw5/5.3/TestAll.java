package hw_5;

public class TestAll	{
	// draw the class diagram, including interface
	// why are these declararions legal?
	static I1	anI1	=	new C1();
	static I2	anI2	=	new C3();
	static I1	anI1a	=	new C2();
	static I1	anI1b	=	new C4();
	static I2	anI2a	=	new C4();

	// which methods will be called and why?
	public static void test1()	{
		anI1.i1method();
		anI1b.i1method();
		anI1b.i1and2method();
		anI1b.i1method();
		anI2.i2method();
		anI2.i1and2method();
	}
	// which methods will be called and why?
	public static void test2()	{
		C3 aC3 = new C3();
		C5 aC5 = new C5();
		C3 aaC3 = (C3)aC5;
		aC3.c3andC5m();
		aC5.c3andC5m();
		aaC3.c3andC5m();
		System.out.println("aaC3.c3andC5 = " + aaC3.c3andC5 );
		aaC3.c3andC5 = 99999;
		aaC3.c3andC5m();
		System.out.println("aaC3.c3andC5 = " + aaC3.c3andC5 );
	}
	public static void main(String[] args)	{
		test1();
		test2();
	}
	// give an example when you would use an abstract class but not an interface
	// give an example when you would use an interface but not an abstract class
	// give an example when you have to use an interface
}
