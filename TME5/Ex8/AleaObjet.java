public class AleaObjet{
    private int min,max;
    private int poids;
    private String type;
    
    public AleaObjet(String type, int min, int max){
	this.min  = min;
	this.max  = max;
	this.type = type;
	poids     = min + (int)(Math.random() * (max-min));
    }

    public int getPoids(){
	return poids;
    }
    
    public String toString(){
	return type+"; "+poids+"kg.";
    }
}
