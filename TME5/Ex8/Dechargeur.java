public class Dechargeur implements Runnable{

    
    private AleaStock stock;
    private Chariot   chariot;
    private boolean   fini  = false;

    public Dechargeur(AleaStock stock, Chariot chariot){
	this.stock   = stock;
	this.chariot = chariot;
    }

    public void run(){
	while(!fini){
	    fini = chariot.decharger();
	}
    }
}
