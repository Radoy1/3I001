import graphic.*;
import java.awt.Point;

public class TestRun{
    public static void main(String[] args){
	Window win = new Window(150, 150, "Graphe");
	Point p1   = new Point(15,15);
	Point p2   = new Point(40,15);
	Point p3   = new Point(25,30);

	Thread t1  = new DessineLigne(p1,p2,win);
	Thread t2  = new DessineLigne(p2,p3,win);
	Thread t3  = new DessineLigne(p1,p3,win);
	t1.start();
	t2.start();
	t3.start();
    }
}
