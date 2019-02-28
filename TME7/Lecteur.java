import java.util.Random;

public class Lecteur implements Runnable{

    private int ID;
    private EnsembleDonnees donnees;

    public Lecteur(int ID, EnsembleDonnees donnees){
	this.ID      = ID;
	this.donnees = donnees;
    }

    public void run(){

	try{
	    for(int i = 0; i<3; i++){
		donnees.lire(ID);
		Thread.sleep(100);
	    }
	}catch(InterruptedException e){
	}
    }
}
