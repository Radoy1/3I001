import java.util.concurrent.locks*;

public class Serveur{

    private ReentrantLock lock    = new ReantrantLock();
    private Condition     pris    = lock.newCondition();
    private Condition     requete = lock.newCondition();
    private Client         client;
    private ReponseRequete repReq;
    private boolean        occupe;

    public Serveur(ReponseRequete repReq){
	this.repReq = repReq;
	occupe      = false;
    }
    
    public void run(){

	try{
	    while(true){
		attendreRequete();
		traiterRequete();
	    }catch(InterruptedException e){
		System.out.println("Serveur Interrompu");
	    }
	}
    }

    public void soumettre(Client client, int id, int type){
	lock.lock();
	try{
	    while(occupe){
		pris.await();
	    }
	    occupe = false;
	    
	    

}

    
	    
