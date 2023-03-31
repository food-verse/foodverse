package com.foodverse.models;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Store {
    private JPanel panel;
    private JLabel nameLabel;

    public store()
	{
	panel = new JPanel();
	nameLabel = new JLabel("Offers");
	
	panel.add(nameLabel);
	
	this.setContentPane(panel);
	
	this.setVisible(true);
	this.setSize(1540, 800);
	this.setTitle("Store");
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
}

