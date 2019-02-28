import java.util.concurrent.ThreadLocalRandom;

public class Kong implements Runnable{

    private Corde      c;
    private Position   p;
    private int        id;
    private static int cpt = 0;

    public Kong (Corde c,Position p){
	this.c = c;
	this.p = p;
	id = cpt++;
    }

    public void traverser() throws InterruptedException{

	int min = 800, max = 1200;

	Thread.sleep(min + ThreadLocalRandom.current().nextInt(max-min));
    }

    public void run(){

	try{
	    c.accederKong(p);
	    System.out.println(this.toString() + "   " +  id + " a pris la corde.");
	    traverser();
	    System.out.println(this.toString() + "   " +  id + " est arrive.");
	    c.lacherKong(p);
	}catch(InterruptedException e){
	    System.out.println("Pb KONG !");
	}
    }
}
