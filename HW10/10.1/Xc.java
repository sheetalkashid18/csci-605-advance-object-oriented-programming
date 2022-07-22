public class Xc extends Thread    {
    static Object o = new Object();
    static int counter = 0;
    int id;
    public Xc(int id)	{
        this.id = id;
        o       = new Object();
    }
    public void run () {
        if ( id == 0 )	{
            new Xc(1).start();
            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            new Xc(2).start();
            return;
        }


        synchronized ( o ) {
            if(id == 1){
                try {
                    sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.err.println(id + " --->");
            try {
                if(id == 2){
                    sleep(200);
                }
                if ( counter == 0 )	{
                    counter = 1;
                   // sleep(1000);
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
        new Xc(0).start();
    }
}

