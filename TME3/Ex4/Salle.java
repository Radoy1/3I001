public class Salle{
    private boolean[][] nbPlacesLibres;
    private int nbRangs,nbPlacesParRang;
    
    public Salle(int nbPlacesParRang, int nbRangs){
	this.nbPlacesParRang = nbPlacesParRang;
	this.nbRangs         = nbRangs;
	nbPlacesLibres       = new boolean[nbPlacesParRang][nbRangs];
	for (int i = 0; i < nbRangs; i++){
	    for (int j = 0; j < nbPlacesParRang; j++){
		nbPlacesLibres[j][i]=true;
	    }
	}
    }

    private boolean capaciteOK(int n){
	int places=0;
	
	for (int i = 0; i < nbRangs; i++){
	    for (int j = 0; j < nbPlacesParRang; j++){
		if(nbPlacesLibres[j][i])
		    places++;
	    }
	}
	return places >= n;
    }

    private int nContiguesAuRangI(int n, int i){
	int cpt = 0;
	int pos = 0;
	
	for(int j = 0; j < nbPlacesParRang; j++){
	    if(cpt==n){
		pos = j;
		break;
	    }
	    if(nbPlacesLibres[j][i])
		cpt++;
	    else
		cpt=0;
	}
	if(cpt==n)
	    return pos - cpt;
	else
	    return -1;
    }

    private boolean reserverContigues(Groupe g, int n){

	int res=0;



	for(int i=0;i<nbRangs;i++){
	    res=nContiguesAuRangI(n,i);
	    if(res!=-1){
		for(int j = 0; j < n; j++){
		    nbPlacesLibres[j+res][i] = false;
		    g.position.add(new Position(j+res,i));
		}
	
		return true;
	    }
	}
	return false;

    }

    public synchronized boolean reserver(Groupe g, int n){

	int dec = n;
	
	if(!capaciteOK(n))
	    return false;
	if(reserverContigues(g,n))
	    return true;
	    
	for (int i = 0; i < nbRangs; i++){
	    for (int j = 0; j < nbPlacesParRang; j++){
		if(nbPlacesLibres[j][i]){
		    nbPlacesLibres[j][i] = false;
		    g.position.add(new Position(j,i));
		    dec--;
		}
		if(dec==0)
		    return true;
	    }
	}
	return true;
    }

    public void affiche(){
	for (int i = 0; i < nbRangs; i++){
	    for (int j = 0; j < nbPlacesParRang; j++){
		if(nbPlacesLibres[j][i])
		    System.out.print("- ");
		else
		    System.out.print("* ");
	    }
	    System.out.println();
	}
    }

    public void annuler(Groupe g, boolean tout, int nbPlaces, int nb){
	int x,y;
	
	if(tout){
	    for (int i = 0; i < nb; i++){
		x=g.position.get(i).x;
		y=g.position.get(i).y;
		nbPlacesLibres[y][x] = false;
	    }
	    g.position.clear();
	}else{
	    for (int i = nb-nbPlaces; i < nb; i++){
		x=g.position.get(i).x;
		y=g.position.get(i).y;
		nbPlacesLibres[y][x] = false;
		g.position.remove(i);
	    }
	}
    }
}
