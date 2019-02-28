import java.util.Random;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.BrokenBarrierException;

public class Client implements Runnable{

    private int           id;
    private Restaurant    rest;
    private CyclicBarrier attendrePotes;
    private Random        random;
    private GroupeClients monGroupe;

    public Client(Restaurant rest, int id, CyclicBarrier b, GroupeClients g){
	this.rest     = rest;
	this.id       = id;
	attendrePotes = b;
	random        = new Random();
	monGroupe     = g;
    }

    public void run(){

	try{
	    monGroupe.reserver();
	    Thread.sleep(random.nextInt(1200));
	    if(attendrePotes.await()==0){
		int id_groupe = id/10;
		System.out.println("Le groupe "+ id_groupe + " a commandé son menu");
	    }
	}catch(BrokenBarrierException b){
	    return;
	}catch(InterruptedException e){
	    return;
	}
    }
}
