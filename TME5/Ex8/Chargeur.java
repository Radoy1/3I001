public class Chargeur implements Runnable{

    
    private AleaStock stock;
    private Chariot   chariot;

    public Chargeur(AleaStock stock, Chariot chariot){
	this.stock   = stock;
	this.chariot = chariot;
    }

    public void run(){
	AleaObjet obj;
	boolean estVide;
	while(!stock.estVide()){
	    obj     = stock.extraireStock();
	    estVide = stock.estVide();
	    chariot.charger(obj, estVide);
	}
    }
}
