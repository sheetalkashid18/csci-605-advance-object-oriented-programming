public class Xe extends Thread    {
    static Object o = new Object();
    static int counter = 0;
    int id;
    public Xe(int id)	{
        this.id = id;
        o       = new Object();
    }
    public void run () {
        if ( id == 0 )	{
            new Xe(1).start();
            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            new Xe(2).start();
            return;
        }


        synchronized ( o ) {
            System.err.println(id + " --->");
            if(id == 2){
                try {
                    sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            try {
                if(id == 1){
                    sleep(200);
                }
                if ( counter == 0 )	{
                    //sleep(1000);
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
        new Xe(0).start();
    }
}
