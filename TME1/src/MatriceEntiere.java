import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;
	
public class MatriceEntiere {
	
    int nbLignes;
    int nbColonnes;
    int[][] matrice;
	
    public MatriceEntiere(int nbLignes, int nbColonnes){
	
	this.nbLignes = nbLignes;
	this.nbColonnes = nbColonnes;
	
	matrice = new int[nbLignes][nbColonnes];
    }
    public MatriceEntiere(File file) {
	this.initMatrixFromFile(file);
    }
	
    public int getElem(int i, int j){
	
	return this.matrice[i][j];
    }
	
    public void setElem(int i, int j, int val){
	
	this.matrice [i][j]=val;
	return;
    }
	
    @Override
    public String toString() {
	// TODO Auto-generated method stub
	return super.toString();
    }
	
    public void afficherMatrice(){
	
	int i,j;
	for (i=0; i<nbLignes; i++){
	    for (j=0; j<nbColonnes; j++)
		System.out.print(this.matrice[i][j]+"  ");
	    System.out.print(System.lineSeparator());
	}	
	return;
    }
	
    private void initMatrixFromFile (File file){
	
	int x,y,val;
	BufferedReader buffer;
		
	try {
	    buffer = new BufferedReader(new FileReader(file));
	    Scanner sc          = new Scanner(buffer);
	    x= sc.nextInt();
	    this.nbLignes   = x;
	    y= sc.nextInt();
	    this.nbColonnes = y;
	    this.matrice = new int[x][y];
	    for (int i = 0; i<x; i++){
		for (int j = 0; j<y; j++){
		    val = sc.nextInt();
		    this.setElem(i,j,val);
		}    
	    }
	    buffer.close();
	    sc.close();
	}catch (Exception e) {
	}		
    }
	
    public void initialisationAZero() {
	int i,j;
	for(i=0;i<this.nbLignes;i++) {
	    for(j=0; j<this.nbColonnes; j++) {
		this.matrice[i][j]=0;
	    }
	}
	return;
    }
	
    public void transposee(MatriceEntiere m) {
	int[][] nouvelleMatrice = new int[m.nbColonnes][m.nbLignes];
		
	for (int i=0; i<m.nbLignes; i++) {
	    for(int j=0; j<m.nbColonnes; j++) {
		nouvelleMatrice[j][i]=m.matrice[i][j];
	    }
	}
		
	m.matrice = nouvelleMatrice;
		
	return;
    }
	
    public int[][] sommeMatrices(MatriceEntiere a, MatriceEntiere b) throws TaillesNonConcordantesException {
		
		
	if(a.nbColonnes!=b.nbLignes || a.nbLignes!=b.nbColonnes) 
	    throw new TaillesNonConcordantesException();
		
	int[][] matrice = new int[a.nbLignes][a.nbColonnes];
		
	for (int i = 0; i< a.nbLignes; i++) {
	    for ( int j = 0; j< a.nbColonnes; j++) {
		matrice[i][j] = a.matrice[i][j] + b.matrice[i][j];
	    }
	}
	return matrice;
    }
	
    public int[][] produitMatrice(MatriceEntiere a, MatriceEntiere b) throws TaillesNonConcordantesException{
		
	if(a.nbColonnes!=b.nbColonnes || a.nbLignes!=b.nbLignes) 
	    throw new TaillesNonConcordantesException();
		
	int val         = 0;
	int [][] matrice = new int[a.nbLignes][a.nbColonnes];
	for (int i = 0; i< a.nbLignes; i++) {
	    for ( int j = 0; j< a.nbColonnes; j++) {
		for ( int k = 0; k< a.nbColonnes; k++) {
		    val = a.matrice[k][j] * b.matrice[j][k] + val;
		}
		matrice[i][j] = val;
	    }
	}
	return matrice;
    }
}
	
