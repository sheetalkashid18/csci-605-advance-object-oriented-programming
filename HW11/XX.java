public class XX extends Thread	{
    private String info;
    Object o_1;
    Object o_2;
    Object stop;
    static boolean oneIsRunning = false;


    public XX (String info, Object o_1, Object o_2, Object stop) {

        this.info    = info;
        this.o_1    = o_1;
        this.o_2    = o_2;
        this.stop    = stop;
    }
    public void run () {
        //System.out.println(getName());

        synchronized ( o_1 ) {

            System.out.println(info);
            try {
                if ( ! oneIsRunning )	{
                    new XX("1", o_2, o_1, stop).start();

                    oneIsRunning = true;
                }

                System.out.println(" try I am " + getName());

                synchronized ( o_2 ) {
                    System.out.println(" I am " + getName());
                    o_2.wait();
                    System.out.println("I will not get there");
                }
            } catch ( Exception e ) { }
        }
    }
    public static void main (String args []) {
        Object o_1 = new Object();
        Object o_2 = new Object();
        Object stop = new Object();
        new XX("0", o_1, o_2, stop).start();
    }
}
