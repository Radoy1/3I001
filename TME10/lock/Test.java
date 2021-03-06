
public class Test{

    private static int NB_BABOUINS = 12;

    public static void main(String[] args){

	Corde corde    = new Corde();
	Thread[] bSud  = new Thread[NB_BABOUINS];
	Thread[] bNord = new Thread[NB_BABOUINS];
	Thread kong    = new Thread(new Kong(corde, Position.NORD));
	
	for (int i = 0; i < NB_BABOUINS; i++){
	    bSud[i]    = new Thread(new Babouin(corde, Position.SUD));
	    bNord[i]   = new Thread(new Babouin(corde, Position.NORD));
	    bSud[i].start();
	    bNord[i].start();
	}
	kong.start();

	System.out.println("Je finis de mettre les babouins en place!");
    }
}
