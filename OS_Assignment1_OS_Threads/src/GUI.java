import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;

import java.io.File;  // Import the File class
import java.io.IOException;  // Import the IOException class to handle errors
import java.io.FileWriter;   // Import the FileWriter class

public class GUI extends Thread{
	static int number;
	static String filename;
  //  public static void running_gui() 
	public synchronized void run()
		 {
        //Creating the Frame
        JFrame frame = new JFrame("Chat Frame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 400);
        frame.setBackground(Color.red);
        // jframe.setBackground(Color.RED);

        //Creating the panel at bottom and adding components
        JPanel panel = new JPanel(); // the panel is not visible in output
        JLabel label = new JLabel("Number             ");
        JTextField tf = new JTextField(10); // accepts upto 10 characters
        panel.add(label); // Components Added using Flow Layout
        panel.add(tf);

         //Creating the panel at bottom and adding components
         JPanel panell = new JPanel(); // the panel is not visible in output
         JLabel labell = new JLabel("Output File");
         JTextField tff = new JTextField(10); // accepts upto 10 characters
         JButton send = new JButton("        Start Producer        ");
         send.setBounds(40,100,100,40);
         panel.add(labell); // Components Added using Flow Layout
         panel.add(tff);
         panel.add(send);

        //Creating the panel at bottom and adding components
        JPanel panelll = new JPanel(); // the panel is not visible in output
        /////////////////////////////////////////
        //////////////////////////////////////////????????
        
        long startTime = System.nanoTime(); //for calculating the time 
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        
        JLabel labelll = new JLabel("    Largest prime num               " + consumer_C.largeNumber);
        JLabel labellll = new JLabel("   Generted prime nums             " + producer_c.counter_nums);
        JLabel labelllll = new JLabel("  Time since start processing    " + duration);

        panel.add(labelll); 
        panel.add(labellll); 
        panel.add(labelllll); 

        //Adding Components to the frame.
        frame.getContentPane().add(BorderLayout.CENTER, panel);
        frame.getContentPane().add(BorderLayout.NORTH, panell);
        frame.getContentPane().add(BorderLayout.SOUTH, panelll);
        frame.setVisible(true);

        send.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                if (!tf.getText().equals("")){
                 number = Integer.parseInt(tf.getText());
                 filename = tff.getText();
                 System.out.println(number);
                 System.out.println(filename);
                 try {
                    File myObj = new File(filename+".txt");
                    if (myObj.createNewFile()) {
                      System.out.println("File created: " + myObj.getName());
                      FileWriter myWriter = new FileWriter("filename.txt");
                      myWriter.write("Write Hereeee");
                      myWriter.close();

                    } else {
                      System.out.println("File already exists.");
                    }
                  } catch (IOException e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                  }
                }
            }
         });
         

         
		 }
}
	