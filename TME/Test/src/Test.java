
public class Test {

	    private static final int nbRangs=5;
	    private static final int nbSieges=4;


	    public static void main(String args[]){
		Avion avion = new Avion(nbRangs,nbSieges);	
		Thread chef = new Thread(new ChefDeBord(avion));
		Thread[] passager = new Thread[nbRangs*nbSieges];
		
		chef.start();
		for (int i =0; i < nbRangs ;i++){
		    for(int j = 0; j < nbSieges; j++){
			passager[i+j] = new Thread(new Passager(avion, i, j));
			passager[i+j].start();
		    }
		}
		
		try{
		    chef.join();
		    for (int i =0; i < nbRangs*nbSieges; i++){
			passager[i].join();
		    }
		}catch (InterruptedException e){
		}
		
		System.out.println("Tous les passagers on été embarqués.");
		
	    }
	    
	}


