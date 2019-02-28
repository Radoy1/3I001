import java.util.ArrayList;

public class SegAcceuil{
    private boolean reserve;
    //private 

    public SegAcceuil(){
	reserve = false;
    }

    public synchronized void reserver() throws InterruptedException{
	
	while(reserve)
	    this.wait();
	reserve = true;
    }

}
