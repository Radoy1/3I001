import java.util.Random;

public class Producteur implements Runnable{

    private EnsembleDonnees donnees;
    private Random random = new Random();
    public Producteur(EnsembleDonnees donnees){
	this.donnees = donnees;
    }

    public void run(){
	try{
	    for(int i=0; i < 5; i++){
		donnees.produire(random.nextInt(20));
		Thread.yield();
	    }

	    Thread.sleep(500);

	    for(int i=0; i < 4; i++){
		donnees.produire(random.nextInt(20));
		Thread.yield();
	    }

	    Thread.sleep(500);
	
	    for(int i=0; i < 5; i++){
		donnees.produire(random.nextInt(20));
		Thread.yield();
	    }

	    Thread.sleep(500);
	}catch(InterruptedException e){
	}
    }
}
