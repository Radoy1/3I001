public class Capote{
	
    Thread t1,t2;
    MoteurVitre md,mg;
    
    public Capote(){
	mg = new MoteurVitre(Cote.GAUCHE);
	md = new MoteurVitre(Cote.DROITE);
	t1 = new Thread(mg);
	t2 = new Thread(md);
    }

    public void ouvrirCapote(){
	t1.start();
	t2.start();
	if(mg.getPosition().equals(Position.HAUTE))
	    mg.demander(Demande.DESCENDRE);
	if(md.getPosition().equals(Position.HAUTE))
	    md.demander(Demande.DESCENDRE);
	   
	while(true){
	    if(mg.getPosition().equals(Position.HAUTE) &&
	       md.getPosition().equals(Position.HAUTE) )
		break;
	}
	System.out.println("La capote s'ouvre");

	mg.demander(Demande.MONTER);
	md.demander(Demande.MONTER);

	while(true){
	    if(mg.getPosition().equals(Position.BASSE) &&
	       md.getPosition().equals(Position.BASSE) )
		break;
	}
	    
	t1.interrupt();
	t2.interrupt();
}

    
    
}
