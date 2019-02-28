public class Test{
    public static void main(String[] args){

	AleaStock stock   = new AleaStock(); 
	Chariot chariot   = new Chariot(20,5);
	Thread chargeur   = new Thread (new Chargeur(stock, chariot));
	Thread dechargeur = new Thread(new Dechargeur(stock, chariot));
	stock.remplirStock(1,10);
	chargeur.start();
	dechargeur.start();

	try{
	    chargeur.join();
	}catch(InterruptedException e){
	}
    }
}
