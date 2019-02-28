public class Client implements Runnable{
    private boolean barbierOccupe = true;
    private Salon salon;
    private static int cpt=0;
    public  int id;

    public Client(Salon salon){
	this.salon = salon;
	cpt++;
	id = cpt;
    }

    public void disableOccupe(){
	barbierOccupe=false;
    }
    public void run(){
	if(salon.entrer(this)){
	    try{
		synchronized(this){
		    while(barbierOccupe)
			this.wait();
		}
	    }catch(InterruptedException e){
	    }
	    System.out.println("Client "+id+" sort");
	}
    }
}

