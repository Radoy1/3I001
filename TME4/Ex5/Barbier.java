public class Barbier implements Runnable{

    private Salon salon;
    
    public Barbier(Salon salon){
	this.salon = salon;
    }

    public void run(){
	try{
	    while(true){
		salon.prendreEnCharge();
	    }
	}catch(InterruptedException e){
	}
    }
		
}
