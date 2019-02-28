import java.util.*;
public class Client implements Runnable {
	private Serveur serveur;
	private ArrayList<ReponseRequete> rep;
	public int id;
	private static int compt=0;

	public Client(Serveur serv){
		
		serveur=serv;
		rep=new ArrayList<ReponseRequete>();
		id=compt;
		compt++;
		System.out.println("je me cree "+id);
	}
	public void run(){
		try{
			for(int i=0;i<5;i++){
				if(id%3==0){
					serveur.soumettre(this,i,1);
				}else{
					serveur.soumettre(this,i,2);
				}
			}
			while(rep.size()!=5){
				Thread.sleep(300);
			}
			System.out.println("je me termine"+id);
		}catch(InterruptedException e){

			}
		

	}

	public void requeteServie(ReponseRequete r){
		rep.add(r);

	}
}
	
