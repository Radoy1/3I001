import java.util.Random;

public class Effaceur implements Runnable{

    private EnsembleDonnees donnees;
    private Random random = new Random();

    public Effaceur(EnsembleDonnees donnees){
	this.donnees = donnees;
    }

    public void run(){
	try{
	    for(int i = 0; i < 3; i++){
		try{
		    int w = random.nextInt(20);
		    //System.out.println("On va essayer d'effacer "+w);
		    donnees.effacer(w);
		}catch(DonneeInvalideException e){
		}
		Thread.sleep(200);
	    }
	}catch(InterruptedException e){
	}
    }
}
