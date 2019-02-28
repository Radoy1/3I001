public class PoolHangars{

    private Hangar[] pool;

    public PoolHangars(int n){
	pool = new Hangar[n];
    }

    public Hangar getHangar(int i){
	boolean trouve=false;

	if (i==0)
	    i++;
	
	while (trouve && i < pool.length){
	    if (pool[i].getOccupe() == true)
		i++;
	    else
		trouve = true;
	}

	return pool[i];
    }
    
}
	    
