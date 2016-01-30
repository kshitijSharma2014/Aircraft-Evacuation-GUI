package prototype104;

import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import javax.swing.ScrollPaneConstants;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Path;
import java.util.Date;

public class Monitor {

	JFrame frame2;
	JTextArea textArea;
	private JButton btnPause;
	private JButton button_1;
	public static int tcount;
	public static int dcount;

	/**
	 * Create the application.
	 */
	public Monitor() {
		this.tcount = 0;
		this.dcount = 0;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
        // Create and set up the window.
	        frame2 = new JFrame();
	        // Display the window.
	        frame2.setSize(390, 500);
	        frame2.setVisible(true);
	        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame2.getContentPane().setLayout(null);
	        
	        JScrollPane scrollPane = new JScrollPane();
	        scrollPane.setBounds(10, 10, 350, 350);
	        frame2.getContentPane().add(scrollPane);
	        
	        this.textArea = new JTextArea();
	        textArea.setEditable(false);
	        scrollPane.setViewportView(this.textArea);
	        
	        JButton btnCopyDataTo = new JButton("Copy data to file");
	        btnCopyDataTo.setBounds(127, 391, 132, 23);
	        frame2.getContentPane().add(btnCopyDataTo);
	        btnCopyDataTo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//frame3 newFrame = new frame3();
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							try {
								//int count = 0;
								String panelTitle = frame2.getTitle();
								File f = null;
								if(panelTitle =="Distance Data Copyright © DA-IICT"){
									f = new File("distanceData");
									String tempFileName = f.getName();
									while(f.exists()) 
									{
										++dcount;
										System.out.println("file name"+f.getName());
									    tempFileName = f.getName()+dcount;
									    f = new File(tempFileName);
									    System.out.println("entered here in file exists");
									    //newFullPath = Path.Combine(path, tempFileName + extension);
									}
								}
								else if(panelTitle =="Time data Copyright © DA-IICT"){
									f = new File("timeData");
									String tempFileName = f.getName();
									while(f.exists()) 
									{
										++tcount;
										System.out.println("file name"+f.getName());
									    tempFileName = f.getName()+tcount;
									    f = new File(tempFileName);
									    System.out.println("entered here in file exists");
									    //newFullPath = Path.Combine(path, tempFileName + extension);
									}
								}
								else if(panelTitle =="ID data data Copyright © DA-IICT"){
									f = new File("idData");
									String tempFileName = f.getName();
									while(f.exists()) 
									{
										++tcount;
										System.out.println("file name"+f.getName());
									    tempFileName = f.getName()+tcount;
									    f = new File(tempFileName);
									    System.out.println("entered here in file exists");
									    //newFullPath = Path.Combine(path, tempFileName + extension);
									}
								}
								BufferedWriter fileOut = new BufferedWriter(new FileWriter(f));
								String text = textArea.getText();
								fileOut.write(text);
								//f.renameTo(tempFileName+".txt");
								fileOut.close();
								
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});
				}
			});
/*	        JButton button = new JButton("<<");
	        button.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent arg0) {
	        	}
	        });
	        button.setBounds(80, 342, 59, 23);
	        frame2.getContentPane().add(button);
	        
	        btnPause = new JButton("  Pause");
	        btnPause.setBounds(149, 342, 77, 23);
	        frame2.getContentPane().add(btnPause);
	        
	        button_1 = new JButton(">>");
	        button_1.setBounds(236, 342, 59, 23);
	        frame2.getContentPane().add(button_1);*/

	}
	public void addData(String data){
		this.textArea.append(data);
	}
}
