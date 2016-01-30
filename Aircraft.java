package prototype104;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;

public class Aircraft {

	JFrame frame;
	public static int N;	//number of columns
	public JButton[][] b = new JButton[100][100];	
	public boolean[][] pass = new boolean[100][100];
	private Person pers[];// = new Person[180];
	private int noofPass;
	private int noofgrps;

	/**
	 * Create the application.
	 * @throws InterruptedException 
	 */
	public Aircraft(Inputs f) {
		int i = f.getAirplaneType();
		String value = f.getNoofPassengers();
		this.noofPass = Integer.parseInt(value);
		value = f.getNumberOfGroups();
		this.noofgrps=Integer.parseInt(value);
		//int disasterScale = f.getDegreeofDisaster();
		//System.out.println(i);
		//System.out.println(noofPass);
		if(i == 1){
			N = 5;
		}
		else if(i == 0)
			N = 7;
		//System.out.println(N);

		initialize(N);
		this.pers = new Person[noofPass];
		this.seatPassengers(noofPass);
		//TimeUnit.SECONDS.sleep(2);
		//long startTime = System.currentTimeMillis();
		MoveToExit mte = new MoveToExit(this,pers);		
		System.out.println("hi kshitij");
		//System.out.println("sie of data"+mte.sizeOfData());
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(int n) {
		frame = new JFrame("Copyright © DA-IICT");
		N=n;
		frame.setLayout(new GridLayout(33,N));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		int k = 0;

		int q=0;
		String number;
		int num = 1;

		for (int i = 0; i < 33; i++){
			for (int j = 0; j < N; j++){
				this.pass[i][j] = false;
			}
		}
		for (int i = 0; i < 33; i++){
			for (int j = 0; j < N; j++)
			{
				number = Integer.toString(num);



				if(j!=N/2 && i!=0 & i!=16 && i!=32){
					this.b[i][j] = new JButton();
					++num;
					frame.add(b[i][j]);
					this.b[i][j].setBackground(Color.ORANGE);
					this.b[i][j].setText("0");
					 
				}
				else if(i == 0 || i == 16 ||  i == 32){
					this.b[i][j] = new JButton();
					frame.add(b[i][j]);
					this.b[i][j].setBackground(Color.ORANGE);
					//this.b[i][j].setBorder(BorderFactory.createEmptyBorder(10,10,0,0));
					this.b[i][j].setText("0");
				}
				else{
					this.b[i][j] = new JButton();
					frame.add(b[i][j]);
					this.b[i][j].setBackground(Color.ORANGE);
					this.b[i][j].setText("0");
					//this.b[i][j].setBorder(BorderFactory.createEmptyBorder(10,10,0,0));
					//this.b[i][j].setMargin(new Insets(10,10,0,0));
					//this.b[i][j].setPreferredSize(new Dimension(25, 25));
				}
				this.b[i][j].setEnabled(false);
			}
		}
		this.b[0][0].setBackground(Color.green);
		this.b[0][0].setText("EXIT");
		this.b[0][N-1].setBackground(Color.green);
		this.b[0][N-1].setText("EXIT");
		this.b[32][0].setBackground(Color.green);
		this.b[32][0].setText("EXIT");
		this.b[32][N-1].setBackground(Color.green);
		this.b[32][N-1].setText("EXIT");
		this.b[16][0].setBackground(Color.green);
		this.b[16][0].setText("EXIT");
		this.b[16][N-1].setBackground(Color.green);
		this.b[16][N-1].setText("EXIT");
		frame.setBounds(100, 100, 500, 750);

	}

	/*public void addPerson(person pers){
		b[pers.getx()][pers.gety()].setBackground(Color.BLUE);
	}*/

	public void sitPerson(int i,int j, Person p){
		p.setx(i);
		p.sety(j);
	}
	public void seatPassengers(int m){
		//for(int i=0; i<m;i++)
		//this.pers[i] = new Person();
		int temp = m;
		int q = 0;
		int k=1;
		String num;
		int temp1 = this.noofgrps;
		int z=0;
		if(m>0){
			do{
				for(int i = 1;i<32;++i){
					if(i == 16){
						++i;
					}
					for(int j = 0;j< this.N;++j){
						if(j==this.N/2){
							++j;
						}
						Random rand = new Random();
						float p = rand.nextInt(100-1)+1;
						if(p>95 && temp>0 && this.b[i][j].getBackground()== Color.ORANGE){
							int sex = rand.nextInt(2);
							//System.out.println(sex+","+ (z+1));

							if(sex==1)
							{
								//System.out.println(randomIndex+","+ (z+1)+ "M"+"("+i+","+j+")");
								this.b[i][j].setBackground(Color.BLUE);
								this.pers[z] = new Person(i,j,this);
								this.pers[z].setPersontype('M');
								int time = rand.nextInt((1750 - 875) + 1) + 875;
								this.pers[z].setMtime(time);
								time = rand.nextInt((1000 - 500) + 1) + 500;
								this.pers[z].setRtime(time);
								--temp;
							}
							else if(sex==0){
								//System.out.println(randomIndex+","+ (z+1)+"F"+"("+i+","+j+")");
								this.b[i][j].setBackground(Color.blue);
								this.pers[z] = new Person(i,j,this);
								this.pers[z].setPersontype('F');
								int time = rand.nextInt((1950 - 921) + 1) + 921;
								this.pers[z].setMtime(time);
								time = rand.nextInt((1100 - 600) + 1) + 600;
								this.pers[z].setRtime(time);
								--temp;
							}
							else{
								//System.out.println(randomIndex+","+ (z+1)+"M1"+"("+i+","+j+")");
								this.b[i][j].setBackground(Color.BLUE);
								this.pers[z] = new Person(i,j,this);
								this.pers[z].setPersontype('M');
								int time = rand.nextInt((1750 - 875) + 1) + 875;
								this.pers[z].setMtime(time);
								time = rand.nextInt((1000 - 500) + 1) + 500;
								this.pers[z].setRtime(time);
								--temp;
							}
							this.b[i][j].setText("1");
							//System.out.println("z="+z);
							this.pers[z].setIndex(z+1);
							//num = Integer.toString(z+1);
							//this.b[i][j].setText("("+i+","+j+")");
							this.pass[i][j] = true;
							//System.out.println("temp"+temp);

							++q;
							++z;
						}
					}
				}				
			}while(temp>0);
		}
	}
	public int getNoofPass(){
		return this.noofPass;
	}


}
