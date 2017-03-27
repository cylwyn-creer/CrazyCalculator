package crazycalculator.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.BoundedRangeModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

@SuppressWarnings("serial")
public class Frame extends JFrame{
	
	private JPanel buttonsPanel = new JPanel();
	private JPanel calculatorPanel = new JPanel();
	private JPanel dataStructPanel = new JPanel();
	private JPanel textPanel = new JPanel();
	public JButton[] inputB = new JButton[20];
	public JTextField inputTF = new JTextField(100);
	public JTextField outputTF = new JTextField();
	private String[] symbols = {"(", ")", "Del", "AC", "7", "8", "9", "+", "4", "5", "6", "-", "1", "2", "3", "*", "0", ".", "=", "/"};
	
	private JLabel header = new JLabel("Data Structures", SwingConstants.CENTER);
	private JLabel postfixL = new JLabel("Postfix");
	private JLabel stackL = new JLabel("Stack");
	
	public final JTextField postfixTF = new JTextField(100);
	
	private JPanel postfixPanel1 = new JPanel(new GridLayout(20, 1));
	private JPanel postfixPanel2 = new JPanel(new GridLayout(20, 1));
	private JPanel postfixPanel3 = new JPanel(new GridLayout(20, 1));
	
	public JLabel[] postfixItem = new JLabel[60];
	
	private JLabel queueL = new JLabel("Queue");
	private JLabel pseudoArrayL = new JLabel("Pseudo Array");
	private JLabel linkedListL = new JLabel("Linked List");
	
	public JTextField queue = new JTextField();
	public JTextArea pseudoArray = new JTextArea();
	private JScrollPane scrollP1;
	public JTextArea linkedList = new JTextArea();
	private JScrollPane scrollP2;
	
	public Frame() {
		super("Crazy Calculator");
		
		getContentPane().setBackground(new Color(255, 255, 255, 200));
		
		try {
			UIManager.setLookAndFeel(new NimbusLookAndFeel());
			SwingUtilities.updateComponentTreeUI(this);
		} 
		catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
		setComponents();
		
		add(calculatorPanel);
		add(dataStructPanel);
		
		calculatorPanel.add(buttonsPanel);
		calculatorPanel.add(textPanel);

		setLayout(null);
		setSize(960,520);
		setVisible(true);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void setComponents() {
		calculatorPanel.setBounds(0, 0, 320, 520);
		calculatorPanel.setLayout(null);
		dataStructPanel.setBounds(320, 0, 640, 520);
		dataStructPanel.setLayout(null);
		
		header.setBounds(0, 0, 640, 25);
		header.setFont(new Font("Courier New", Font.BOLD, 20));
		dataStructPanel.add(header);
		
		postfixPanel1.setBounds(0, 60, 75, 340);
		postfixPanel2.setBounds(80, 60, 75, 340);
		postfixPanel3.setBounds(160, 60, 75, 340);
		dataStructPanel.add(postfixPanel1);
		dataStructPanel.add(postfixPanel2);
		dataStructPanel.add(postfixPanel3);
		
		for(int a = 0; a < postfixItem.length; a++) {
			postfixItem[a] = new JLabel("" ,SwingConstants.CENTER);
			postfixItem[a].setFont(new Font("Courier New", Font.BOLD, 15));
			postfixItem[a].setBorder(BorderFactory.createLineBorder(Color.CYAN));
			
			if(a >= 0 && a < 20) {
				postfixPanel1.add(postfixItem[a]);
			}
			else if(a >= 20 && a < 40) {
				postfixPanel2.add(postfixItem[a]);
			}
			else {
				postfixPanel3.add(postfixItem[a]);
			}
		}
		
		postfixL.setBounds(0, 400, 70, 20);
		postfixL.setFont(new Font("Courier New", Font.BOLD, 15));
		dataStructPanel.add(postfixL);
		
		stackL.setBounds(0, 30, 75, 20);
		stackL.setFont(new Font("Courier New", Font.BOLD, 15));
		dataStructPanel.add(stackL);
		
		queueL.setBounds(245, 30, 75, 20);
		queueL.setFont(new Font("Courier New", Font.BOLD, 15));
		dataStructPanel.add(queueL);
		
		pseudoArrayL.setBounds(245, 120, 180, 20);
		pseudoArrayL.setFont(new Font("Courier New", Font.BOLD, 15));
		dataStructPanel.add(pseudoArrayL);
		
		linkedListL.setBounds(440, 120, 180, 20);
		linkedListL.setFont(new Font("Courier New", Font.BOLD, 15));
		dataStructPanel.add(linkedListL);
		
		queue.setBounds(245 , 60, 375, 30);
		queue.setEditable(false);
		queue.setBorder(BorderFactory.createLineBorder(Color.CYAN));
		queue.setFont(new Font("Courier New", Font.BOLD, 15));
		dataStructPanel.add(queue);
		
		JScrollBar scroll = new JScrollBar(JScrollBar.HORIZONTAL);
		
		JScrollBar queueScroll = new JScrollBar(JScrollBar.HORIZONTAL);
		
		BoundedRangeModel brm1 = queue.getHorizontalVisibility();
		queueScroll.setModel(brm1);
		queueScroll.setBounds(245, 90, 375, 20);
		dataStructPanel.add(queueScroll);
		
		postfixTF.setBounds(0, 420, 620, 30);
		postfixTF.setEditable(false);
		postfixTF.setFont(new Font("Courier New", Font.BOLD, 15));
		postfixTF.setBorder(BorderFactory.createLineBorder(Color.CYAN));
		dataStructPanel.add(postfixTF);
		
		BoundedRangeModel brm = postfixTF.getHorizontalVisibility();
		scroll.setModel(brm);
		scroll.setBounds(0, 450, 620, 20);
		dataStructPanel.add(scroll);
		
		pseudoArray.setFont(new Font("Courier New", Font.BOLD, 15));
		pseudoArray.setEditable(false);
		
		scrollP1 = new JScrollPane(pseudoArray);
		scrollP1.setBorder(BorderFactory.createLineBorder(Color.CYAN));
		scrollP1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollP1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollP1.setBounds(245, 150, 180, 250);
		dataStructPanel.add(scrollP1);
		
		linkedList.setFont(new Font("Courier New", Font.BOLD, 15));
		linkedList.setEditable(false);
		
		scrollP2 = new JScrollPane(linkedList);
		scrollP2.setBorder(BorderFactory.createLineBorder(Color.CYAN));
		scrollP2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollP2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollP2.setBounds(440, 150, 180, 250);
		dataStructPanel.add(scrollP2);
		
		buttonsPanel.setLayout(new GridLayout(5, 4, 5, 5));
		buttonsPanel.setBounds(7, 130, 300, 330);
		
		for(int i = 0; i < 20;  i++) {
			inputB[i] = new JButton(symbols[i]);
			inputB[i].setFont(new Font("Courier New", Font.BOLD, 20));
			inputB[i].setBackground(new Color(255, 255, 255));
			inputB[i].setBorder(BorderFactory.createLineBorder(Color.CYAN));
			buttonsPanel.add(inputB[i]);
		}
	
		inputTF.setFont(new Font("Courier New", Font.BOLD, 25));
		inputTF.setBorder(BorderFactory.createEmptyBorder());
		
		outputTF.setEditable(false);
		outputTF.setFont(new Font("Courier New", Font.BOLD, 25));
		outputTF.setText("0");
		outputTF.setHorizontalAlignment(SwingConstants.RIGHT);
		outputTF.setBorder(BorderFactory.createEmptyBorder());
		outputTF.setBackground(Color.WHITE);
		
		textPanel = new JPanel(new GridLayout(2, 1));
		textPanel.setBounds(7, 10, 300, 100);
		textPanel.setBorder(BorderFactory.createLineBorder(Color.CYAN));
		textPanel.add(inputTF);
		textPanel.add(outputTF);
	}
	
}