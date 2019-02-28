public class Hangar{

    private Loco loco;
    protected boolean occupe;
    protected int id;
    private static int cpt = 0;
    
    public Hangar(){
	occupe = false;
	id = cpt++;
    }

    public boolean getOccupe(){
	return occupe;
    }
}
