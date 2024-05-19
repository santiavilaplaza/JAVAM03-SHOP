package view;

import model.Employee; 
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

import utils.Constants;
import exception.LimitLoginException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import exception.LimitLoginException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JFormattedTextField;
import java.awt.Color;

public class LoginView extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JFormattedTextField textNumEmployee;
	private JPasswordField textPassword;
	private JButton buttonAcceder;
	private int numErrores;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginView frame = new LoginView();
					frame.setTitle("Login");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNumEmployee = new JLabel("Numero de empleado");
		lblNumEmployee.setBounds(39, 43, 159, 14);
		contentPane.add(lblNumEmployee);
		
		textNumEmployee = new JFormattedTextField();
		textNumEmployee.setBounds(49, 64, 96, 20);
		contentPane.add(textNumEmployee);
		textNumEmployee.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(39, 105, 106, 14);
		contentPane.add(lblPassword);
		
		textPassword = new JPasswordField();
		textPassword.setBounds(52, 121, 96, 20);
		contentPane.add(textPassword);
		textPassword.setColumns(10);
		
		buttonAcceder = new JButton("Acceder");
		buttonAcceder.setForeground(new Color(255, 255, 255));
		buttonAcceder.setBackground(new Color(0, 128, 128));
		buttonAcceder.addActionListener(this);
		buttonAcceder.setBounds(301, 215, 89, 23);
		contentPane.add(buttonAcceder);
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == buttonAcceder) {
			String numEmployeeStr = textNumEmployee.getText();
			String password = textPassword.getText();
			
			int numEmployee;
	        try {
	            numEmployee = Integer.parseInt(numEmployeeStr);
	        } catch (NumberFormatException ex) {
	        	JOptionPane.showMessageDialog(this, "Formato del número de empleado inválido", "Error", JOptionPane.ERROR_MESSAGE);
	        	return; 
	        }
			
			Employee employee = new Employee(1, numEmployee, password);
			boolean loginSuccesful = employee.login(numEmployee, password);
			
			
			if (loginSuccesful) {
			    ShopView shopView = new ShopView();

				shopView.setVisible(true);
			    
			    dispose();
			} else {
				JOptionPane.showMessageDialog(this, "Usario o password incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
	        	numErrores ++;
	        	System.out.println(numErrores);
	        	if (numErrores >= Constants.MAX_LOGIN_TIMES) {
	        		try {
	        			throw new LimitLoginException(numErrores);
	        		} catch (LimitLoginException ex){
	    				JOptionPane.showMessageDialog(this, ex.toString(), "Error", JOptionPane.ERROR_MESSAGE);
	    				dispose();
	        		}
	        	}
			}
		}
	}
		
}
