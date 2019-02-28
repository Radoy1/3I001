import java.util.concurrent.CyclicBarrier;
import java.lang.InterruptedException;

public class GroupeClients implements Runnable{

    private Restaurant rest;
    private int        nb_personnes;
    private int        id;
    private Thread[]   clients;
    private int        numero_table;
    private int        commandePret;
    private CyclicBarrier barrage;

    public GroupeClients(Restaurant rest, int id, int nb_personnes){

	this.rest         = rest;
	this.id           = id;
	this.nb_personnes = nb_personnes;
	clients           = new Thread[nb_personnes];
	barrage           = new CyclicBarrier (nb_personnes);
	numero_table      = -1;
	
    }

    public synchronized void reserver()throws InterruptedException{

	if(Thread.interrupted()){
	    //System.out.println("Le groupe "+ id + "veut s'interrompre");
	    throw new InterruptedException();
	}
	if(numero_table!=-1)
	    return;
	Integer reservation = rest.reserver(nb_personnes);
	if(reservation == null){
	    for(int i = 0; i < nb_personnes; i++){
		clients[i].interrupt();
	    }
	    throw new InterruptedException();
	}else{
	    numero_table = reservation.intValue();
	}
    }

    public void run(){
	for (int i = 0; i < nb_personnes; i++){
	    clients[i] = new Thread( new Client(rest, id*10+i, barrage, this));
	}
	System.out.println("Groupe "+id+" avec "+nb_personnes+" personnes veulent diner");
	for(int i = 0; i < nb_personnes; i++)
	    clients[i].start();
    }
}
