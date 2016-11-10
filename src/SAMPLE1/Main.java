package SAMPLE1;

import Database.Database;
import Database.DatabaseAccess;
import Database.I_Database;
import GUI.*;
import Java.ConcreteFramework;
import Java.ConcreteInterceptor;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Main {
	public static ConcreteFramework c;
	public static GUI.ActionListenerSuper actionListener;
	public static void main(String [] args){

		// register || log in
		I_Database dbConn = new Database();
		DatabaseAccess dba = DatabaseAccess.getInstance();
		dba.setDbConn(dbConn);
		try {
			dba.connect("jdbc:sqlserver://localhost;database=Creationary;integratedSecurity=true;");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		c = new ConcreteFramework();
		actionListener = new ActionListenerSuper();
		ConcreteInterceptor Int1 = new ConcreteInterceptor();
		attach(Int1);


        SplashWindow splashWindow = new SplashWindow();
		MainWindow window = new MainWindow();
		PanelFactory panelFac = new PanelFactory();
		PanelManager panelMgr = new PanelManager(panelFac);
		panelMgr.registerObserver(window);

	}

	private static void attach(ConcreteInterceptor i) {
		c.getDispacher().register(i);
	}



}