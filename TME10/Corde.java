import java.util.concurrent.*;
import java.util.concurrent.locks.*;

public class Corde{

    private Semaphore     s;
    private Semaphore     condition;
    private Semaphore     plein;
    private boolean       kongPasse;
    private int           cpt;
    private int           cpt2;
    private int           cpt3;
    private Position      sens;
    
    public Corde(){
	s         = new Semaphore(1);
	condition = new Semaphore(0);
	plein     = new Semaphore(5);
	cpt       = 0;
	cpt2      = 0;
	cpt3      = 0;
	sens      = null;
	kongPasse = false;
	
    }

    public void acceder(Position p) throws InterruptedException{
	try{
	    s.acquire();
	    
	    while( p != sens && cpt2 > 0){
		cpt++;
		s.release();
		condition.acquire();
		s.acquire();
		cpt--;
	    }
	    while( cpt3 == 5 ){
		s.release();
		plein.acquire();
		s.acquire();
	    }
	    while(kongPasse){
		s.release();
		s.acquire();
	    }
	    
	    cpt3++;
	    cpt2++;
	    sens = p;
	}finally{
	    s.release();
	}
    }

    public void relacher(Position p)throws InterruptedException{
	try{
	    s.acquire();
	    cpt3--;
	    cpt2--;
	    if(cpt2 == 0){
		sens = null;
		if(cpt > 0){
		    condition.release(cpt);
		}
	    }
	    if(cpt3 == 0){
		plein.release(5);
	    }
		
	}finally{
	    s.release();
	}
    }

    public void accederKong(Position p) throws InterruptedException{
	try{
	    s.acquire();
	    while(cpt2 > 0){
		cpt++;
		s.release();
		condition.acquire();
		s.acquire();
		cpt--;
	    }
	    kongPasse = true;
	}finally{
	    s.release();
	} 
    }
    
    public void relacherKong(Position p)throws InterruptedException{
	try{
	    s.acquire();
	    kongPasse = false;
	}finally{
	    s.release();
	}
    }
}
	
