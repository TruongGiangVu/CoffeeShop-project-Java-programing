package design;

import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.Color;

import javax.swing.ImageIcon;

public class Icon {
	public Color contentColor =new Color(78,186,163);
	public Color tableColor =new Color(152,209,203);
	public Color paneColor=new Color(204,228,228);
	public Color insertColor= new Color(0, 255, 0);
	public Color deleteColor = new Color(178, 34, 34);
	public Color editColor =new Color(244, 117, 32);
	public Color coverColor =new Color(30,100,174);
	public Color searchColor =new Color(227, 231, 234);
	public Color sortColor=new Color(0, 128, 128);
	public Color clearColor =new Color(178,34,34);
	public ImageIcon imageIconSize(String nameImage,int width,int height) {
		ImageIcon icon = new ImageIcon(nameImage); // load the image to a imageIcon
		Image image = icon.getImage(); // transform it
		Image newimg = image.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH); // scale it the// smooth way
		icon = new ImageIcon(newimg);
		return icon;
	}
	public Cursor imageCursor(String imageCursor) {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image image = toolkit.getImage(imageCursor);
		Cursor c = toolkit.createCustomCursor(image , new Point(0,0), "img");
		return c;
	}
	public Color getClearColor() {
		return clearColor;
	}
	public Color getContentColor() {
		return contentColor;
	}
	public Color getCoverColor() {
		return coverColor;
	}
	public Color getDeleteColor() {
		return deleteColor;
	}
	public Color getEditColor() {
		return editColor;
	}
	public Color getInsertColor() {
		return insertColor;
	}
	public Color getPaneColor() {
		return paneColor;
	}
	public Color getSearchColor() {
		return searchColor;
	}
	public Color getSortColor() {
		return sortColor;
	}
	public Color getTableColor() {
		return tableColor;
	}
}
