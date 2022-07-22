public class Xg extends Thread    {
    static Object o = new Object();
    static int counter = 0;
    int id;
    public Xg(int id)	{
        this.id = id;
        o       = new Object();
    }
    public void run () {
        if ( id == 0 )	{
            new Xg(1).start();
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            new Xg(2).start();
            return;
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
        new Xg(0).start();
    }
}
