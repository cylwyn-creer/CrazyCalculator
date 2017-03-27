package crazycalculator.gui;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import crazycalculator.datastructure.Stack;

public class EventHandler extends Frame implements ActionListener, KeyListener, MouseListener {
	
	private String input;
	private int inputSize;
	private String postfix;
	private boolean correct;
	private boolean proceed;
	private static final long serialVersionUID = 42L;
	
	public EventHandler() {
		
		proceed = true;
		
		inputTF.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent event) {
					
				equalsEvent();
							
			}
		});
	
		inputTF.addKeyListener(this);
		
		for(int i = 0; i < 20; i++) {
			
			inputB[i].addActionListener(this);
			inputB[i].addMouseListener(this);
			
		}
		
	}
	
	public void actionPerformed(ActionEvent event) {
		
		String button = event.getActionCommand();
		
		if(button == "Del") {
			
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
			
			equalsEvent();
			
		} else {
			if(inputSize < 100) {
				input = inputTF.getText() + button;
				inputTF.setText(input);
				inputSize++;
			}
		}
		
	}
	
	public void keyTyped(KeyEvent event) {
		
		char character = event.getKeyChar();
		
		
		if(!(Character.isDigit(character) || character == '.' || character == '(' || character == ')' ||
				character == '+' || character == '-' || character == '/' || character == '*' ||
						character == '=' || event.getKeyCode() == KeyEvent.VK_BACK_SPACE || 
						event.getKeyCode() == KeyEvent.VK_ESCAPE)) {	
			
			event.consume();
			
		} else 
		if(event.getKeyCode() == KeyEvent.VK_ESCAPE) {		//not working
			
			input = "";
			inputTF.setText(input);
			outputTF.setText("0");
			inputSize = 0;
			
		} else
		if(event.getKeyChar() == '=') {
			
			equalsEvent();
			
			event.consume();
			
		} else {
			
			if(inputTF.getText().length() < 100) {
				
				input = inputTF.getText();
				inputSize = input.length();
				
			} else {
				
				event.consume();
				
			}
		}
		
	}
	
	public void keyReleased(KeyEvent event) {
		
		Color color = new Color(255, 255, 255);
		
		setColor(event.getKeyChar(), color, event.getKeyCode());
		
	}
	
	public void keyPressed(KeyEvent event) {

		Color color = new Color(150, 150, 255);
		
		setColor(event.getKeyChar(), color, event.getKeyCode());
		
	}
	
	public void setColor(char character, Color color, int code) {
		
		if(character == '(')
			inputB[0].setBackground(color);
		if(character == ')')
			inputB[1].setBackground(color);
		if(code == KeyEvent.VK_BACK_SPACE)
			inputB[2].setBackground(color);
		if(code == KeyEvent.VK_ESCAPE)
			inputB[3].setBackground(color);
		if(character == '7')
			inputB[4].setBackground(color);
		if(character == '8')
			inputB[5].setBackground(color);
		if(character == '9')
			inputB[6].setBackground(color);
		if(character == '+')
			inputB[7].setBackground(color);
		if(character == '4')
			inputB[8].setBackground(color);
		if(character == '5')
			inputB[9].setBackground(color);
		if(character == '6')
			inputB[10].setBackground(color);
		if(character == '-')
			inputB[11].setBackground(color);
		if(character == '1')
			inputB[12].setBackground(color);
		if(character == '2')
			inputB[13].setBackground(color);
		if(character == '3')
			inputB[14].setBackground(color);
		if(character == '*')
			inputB[15].setBackground(color);
		if(character == '0')
			inputB[16].setBackground(color);
		if(character == '.')
			inputB[17].setBackground(color);
		if(character == '=')
			inputB[18].setBackground(color);
		if(character == '/')
			inputB[19].setBackground(color);
		
	}
	
	public void mouseExited(MouseEvent event) {
		
		event.getComponent().setBackground(new Color(255, 255, 255));
		
	}
	
	public void mouseEntered(MouseEvent event) {
		
		event.getComponent().setBackground(new Color(150, 150, 255));
		
	}
	
	public void mouseClicked(MouseEvent event) {
		
		event.getComponent().setBackground(new Color(150, 150, 255));
		
	}
	
	public void mouseReleased(MouseEvent event) {
		
		event.getComponent().setBackground(new Color(255, 255, 255));
		
	}
	
	public void mousePressed(MouseEvent event) {
		
		event.getComponent().setBackground(new Color(150, 150, 255));
		
	}
	
	public void equalsEvent() {
		
		if(proceed) {
			
			input = inputTF.getText();
			inputSize = input.length();
			
			correct = checkSyntax(input);
			
			postfix = "";
			
			if(correct) {
				
				proceed = false;
				inputTF.setEditable(false);
				inputTF.setFocusable(false);
				compute(input);
				
			} else {
				
				postfix = "Syntax error";
				outputTF.setText(postfix);
				
			}
			
		}
		
	}
	
	public boolean checkSyntax(String infix) {
		
		boolean isCorrect = false;
		int numOperand = 0;
		int numOperator = 0;
		int numDecimal = 0;
		int numClosePar = 0;
		int numOpenPar = 0;
		//Stack< String > parenthesis = new Stack< String >(infix.length());
		
		infix = addSeparator(infix);
		
		System.out.println("Infix: " + infix);
		
		if(infix.charAt(0) == ' ') {
			
			infix = infix.substring(1, infix.length());
			
		}
		
		for(String character : infix.split("\\s+")) {
			
			if(character.equals("+") || character.equals("-") || character.equals("*") || character.equals("/")) {
				
				System.out.println(character + "is an operator");
				numOperator++;
				
			} else
			if(character.equals("(")) {
				
				System.out.println(character + "is an open paren");
				numOpenPar++;
				//parenthesis.push(character);
				
			} else
			if(character.equals(")")) {
				
				numClosePar++;
				
				System.out.println(character + "is an close paren");
				/*if(!parenthesis.isEmpty()) {
					
					parenthesis.pop();
					
				} else {
					
					return false;
					
				}*/
				
			} else {
				
				for(int i = 0; i < character.length(); i++) {
					
					if(character.charAt(i) == '.') {
					
						numDecimal++;
						
						if((i + 1) < character.length()) {
							
							if(!Character.isDigit(character.charAt(i+1))) {
								
								return false;
								
							}
							
						} 
						
						if(i == character.length() - 1) {
							
							return false;
							
						}
						
					}
					
				}
				
				if(numDecimal > 1) {
					
					return false;
					
				} else {
					
					System.out.println(character + "is an operand");
					numOperand++;
					numDecimal = 0;
					
				}
					
				
			}
			
		}
		
		System.out.print("number of operand: " + numOperand + " number of operator: " + numOperator);
		System.out.println("number of open par: " + numOpenPar + "numClosePar: " + numClosePar);
		if((numOperand == (numOperator + 1)) || (numOperand == 1 && numOperator == 0)) {
			
			if(numOpenPar == numClosePar)
				isCorrect = true;
			else
				isCorrect = false;
			
		} else {
			
			isCorrect = false;
			
		}
		
		return isCorrect;
		
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
	
	public void compute(String infix) {
		Stack< Character > stack = new Stack< Character >(infix.length());
		
		Thread thread = new Thread()
		{
			private String output = "";
			
			public void run() {
				try {
					int index = postfixItem.length - 1;
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
								
								postfixItem[index].setText(String.valueOf(stack.displayItemAt(stack.size() - 1)));
								queue.setText(stack.displayQueue());
								pseudoArray.setText(stack.displayPseudoArray());
								linkedList.setText(stack.displayLinkedList());
								index--;
								Thread.sleep(500);
							}
							else if(character == ')') {
								while(!stack.isEmpty()) {
									char top = stack.pop();
									
									postfixItem[index + 1].setText(null);
									queue.setText(stack.displayQueue());
									pseudoArray.setText(stack.displayPseudoArray());
									linkedList.setText(stack.displayLinkedList());
									index++;
									Thread.sleep(500);
									
									if(top != '(') {
										output = output + ' ' + top;
										
										postfixTF.setText(output);
										Thread.sleep(500);
									} else {
										break;
									}
								}
							} else {
								output = output + ' ';
								postfixTF.setText(output);
								Thread.sleep(500);
								
								while(!stack.isEmpty()) {
									char top = stack.pop();
									
									postfixItem[index + 1].setText(null);
									queue.setText(stack.displayQueue());
									pseudoArray.setText(stack.displayPseudoArray());
									linkedList.setText(stack.displayLinkedList());
									index++;
									Thread.sleep(500);
									
									if(top == '(') {
										stack.push(top);

										postfixItem[index].setText(String.valueOf(stack.displayItemAt(stack.size() - 1)));
										queue.setText(stack.displayQueue());
										pseudoArray.setText(stack.displayPseudoArray());
										linkedList.setText(stack.displayLinkedList());
										index--;
										Thread.sleep(500);
										
										break;
									} else {
										if(checkPrecedence(top) < checkPrecedence(character)) {
											stack.push(top);
											
											postfixItem[index].setText(String.valueOf(stack.displayItemAt(stack.size() - 1)));
											queue.setText(stack.displayQueue());
											pseudoArray.setText(stack.displayPseudoArray());
											linkedList.setText(stack.displayLinkedList());
											index--;
											Thread.sleep(500);
											break;
											
										} else {
											output = output + top + ' ';
											postfixTF.setText(output);
											Thread.sleep(500);
										}
									}
								}
								stack.push(character);
								
								postfixItem[index].setText(String.valueOf(stack.displayItemAt(stack.size() - 1)));
								queue.setText(stack.displayQueue());
								pseudoArray.setText(stack.displayPseudoArray());
								linkedList.setText(stack.displayLinkedList());
								index--;
								Thread.sleep(500);
							}
						}
					}
					
					postfixTF.setText(output);
					Thread.sleep(500);
					
					while(!stack.isEmpty()) {
						output = output + ' ' + stack.pop();
						
						postfixTF.setText(output);
						Thread.sleep(500);
						
						postfixItem[index + 1].setText(null);
						queue.setText(stack.displayQueue());
						pseudoArray.setText(stack.displayPseudoArray());
						linkedList.setText(stack.displayLinkedList());
						index++;
						Thread.sleep(500);
					}
					System.out.println(output);
					
					String result = evaluatePostfix(output);
					
					outputTF.setText(result);
					
					proceed = true;
					inputTF.setEditable(true);
					inputTF.setFocusable(true);
				}
				catch(InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		thread.start();
	}
	
	public int checkPrecedence(char c) {
		
		if(c == '+' || c == '-') {
			
			return 1;
			
		} else {
	
			return 2;
			
		}
		
	}
	
	public String evaluatePostfix(String postfix) {
		
		String answer = "";
		Stack<String> expression = new Stack<String>(postfix.length());
		Stack<Double> temporaryAnswer = new Stack<Double>(postfix.length());
		
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
		
}