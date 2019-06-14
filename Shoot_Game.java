import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Shoot_Game extends JFrame implements KeyListener
{
	JPanel gamePanel = new JPanel();
	JLabel base = new JLabel();
	JLabel bullet = new JLabel();
	JLabel target = new JLabel();
	TargetThread targetThread;
	BaseThread baseThread;
	BulletThread bulletThread = null;
	boolean direction; // true : Right , false : Left
	boolean fired; // true : fired , false : not fired yet
	
	public Shoot_Game()
	{
		super("슈팅 게임");
		setSize(800,600);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		
		add(gamePanel);
		gamePanel.setLayout(null);
		
		target.setSize(30,30);
		target.setOpaque(true);
		target.setBackground(Color.yellow);
		target.setLocation(0, 0);
		
		base.setSize(40,40);
		base.setOpaque(true);
		base.setBackground(Color.BLACK);
		base.setLocation(380, 520);
		
		bullet.setSize(10,10);
		bullet.setOpaque(true);
		bullet.setBackground(Color.red);
		bullet.setLocation(base.getX()+15,base.getY()-10);
		
		gamePanel.add(target);
		gamePanel.add(base);
		gamePanel.add(bullet);
		
		targetThread = new TargetThread(target);
		targetThread.start();
		
		base.requestFocus();
		base.addKeyListener(this);
	}
	
	public class TargetThread extends Thread // Target Thread Inner Class
	{
		JLabel target;
		
		public TargetThread(JLabel target)
		{
			this.target = target;
			target.setLocation(0,0);
		}
		
		public void run()
		{
			while(true)
			{
				int x=target.getX()+5;//5픽셀씩 이동
                int y=target.getY();
                
                //프레임 밖으로 나갈경우
                if(x>800)
                    target.setLocation(0, 0);
                //프레임 안에 있을경우 5픽셀씩 이동
                else
                    target.setLocation(x, y);
                
                //0.02초마다 이동
                try{
                    sleep(20);
                }
                //스레드가 죽게되면 초기 위치에 위치하고, 0.5초를 기다린다.
                catch(Exception e){
                    target.setLocation(0, 0);
                    try{
                        sleep(500);
                    }
                    catch(Exception e2){}
                }
			}
		}
	}

	public class BulletThread extends Thread // Bullet Thread Inner Class
	{
		JLabel bullet, target;
		Thread targetThread;
		
		public BulletThread(JLabel bullet, JLabel target, Thread targetThread)
		{
			this.bullet = bullet;
			this.target = target;
			this.targetThread = targetThread;
		}
		
		public void run()
		{
			fired = true;
			while(true)
			{
				if(hit())
				{
					fired = false;
					targetThread.interrupt(); // Kill the targetThread
					bullet.setLocation(base.getX()+15,base.getY()-10);
					return;
				}
				else
				{
					int x = bullet.getX();
					int y = bullet.getY()-5;
					
					if(y<0)
					{
						fired = false;
						bullet.setLocation(base.getX()+15,base.getY()-10);
						return;
					}
					else
						bullet.setLocation(x,y);
				}
				
				try {
					sleep(20);
				}
				catch(Exception e) {}
			}
		}
		
		private boolean hit()
		{
			int x = bullet.getX();
			int y = bullet.getY();
			int w = bullet.getWidth();
			int h = bullet.getHeight();
			
			int x_ = target.getX();
			int y_ = target.getY();
			int w_ = target.getWidth();
			int h_ = target.getHeight();
			
			if((x_<=x) && (x<= x_+w_) && 
					(y_<=y) && (y<=y_+h_))
				return true;
			else 
				return false;
			
		}
	}
	
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
				x = base.getX()+5;
				y = base.getY();
			}
			else
			{
				x = base.getX()-5;
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
				bulletThread = new BulletThread(bullet,target,targetThread);
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
	
	public static void main(String[] args)
	{
		Shoot_Game frame = new Shoot_Game();
	}
}
