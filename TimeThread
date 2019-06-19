    class TimeThread extends Thread{
    		JLabel time;
    		int timelimit;
    		public TimeThread(){
    			timelimit=60;
    		}
    		public void run() {
    			while(true) {
    				
    				try 
    				{
    					time.setText(Integer.toString(timelimit));
    					sleep(1000);
    					timelimit--;
    				}
    				catch (Exception e)
    				{
    					e.printStackTrace();
    				}
    			}
    		}
    }
