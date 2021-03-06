package mutual_exclusion;


public class Peterson {
	
	final static int COUNT = 1;
	public static int flag1;
	public static int flag2;
	public static int turn;
	public static int x;
	
	public static void main(String[] args) {
		
		flag1 = 0;
		flag2 = 0;
		turn = 0;
		x = 0;
		
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				int count = 0;
				flag1 = 1;
				turn = 2;
				while(flag2 == 1 && turn ==2){
					if(count++ > COUNT)break;
				}
				
				//cs
				x = 1;
				assert x == 1 : "Bug found";
				flag1 = 0;
				
			}
		});
		
		Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				int count = 0;
				flag2 = 1;
				turn = 1;
				while(flag1 == 1 && turn ==1){
					if(count++ > COUNT)break;
				}
				
				//cs
				x = 2;
				assert x == 2 : "Bug found";
				flag2 = 0;
			}
		});
		
		t1.start();
		t2.start();
		try {
			t1.join();
			t2.join();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
