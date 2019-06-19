package javaapplication30;

import java.util.Scanner;
import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.Color;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.net.*;

class number{
	public  static int count;
	  public  static int enemy;
	  public  static int point;
	  public  static int e;
	
	  public static int timelimit;
	number(int c){
		  if(c==0)
		  {enemy=6;
		  e=1;
		  timelimit=60;
		  }
		  if(c==1)
		  {enemy=4;
		  e=2;
		  timelimit=40;
		  }
		  if(c==2)
		  {enemy=2;
		  e=3;
		  timelimit=20;
		  }
	
	}
	  }

class Ex5 extends JFrame{
    Ex5(){
        this.setTitle("��� ����");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        GamePanel p = new GamePanel();
        this.add(p);
        
        this.setLocationRelativeTo(null);
        this.setSize(800,800);
        this.setResizable(false);
        this.setVisible(true);
        p.startGame();
    }
}

class GamePanel extends JPanel implements KeyListener{
	
	GamePanel k;
	number num = new number();
	TimeThread timethread = new TimeThread();
	TargetThread[] targetThread = new TargetThread[num.enemy];
    TargetThread2 targetThread2;
    TargetThread3 targetThread3;
    TargetThread4 targetThread4;
    JLabel pointshow=new JLabel();
    JLabel time=new JLabel();
    JLabel gameover=new JLabel();
    JTextField ioField=new JTextField();
    JLabel base = new JLabel();
    JLabel bullet = new JLabel();
    JLabel[] target = new JLabel[num.enemy];
    JLabel target2 = new JLabel();
    JLabel target3= new JLabel();
    JLabel target4= new JLabel();
    ImageIcon img;
    ImageIcon img2;
    ImageIcon img3;
    ImageIcon img4;
	BaseThread baseThread;
	BulletThread bulletThread = null;
    boolean direction; // true : Right , false : Left
	boolean fired; // true : fired , false : not fired yet
    //AudioClip sound;

    GamePanel()
    {
        this.setLayout(null);
        base.setSize(40,40);
		base.setOpaque(true);
		base.setBackground(Color.BLACK);
		base.setLocation(380, 520);
        base.requestFocus();
		base.addKeyListener(this);
        
        img = new ImageIcon("C:\\Users\\ekc14\\eclipse-workspace\\java project\\src\\javaapplication30\\�⺻����.png");
        img2 = new ImageIcon("C:\\\\Users\\\\ekc14\\\\eclipse-workspace\\\\java project\\\\src\\\\javaapplication30\\\\����������.png");
        img3 = new ImageIcon("C:\\\\Users\\\\ekc14\\\\eclipse-workspace\\\\java project\\\\src\\\\javaapplication30\\\\�ذ�.png");
        img4 = new ImageIcon("C:\\\\Users\\\\ekc14\\\\eclipse-workspace\\\\java project\\\\src\\\\javaapplication30\\\\����.png");
        for(int i=0;i<targetThread.length;i++)
        {
        	 target[i] = new JLabel(img);
             target[i].setSize(img.getIconWidth(),img.getIconHeight());
             this.add(target[i]);
        }
      
        
        target2 = new JLabel(img2);
        target3 = new JLabel(img3);
        target4 = new JLabel(img4);
        //�̹��� ũ�⸸ŭ ���̺� ũ�� ����
        target2.setSize(img2.getIconWidth(),img2.getIconHeight());
      //  System.out.println(""+img2.getIconWidth()+"  "+img2.getIconHeight());
        // Ȯ�ΰ�� ũ�� 96 96
        this.add(target2);
        
        target3.setSize(img3.getIconWidth(),img3.getIconHeight());
        
        this.add(target3);
        
        target4.setSize(img4.getIconWidth(),img4.getIconHeight());
       
        this.add(target4);
        pointshow.setSize(100,50);
        time.setSize(100,50);
        //ioField.setSize(50,50);
        
        this.add(pointshow);
        this.add(time);
        //this.add(ioField);
        
      
        bullet.setSize(10,10);
        bullet.setOpaque(true);
        bullet.setBackground(Color.red);
        this.add(base);
        this.add(bullet);
        this.add(gameover);
        
        //URL url = Ex5.class.getResource("LASER.wav");
        //sound = Applet.newAudioClip(url);
        /////////////
        
        /////////////
    }
    
    public void startGame(){
        base.setLocation(this.getWidth()/2-20, this.getHeight()-40);
        bullet.setLocation(this.getWidth()/2-5, this.getHeight()-50);
        
        for(int i=0;i<targetThread.length;i++)
        {
        	target[i].setLocation(0, 0);
        }
        target2.setLocation(-96, -96);
        target3.setLocation(-96, -96);
        target4.setLocation(-96, -96);
        pointshow.setLocation(350,0);
        time.setLocation(300,0);
        //ioField.setLocation(400,0);
        //Ÿ���� �����̴� ������
        
        
        
        timethread.start();
      
        	
        for(int i=0;i<targetThread.length;i++)
        {
        	targetThread[i] = new TargetThread(target[i]);
        	targetThread[i].start();
        }
        
        
        targetThread2 = new TargetThread2(target2);
    		//targetThread2.start();
    	
        targetThread3 = new TargetThread3(target3);
    		//targetThread3.start();
    	
        targetThread4 = new TargetThread4(target4);
    		//targetThread4.start();
    
        
        
        //���̽��� ������ �ΰ� ����Ű �Է¿� ���� bullet������ ����
        base.requestFocus();
        base.addKeyListener(this);
            
                    
                
            }

       
            
            
      
    
    /*public void startGame2(){
    	if(num.count==2)
        { targetThread2 = new TargetThread2(target2);
    		targetThread2.start();}
    	if(num.count==2)
        { targetThread3 = new TargetThread3(target3);
    		targetThread3.start();}
    	if(num.count==2)
        { targetThread4 = new TargetThread4(target4);
    		targetThread4.start();}
    }*/
   class TimeThread extends Thread{
		
		
		public TimeThread(){
			timelimit=num.timelimit;
		}
		public void run() {
			while(true) {
				
				try 
				{
					time.setText(Integer.toString(timelimit));
					sleep(1000);
					timelimit--;
					if(timelimit==-1)
					{
						break;
					}
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
			 KillThread();
			 gameover.setSize(800,800);
		     gameover.setOpaque(true); //Opaque���� true�� �̸� ������ �־�� ������ ����ȴ�.
		     gameover.setForeground(Color.red);
		     gameover.setText("Game Over");
		     gameover.setHorizontalAlignment(JLabel.CENTER);
		     gameover.setVisible(true);
		    
		}
		
    }
   void KillThread()
   {
	   for(int i=0;i<num.enemy;i++)
		   targetThread[i].stop();
	   targetThread2.stop();
	   targetThread3.stop();
	   targetThread4.stop();
	  // bulletThread.stop();
	  // baseThread.stop();
   }
    class TargetThread extends Thread{
        JLabel target;
        int d=20;
       
        int xx;
        int yy;
        TargetThread(JLabel target){
        	int xx = (int) Math.floor(Math.random() * 800);
        	int yy = (int) Math.floor(Math.random() * 600);
            this.target=target;
            target.setLocation(xx, yy);
        }
        public void run(){
        	int a=5;
        	int b=5;
        	int d=20;
            while(true){
            	/*	
            	    if(c.count==6)
            	  	{
            	  	a = (int) Math.floor(Math.random() * 9);
            	  	}
            	*/
            	if(num.count>=4)
            	{
            		if(num.count>=6)
            		{d=10;}
            		else
            		{d=15;}
            	}
                int x=target.getX()+a;//5�ȼ��� �̵�
                int y=target.getY();
                if(num.count>=2)
                {y=target.getY()+b;}                
                //������ ������ �������
                if(x>GamePanel.this.getWidth())
                	{
                	a=-5;
                    target.setLocation(GamePanel.this.getWidth(), y);
                    }
                //������ �ȿ� ������� 5�ȼ��� �̵�
                else
                    target.setLocation(x, y);
                
                if(x<0)
            	{
                	a=5;
                target.setLocation(5, y);}
                else
                    target.setLocation(x, y);
//////////////////////////////////////////////////////////////////////////////////////
                
                if(y>600)
            	{
            	b=-5;
                target.setLocation(x, 600);
                }
            //������ �ȿ� ������� 5�ȼ��� �̵�
            else
                target.setLocation(x, y);
            
            if(y<0)
        	{
            	b=5;
            target.setLocation(x, 0);}
            else
                target.setLocation(x, y);
            
            
////////////////////////////////////////////////////////////////////////////////////
                //0.02�ʸ��� �̵�
                try{
                    sleep(d);
                }
                //�����尡 �װԵǸ� �ʱ� ��ġ�� ��ġ�ϰ�, 0.5�ʸ� ��ٸ���.
                catch(Exception e){
                    target.setLocation(5, y);
                    num.point++;
                    timelimit+=6/num.e;
                	num.count++;
                    pointshow.setText("����:"+num.point);
                    try{
                        sleep(500);
                    }
                    catch(Exception e2){}
                }
            }
        }
    }
    
    class TargetThread2 extends Thread{
    
    	JLabel target;
        int d=20;
       
        int xx;
        int yy;
        
        
        TargetThread2(JLabel target){
        
        	int xx = (int) Math.floor(Math.random() * 800);
        	int yy = (int) Math.floor(Math.random() * 600);
            this.target=target;
            if(num.count==2) {target.setLocation(xx, yy);}
        	
        }
        
        public void run(){
        	 {
        	int a=5;
        	int b=5;
        	int d=15;
            while(true){
            	/*	
            	    if(c.count==6)
            	  	{
            	  	a = (int) Math.floor(Math.random() * 9);
            	  	}
            	*/
            	if(num.count>=4)
            	{
            		if(num.count>=6)
            		{d=5;}
            		else
            		{d=10;}
            	}
                int x=target.getX()+a;//5�ȼ��� �̵�
                int y=target.getY();
                if(num.count>=2)
                {y=target.getY()+b;}                
                //������ ������ �������
                if(x>GamePanel.this.getWidth())
                	{
                	a=-5;
                    target.setLocation(GamePanel.this.getWidth(), y);
                    }
                //������ �ȿ� ������� 5�ȼ��� �̵�
                else
                    target.setLocation(x, y);
                
                if(x<0)
            	{
                	a=5;
                target.setLocation(5, y);}
                else
                    target.setLocation(x, y);
//////////////////////////////////////////////////////////////////////////////////////
                
                if(y>600)
            	{
            	b=-5;
                target.setLocation(x, 600);
                }
            //������ �ȿ� ������� 5�ȼ��� �̵�
            else
                target.setLocation(x, y);
            
            if(y<0)
        	{
            	b=5;
            target.setLocation(x, 0);}
            else
                target.setLocation(x, y);
            
            
////////////////////////////////////////////////////////////////////////////////////
                //0.02�ʸ��� �̵�
                try{
                    sleep(d);
                }
                //�����尡 �װԵǸ� �ʱ� ��ġ�� ��ġ�ϰ�, 0.5�ʸ� ��ٸ���.
                catch(Exception e){
                    target.setLocation(5, y);
                    num.point+=5;
                    timelimit-=10;
                	num.count++;
                    pointshow.setText("����:"+num.point);
                    try{
                        sleep(500);
                    }
                    catch(Exception e2){}
                }
            }
        }
        }
        
    }
    
    class TargetThread3 extends Thread{
        JLabel target;
        int d=20;
       
        int xx;
        int yy;
        TargetThread3(JLabel target){
        	int xx = (int) Math.floor(Math.random() * 800);
        	int yy = (int) Math.floor(Math.random() * 600);
            this.target=target;
            if(num.count==6) {target.setLocation(xx, yy);}
        }
        public void run(){
        	int a=50;
        	int b=50;
        	int d=50;
            while(true){
            	/*	
            	    if(c.count==6)
            	  	{
            	  	a = (int) Math.floor(Math.random() * 9);
            	  	}
            	*/
            	/*if(num.count>=4)
            	{
            		if(num.count>=6)
            		{d=5;}
            		else
            		{d=10;}
            	}*/
                int x=target.getX()+a;//5�ȼ��� �̵�
                int y=target.getY();
                if(num.count>=2)
                {y=target.getY()+b;}                
                //������ ������ �������
                if(x>GamePanel.this.getWidth())
                	{
                	a=-a;
                    target.setLocation(GamePanel.this.getWidth(), y);
                    }
                //������ �ȿ� ������� 5�ȼ��� �̵�
                else
                    target.setLocation(x, y);
                
                if(x<0)
            	{
                	a=a;
                target.setLocation(5, y);}
                else
                    target.setLocation(x, y);
//////////////////////////////////////////////////////////////////////////////////////
                
                if(y>600)
            	{
            	b=-b;
                target.setLocation(x, 600);
                }
            //������ �ȿ� ������� 5�ȼ��� �̵�
            else
                target.setLocation(x, y);
            
            if(y<0)
        	{
            	b=b;
            target.setLocation(x, 0);}
            else
                target.setLocation(x, y);
            
            
////////////////////////////////////////////////////////////////////////////////////
                //0.02�ʸ��� �̵�
                try{
                    sleep(d);
                }
                //�����尡 �װԵǸ� �ʱ� ��ġ�� ��ġ�ϰ�, 0.5�ʸ� ��ٸ���.
                catch(Exception e){
                    target.setLocation(5, y);
                    num.point+=100;
                	num.count++;
                    pointshow.setText("����:"+num.point);
                    try{
                        sleep(500);
                    }
                    catch(Exception e2){}
                }
            }
        }
    }
    
    class TargetThread4 extends Thread{
        JLabel target;
        int d=20;
       
        int xx;
        int yy;
        TargetThread4(JLabel target){
        	int xx = (int) Math.floor(Math.random() * 800);
        	int yy = (int) Math.floor(Math.random() * 600);
            this.target=target;
            if(num.count==4) {target.setLocation(xx, yy);}
        }
        public void run(){
        	int a=5;
        	int b=5;
        	int d=20;
            while(true){
            	/*	
            	    if(c.count==6)
            	  	{
            	  	a = (int) Math.floor(Math.random() * 9);
            	  	}
            	*/
            	/*if(num.count>=4)
            	{
            		if(num.count>=6)
            		{d=5;}
            		else
            		{d=10;}
            	}*/
                int x=target.getX()+a;//5�ȼ��� �̵�
                int y=target.getY();
                if(num.count>=2)
                {y=target.getY()+b;}                
                //������ ������ �������
                if(x>GamePanel.this.getWidth())
                	{
                	a=-5;
                    target.setLocation(GamePanel.this.getWidth(), y);
                    }
                //������ �ȿ� ������� 5�ȼ��� �̵�
                else
                    target.setLocation(x, y);
                
                if(x<0)
            	{
                	a=5;
                target.setLocation(5, y);}
                else
                    target.setLocation(x, y);
//////////////////////////////////////////////////////////////////////////////////////
                
                if(y>600)
            	{
            	b=-5;
                target.setLocation(x, 600);
                }
            //������ �ȿ� ������� 5�ȼ��� �̵�
            else
                target.setLocation(x, y);
            
            if(y<0)
        	{
            	b=5;
            target.setLocation(x, 0);}
            else
                target.setLocation(x, y);
            
            
////////////////////////////////////////////////////////////////////////////////////
                //0.02�ʸ��� �̵�
                try{
                    sleep(d);
                }
                //�����尡 �װԵǸ� �ʱ� ��ġ�� ��ġ�ϰ�, 0.5�ʸ� ��ٸ���.
                catch(Exception e){
                    target.setLocation(5, y);
                    num.point-=10;
                    timelimit=0;
                	num.count++;
                    pointshow.setText("����:"+num.point);
                    try{
                        sleep(500);
                    }
                    catch(Exception e2){}
                }
            }
        }
    }

    public class BulletThread extends Thread {
        JLabel bullet;
        JLabel target2 = new JLabel();
        JLabel target3 = new JLabel();
        JLabel target4 = new JLabel();
        JLabel[] target= new JLabel[num.enemy];
        Thread[] targetThread= new Thread[num.enemy];
        Thread targetThread2 =new Thread();
        Thread targetThread3 =new Thread();
        Thread targetThread4 =new Thread();
        int attack=0;
        
        public BulletThread(JLabel bullet, JLabel[] target, JLabel target2,JLabel target3,JLabel target4,Thread[] targetThread ,Thread targetThread2, Thread targetThread3, Thread targetThread4){
        	int a=0;
            this.bullet=bullet;
            
            for(int i=0;i<num.enemy;i++)
            {
            this.target[i]=target[i];
            this.targetThread[i]=targetThread[i];
            }
            this.targetThread2=targetThread2;
            this.targetThread3=targetThread3;
            this.targetThread4=targetThread4;
            this.target2=target2;
            this.target3=target3;
            this.target4=target4;
        }
    
        public void startGame2(){
        	if(num.count==2)
            {targetThread2.start();}
        	
        	if(num.count==6)
            {targetThread3.start();}
        	
        	if(num.count==4)
            {targetThread4.start();}
        }
        public void run(){
        	fired = true;
            while(true){
                if(hit()){//Ÿ���� �¾Ҵٸ�
                	if(attack<num.enemy)
                	{
                    targetThread[attack].interrupt();//Ÿ�� �����带 ���δ�.
                	}
                	if(attack==num.enemy)
                	{
                	targetThread2.interrupt();
                	}
                	if(attack==num.enemy+1)
                	{
                	targetThread3.interrupt();
                	}
                	if(attack==num.enemy+2)
                	{
                	targetThread4.interrupt();
                	}
                    fired = false;
                   
                    //�Ѿ��� ���� ��ġ�� �̵�
                    startGame2();
                
                    //ioField.setText("����:"+num.point);
                    bullet.setLocation(base.getX()+15,base.getY()-10);
                  
                    return;//�Ѿ� �����嵵 ���δ�.
                }
                else{
                    int x=bullet.getX();
                    int y=bullet.getY()-5;//5�ȼ��� ���� �̵��Ѵ�.=�Ѿ� �ӵ��� 5�ȼ�
                    //�Ѿ��� ������ ������ ������ ��
                    if(y<0){
                        //�Ѿ� ���� ��ġ�� �̵�
                    	fired = false;
                    	bullet.setLocation(base.getX()+15,base.getY()-10);
                        return;//�Ѿ� �����带 ���δ�.
                    }
                    //�Ѿ��� ������ �ȿ� ������ 5�ȼ��� �̵��Ѵ�.
                    else
                    	bullet.setLocation(x,y);
                }
                //0.02�ʸ��� 5�ȼ��� �̵�
                try{
                    sleep(20);
                }
                
                catch(Exception e){}
            }
        }
        
        private boolean hit()
        {
            int x=bullet.getX();
            int y=bullet.getY();
            int w=bullet.getWidth();
            int h=bullet.getHeight();
            
            if(targetContains(x,y)
                    ||targetContains(x+w-1,y)
                    ||targetContains(x+w-1,y+h-1)
                    ||targetContains(x,y+h-1))
                return true;
            else
                return false;
        }
        
        
        private boolean targetContains(int x, int y){
            //Ÿ���� x��ǥ�� �Ѿ� x��ǥ���� �۰ų� ������ �Ѿ� x��ǥ���� Ÿ�� x��ǥ + Ÿ���� ���� ���̰� ũ��
            if(((target2.getX()<=x)&&(x<target2.getX()+target2.getWidth()))   
                    //Ÿ���� y��ǥ�� �Ѿ� y��ǥ���� �۰ų� ������ �Ѿ� y��ǥ���� Ÿ�� y��ǥ + Ÿ���� ���� ���̰� ũ��
                    &&((target2.getY()<=y)&&(y<target2.getY()+target2.getHeight())))
            {
            	
            	attack=num.enemy;
            	return true;
            	
            }
            	if(((target3.getX()<=x)&&(x<target3.getX()+target3.getWidth()))   
                        //Ÿ���� y��ǥ�� �Ѿ� y��ǥ���� �۰ų� ������ �Ѿ� y��ǥ���� Ÿ�� y��ǥ + Ÿ���� ���� ���̰� ũ��
                        &&((target3.getY()<=y)&&(y<target3.getY()+target3.getHeight())))
            	{
                	
                	attack=num.enemy+1;
                	return true;
                	
                }
            		if(((target4.getX()<=x)&&(x<target4.getX()+target4.getWidth()))   
                            //Ÿ���� y��ǥ�� �Ѿ� y��ǥ���� �۰ų� ������ �Ѿ� y��ǥ���� Ÿ�� y��ǥ + Ÿ���� ���� ���̰� ũ��
                            &&((target4.getY()<=y)&&(y<target4.getY()+target4.getHeight())))
            		 {
                    	
                    	attack=num.enemy+2;
                    	return true;
                    	
                    }
        	for(int i=0;i<num.enemy;i++)
        	{
            if(((target[i].getX()<=x)&&(x<target[i].getX()+target[i].getWidth()))   
                    //Ÿ���� y��ǥ�� �Ѿ� y��ǥ���� �۰ų� ������ �Ѿ� y��ǥ���� Ÿ�� y��ǥ + Ÿ���� ���� ���̰� ũ��
                    &&((target[i].getY()<=y)&&(y<target[i].getY()+target[i].getHeight())))
            {
            	
            
            	attack=i;
            	return true;
            	
            }
        	}
            
         
                return false;
        	
        	}
    
    }
    
    ///////////////////////////////////////
    public class BaseThread extends Thread // Base Thread Inner Class
	{
		JLabel base;
		JLabel bullet;
		public BaseThread(JLabel base, JLabel bullet)
		{
			this.base = base;
			this.bullet = bullet;
		}
		public void run()
		{
			int x, y;
			if(direction)
			{
				x = base.getX()+20;
				y = base.getY();
			}
			else
			{
				x = base.getX()-20;
				y = base.getY();
			}
			if(0<=x && x <=760)
				base.setLocation(x, y);
			
			if(!fired)
				bullet.setLocation(base.getX()+15,base.getY()-10);
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		
		if(e.getKeyChar() == KeyEvent.VK_SPACE)
		{
			if(bulletThread == null || !bulletThread.isAlive())
			{
				bulletThread = new BulletThread(bullet,target,target2,target3,target4,targetThread,targetThread2,targetThread3,targetThread4);
				bulletThread.start();
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			direction = false;
			baseThread = new BaseThread(base,bullet);
			baseThread.start();
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			direction = true;
			baseThread = new BaseThread(base,bullet);
			baseThread.start();
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {}
	@Override
	public void keyTyped(KeyEvent e) {}
	

	/////////////////////
}
    



        

public class gametest extends number{
    public static void main(String[] args) {
    	
    
    	
    	
        new Ex5();
    }
}
            