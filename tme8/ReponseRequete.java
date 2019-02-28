public class ReponseRequete {
	private Client client;
	private int reponse;
	private int numrequete;


	public ReponseRequete(Client c , int rep, int num){
		client=c;
		reponse=rep;
		numrequete=num;
	}

	public String toString(){

		return "la requete numero "+numrequete+" a pour reponse : "+reponse;

	}
	
}