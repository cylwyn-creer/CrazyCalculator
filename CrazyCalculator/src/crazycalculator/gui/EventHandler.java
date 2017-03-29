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
	
	private int numOperand = 0;
	private int numOperator = 0;
	private int numDecimal = 0;
	private int numClosePar = 0;
	private int numOpenPar = 0;
	
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
				
			} else
			if(character.equals(")")) {
				
				numClosePar++;
				
				System.out.println(character + "is an close paren");
				
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
		header.setText("Converting to Postfix Notation");
		
		Stack< Character > stack = new Stack< Character >(numOperator + numOpenPar + numClosePar);
		
		numOperand = numOperator = numDecimal = numClosePar = numOpenPar = 0;
		
		Thread thread = new Thread()
		{
			private String output = "";
			private String parsedString = "";
			
			public void run() {
				try {
					for(int a = 0; a < infix.length(); a++) {
						char character = infix.charAt(a);
						if(Character.isDigit(character)) {
							output = output + character;
							parsedString = parsedString + character;
						} else {	
							if(character == '.') {
								output = output + character;
								parsedString = parsedString + character;
								continue;
							}
							if(character == '(') {
								parseTF.setText("(");
								Thread.sleep(500);
								
								stack.push(character);

								stackT.setText(stack.displayContents());
								queue.setText(stack.displayQueue());
								pseudoArray.setText(stack.displayPseudoArray());
								linkedList.setText(stack.displayLinkedList());
								Thread.sleep(500);
							}
							else if(character == ')') {
								parseTF.setText(parsedString);
								parsedString = "";
								
								postfixTF.setText(output);
								Thread.sleep(500);
								
								parseTF.setText(")");
								Thread.sleep(500);
								
								while(!stack.isEmpty()) {
									char top = stack.pop();

									stackT.setText(stack.displayContents());
									queue.setText(stack.displayQueue());
									pseudoArray.setText(stack.displayPseudoArray());
									linkedList.setText(stack.displayLinkedList());
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
								parseTF.setText(parsedString);
								parsedString = "";
								output = output + ' ';
								postfixTF.setText(output);
								Thread.sleep(500);
								
								parseTF.setText(String.valueOf(character));
								Thread.sleep(500);
								
								while(!stack.isEmpty()) {
									char top = stack.pop();
									
									stackT.setText(stack.displayContents());
									queue.setText(stack.displayQueue());
									pseudoArray.setText(stack.displayPseudoArray());
									linkedList.setText(stack.displayLinkedList());
									Thread.sleep(500);
									
									if(top == '(') {
										stack.push(top);

										stackT.setText(stack.displayContents());
										queue.setText(stack.displayQueue());
										pseudoArray.setText(stack.displayPseudoArray());
										linkedList.setText(stack.displayLinkedList());
										Thread.sleep(500);
										
										break;
									} else {
										if(checkPrecedence(top) < checkPrecedence(character)) {
											stack.push(top);
											
											stackT.setText(stack.displayContents());
											queue.setText(stack.displayQueue());
											pseudoArray.setText(stack.displayPseudoArray());
											linkedList.setText(stack.displayLinkedList());
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
								
								stackT.setText(stack.displayContents());
								queue.setText(stack.displayQueue());
								pseudoArray.setText(stack.displayPseudoArray());
								linkedList.setText(stack.displayLinkedList());
								Thread.sleep(500);
							}
						}
					}
					
					parseTF.setText(parsedString);
					
					postfixTF.setText(output);
					Thread.sleep(500);
					
					parseTF.setText("END");
					
					while(!stack.isEmpty()) {
						output = output + ' ' + stack.pop();
						
						postfixTF.setText(output);
						Thread.sleep(500);
						
						stackT.setText(stack.displayContents());
						queue.setText(stack.displayQueue());
						pseudoArray.setText(stack.displayPseudoArray());
						linkedList.setText(stack.displayLinkedList());
						Thread.sleep(500);
					}
					System.out.println(output);
					
					evaluatePostfix(output);
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
	
	public void evaluatePostfix(String postfix) {
		header.setText("Evaluating Postfix Notation");
		
		Thread thread = new Thread() {
			String answer = "";
			String[] terms = postfix.split(" ");
			
			Stack<String> expression = new Stack<String>(terms.length);
			Stack<Double> temporaryAnswer = new Stack<Double>(terms.length);
			
			public void run() {
				for(int a = terms.length - 1; a >= 0; a--) {
			
					expression.push(terms[a]);
			
				}
		
				while(!expression.isEmpty()) {
			
					String top = expression.pop();
			
					try {
						
						parseTF.setText(top);
						Thread.sleep(500);
						
						temporaryAnswer.push(Double.parseDouble(top));
						
						stackT.setText(temporaryAnswer.displayContents());
						queue.setText(temporaryAnswer.displayQueue());
						pseudoArray.setText(temporaryAnswer.displayPseudoArray());
						linkedList.setText(temporaryAnswer.displayLinkedList());
						Thread.sleep(500);
				
					} catch(NumberFormatException e) {
						try {
							double value2 = temporaryAnswer.pop();
						
							stackT.setText(temporaryAnswer.displayContents());
							queue.setText(temporaryAnswer.displayQueue());
							pseudoArray.setText(temporaryAnswer.displayPseudoArray());
							linkedList.setText(temporaryAnswer.displayLinkedList());
							Thread.sleep(500);
						
							double value1 = temporaryAnswer.pop();
						
							stackT.setText(temporaryAnswer.displayContents());
							queue.setText(temporaryAnswer.displayQueue());
							pseudoArray.setText(temporaryAnswer.displayPseudoArray());
							linkedList.setText(temporaryAnswer.displayLinkedList());
							Thread.sleep(500);
				
							if(top.equals("+")) {
					
								temporaryAnswer.push(value1 + value2);
								
								stackT.setText(temporaryAnswer.displayContents());
								queue.setText(temporaryAnswer.displayQueue());
								pseudoArray.setText(temporaryAnswer.displayPseudoArray());
								linkedList.setText(temporaryAnswer.displayLinkedList());
								Thread.sleep(500);
					
							}
							else if(top.equals("-")) {
					
								temporaryAnswer.push(value1 - value2);
								
								stackT.setText(temporaryAnswer.displayContents());
								queue.setText(temporaryAnswer.displayQueue());
								pseudoArray.setText(temporaryAnswer.displayPseudoArray());
								linkedList.setText(temporaryAnswer.displayLinkedList());
								Thread.sleep(500);
					
							}
							else if(top.equals("*")) {
					
								temporaryAnswer.push(value1 * value2);
								
								stackT.setText(temporaryAnswer.displayContents());
								queue.setText(temporaryAnswer.displayQueue());
								pseudoArray.setText(temporaryAnswer.displayPseudoArray());
								linkedList.setText(temporaryAnswer.displayLinkedList());
								Thread.sleep(500);
					
							}
							else {
					
								temporaryAnswer.push(value1 / value2);
								
								stackT.setText(temporaryAnswer.displayContents());
								queue.setText(temporaryAnswer.displayQueue());
								pseudoArray.setText(temporaryAnswer.displayPseudoArray());
								linkedList.setText(temporaryAnswer.displayLinkedList());
								Thread.sleep(500);
					
							}
						} catch(InterruptedException ex) {
							ex.printStackTrace();
						}
				
					} catch(InterruptedException e) {
						e.printStackTrace();
					}
			
				}
		
				
				try {
					parseTF.setText("END");
					Thread.sleep(500);
				
					answer = String.valueOf(temporaryAnswer.pop());
				
					stackT.setText(temporaryAnswer.displayContents());
					queue.setText(temporaryAnswer.displayQueue());
					pseudoArray.setText(temporaryAnswer.displayPseudoArray());
					linkedList.setText(temporaryAnswer.displayLinkedList());
					Thread.sleep(500);
				} catch(InterruptedException e) {
					e.printStackTrace();
				}
				
				
				if(answer.equals("Infinity") || answer.equals("NaN")) {
					answer = "Math Error";
				}
				
				outputTF.setText(answer);
				
				proceed = true;
				inputTF.setEditable(true);
				inputTF.setFocusable(true);
				
				header.setText("Data Structures");
			}
		};
		thread.start();
	}
}