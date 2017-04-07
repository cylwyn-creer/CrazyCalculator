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
	
	private int numOperand = 0;
	private int numOperator = 0;
	private int numDecimal = 0;
	private int numClosePar = 0;
	private int numOpenPar = 0;
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
		
		inputSize = inputTF.getText().length();
		
		if(proceed) {
			
			if(button == "Del") {
				
				if(inputSize != 0) {
		
					inputTF.setText(inputTF.getText().substring(0, inputSize - 1));
					
				}
				
			} else 
			if(button == "AC") {
				
				inputTF.setText("");
				outputTF.setText("0");
				
			} else
			if(button == "=") {
				
				equalsEvent();
				
			} else {
				if(inputSize < 100) {
					
					inputTF.setText(inputTF.getText() + button);
					
				}
			}
			
		}
		
	}
	
	public void keyTyped(KeyEvent event) {
		
		char character = event.getKeyChar();
		
		
		if(proceed) {
			
			if(!(Character.isDigit(character) || character == '.' || character == '(' || character == ')' ||
					character == '+' || character == '-' || character == '/' || character == '*' ||
							character == '=' || event.getKeyCode() == KeyEvent.VK_BACK_SPACE)) {	
				
				event.consume();
				
			} else
			if(event.getKeyChar() == '=') {
				
				equalsEvent();
				
				event.consume();
				
			} else {
				
				if(inputTF.getText().length() >= 100) {
					
					event.consume();
					
				} 
				
			}
			
		} else {
			
			event.consume();
			
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
		
		inputB[18].setBackground(new Color(150, 150, 255));
		
		if(inputTF.getText().equals("")) {
			
			outputTF.setText("0");
			
		}
		
		if(proceed && !inputTF.getText().equals("")) {
			
			input = inputTF.getText();
			inputSize = input.length();
			
			numOperand = numOperator = numDecimal = numClosePar = numOpenPar = 0;
			
			
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
				inputB[18].setBackground(Color.WHITE);
				
			}
			
		}
		
		
	}
	
	public boolean checkSyntax(String infix) {
		
		boolean isCorrect = false;
		
		String character, prev, next;
		
		infix = addSeparator(infix);
		
		if(infix.charAt(0) == ' ') {
			
			infix = infix.substring(1, infix.length());
			
		}
		
		String[] arrElement = infix.split("\\s+");
		
		for(int i = 0; i < arrElement.length; i++) {
			
			character = arrElement[i];
			
			if(character.equals("+") || character.equals("-") || character.equals("*") || character.equals("/")) {
				
				numOperator++;
				
			} else
			if(character.equals("(")) {
				
				if(i != 0) {
					
					prev = arrElement[i - 1];
					
					if(!(prev.equals("+") || prev.equals("-") || prev.equals("*") || prev.equals("/"))) {
						
						return false;
						
					}
					
				}
				
				if(i + 1 < arrElement.length) {
					
					next = arrElement[i + 1];
					
					if(next.equals("+") || next.equals("-") || next.equals("*") || next.equals("/")) {
						
						return false;
						
					}
					
				}
				
				numOpenPar++;
				
			} else
			if(character.equals(")")) {
				
				if(i != 0) {
					
					prev = arrElement[i - 1];
					
					if(prev.equals("+") || prev.equals("-") || prev.equals("*") || prev.equals("/")) {
						
						return false;
						
					}
					
				}
				
				if(i + 1 < arrElement.length) {
					
					next = arrElement[i + 1];
					
					if(!(next.equals("+") || next.equals("-") || next.equals("*") || next.equals("/"))) {
						
						return false;
						
					}
					
				}

				numClosePar++;
				
			} else {
				
				for(int j = 0; j < character.length(); j++) {
					
					if(character.charAt(j) == '.') {
					
						numDecimal++;
						
						if((j + 1) < character.length()) {
							
							if(!Character.isDigit(character.charAt(j+1))) {
								
								return false;
								
							}
							
						} 
						
						if(j == character.length() - 1) {
							
							return false;
							
						}
						
					}
					
				}
				
				if(numDecimal > 1) {
					
					return false;
					
				} else {
					
					numOperand++;
					numDecimal = 0;
					
				}
					
				
			}
			
		}
		
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
		
		String indent = createIndent(addSeparator(infix));
		int length = indent.length();
		
		System.out.println("Converting to Postfix Notation");
		System.out.println("READ" + indent.substring(0, length-"READ".length()) + "PARSED" + indent.substring(0, length-"PARSED".length()) +
				"WRITTEN" + indent.substring(0, length-"WRITTEN".length()) + "STACK" + indent.substring(0, length-"STACK".length()));
		
		Thread thread = new Thread()
		{
			private String output = "";
			private String readString = "";
			private String parsedString = "";
			private String writtenString = "";
			
			public void run() {
				try {
					for(int a = 0; a < infix.length(); a++) {
						char character = infix.charAt(a);
						if(Character.isDigit(character)) {
							output = output + character;
							readString = readString + character;
						} else {	
							if(character == '.') {
								output = output + character;
								readString = readString + character;
								continue;
							}
							if(character == '(') {
								parseTF.setText("(");
								
								Thread.sleep(500);
								
								stack.push(character);
								parsedString = parsedString + "( ";
								writtenString = output.trim();
								System.out.println("(" + indent.substring(0, length - 1) + parsedString + indent.substring(0, length - parsedString.length()) +
										writtenString + indent.substring(0, length - writtenString.length()) + stack.getStackString() + indent.substring(0, length - stack.getStackString().length()));
								
								stackT.setText(stack.displayContents());
								queue.setText(stack.displayQueue());
								pseudoArray.setText(stack.displayPseudoArray());
								linkedList.setText(stack.displayLinkedList());
								Thread.sleep(500);
							}
							else if(character == ')') {
								if(!readString.equals("")) {
									parseTF.setText(readString);
									parsedString = parsedString + readString + " ";
									writtenString = output.trim();
									System.out.println(readString + indent.substring(0, length - readString.length()) + parsedString + indent.substring(0, length - parsedString.length()) +
											writtenString + indent.substring(0, length - writtenString.length()) + stack.getStackString() + indent.substring(0, length - stack.getStackString().length()));
									readString = "";
								}
								
								postfixTF.setText(output);
								Thread.sleep(500);
								
								parseTF.setText(")");
								
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
								
								parsedString = parsedString + ") ";
								writtenString = output.trim();
								System.out.println(")" + indent.substring(0, length - 1) + parsedString + indent.substring(0, length - parsedString.length()) +
										writtenString + indent.substring(0, length - writtenString.length()) + stack.getStackString() + indent.substring(0, length - stack.getStackString().length()));
								Thread.sleep(500);
							} else {
								if(!readString.equals("")) {
									parseTF.setText(readString);
									parsedString = parsedString + readString + " ";
									writtenString = output.trim();
									System.out.println(readString + indent.substring(0, length - readString.length()) + parsedString + indent.substring(0, length - parsedString.length()) +
											writtenString + indent.substring(0, length - writtenString.length()) + stack.getStackString() + indent.substring(0, length - stack.getStackString().length()));
									readString = "";
								}
								output = output + ' ';
								postfixTF.setText(output);
								Thread.sleep(500);
								
								parseTF.setText(String.valueOf(character));
								parsedString = parsedString + character + " ";
								writtenString = output.trim();
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
								System.out.println(String.valueOf(character) + indent.substring(0, length - 1) + parsedString + indent.substring(0, length - parsedString.length()) +
										writtenString + indent.substring(0, length - writtenString.length()) + stack.getStackString() + indent.substring(0, length - stack.getStackString().length()));
								
								stackT.setText(stack.displayContents());
								queue.setText(stack.displayQueue());
								pseudoArray.setText(stack.displayPseudoArray());
								linkedList.setText(stack.displayLinkedList());
								Thread.sleep(500);
							}
						}
					}
					
					if(!readString.equals("")) {
						parseTF.setText(readString);
						parsedString = parsedString + readString + " ";
						writtenString = output.trim();
						System.out.println(readString + indent.substring(0, length - readString.length()) + parsedString + indent.substring(0, length - parsedString.length()) +
								writtenString + indent.substring(0, length - writtenString.length()) + stack.getStackString() + indent.substring(0, length - stack.getStackString().length()));
					}
						
					postfixTF.setText(output);
					Thread.sleep(500);
					
					parseTF.setText("END");
					writtenString = output.trim();
					System.out.println("END" + indent.substring(0, length - "END".length()) + parsedString + indent.substring(0, length - parsedString.length()) +
							writtenString + indent.substring(0, length - writtenString.length()) + stack.getStackString() + indent.substring(0, length - stack.getStackString().length()));
					
					while(!stack.isEmpty()) {
						output = output + ' ' + stack.pop();
						
						writtenString = output.trim();
						System.out.println("" + indent.substring(0, length - "".length()) + parsedString + indent.substring(0, length - parsedString.length()) +
								writtenString + indent.substring(0, length - writtenString.length()) + stack.getStackString() + indent.substring(0, length - stack.getStackString().length()));
						
						postfixTF.setText(output);
						Thread.sleep(500);
						
						stackT.setText(stack.displayContents());
						queue.setText(stack.displayQueue());
						pseudoArray.setText(stack.displayPseudoArray());
						linkedList.setText(stack.displayLinkedList());
						Thread.sleep(500);
					}
					
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
		
		String indent = createIndent(postfix);
		int length = indent.length();
		
		System.out.println("Evaluating Postfix Notation");
		System.out.println("READ" + indent.substring(0, length-"READ".length()) + "PARSED" + indent.substring(0, length-"PARSED".length()) +
				  "STACK" + indent.substring(0, length-"STACK".length()));
		
		
		Thread thread = new Thread() {
			String answer = "";
			String[] terms = postfix.split(" ");
			String parsed = "";
			
			Stack<String> expression = new Stack<String>(terms.length);
			Stack<Double> temporaryAnswer = new Stack<Double>(terms.length);
			
			public void run() {
				for(int a = terms.length - 1; a >= 0; a--) {
			
					expression.push(terms[a]);
			
				}
		
				while(!expression.isEmpty()) {
			
					String top = expression.pop();
					
					parsed = parsed + top + " ";
					
					System.out.print(top + indent.substring(0, length - top.length()) + parsed + indent.substring(0, length - parsed.length()));
			
					try {
						
						parseTF.setText(top);
						Thread.sleep(500);
						
						temporaryAnswer.push(Double.parseDouble(top));
						
						System.out.println(temporaryAnswer.displayQueue());
						
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
								
								System.out.println(temporaryAnswer.displayQueue());
								
								stackT.setText(temporaryAnswer.displayContents());
								queue.setText(temporaryAnswer.displayQueue());
								pseudoArray.setText(temporaryAnswer.displayPseudoArray());
								linkedList.setText(temporaryAnswer.displayLinkedList());
								Thread.sleep(500);
					
							}
							else if(top.equals("-")) {
					
								temporaryAnswer.push(value1 - value2);
								
								System.out.println(temporaryAnswer.displayQueue());
								
								stackT.setText(temporaryAnswer.displayContents());
								queue.setText(temporaryAnswer.displayQueue());
								pseudoArray.setText(temporaryAnswer.displayPseudoArray());
								linkedList.setText(temporaryAnswer.displayLinkedList());
								Thread.sleep(500);
					
							}
							else if(top.equals("*")) {
					
								temporaryAnswer.push(value1 * value2);
								
								System.out.println(temporaryAnswer.displayQueue());
								
								stackT.setText(temporaryAnswer.displayContents());
								queue.setText(temporaryAnswer.displayQueue());
								pseudoArray.setText(temporaryAnswer.displayPseudoArray());
								linkedList.setText(temporaryAnswer.displayLinkedList());
								Thread.sleep(500);
					
							}
							else {
					
								temporaryAnswer.push(value1 / value2);
								
								System.out.println(temporaryAnswer.displayQueue());
								
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
				
				if(answer.endsWith(".0")) {
					outputTF.setText(answer.substring(0, answer.length() - 2));
				}
				else {
					outputTF.setText(answer);
				}
				
				proceed = true;
				inputTF.setEditable(true);
				inputTF.setFocusable(true);
				
				header.setText("Data Structures");
			}
		};
		thread.start();
		
		inputB[18].setBackground(Color.WHITE);
				
	}
	
	public String createIndent(String string) {
		
		String spaces = "";
		
		for(int i = 0; i < string.length() + 7 ; i++)
			spaces += " ";
		
		return spaces;
		
	}
}