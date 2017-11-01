package ie.gmit.os;

public class process implements Comparable<process> {
	
	int burstTime;
	private int waitTime;
	String name;
	
	public process(int burstTime, String name)
	{
		this.burstTime = burstTime;
		this.name = name;
		
	}
	
	public void display()
	{
		System.out.println("Process name: " + name + "\nBurst Time:" + burstTime + "\n");
	}
	
	public int getBurstTime()
	{
		return burstTime;
	}
	
	
	@Override
	public int compareTo(process arg0) {
		// TODO Auto-generated method stub
		
		int result_compare = arg0.burstTime;
		
		//ascending order
		return this.burstTime - result_compare;

		
	}

	public void setWaitTime(int waitTime) {
		// TODO Auto-generated method stub
		this.waitTime = waitTime;
		
	}
	
	public int getWaitTime() {
		return waitTime;
	}


}