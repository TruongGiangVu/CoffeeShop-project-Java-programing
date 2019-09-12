package gui;

import javax.swing.*;

public class ProTabbedPane extends JTabbedPane{
	ProTabbedPane(String id){
		//this.setSize(1000,700);
		//setLayout(null);
		JPanel p1=new ProductGUI(id);
		this.add("Sản phẩm",p1);
		JPanel p2=new IngredientGUI(id);
		this.add("Nguyên liệu",p2);
		JPanel p3=new RecipeGUI(id);
		this.add("Công thức",p3);
		
	}
}
