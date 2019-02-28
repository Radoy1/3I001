public class ReponseRequette{

    private String message;
    private int    numeroRequette;
    private Client client;
    private Random random = new Random();
    private int    valeur;

    public ReponseRequette(String message, int numeroRequette, Client client){
	this.message        = message;
	this.numeroRequette = numeroRequette;
	this.client         = client;
    }
    

    public String toString(){

	return "J'ai recu la requette " + numeroRequette+" du client "+
	    client+", et j'envoie "+valeur+".";
    }
}
