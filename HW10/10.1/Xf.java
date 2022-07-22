public class Xf extends Thread    {
    static Object o = new Object();
    static int counter = 0;
    int id;
    public Xf(int id)	{
        this.id = id;
        o       = new Object();
    }
    public void run () {
        if ( id == 0 )	{
            new Xf(1).start();
            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            new Xf(2).start();
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
                    if(id == 1){
                        sleep(200);
                    }
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
        new Xf(0).start();
    }
}

