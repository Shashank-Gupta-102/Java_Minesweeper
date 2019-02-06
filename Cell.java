import java.util.*;
import java.awt.*;
public class Cell{
	int x,y;
	static final int w=40;
	static final int h=40;
	int value=0;
	int state=0;
	Color c;
	Cell(int i,int j)
	{
		y=i*h;
		x=j*w;
		c=Color.BLUE;
		state=0;
	}
}