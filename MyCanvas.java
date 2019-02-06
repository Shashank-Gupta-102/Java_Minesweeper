import java.awt.*;  
import java.awt.event.*;
import javax.swing.*;
class MyCanvas extends Canvas implements MouseListener{
  int dim;
  static Minesweeper m;
  MyCanvas(int d){

    addMouseListener(this);
    setBackground (Color.GRAY);  
    setSize(d*Cell.w, d*Cell.h);
    setBounds(10,50,d*Cell.w, d*Cell.h);
    dim=d;
  }
  public void paint(Graphics g){
      for (int i=0;i<dim;i++) {
        for (int j=0;j<dim ;j++ ) {
          Cell C=Minesweeper.Minefield[i][j];
          //System.out.println("Painting");
          
          if(C.state==0){
          g.setColor(Color.BLUE);
          g.fillRect(C.x,C.y,C.w-1,C.h-1);
          }
          if(C.state==1){
            g.setColor(Color.BLACK);
            g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
            g.drawString(Integer.toString(C.value),C.x+C.w/3,C.y+3*C.h/4);
          }
          if (C.state==2) {
          g.setColor(Color.RED);
          g.fillRect(C.x,C.y,C.w-1,C.h-1);  
          }
        }
      }
    }
    public void quit(){
      for (int i=0; i<dim; i++) {
        for (int j=0; j<dim;j++ ) {
          if (Minesweeper.Minefield[i][j].value==-1) {
            Minesweeper.Minefield[i][j].state=1;        
          }    
        }
      }
      repaint();
      removeMouseListener (this);  
      String[] options = {"restart","quit"};
      int x = JOptionPane.showOptionDialog(this, "Returns the position of your choice on the array","Click a button",JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        if (x==0) {
          m.f.dispose();
          m=new Minesweeper();
        }
        if(x==1){
          System.exit(0);
        }
    }
    public void recurclick(int i,int j){
      if(i<0||j<0||i>=dim||j>=dim)return;
      if(Minesweeper.Minefield[i][j].state==1)return;
      //System.out.println(Minesweeper.Minefield[i][j].value);
      Minesweeper.Minefield[i][j].state=1;
      if(Minesweeper.Minefield[i][j].value==-1)quit();
      if(Minesweeper.Minefield[i][j].value!=0)return;
      recurclick(i-1,j-1);
      recurclick(i-1,j);
      recurclick(i-1,j+1);
      recurclick(i,j-1);
      recurclick(i,j+1);
      recurclick(i+1,j-1);
      recurclick(i+1,j);
      recurclick(i+1,j+1);
    }
    public void mouseClicked(MouseEvent e) {
    }  
    public void mouseEntered(MouseEvent e) { 
    }  
    public void mouseExited(MouseEvent e) {  
    }  
    public void mousePressed(MouseEvent e) {
      int i=(e.getY()/Cell.h);
      int j=(e.getX()/Cell.w);
      Cell C=Minesweeper.Minefield[i][j];
      //System.out.println("( "+i+","+j+" )");
      if(e.getButton()==MouseEvent.BUTTON1){
          if (C.state==0)
            recurclick(i,j);
      }
      if(e.getButton()==MouseEvent.BUTTON3){
        if(C.state==0){
          C.state=2;
        }else if (C.state==2) {
          C.state=0;
        }
      }
      repaint();
    }
    public void mouseReleased(MouseEvent e) {  
    }
    public static void main(String[] args) {
      m=new Minesweeper();
    }
}