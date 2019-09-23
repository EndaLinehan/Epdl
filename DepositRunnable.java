package bankAccount;

public class DepositRunnable extends Thread implements Runnable {
	Account acc;
	double amount;
	int loop;
	
	DepositRunnable(Account acc, double amount,int loop)
	{
		this.acc = acc;
		this.amount = amount;
		this.loop = loop;
	}
	
	
	public synchronized void run() {

		/* insert code here */
		for(int i = 0; i<loop;i++) 
		{
			
			System.out.println("Depositing:" + amount);
			acc.setBalance(acc.getBalance( ) + amount);
			System.out.println("New Balance:" + acc.getBalance());
			notifyAll();
		}
	}
	
	
}
