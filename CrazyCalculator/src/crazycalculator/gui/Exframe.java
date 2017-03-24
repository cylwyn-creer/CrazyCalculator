package crazycalculator.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import crazycalculator.datastructure.Stack;

@SuppressWarnings("serial")
public class Exframe extends JFrame{
	private JPanel buttonsPanel = new JPanel();
	private JPanel calculatorPanel = new JPanel();
	private JPanel dataStructPanel = new JPanel();
	private JPanel textPanel = new JPanel();
	private ButtonHandler buttonHandler = new ButtonHandler();
	private KeyHandler keyHandler = new KeyHandler();
	private JButton[] inputB = new JButton[20];
	private JTextField inputTF = new JTextField(100);
	private JTextField outputTF = new JTextField();
	private String input = "";
	private String[] symbols = {"(", ")", "AC", "C", "7", "8", "9", "+", "4", "5", "6", "-", "1", "2", "3", "*", "0", ".", "=", "/"};
	private int inputSize = 0;
	private String output = "";
	
	private JLabel header = new JLabel("Stack Data Structure");
	private JLabel postfixL = new JLabel("Postfix");
	
	private JTextField postfixTF = new JTextField(100);
	
	private JPanel stackPanel = new JPanel(new GridLayout(1, 3));
	private JPanel postfixPanel = new JPanel(new GridLayout(20, 1));
	private JLabel[] postfixItem = new JLabel[20];
	//private JPanel expressionPanel = new JPanel(new GridLayout(20, 1));
	//private JPanel 
	
	public Exframe() {
		
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
		setSize(640,520);
		setVisible(true);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	
	public void setComponents() {
		
		calculatorPanel.setBounds(0, 0, 320, 520);
		calculatorPanel.setLayout(null);
		dataStructPanel.setBounds(320, 0, 320, 520);
		dataStructPanel.setLayout(null);
		
		header.setBounds(0, 0, 320, 25);
		header.setFont(new Font("Courier New", Font.BOLD, 20));
		dataStructPanel.add(header);
		
		stackPanel.setBounds(0, 25, 300, 375);
		stackPanel.setBorder(BorderFactory.createLineBorder(Color.CYAN));
		dataStructPanel.add(stackPanel);
		
		stackPanel.add(postfixPanel);
		
		for(int a = 0; a < postfixItem.length; a++) {
			postfixItem[a] = new JLabel();
			postfixItem[a].setFont(new Font("Courier New", Font.PLAIN, 11));
			postfixItem[a].setBorder(BorderFactory.createLineBorder(Color.CYAN));
			postfixPanel.add(postfixItem[a]);
		}
		
		postfixL.setBounds(0, 400, 70, 20);
		postfixL.setFont(new Font("Courier New", Font.BOLD, 15));
		dataStructPanel.add(postfixL);
		
		postfixTF.setBounds(0, 420, 300, 40);
		postfixTF.setEditable(false);
		postfixTF.setFont(new Font("Courier New", Font.BOLD, 25));
		postfixTF.setBorder(BorderFactory.createLineBorder(Color.CYAN));
		dataStructPanel.add(postfixTF);
		
		buttonsPanel.setLayout(new GridLayout(5, 4, 5, 5));
		buttonsPanel.setBounds(7, 130, 300, 330);
		
		for(int i = 0; i < 20;  i++) {
			
			inputB[i] = new JButton(symbols[i]);
			inputB[i].addActionListener(buttonHandler);
			inputB[i].setFont(new Font("Courier New", Font.BOLD, 20));
			inputB[i].setBackground(new Color(255, 255, 255));
			inputB[i].setBorder(BorderFactory.createLineBorder(Color.CYAN));
			buttonsPanel.add(inputB[i]);
			
		}
	
		inputTF.setFont(new Font("Courier New", Font.BOLD, 25));
		inputTF.setBorder(BorderFactory.createEmptyBorder());
		inputTF.addActionListener( new ActionListener() {
			
			public void actionPerformed(ActionEvent event) {

				input = inputTF.getText();
				inputSize = input.length();
				
				String inputCopy = addSeparator(input);
				
				boolean correct = false;
				
				correct = checkSyntax(inputCopy);
				
				System.out.println(inputCopy);
				
				String postfix = "";
				
				if(correct) {
					
					System.out.println(input);
					postfix = convertInfixToPostfix(input);
					outputTF.setText(evaluatePostfix(postfix));
					
				} else {
					
					postfix = "Syntax error";
					System.out.println(input);
					outputTF.setText(postfix);
					
				}
				
			}
		});
		
		inputTF.addKeyListener(keyHandler);
		
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
	
	private class ButtonHandler implements ActionListener {
		
		public void actionPerformed(ActionEvent event) {
		
			String button = event.getActionCommand();
			
			if(button == "C") {
				
				if(inputSize != 0) {
					
					input = inputTF.getText();
					inputSize = input.length() - 1;
					input = input.substring(0, inputSize);
					inputTF.setText(input);
					
				}
				
			} else 
			if(button == "AC") {
				
				input = "";
				inputTF.setText(input);
				outputTF.setText("0");
				inputSize = 0;
				
			} else
			if(button == "=") {
				
				input = inputTF.getText();
				inputSize = input.length();
				
				String inputCopy = addSeparator(input);
				
				boolean correct = false;
				
				correct = checkSyntax(inputCopy);
				
				System.out.println(inputCopy);
				
				String postfix = "";
				
				if(correct) {
					
					System.out.println(input);
					postfix = convertInfixToPostfix(input);
					//outputTF.setText(evaluatePostfix(postfix));
					
				} else {
					
					postfix = "Syntax error";
					System.out.println(input);
					outputTF.setText(postfix);
					
				}
				
			} else {
				if(inputSize < 100) {
					input = inputTF.getText() + button;
					inputTF.setText(input);
					inputSize++;
				}
			}
			
		}
		
	}
	
	public String addSeparator(String infix) {
		
		String newInput = "";
		
		for(int i = 0; i < infix.length(); i++) {
			
			char character = infix.charAt(i); 
			
			if(character == '(' || character == ')' || character == '+' || character == '-' || character == '*' || character == '/') {
				
				newInput = newInput + " " + character + " ";
				
			} else {
				
				newInput = newInput + character;
				
			}
			
		}
		
		return newInput;
		
	}
	
	public boolean checkSyntax(String infix) {
		
		boolean isCorrect = false;
		int numOperand = 0;
		int numOperator = 0;
		Stack< String > parenthesis = new Stack< String >(infix.length());
		
		if(infix.charAt(0) == ' ') {
			
			infix = infix.substring(1, infix.length());
			
		}
		
		for(String character : infix.split("\\s+")) {
			
			if(character.equals("+") || character.equals("-") || character.equals("*") || character.equals("/")) {
				
				System.out.println(character + " is operator");
				numOperator++;
				
			} else
			if(character.equals("(")) {
				
				System.out.println(character + " is open par");
				
				parenthesis.push(character);
				
			} else
			if(character.equals(")")) {
				
				if(!parenthesis.isEmpty()) {
					
					System.out.println(character + " is close par");
					parenthesis.pop();
					
				} else {
					
					return false;
					
				}
				
			} else {
				
				System.out.println(character + " is operand");
				numOperand++;
					
				
			}
			
		}
		
		System.out.println("numOperand: " + numOperand + " numOperator: " + numOperator);
		if((numOperand == (numOperator + 1)) || (numOperand == 1 && numOperator == 0)) {
			
			isCorrect = true;
			
		} else
		if(!parenthesis.isEmpty()) {
			
			isCorrect = false;
			
		} else {
			
			isCorrect = false;
			
		}
		
		return isCorrect;
		
	}
	/*
	public String convertInfixToPostfix(String infix) {
		
		String output = "";
		Stack< Character > stack = new Stack< Character >(infix.length());
		
		for(int a = 0; a < infix.length(); a++) {
			
			char character = infix.charAt(a);
			
			if(Character.isDigit(character)) {
				
				output = output + character;
				
			} else {
				if(character == '.') {
					
					output = output + character;
					
					continue;
					
				}
				
				if(character == '(') {
					
					stack.push(character);
					
				}
				else if(character == ')') {
					
					while(!stack.isEmpty()) {
						
						char top = stack.pop();
						
						if(top != '(') {
							
							output = output + ' ' + top;
							
						} else {
							
							break;
							
						}
						
					}
					
				} else {
					
					output = output + ' ';
					
					while(!stack.isEmpty()) {
						
						char top = stack.pop();
						
						if(top == '(') {
							
							stack.push(top);
							break;
							
						} else {
							
							if(checkPrecedence(top) < checkPrecedence(character)) {
								
								stack.push(top);
								break;
								
							} else {
								
								output = output + top + ' ';
								
							}
							
						}
						
					}
					
					stack.push(character);
				
				}
			
			}
			
		}
		
		while(!stack.isEmpty()) {
			
			output = output + ' ' + stack.pop();
			
		}
		
		System.out.println(output);
		
		return output;
		
	}
	*/
	public String convertInfixToPostfix(String infix) {
		
		//String output = "";
		Stack< Character > stack = new Stack< Character >(infix.length());
		
		Thread thread = new Thread()
		{
			private String output = "";
			
			public void run() {
				//synchronized(this) {
				try {
					for(int a = 0; a < infix.length(); a++) {
			
						char character = infix.charAt(a);
			
						if(Character.isDigit(character)) {
				
							output = output + character;
				
						} else {
							if(character == '.') {
					
								output = output + character;
					
								continue;
					
							}
				
							if(character == '(') {
					
								stack.push(character);
								
								refreshStack();
								
								for(int z = 0; z < stack.size(); z++) {
									postfixItem[z].setText(String.valueOf(stack.displayItemAt(z)));
									
									Thread.sleep(500);
								}
					
							}
							else if(character == ')') {
					
								while(!stack.isEmpty()) {
						
									char top = stack.pop();
									
									refreshStack();
									
									for(int z = 0; z < stack.size(); z++) {
										postfixItem[z].setText(String.valueOf(stack.displayItemAt(z)));
										
										Thread.sleep(500);
									}
									
									if(top != '(') {
							
										output = output + ' ' + top;
							
									} else {
							
										break;
							
									}
						
								}
					
							} else {
					
								output = output + ' ';
					
								while(!stack.isEmpty()) {
						
									char top = stack.pop();
									
									refreshStack();
									
									for(int z = 0; z < stack.size(); z++) {
										postfixItem[z].setText(String.valueOf(stack.displayItemAt(z)));
										
										Thread.sleep(500);
									}
									
									if(top == '(') {
							
										stack.push(top);
										
										refreshStack();
										
										for(int z = 0; z < stack.size(); z++) {
											postfixItem[z].setText(String.valueOf(stack.displayItemAt(z)));
											
											Thread.sleep(500);
										}
										
										break;
							
									} else {
							
										if(checkPrecedence(top) < checkPrecedence(character)) {
								
											stack.push(top);
											
											refreshStack();
											
											for(int z = 0; z < stack.size(); z++) {
												postfixItem[z].setText(String.valueOf(stack.displayItemAt(z)));
												
												Thread.sleep(500);
											}
											
											break;
								
										} else {
								
											output = output + top + ' ';
								
										}
							
									}
						
								}
					
								stack.push(character);
								
								refreshStack();
								
								for(int z = 0; z < stack.size(); z++) {
									postfixItem[z].setText(String.valueOf(stack.displayItemAt(z)));
									
									Thread.sleep(500);
								}
								
							}
			
						}
			
					}
					
					while(!stack.isEmpty()) {
						
						output = output + ' ' + stack.pop();
						
						refreshStack();
						
						for(int z = 0; z < stack.size(); z++) {
							postfixItem[z].setText(String.valueOf(stack.displayItemAt(z)));
							
							Thread.sleep(500);
						}
					}
					
					setOutput(output);
					System.out.println(output);
					
					outputTF.setText(evaluatePostfix(output));
				}
				catch(InterruptedException e) {
					e.printStackTrace();
				}
				//}
				//notify();
			}
		};
		thread.start();
		/*
		synchronized(thread) {
		System.out.println(output);
		
		try {
			thread.wait();
		}
		catch(InterruptedException e) {
			e.printStackTrace();
		}
		*/
		return output;
		}
		
	//}
	
	public void setOutput(String output) {
		this.output = output;
	}
	
	public void refreshStack() {
		for(int z = 0; z < 20; z++) {
			postfixItem[z].setText("");
		}
	}
	
	public String evaluatePostfix(String postfix) {
		
		String answer = "";
		Stack<String> expression = new Stack<String>(100);
		Stack<Double> temporaryAnswer = new Stack<Double>(100);
		
		String[] terms = postfix.split(" ");
		
		for(int a = terms.length - 1; a >= 0; a--) {
			
			expression.push(terms[a]);
			
		}
		
		while(!expression.isEmpty()) {
			
			String top = expression.pop();
			
			try {
				
				temporaryAnswer.push(Double.parseDouble(top));
				
			} catch(NumberFormatException e) {
				
				double value2 = temporaryAnswer.pop();
				double value1 = temporaryAnswer.pop();
				
				if(top.equals("+")) {
					
					temporaryAnswer.push(value1 + value2);
					
				}
				else if(top.equals("-")) {
					
					temporaryAnswer.push(value1 - value2);
					
				}
				else if(top.equals("*")) {
					
					temporaryAnswer.push(value1 * value2);
					
				}
				else {
					
					temporaryAnswer.push(value1 / value2);
					
				}
				
			}
			
		}
		
		answer = String.valueOf(temporaryAnswer.pop());
		
		if(answer.equals("Infinity")) {
			answer = "Math Error";
		}
		
		return answer;
	}
	
	public int checkPrecedence(char c) {
		
		if(c == '+' || c == '-') {
			
			return 1;
			
		} else {
	
			return 2;
			
		}
		
	}
	
	private class KeyHandler extends KeyAdapter {
		
		public void keyTyped(KeyEvent event) {
		
			char character = event.getKeyChar();
			
			
			if(!(Character.isDigit(character) || character == '.' || character == '(' || character == ')' ||
					character == '+' || character == '-' || character == '/' || character == '*' ||
							character == '=' || event.getKeyCode() == KeyEvent.VK_BACK_SPACE)) {	
				
				event.consume();
				
			} else 
			if(event.getKeyChar() == '=') {
				
				input = inputTF.getText();
				inputSize = input.length();
				
				String inputCopy = addSeparator(input);
				
				boolean correct = false;
				
				correct = checkSyntax(inputCopy);
				
				System.out.println(inputCopy);
				
				String postfix = "";
				
				if(correct) {
					
					System.out.println(input);
					postfix = convertInfixToPostfix(input);
					outputTF.setText(evaluatePostfix(postfix));
					
				} else {
					
					postfix = "Syntax error";
					System.out.println(input);
					outputTF.setText(postfix);
					
				}
				
				event.consume();
				//do the math
				
			} else {
				if(inputTF.getText().length() < 100) {
					input = inputTF.getText();
					inputSize = input.length();
				} else {
					
					event.consume();
					
				}
			}
			
		}
		
	}
}
