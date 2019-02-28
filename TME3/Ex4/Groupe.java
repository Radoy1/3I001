import java.util.ArrayList;

public class Groupe implements Runnable{
    private int nb;
    private int id=0;
    private static int cpt = 0;
    private Salle  s;
    public ArrayList<Position> position; 
    
    public Groupe(int nb,Salle s){
	this.nb = nb;
	this.id = cpt++;
	this.s  = s;
	position=new ArrayList<Position>();
    }

    public void run(){
	s.reserver(this,nb);
    }
}
		    
    
