package hw10;

public class X_1d extends Thread    {
    static Object o = new Object();
    static int counter = 0;
    int id;
    public X_1d(int id)	{
        this.id = id;
        o       = new Object();
    }
    public void run () {
        if ( id == 0 )	{
            new X_1d(1).start();
            new X_1d(2).start();
            return;
        }
        if (id == 1){
            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        synchronized ( o ) {
            System.err.println(id + " --->");
            try {
                if ( counter == 0 )	{
                    counter = 1;
                    o.wait();
                } else
                    o.notifyAll();
            }
            catch (  InterruptedException e ) {
            }
            System.err.println(id + " <---");
        }
    }
    public static void main (String args []) {
        new X_1d(0).start();
    }
}

