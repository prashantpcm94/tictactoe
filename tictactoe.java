package tictactoe;
import java.applet.Applet;
import java.awt.*;
import java.applet.*;
import java.awt.event.*;
public class game extends Applet implements Runnable ,MouseListener,MouseMotionListener
{
    Thread t=null;
    public void init() 
    {
         addMouseListener(this);
         addMouseMotionListener(this);
         setBackground(Color.gray);
    }
    int q[][]=new int[3][3];
    public void start()
    {
       t=new Thread(this);
       t.start();
    }
    boolean w=false,b=true;
    public void run()
    {
        try
        {
            for(;;)
            { 
                for(int i=0;i<3;i++)
                {
                    if(q[i][0]==q[i][1]&&q[i][0]==q[i][2]&&(q[i][0]==1||q[i][0]==-1))
                    {
                        w=true;
                        break;
                    }
                    
                    if(q[0][i]==q[1][i]&&q[1][i]==q[2][i]&&(q[1][i]==1||q[1][i]==-1))
                    {
                        w=true;
                        break;
                    }
                    
                }  
                 if(q[0][0]==q[1][1]&&q[1][1]==q[2][2]&&(q[1][1]==1||q[1][1]==-1))
                    {
                        w=true;
                    }
                  if(q[0][2]==q[1][1]&&q[1][1]==q[2][0]&&(q[1][1]==1||q[1][1]==-1))
                    {
                        w=true;
                    }
                  if(w)
                  {
                      repaint();
                      t.stop();
                  }
                repaint();
                Thread.sleep(200);
            }
         }
        catch(InterruptedException e)
        {
        }
    }
   
    public void mouseReleased(MouseEvent ke)
    {
    }
     public void mouseExited(MouseEvent ke)
    {
    }
    public void mouseEntered(MouseEvent ke)
    {
    }
    int x,y;
    int findBox(int l,int m)
    {
        if(l<200&&l>100)
            x=l=150;
        if(l<300&&l>200)
            x=l=250;
        if(l<400&&l>300)
            x=l=350;
        if(m>50&&m<150)
            y=m=100;
        if(m>150&&m<250)
            y=m=200;
        if(m>250&&m<350)
            y=m=300;
        if(l==150&&m==100)
            return 1;
        if(l==250&&m==100)
            return 2;
        if(l==350&&m==100)
            return 3;
       if(l==150&&m==200)
            return 4;
        if(l==250&&m==200)
            return 5;
        if(l==350&&m==200)
            return 6;
        if(l==150&&m==300)
            return 7;
        if(l==250&&m==300)
            return 8;
        if(l==350&&m==300)
            return 9;
        else
        {
            x=-100;
            y=-100;
            return 0;
        }
    }
    int box=0,k=1;
    int f(int t,int r)
    {
        if(t==0)
        {
            if(x>100&&x<400&&y>50&&y<350)
        {
            b=!b;
            k=k*(-1);
        }
            return r;
        }
        else
            return t;
    }
    public void mouseClicked(MouseEvent me)
    {
        if(!w)
        {
        x=me.getX();
        y=me.getY();
        box=findBox(x,y);
        switch(box)
        {
            case 1:
                q[0][0]=f(q[0][0],k);
                break;
            case 2:
                q[0][1]=f(q[0][1],k);
                break;
            case 3:
                q[0][2]=f(q[0][2],k);
                break;
            case 4:
                q[1][0]=f(q[1][0],k);
                break;
            case 5:
                q[1][1]=f(q[1][1],k);
                break;
            case 6:
                q[1][2]=f(q[1][2],k);
                break;
            case 7:
                q[2][0]=f(q[2][0],k);
                break;
            case 8:
                q[2][1]=f(q[2][1],k);
                break;
            case 9:
                q[2][2]=f(q[2][2],k);
                break;
        }
        
        repaint();
        }
    }
    public void mouseDragged(MouseEvent ke)
    {
    }
    public void mouseMoved(MouseEvent ke)
    {
    }
    public void mousePressed(MouseEvent ke)
    {
    }
    public void paint(Graphics g)
    {
        if(b)
        {
            g.drawString("PLAYER 1", 220, 20);
            
        }
        else
        {
            g.drawString("PLAYER 2", 220, 20);
           
        }
        g.setColor(Color.red);
        g.drawRect(100,50, 300, 300);
        g.setColor(Color.yellow);
        g.drawLine(100, 150, 400, 150);
        g.drawLine(100, 250, 400, 250);
        g.drawLine(200, 50, 200, 350);
        g.drawLine(300, 50, 300, 350);
        if(w)
        {
            if(b)
                g.drawString("Player2  Wins", 220, 420);
            else
                g.drawString("Player1  Wins", 220, 420); 
        }
        int c=0;
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                if(q[i][j]==1)
                {
                     g.setColor(Color.green);
                     g.drawLine(50+100*(j+1)-40,100*(i+1)-40, 50+100*(j+1)+40,100*(i+1)+40 );
                     g.drawLine(50+100*(j+1)-40,100*(i+1)+40, 50+100*(j+1)+40,100*(i+1)-40);
                     c++;
                }
                if(q[i][j]==-1)
                {
                    g.setColor(Color.cyan);
                    g.drawOval(50+100*(j+1)-40,100*(i+1)-40,80, 80);
                    c++;
                }
                   
            }
            
        }
        if(c==9)
        {
            g.drawString("Game  Draws", 220, 420); 
            k=1;
            b=true;
            w=false;
            for(int i=0;i<3;i++)
                for(int j=0;j<3;j++)
                    q[i][j]=0;
            box=0;
            
            try{
            Thread.sleep(5000);
            }
            catch(Exception e)
            {}
            t.start();
        }
    }
}
