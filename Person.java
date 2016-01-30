package prototype104;

import java.util.Random;
import java.util.Timer;

public class Person{
	private int index;
	private int x;
	private int y;
	//private int AccessMatrix[][] = new int[100][100];
	private int status;
	private int N;
	private float Mtime; //movement time
	private float Wtime; //wait time
	private float Rtime; //reaction time
	private double fear;
	private double agility;
	private double diameter; // diameter occupied by passenger
	public float totaltime;
	public float totalDist;
	public double speed;
	private char Persontype;
	private int grpstatus;
	public double timeSteps;
	public Person(int a,int b, Aircraft A)
	{
		this.N = A.N;
		this.timeSteps = 0;
		/*boolean AccessMatrix[][] = new boolean[N][N];
		for(int i=0;i<N;i++){			//System.out.println("hii");
			for(int j=0;j<N;j++){
				System.out.print("("+i +","+j+")");		
				if(g.b[i][j].getBackground()==Color.RED)
					AccessMatrix[i][j]=false;
				else
					AccessMatrix[i][j]=true;
				System.out.print(AccessMatrix[i][j]);
			}
			System.out.println("\n");
		}*/
		this.fear = 0.4;
		this.agility = 0.4;
		this.status = 0;
		this.x = a;
		this.y = b;
		this.speed = 2;
		this.totalDist = 0;
		//System.out.println("hello");
		Random rand = new Random();
		/*float weight = rand.nextInt(100-30)+30;
		float age =  rand.nextInt(100-10)+10;*/
		//this.vMtime = 1250;
		//this.hMtime = 1250;
		//this.Mtime = 1166;
		//(float) (0.40*age + 0.60*weight);
		//this.Rtime = 500;
		this.Wtime = 1;
		this.totaltime = 0;
		grpstatus = 0;
	}
	public float getMtine(){
		return this.Mtime;
	}
	public float getWtine(){
		return this.Wtime;
	}
	public float getRtine(){
		return this.Rtime;
	}
	public int getStatus(){
		return this.status;
	}
	public void setStatus(int a){
		this.status=a;
	}
	public void setIndex(int z){
		this.index = z;
	}
	public int getx(){
		return this.x;
	}
	public int gety(){
		return this.y;
	}
	public char getPersontype(){
		return this.Persontype;
	}
	public void setPersontype(char ch){
		this.Persontype=ch;
	}
	public void setx(int a){
		this.x=a;
	}
	public void sety(int b){
		this.y=b;
	}	
	public void calcE(double fear, double agility){
		
	}
	public void setMtime(int time){
		this.Mtime = time;
	}
	public void setRtime(int time){
		this.Rtime = time;
	}
}
