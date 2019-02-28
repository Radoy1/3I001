public class AleaStock{

    private AleaObjet[] stock;
    private int taille;
    private int rempli;

    public AleaStock(){
	this.taille = (int)(Math.random()*100);
	System.out.println(taille);
	stock       = new AleaObjet[taille];
	rempli      = 0;
    }

    public void remplirStock(int min, int max){
	if( rempli == taille ){
	    System.out.println("Stock plein");
	    return;
	}
	for(int i = 0; i < taille; i++){
	    stock[i] = new AleaObjet("Pommes",min,max);
	    rempli++;
	}
    }
    
    public boolean estVide(){
	return rempli == 0;
    }

    public AleaObjet extraireStock(){
	AleaObjet objet;
	objet = stock[0];
	for(int i = 0; i < rempli-1;i++){
	    stock[i]=stock[i+1];
	}
	stock[rempli-1] = null;
	rempli--;
	return objet;
    }
}
	    
