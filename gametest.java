

import java.util.Scanner;
import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;



import java.net.*;

class number{
	  public  static int count=0;
	  public  static int enemy=0;}

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

class GamePanel extends JPanel {
	
	number num = new number();
	TargetThread[] targetThread = new TargetThread[num.enemy];
    TargetThread targetThread2;
    JLabel base = new JLabel();
    JLabel bullet = new JLabel();
    JLabel[] target = new JLabel[num.enemy];
    JLabel target2;
    
    //AudioClip sound;
    GamePanel()
    {
        this.setLayout(null);
        base.setSize(40,40);
        base.setOpaque(true);
        base.setBackground(Color.black);
        
        ImageIcon img = new ImageIcon("C:\\Users\\ekc14\\eclipse-workspace\\java project\\src\\javaapplication30\\�⺻����.png");
        ImageIcon img2 = new ImageIcon("C:\\Users\\ekc14\\eclipse-workspace\\java project\\src\\javaapplication30\\����������.png");
        for(int i=0;i<targetThread.length;i++)
        {
        	 target[i] = new JLabel(img);
             target[i].setSize(img.getIconWidth(),img.getIconHeight());
             this.add(target[i]);
        }
      
        
        target2 = new JLabel(img2);
        //�̹��� ũ�⸸ŭ ���̺� ũ�� ����
     
        target2.setSize(img2.getIconWidth(),img2.getIconHeight());
        bullet.setSize(10,10);
        bullet.setOpaque(true);
        bullet.setBackground(Color.red);
        this.add(base);
     
        this.add(target2);
        this.add(bullet);
        
        //URL url = Ex5.class.getResource("LASER.wav");
        //sound = Applet.newAudioClip(url);
    }
    
    public void startGame(){
        base.setLocation(this.getWidth()/2-20, this.getHeight()-40);
        bullet.setLocation(this.getWidth()/2-5, this.getHeight()-50);
        
        for(int i=0;i<targetThread.length;i++)
        {
        	target[i].setLocation(0, 0);
        }
       
        target2.setLocation(0, 0);
        //Ÿ���� �����̴� ������
        
        
        for(int i=0;i<targetThread.length;i++)
        {
        	targetThread[i] = new TargetThread(target[i]);
        	targetThread[i].start();
        }
        targetThread2 = new TargetThread(target2);
        targetThread2.start();
        
        //���̽��� ������ �ΰ� ����Ű �Է¿� ���� bullet������ ����
        base.requestFocus();
        base.addKeyListener(new KeyListener(){
            BulletThread bulletThread = null;
            @Override
            public void keyTyped(KeyEvent ke) {
            }

            @Override
            public void keyPressed(KeyEvent ke) {
                if(ke.getKeyChar()==KeyEvent.VK_ENTER){
                    //�����尡 �׾��ִ� �������� Ȯ��
                    if(bulletThread==null || !bulletThread.isAlive()){
                        //sound.play();
                        //�Ѿ˷� Ÿ���� ������� Ȯ���ϱ� ���� 2���� ���̺�� Ÿ�ٽ����带 �Ѱ��ش�.
                        bulletThread = new BulletThread(bullet,target,targetThread);
                        bulletThread.start();
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent ke) {}
            
        });
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
            	if(num.count>=2)
            	{
            		d =100;
            	}
                int x=target.getX()+a;//5�ȼ��� �̵�
                int y=target.getY();
                if(num.count>=4)
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
                    num.count++;
                    try{
                        sleep(500);
                    }
                    catch(Exception e2){}
                }
            }
        }
    }

    class BulletThread extends Thread {
        JLabel bullet;
        JLabel[] target;
        Thread[] targetThread;
        
        int attack=0;
        
        public BulletThread(JLabel bullet, JLabel[] target, Thread[] targetThread){
        	int a=0;
            this.bullet=bullet;
            
            for(int i=0;i<num.enemy;i++)
            {
            this.target[i]=target[i];
            this.targetThread[i]=targetThread[i];
            }
        }
    
        
        public void run(){
            while(true){
                if(hit()){//Ÿ���� �¾Ҵٸ�
                    targetThread[attack].interrupt();//Ÿ�� �����带 ���δ�.
                    //�Ѿ��� ���� ��ġ�� �̵�
                    bullet.setLocation(bullet.getParent().getWidth()/2-5, bullet.getParent().getHeight()-50);
                    return;//�Ѿ� �����嵵 ���δ�.
                }
                else{
                    int x=bullet.getX();
                    int y=bullet.getY()-5;//5�ȼ��� ���� �̵��Ѵ�.=�Ѿ� �ӵ��� 5�ȼ�
                    //�Ѿ��� ������ ������ ������ ��
                    if(y<0){
                        //�Ѿ� ���� ��ġ�� �̵�
                        bullet.setLocation(bullet.getParent().getWidth()/2-5, bullet.getParent().getHeight()-50);
                        return;//�Ѿ� �����带 ���δ�.
                    }
                    //�Ѿ��� ������ �ȿ� ������ 5�ȼ��� �̵��Ѵ�.
                    else
                        bullet.setLocation(x, y);
                }
                //0.02�ʸ��� 5�ȼ��� �̵�
                try{
                    sleep(20);
                }
                
                catch(Exception e){}
            }
        }
        
        private boolean hit(){
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
        	for(int i=0;i<num.enemy;i++)
        	{
            if(((target[i].getX()<=x)&&(x<target[i].getX()+target[i].getWidth()))   
                    //Ÿ���� y��ǥ�� �Ѿ� y��ǥ���� �۰ų� ������ �Ѿ� y��ǥ���� Ÿ�� y��ǥ + Ÿ���� ���� ���̰� ũ��
                    &&((target[i].getY()<=y)&&(y<target[i].getY()+target[i].getHeight())))
            {
            	int attack=i;
            	return true;
            	
            }
        	}
            
         
                return false;
        	
        	}
    
    }
}
    



        

        public class gametest extends number{
            public void main(String[] args) {
            	
            
            	
            	 Scanner scan = new Scanner(System.in); 
            	 int num = scan.nextInt(); 
            	 enemy = num;
                new Ex5();
            }
        }
            

  




