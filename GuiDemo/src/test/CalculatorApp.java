package test;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CalculatorApp {

	private JFrame frmCalculator;
	private JTextField textFieldNum1;
	private JTextField textFieldNum2;
	private JTextField textFieldAns;
	private JLabel lblMessage;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CalculatorApp window = new CalculatorApp();
					window.frmCalculator.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CalculatorApp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCalculator = new JFrame();
		frmCalculator.setTitle("Calculator");
		frmCalculator.setBounds(100, 100, 450, 300);
		frmCalculator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCalculator.getContentPane().setLayout(null);
		
		textFieldNum1 = new JTextField();
		textFieldNum1.setBounds(23, 24, 86, 20);
		frmCalculator.getContentPane().add(textFieldNum1);
		textFieldNum1.setColumns(10);
		
		textFieldNum2 = new JTextField();
		textFieldNum2.setBounds(161, 24, 86, 20);
		frmCalculator.getContentPane().add(textFieldNum2);
		textFieldNum2.setColumns(10);
		
		JButton btnNewButton = new JButton("Add");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int num1,num2,ans;
				try{
					// read user input
					num1=Integer.parseInt(textFieldNum1.getText());
					num2=Integer.parseInt(textFieldNum2.getText());
					
					// perform addition
					ans = num1+num2;
					textFieldAns.setText(Integer.toString(ans));
				}catch (Exception eadd){
					//JOptionPane.showMessageDialog(null, "Please enter valid numbers");
					lblMessage.setText("Please enter valid numbers");
				}
			}
		});
		btnNewButton.setBounds(20, 83, 89, 23);
		frmCalculator.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Subtract");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int num1,num2,ans;
				try{
					// read user input
					num1=Integer.parseInt(textFieldNum1.getText());
					num2=Integer.parseInt(textFieldNum2.getText());
					
					// perform subtraction
					ans = num1-num2;
					textFieldAns.setText(Integer.toString(ans));
				}catch (Exception esubtract){
					//JOptionPane.showMessageDialog(null, "Please enter valid numbers");
					lblMessage.setText("Please enter valid numbers");
				}

			}
		});
		btnNewButton_1.setBounds(158, 83, 89, 23);
		frmCalculator.getContentPane().add(btnNewButton_1);
		
		textFieldAns = new JTextField();
		textFieldAns.setBounds(139, 199, 86, 20);
		frmCalculator.getContentPane().add(textFieldAns);
		textFieldAns.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Answer");
		lblNewLabel.setBounds(59, 202, 46, 14);
		frmCalculator.getContentPane().add(lblNewLabel);
		
		lblMessage = new JLabel("New label");
		lblMessage.setBounds(43, 138, 204, 50);
		frmCalculator.getContentPane().add(lblMessage);
	}

}
