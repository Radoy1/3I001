import graphic.*;
import java.awt.Point;

public class DessineLigne2 implements Runnable{
    private Point p1,p2;
    private Window win;

    public DessineLigne2 (Point p1, Point p2, Window win){
	this.p1  = p1;
	this.p2  = p2;
	this.win = win;
    }

    public void run(){
	win.plotLine(p1,p2);
    }
}

