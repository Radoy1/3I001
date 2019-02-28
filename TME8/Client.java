import java.util.ArrayList;

public class Client implements Runnable{
    
    private ArrayList<ReponseRequette> tableau;
    private int               id;
    private static int        cpt = 0;
    private Serveur           serveur;
    private int               nbRequettes;
    private Random            random  = new Random();

    public Client(Serveur serveur){
	id           = cpt++;
	this.serveur = serveur;
	tableau      = new ArrayList<ReponseRequette>();
	nbRequettes  = 0;
    }

    public void run(){
	try{
	    for(int i = 0; i < 5; i++){
		if(id%3==0)
		    serveur.soumettre(this, id*10+i,1);
		else
		    serveur.soumettre(this, id*10+i,2);

		while(tableau.size() < i+1)
		    Thread.sleep(Integer.nextInt(random));
	    }
	    
	}catch(InterruptedException e){
	}
    }

    public void requetteServie(Requette r){
	tableau.add(r);
    }
	
}
