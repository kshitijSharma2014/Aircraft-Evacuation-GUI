package prototype104;


import java.awt.Color;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

public class MoveToExit {
	ThreadPoolExecutor executor;
	Monitor frame_2;
	Monitor frame_3;
	Monitor frame_4;
	//Monitor frame_5;
	static int m =0;
	File file = new File ("data.txt");
	private List<String> data;
	public MoveToExit(Aircraft a, Person pers[]){

		frame_2 = new Monitor();
		frame_2.frame2.setTitle("Time data Copyright © DA-IICT");
		frame_3 = new Monitor();
		frame_2.frame2.setVisible(true);
		frame_3.frame2.setVisible(true);
		frame_3.frame2.setTitle("Distance Data Copyright © DA-IICT");
		frame_4 = new Monitor();
		frame_4.frame2.setTitle("ID data data Copyright © DA-IICT");
		frame_4.frame2.setVisible(true);
		/*frame_5 = new Monitor();
		frame_5.frame2.setTitle("only time data data Copyright © DA-IICT");
		frame_5.frame2.setVisible(true);*/

		int noofPass = a.getNoofPass();
		int  corePoolSize = noofPass;
		int  maxPoolSize = noofPass;
		long keepAliveTime = 90;

		// Create the ThreadPoolExecutor
		this.executor = new ThreadPoolExecutor(corePoolSize, maxPoolSize, keepAliveTime,
				TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
		this.executor.allowCoreThreadTimeOut(true);
		/*		for(int i=0;i<32;++i){
			for(int j=0;j<a.N;++j){
				System.out.print(a.b[i][j]+ "\t");
			}
			System.out.println("");
		}*/
		// Starting the monitor thread as a daemon
		Thread monitor = new Thread(new MyMonitorThread(executor));
		monitor.setDaemon(true);
		monitor.start();

		//  BuffeblueWriter out = null;

		// Adding the tasks
		for(int i= 0;i<noofPass;++i){
			//System.out.println("i = "+ i);
			this.executor.execute(new MyWork(a,pers[i],this.executor));

		}
	}
	public class MyWork implements Runnable
	{
		private Aircraft aero;
		private Person per;
		ThreadPoolExecutor executor;

		public MyWork(Aircraft a, Person pers,ThreadPoolExecutor executor)
		{
			this.aero = a;
			this.per = pers;
			this.executor = executor;
		}

		public void move(int x,int y, Aircraft a, Person p){
			if(p.getPersontype() == 'M'){
				try {
					if(y!=a.N/2 && x!=0 && x!=16 && x!=32){
						while(a.b[x][y].getBackground()==Color.ORANGE){
							p.timeSteps+=178;
							//System.out.println(p.timeSteps + " "+ "hy");
							TimeUnit.MILLISECONDS.sleep(178);
							p.totaltime += p.timeSteps;

							if(p.timeSteps>=2.58*178 && (a.b[x][y].getBackground()==Color.ORANGE)){
								a.b[p.getx()][p.gety()].setBackground(Color.ORANGE);
								a.b[p.getx()][p.gety()].setText("0");
								//System.out.println(p.timeSteps + " "+ "hy");
								a.b[x][y].setBackground(Color.blue);
								p.setx(x);
								p.sety(y);
								a.b[x][y].setText("1");
								//p.timeSteps -=5.75*178;
								p.timeSteps =0;
								p.totalDist += 19;
							}
						}
					}
					else if(y==a.N/2 && x!=0 && x!=16 && x!=32){

						while(a.b[x][y].getBackground()==Color.ORANGE || a.b[x][y].getText()=="1" || a.b[x][y].getText() == "0"){
							p.timeSteps+=178;
							//System.out.println(p.timeSteps + " "+ "hy");
							TimeUnit.MILLISECONDS.sleep(178);
							p.totaltime += p.timeSteps;

							if(p.timeSteps>=3.17*178 && (a.b[x][y].getBackground()==Color.ORANGE || a.b[x][y].getText()=="1" || a.b[x][y].getText() == "0")){
								if(a.b[p.getx()][p.gety()].getText()=="2"){
									//a.b[p.getx()][p.gety()].setBackground(Color.ORANGE);
									a.b[p.getx()][p.gety()].setText("1");
								}
								else if(a.b[p.getx()][p.gety()].getText()=="1"){
									a.b[p.getx()][p.gety()].setBackground(Color.ORANGE);
									a.b[p.getx()][p.gety()].setText("0");
								}
								//System.out.println(p.timeSteps + " "+ "hy");
								a.b[x][y].setBackground(Color.blue);
								p.setx(x);
								p.sety(y);
								if(a.b[x][y].getText()=="1"){
									a.b[x][y].setText("2");
								}
								else{
									a.b[x][y].setText("1");
									//a.b[x][y].setBackground(Color.blue);
								}
								p.timeSteps = 0;
								//p.timeSteps -=3.75*178;
								p.totalDist += 30;

							}
						}
					}
					if((x==0 || x==16 || x==32) && y!=0){

						while(a.b[x][y].getBackground()==Color.ORANGE || a.b[x][y].getText() == "0" || a.b[x][y].getText()=="1"){
							p.timeSteps+=178;
							//System.out.println(p.timeSteps + " "+ "hy");
							TimeUnit.MILLISECONDS.sleep(178);
							p.totaltime += p.timeSteps;

							if(p.timeSteps>=2*178 && (a.b[x][y].getBackground()==Color.ORANGE || a.b[x][y].getText() == "0" || a.b[x][y].getText()=="1")){
								if(a.b[p.getx()][p.gety()].getText()=="2"){
									//a.b[p.getx()][p.gety()].setBackground(Color.ORANGE);
									a.b[p.getx()][p.gety()].setText("1");
								}
								else if(a.b[p.getx()][p.gety()].getText()=="1"){
									a.b[p.getx()][p.gety()].setBackground(Color.ORANGE);
									a.b[p.getx()][p.gety()].setText("0");
								}
								//System.out.println(p.timeSteps + " "+ "hy");


								p.setx(x);
								p.sety(y);
								//p.timeSteps -=2*178;
								if(a.b[x][y].getText()=="1"){
									a.b[x][y].setText("2");
								}
								else{
									a.b[x][y].setText("1");
									a.b[x][y].setBackground(Color.blue);
								}
								p.timeSteps = 0;
								p.totalDist += 19;

							}
						}
					}

				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			else if(p.getPersontype() == 'F'){

				try {					
					if(y!=a.N/2 && x!=0 && x!=16 && x!=32){
						while(a.b[x][y].getBackground()==Color.ORANGE){
							p.timeSteps+=178;
							//System.out.println(p.timeSteps + " "+ "hy");
							TimeUnit.MILLISECONDS.sleep(178);
							p.totaltime += p.timeSteps;

							if(p.timeSteps>=2.76*178 && a.b[x][y].getBackground()==Color.ORANGE){
								a.b[p.getx()][p.gety()].setBackground(Color.ORANGE);
								a.b[p.getx()][p.gety()].setText("0");
								//System.out.println(p.timeSteps + " "+ "hy");
								a.b[x][y].setBackground(Color.blue);
								p.setx(x);
								p.sety(y);
								a.b[x][y].setText("1");
								p.timeSteps = 0;
								//p.timeSteps -=6*178;
								p.totalDist += 19;

							}
						}
					}
					else if(y==a.N/2 && x!=0 && x!=16 && x!=32){
						//mtime = rand.nextInt((800 - 700) + 1) + 700;
						/*p.timeSteps+=178;
						TimeUnit.MILLISECONDS.sleep(178);
						p.totaltime += p.timeSteps;*/
						while(a.b[x][y].getBackground()==Color.ORANGE || a.b[x][y].getText()=="1" || a.b[x][y].getText() == "0"){
							p.timeSteps+=178;
							//System.out.println(p.timeSteps + " "+ "hy");
							TimeUnit.MILLISECONDS.sleep(178);
							p.totaltime += p.timeSteps;

							if(p.timeSteps>=3.4*178 && (a.b[x][y].getBackground()==Color.ORANGE || a.b[x][y].getText()=="1" || a.b[x][y].getText() == "0")){
								a.b[p.getx()][p.gety()].setBackground(Color.ORANGE);
								a.b[p.getx()][p.gety()].setText("0");
								//System.out.println(p.timeSteps + " "+ "hy");
								a.b[x][y].setBackground(Color.blue);
								p.setx(x);
								p.sety(y);
								if(a.b[x][y].getText()=="1"){
									a.b[x][y].setText("2");
								}
								else{
									a.b[x][y].setText("1");
									a.b[x][y].setBackground(Color.blue);
								}
								p.timeSteps = 0;
								//p.timeSteps -=4*178;
								p.totalDist += 30;

							}
						}
					}
					if(x==0 || x==16 || x==32){

						while(a.b[x][y].getBackground()==Color.ORANGE || a.b[x][y].getText()=="1" || a.b[x][y].getText() == "0"){
							p.timeSteps+=178;
							//System.out.println(p.timeSteps + " "+ "hy");
							TimeUnit.MILLISECONDS.sleep(178);
							p.totaltime += p.timeSteps;

							if(p.timeSteps>=2.15*178 && (a.b[x][y].getBackground()==Color.ORANGE || a.b[x][y].getText()=="1" || a.b[x][y].getText() == "0")){
								if(a.b[p.getx()][p.gety()].getText()=="2"){
									//a.b[p.getx()][p.gety()].setBackground(Color.ORANGE);
									a.b[p.getx()][p.gety()].setText("1");
								}
								else if(a.b[p.getx()][p.gety()].getText()=="1"){
									a.b[p.getx()][p.gety()].setBackground(Color.ORANGE);
									a.b[p.getx()][p.gety()].setText("0");
								}
								//System.out.println(p.timeSteps + " "+ "hy");
								a.b[x][y].setBackground(Color.blue);
								p.setx(x);
								p.sety(y);
								if(a.b[x][y].getText()=="1"){
									a.b[x][y].setText("2");
								}
								else{
									a.b[x][y].setText("1");
									a.b[x][y].setBackground(Color.blue);
								}
								//System.out.println(p.timeSteps+"timestep");
								p.timeSteps = 0;
								//System.out.println(p.timeSteps+"timestep");
								//p.timeSteps -=2.15*178;
								p.totalDist += 19;

							}
						}
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		public double distance(int x1, int y1, int x2, int y2){
			return Math.sqrt(Math.pow((x1-x2),2)-Math.pow((y1-y2),2));

		}
		public float[] Run(Aircraft a, Person pers)
		{
			int exitnum[] = new int[2];
			int o = pers.getx();
			int f = pers.gety();
			Random rand = new Random();
			/*try {
				TimeUnit.MILLISECONDS.sleep(5000);
				//System.out.println("enteblue in rtime "+pers.getRtine());
				pers.totaltime += 5000;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			pers.totaltime += 5000;
			/*			System.out.println("pers.getWtine() = "+pers.getWtine());
			System.out.println("pers.getMtine() = "+pers.getMtine());
			System.out.println("pers.getRtine() = "+pers.getRtine());*/
			do{
				if(pers.gety()!=a.N/2 && pers.getx()!=0 && pers.getx()!=16 && pers.getx()!=32){
					//System.out.println("first if condition");
					if(pers.gety()<a.N/2){
						//System.out.println("pers.getx()<a.N/2");
						while(a.b[pers.getx()][pers.gety()+1].getBackground() == Color.BLUE || a.b[pers.getx()][pers.gety()+1].getBackground() == Color.blue){
							try {
								TimeUnit.MILLISECONDS.sleep((long)pers.getWtine());
								pers.totaltime += pers.getWtine();
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						//if(a.b[pers.getx()][pers.gety()+1].getBackground()== Color.orange){
						//System.out.println("enter 1");

						/*a.b[pers.getx()][pers.gety()].setBackground(Color.ORANGE);
							a.b[pers.getx()][pers.gety()].setText("0");*/
						this.move(pers.getx(), pers.gety()+1, a,pers);
						int rtime = rand.nextInt((700 - 400) + 1) + 400;
						try {
							TimeUnit.MILLISECONDS.sleep(rtime);
							pers.totaltime += rtime;
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						//a.b[pers.getx()][pers.gety()+1].setText(a.b[pers.getx()][pers.gety()].getText());
						//}
					}
					if(pers.gety()>a.N/2){
						//System.out.println("pers.getx()>a.N/2");
						while(a.b[pers.getx()][pers.gety()-1].getBackground() == Color.BLUE || a.b[pers.getx()][pers.gety()-1].getBackground() == Color.blue){
							try {
								TimeUnit.MILLISECONDS.sleep((long)pers.getWtine());
								pers.totaltime += pers.getWtine();
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						/*						if(a.b[pers.getx()][pers.gety()-1].getBackground()== Color.ORANGE){
							//System.out.println("enter 2");
						 */							

						/*a.b[pers.getx()][pers.gety()].setBackground(Color.ORANGE);
							a.b[pers.getx()][pers.gety()].setText("0");*/
						this.move(pers.getx(), pers.gety()-1, a,pers);
						int rtime = rand.nextInt((700 - 400) + 1) + 400;
						try {
							TimeUnit.MILLISECONDS.sleep(rtime);
							pers.totaltime += rtime;
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						//a.b[pers.getx()][pers.gety()-1].setText(a.b[pers.getx()][pers.gety()].getText());
						//	}
					}					
				}
				if(pers.gety()==a.N/2 && pers.getx()!=0){ //&& pers.getx()!=16 && pers.getx()!=32){
					int count1 = 0, count2 =0;
					/*	for(int i=0;i<5;++i){
						if((a.b[pers.getx()+1][pers.gety()].getBackground()==Color.BLUE || a.b[pers.getx()+1][pers.gety()].getBackground()==Color.blue) && pers.getx()+1<32){
							++count1;
						}
						if((a.b[pers.getx()-1][pers.gety()].getBackground()==Color.BLUE || a.b[pers.getx()-1][pers.gety()].getBackground()==Color.blue) && pers.getx()-1>0){
							++count2;
						}
					}
					if(count1<3 && count2>=3){
						//move downwards
						this.move(pers.getx()+1, pers.gety(), a,pers);
						int rtime = rand.nextInt((1000 - 500) + 1) + 500;
						try {
							TimeUnit.MILLISECONDS.sleep(rtime);
							pers.totaltime += rtime;
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					else if(count2<3 && count1>=3){
						//move upwards
						this.move(pers.getx()-1, pers.gety(), a,pers);
						int rtime = rand.nextInt((1000 - 500) + 1) + 500;
						try {
							TimeUnit.MILLISECONDS.sleep(rtime);
							pers.totaltime += rtime;
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}*/
					//else{
					if(pers.getx()<16){
						if(pers.getx()>=10){
							while(a.b[pers.getx()+1][pers.gety()].getText()=="2"){
								try {
									TimeUnit.MILLISECONDS.sleep((long)pers.getWtine());
									pers.totaltime += pers.getWtine();
									System.out.println("here 1");
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
							//move down
							/*a.b[pers.getx()][pers.gety()].setBackground(Color.ORANGE);
								a.b[pers.getx()][pers.gety()].setText("0");*/
							this.move(pers.getx()+1, pers.gety(), a, pers);
							int rtime = rand.nextInt((700 - 400) + 1) + 400;
							try {
								TimeUnit.MILLISECONDS.sleep(rtime);
								pers.totaltime += rtime;
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

							//a.b[pers.getx()+1][pers.gety()].setText(a.b[pers.getx()][pers.gety()].getText());
						}
						else if(pers.getx()<10){
							if(pers.getx()-1>=0){
								while(a.b[pers.getx()-1][pers.gety()].getText()=="2"){
									try {
										TimeUnit.MILLISECONDS.sleep((long)pers.getWtine());
										pers.totaltime += pers.getWtine();
										System.out.println("here 2");
									} catch (InterruptedException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
								//move up

								/*	a.b[pers.getx()][pers.gety()].setBackground(Color.ORANGE);
								a.b[pers.getx()][pers.gety()].setText("0");*/
								this.move(pers.getx()-1, pers.gety(), a, pers);
								int rtime = rand.nextInt((700 - 400) + 1) + 400;
								try {
									TimeUnit.MILLISECONDS.sleep(rtime);
									pers.totaltime += rtime;
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								//a.b[pers.getx()-1][pers.gety()].setText(a.b[pers.getx()][pers.gety()].getText());
							}
						}
					}
					else if(pers.getx()>16){
						if((pers.getx()-21)>0){
							if(pers.getx()+1<33){
								while(a.b[pers.getx()+1][pers.gety()].getText()=="2"){
									try {
										TimeUnit.MILLISECONDS.sleep((long)pers.getWtine());
										pers.totaltime += pers.getWtine();
										System.out.println("here 3");
									} catch (InterruptedException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
								//move down
								//System.out.println("move down");
								/*a.b[pers.getx()][pers.gety()].setBackground(Color.ORANGE);
								a.b[pers.getx()][pers.gety()].setText("0");*/
								this.move(pers.getx()+1, pers.gety(), a, pers);
								int rtime = rand.nextInt((700 - 400) + 1) + 400;
								try {
									TimeUnit.MILLISECONDS.sleep(rtime);
									pers.totaltime += rtime;
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
							//a.b[pers.getx()+1][pers.gety()].setText(a.b[pers.getx()][pers.gety()].getText());
							System.out.println("where 1");
						}
						else if((pers.getx()-21)<=0){
							while(a.b[pers.getx()-1][pers.gety()].getText()=="2"){
								try {
									TimeUnit.MILLISECONDS.sleep((long)pers.getWtine());
									pers.totaltime += pers.getWtine();
									System.out.println("here 4");
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
							//move up
							//System.out.println("move up");
							/*a.b[pers.getx()][pers.gety()].setBackground(Color.ORANGE);
								a.b[pers.getx()][pers.gety()].setText("0");*/
							this.move(pers.getx()-1, pers.gety(), a, pers);
							int rtime = rand.nextInt((700 - 400) + 1) + 400;
							try {
								TimeUnit.MILLISECONDS.sleep(rtime);
								pers.totaltime += rtime;
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

							//a.b[pers.getx()-1][pers.gety()].setText(a.b[pers.getx()][pers.gety()].getText());
						}
					}
				}
				//	}
				if(pers.getx()==0 || pers.getx()==16 || pers.getx()==32){
					//if((a.b[pers.getx()][pers.gety()-1].getBackground()==Color.BLUE && a.b[pers.getx()][pers.gety()+1].getBackground()==Color.ORANGE)){
					while(a.b[pers.getx()][pers.gety()-1].getText()=="2"){
						try {
							TimeUnit.MILLISECONDS.sleep((long)pers.getWtine());
							pers.totaltime += pers.getWtine();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					//if(a.N==5){
					//System.out.println("move y-1");

					/*	a.b[pers.getx()][pers.gety()].setBackground(Color.ORANGE);
							a.b[pers.getx()][pers.gety()].setText("0");*/
					this.move(pers.getx(), pers.gety()-1, a, pers);
					int rtime = rand.nextInt((700 - 400) + 1) + 400;
					try {
						TimeUnit.MILLISECONDS.sleep(rtime);
						pers.totaltime += rtime;
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if((pers.getx()==0 || pers.getx()==16 || pers.getx()==32) && (a.b[pers.getx()][pers.gety()-1].getBackground()!= Color.GREEN) && (pers.gety()<a.N/2)){
						//System.out.println("all green");
						//a.b[pers.getx()][pers.gety()].setBackground(Color.ORANGE);
						//a.b[pers.getx()][pers.gety()].setText("0");
						if(a.b[pers.getx()][pers.gety()].getText()=="1"){
							a.b[pers.getx()][pers.gety()].setText("0");
							a.b[pers.getx()][pers.gety()].setBackground(Color.ORANGE);
						}
						else if(a.b[pers.getx()][pers.gety()].getText()=="2"){
							a.b[pers.getx()][pers.gety()].setText("1");
							//a.b[pers.getx()][pers.gety()].setBackground(Color.blue);
						}
						this.move(pers.getx(), pers.gety()-1, a, pers);
						rtime = rand.nextInt((700 - 400) + 1) + 400;
						try {
							TimeUnit.MILLISECONDS.sleep(rtime);
							pers.totaltime += rtime;
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					if((pers.getx()==0 || pers.getx()==16 || pers.getx()==32) && (a.b[pers.getx()][pers.gety()-1].getBackground() == Color.GREEN)){
						//System.out.println("all green");
						//a.b[pers.getx()][pers.gety()].setBackground(Color.ORANGE);
						//a.b[pers.getx()][pers.gety()].setText("0");
						if(a.b[pers.getx()][pers.gety()].getText()=="1"){
							a.b[pers.getx()][pers.gety()].setText("0");
							a.b[pers.getx()][pers.gety()].setBackground(Color.ORANGE);
						}
						else if(a.b[pers.getx()][pers.gety()].getText()=="2"){
							a.b[pers.getx()][pers.gety()].setText("1");
							//a.b[pers.getx()][pers.gety()].setBackground(Color.blue);
						}
						pers.setStatus(1);
						//System.out.println("status : "+pers.getStatus());
					}
				}
			}while(pers.getStatus()==0);
			float ar[] = new float[4];
			ar[0] = pers.totaltime;
			ar[1] = o;
			ar[2] =f;
			ar[3] = pers.totalDist;
			return ar;
		}

		@Override
		public void run() {
			float[] n = this.Run(this.aero,this.per);
			char t1 = Thread.currentThread().getName().charAt(Thread.currentThread().getName().length() - 1);
			char t2 = Thread.currentThread().getName().charAt(Thread.currentThread().getName().length() - 2);
			char t3 = Thread.currentThread().getName().charAt(Thread.currentThread().getName().length() - 3);
			int number = 0;
			if(t3=='-'){
				number = Integer.parseInt(Character.toString(t2))*10 + Integer.parseInt(Character.toString(t1));
			}
			else if(t2 == '-'){
				number = Integer.parseInt(Character.toString(t1));
			}
			else if(t2!='-' && t3!='-'){
				number = Integer.parseInt(Character.toString(t3))*100+Integer.parseInt(Character.toString(t2))*10 + Integer.parseInt(Character.toString(t1)); 
			}
			frame_2.addData(n[0]/1000+"\n");
			frame_3.addData((float)(n[3]*0.0254)+"\n");
			frame_4.addData(number+"\n");
			//frame_5.addData(n[0]/1000+"\n");

		}
	}
	public void addTodata(String t){
		this.data.add(t);
	}
	public List<String> getData(){
		return this.data;
	}
	public int sizeOfData(){
		return this.data.size();
	}

	/**
	 * My monitor thread. To monitor the status of {@link ThreadPoolExecutor}
	 * and its status.
	 */
	public class MyMonitorThread implements Runnable
	{
		ThreadPoolExecutor executor;

		public MyMonitorThread(ThreadPoolExecutor executor)
		{
			this.executor = executor;
		}

		@Override
		public void run()
		{
			/*try
			{
				do
				{
						System.out.println(
								String.format("[monitor] [%d/%d] Active: %d, Completed: %d, Task: %d, isShutdown: %s, isTerminated: %s, kept alive %s",
										this.executor.getPoolSize(),
										this.executor.getCorePoolSize(),
										this.executor.getActiveCount(),
										this.executor.getCompletedTaskCount(),
										this.executor.getTaskCount(),
										this.executor.isShutdown(),
										this.executor.isTerminated(),
										this.executor.getKeepAliveTime(TimeUnit.SECONDS)));
					Thread.sleep(30);
				}
				while (true);
			}
			catch (Exception e)
			{
			}*/
		}
	}
}
