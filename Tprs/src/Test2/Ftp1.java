package Test2;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JPasswordField;

public class Ftp1 {

	private JFrame frame;
	private JTextField textField;

	private String psw;
	private String user;
	private JPasswordField passwordField;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ftp1 window = new Ftp1();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Ftp1() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 621, 306);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("User Name ");
		lblNewLabel.setFont(new Font("Bradley Hand ITC", Font.BOLD, 22));
		lblNewLabel.setBounds(29, 84, 131, 23);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setFont(new Font("Bradley Hand ITC", Font.BOLD, 22));
		lblNewLabel_1.setBounds(29, 144, 95, 23);
		frame.getContentPane().add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(170, 89, 203, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(170, 146, 203, 20);
		frame.getContentPane().add(passwordField);
		
		JLabel lblNewLabel_2 = new JLabel("FILE TRANSFER APPLICATION");
		lblNewLabel_2.setFont(new Font("Bradley Hand ITC", Font.BOLD, 29));
		lblNewLabel_2.setForeground(new Color(0, 0, 0));
		lblNewLabel_2.setBounds(69, 23, 460, 50);
		frame.getContentPane().add(lblNewLabel_2);
		
		
		JButton btnNewButton = new JButton("Connect");
		btnNewButton.setFont(new Font("Forte", Font.PLAIN, 21));
		btnNewButton.setForeground(new Color(25, 25, 112));
		btnNewButton.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				
				user=textField.getText();
				psw=passwordField.getText();
				
				
				
		
					
					Ftp2 ftp2;
					try {
						ftp2 = new Ftp2(psw , user);
						ftp2.setVisible(true);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				
			}

			
		});
		
		btnNewButton.setBounds(170, 206, 203, 33);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel_3 = new JLabel("Assala");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Blackadder ITC", Font.PLAIN, 16));
		lblNewLabel_3.setBounds(535, 217, 72, 52);
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("New label");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setIcon(new ImageIcon("C:\\Users\\LENOVO YOGA 700\\Desktop\\Captureftp.PNG"));
		lblNewLabel_4.setBounds(393, 84, 234, 146);
		frame.getContentPane().add(lblNewLabel_4);
		
		
	}
	
	public void show(String response)
	{
		JFrame frame = new JFrame("JOptionPane showMessageDialog example");
	    
	    // show a joptionpane dialog using showMessageDialog
	    JOptionPane.showMessageDialog(frame,"" + response + "'.");
	    
	}
}
