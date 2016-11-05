package Java;

import SQL.Connect;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
public class ViewStaffDetails implements  ActionListener
{

	private JPanel buttonPanel;
	private JButton exitButton;
	JLabel quantityLabel, nameLabel;
	private final JPanel totalGUI = new JPanel();
	JTextField itemNameTextField, quantityTextField;
	private static final JFrame frame = new JFrame("View Staff Details");

	private JPanel createContentPane()
	{
		//Make bottom JPanel to place buttonPanel on
		//JPanel totalGUI = new JPanel();
		totalGUI.setLayout(null);

		//Make Button Panel
		buttonPanel = new JPanel();
		buttonPanel.setLayout(null);
		buttonPanel.setLocation(10, 10);
		buttonPanel.setSize(295, 185);
		totalGUI.add(buttonPanel);
		int i = 0;

		//get number of rows returned
		try
		{
			//Java.Connect to database
			   Connect con = new Connect(); 
    		   Connection mycon =  con.getconnection();
    		   Statement mystat = mycon.createStatement();
			ResultSet myRe = mystat.executeQuery("select * from items");

			//get db data
			while (myRe.next())
				i++;
		}
		catch(Exception exc)
		{
			System.out.println("Database error");
		}


		//Assign values to listData based on DB values.
		Object[] listData = new Object[i];

		i = 0;
		try
		{
			//get array of names/quantities
			//Java.Connect to database
			   Connect con = new Connect();
    		   Connection mycon =  con.getconnection();
    		   Statement mystat = mycon.createStatement();			
			String sql = "select * from users WHERE accesslvl = 2 OR accesslvl = 3";
			System.out.println(sql);
			ResultSet myRe = mystat.executeQuery(sql);

			String name = "";
			String email = "";
			String address = "";
			String occupancy = " ";

			//get db data
			while (myRe.next())
			{
				int accesslvl = myRe.getInt(3);
				if (accesslvl == 2)
					occupancy = "Employee Type: WareHouse Staff, \n";
				else
					occupancy = "Employee Type: Logistics Staff, \n";


				name = " UserName: " + myRe.getString(2) + ", \n";
				address = " Address:  " +  myRe.getString(6) + ", \n";
				email = " Email: " + myRe.getString(5) + ", \n\n" ;											
				listData[i] = occupancy + name + address  + email;

				i++;
			}
		}
		catch(Exception exc)
		{
			System.out.println("Database error");
		}



		//Make List and scroll pane for items
		JList items = new JList(listData);

			
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(items);
		scrollPane.setLocation(0,0);
		scrollPane.setSize(270, 150);
		
		
		JLabel text = new JLabel();
		text.setLocation(0,0);
		text.setSize(270, 150);
		
		
		buttonPanel.add(scrollPane);


		//Make buttons
		exitButton = new JButton("Exit");
		exitButton.setLocation(0, 150);
		exitButton.setSize(85, 30);
		exitButton.addActionListener(this);
		buttonPanel.add(exitButton);







		totalGUI.setVisible(true);


		try
		{	
			//Java.Connect to database
			   Connect con = new Connect(); 
    		   Connection mycon =  con.getconnection();
    		   Statement mystat = mycon.createStatement();

			String sql = "select * from users";
			ResultSet myRe = mystat.executeQuery(sql);

			//get db data
			while (myRe.next())
			{
				System.out.println(myRe.getString(2));
				System.out.println(myRe.getString(4));
				System.out.println(myRe.getString(6));
			}
		}

		catch(Exception exc)
		{
			System.out.println("Error");
		}

		return totalGUI;
	}

	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == exitButton)
		{
			frame.dispose();
		}

	}

	private static void createAndShowGUI()
	{
		//Create and set up the content pane.
		ViewStaffDetails window = new ViewStaffDetails();
		frame.setContentPane(window.createContentPane());

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(305, 240);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	public static void main2()
	{		

		SwingUtilities.invokeLater(new Runnable() 
		{
			public void run() 
			{
				createAndShowGUI();
			}
		});
	}


}
