import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;


class Minesweeper implements ActionListener{
	final static int [][] difficulty={{8,10},{16,40},{20,85}};
	Frame f;
	ButtonGroup bg; 
	JRadioButton Easy,Medium,Hard;
	JButton b;
	public static Cell Minefield[][];
	public Minesweeper(){
		f=new Frame("Minesweeper");
		f.addWindowListener(new WindowAdapter(){
  			public void windowClosing(WindowEvent we){
    		System.exit(0);
  			}
		});
		Easy = new JRadioButton("Easy", true);
    	Medium = new JRadioButton("Medium", false);
    	Hard = new JRadioButton("Hard", false);
    	b=new JButton("Start");
		f.add(Easy);
		f.add(Medium);
		f.add(Hard);
		bg=new ButtonGroup();
		bg.add(Easy);
		bg.add(Medium);
		bg.add(Hard);
		Easy.setBounds(100,120,100,50);
		Medium.setBounds(200,120,100,50);
		Hard.setBounds(300,120,100,50);
		b.setBounds(200,180,100,50);    
		b.addActionListener(this);  
		f.add(b);
		f.setSize(500,300);
		/*f.add(new MyCanvas(difficulty[d][0]));
		System.out.println(difficulty[d][0]+"\t"+difficulty[d][1]);
		init(difficulty[d][0],difficulty[d][1]);*/
		f.setLayout(null);  
    	//f.setSize(difficulty[d][0]*Cell.w+20, difficulty[d][0]*Cell.h+60); 
    	f.setVisible(true);	
	}

	public void start(int d){
		f.remove(b);
		f.remove(Easy);
		f.remove(Medium);
		f.remove(Hard);
		
		f.add(new MyCanvas(difficulty[d][0]));
		System.out.println(difficulty[d][0]+"\t"+difficulty[d][1]);
		init(difficulty[d][0],difficulty[d][1]);
    	f.setSize(difficulty[d][0]*Cell.w+20, difficulty[d][0]*Cell.h+60); 	
	}

	static void init(int d,int m){
		Minefield=new Cell[d][d];
		for (int i=0;i<d ;i++ ) {
			for (int j=0;j<d ;j++ ) {
				Minefield[i][j]=new Cell(i,j);
			}
		}
		for (int i=0; i<m; i++) {
			int x=(int)(Math.random()*(d-1));
			int y=(int)(Math.random()*(d-1));
			//System.out.println("("+x+","+y+")");
			Minefield[y][x].value=-1;
			if(y>0&&x>0)
				if(Minefield[y-1][x-1].value!=-1)
					Minefield[y-1][x-1].value++;
			if(y>0)
				if(Minefield[y-1][x].value!=-1)
					Minefield[y-1][x].value++;
			if(y>0&&x<d-1)
				if(Minefield[y-1][x+1].value!=-1)
					Minefield[y-1][x+1].value++;
			if(x>0)
				if(Minefield[y][x-1].value!=-1)
					Minefield[y][x-1].value++;
			if(x<d-1)
				if(Minefield[y][x+1].value!=-1)
					Minefield[y][x+1].value++;
			if(y<d-1&&x>0)
				if(Minefield[y+1][x-1].value!=-1)
					Minefield[y+1][x-1].value++;
			if(y<d-1)
				if(Minefield[y+1][x].value!=-1)
					Minefield[y+1][x].value++;
			if(y<d-1&&x<d-1)
				if(Minefield[y+1][x+1].value!=-1)
					Minefield[y+1][x+1].value++;
		}/*
		for (int i=0;i<d ;i++ ) {
			for (int j=0;j<d ;j++ ) {
				System.out.print(Minefield[i][j].value+" ");
			}
			System.out.println();
		}*/
	}


	public void actionPerformed(ActionEvent e){    
		if(Easy.isSelected()){   
			start(0);
		}
		if(Medium.isSelected()){
			start(1);
		}
		if(Hard.isSelected()){
			start(2);
		}    
	}
}