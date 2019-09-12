package gui;

import javax.swing.plaf.metal.MetalLookAndFeel;

import bus.StringExe;

import java.time.*;
import java.time.format.*;
import java.util.*;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try { 
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel"); 
			//UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } 
        catch (Exception e) { 
            System.out.println("Look and Feel not set"); 
        } 
		LoginFrame lg = new LoginFrame();
		
	}

}
