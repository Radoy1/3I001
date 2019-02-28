public class Caculateur implements Runnable {
	private Client client;
	private int typeReq;
	private int numreq;

	public Caculateur(Client c, int t, int n){
		client=c;
		typeReq=t;
		numreq=n;
	}
	
	public void run(){
		if(typeReq==1){
				int time= (int)(Math.random())*500;
				try{
					Thread.sleep(time);
				}catch(InterruptedException e){}
				client.requeteServie(new ReponseRequete(client,(int)(Math.random())*10,numreq));

			}else{
				while(true){

				}

			}
	}
}