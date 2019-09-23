package bankAccount;

public class WithdrawlRunnable extends Thread implements Runnable {

	Account acc;
	double amount;
	int loop;
	
	WithdrawlRunnable(Account acc, double amount,int loop)
	{
		this.acc = acc;
		this.amount = amount;
		this.loop = loop;
	}
	
	public synchronized void run() {

		/* insert code here */
		for(int i = 0; i<loop;i++) 
		{
			if(acc.getBalance( )!=0) {
			System.out.println("Withdrawing:" + amount);
			acc.setBalance(acc.getBalance( ) - amount);
			System.out.println("New Balance:" + acc.getBalance( ));
			}
			else 
			{
				try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}
	
	
}
