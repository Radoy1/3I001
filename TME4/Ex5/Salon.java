

public class Salon{

    private Client[] tabClients;
    private int nbOccupees;
    
    public Salon(int placesTotal){
	tabClients = new Client[placesTotal];
	nbOccupees = 0;
    }

    public synchronized boolean entrer(Client c){

	if(nbOccupees == tabClients.length){
	    System.out.println("Pas de place "+c.id+" s'en va");
	    return false;
	}
	System.out.println("Client "+c.id+" rentre");
	tabClients[nbOccupees] = c;
	nbOccupees++;
	this.notifyAll();
	return true;
    }

    public synchronized void prendreEnCharge() throws InterruptedException{
	while(nbOccupees == 0){
	    this.wait();
	}
	System.out.println("Client "+tabClients[0].id+" est pris en charge");
	synchronized(tabClients[0]){
	tabClients[0].disableOccupe();
	tabClients[0].notify();
	}
	for(int i=0;i<nbOccupees-1;i++)
	    tabClients[i]=tabClients[i+1];
	tabClients[nbOccupees-1] = null;
	nbOccupees--;
    }
}
