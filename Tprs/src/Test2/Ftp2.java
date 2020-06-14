package Test2;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSeparator;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.TitledBorder;



import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import javax.swing.border.EtchedBorder;
import javax.swing.JList;
import javax.swing.AbstractListModel;

public class Ftp2 extends JFrame {

	
	private static String psw;
	private static String user;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	public void show(String response)
	{
		JFrame frame = new JFrame("FTP msg");
	    
	    // show a joptionpane dialog using showMessageDialog
	    JOptionPane.showMessageDialog(frame,
	        "::" + response + " ");
	   
	}
	public void show2(String response)
	{
		JFrame frame = new JFrame("FTP msg");
	    
	    // show a joptionpane dialog using showMessageDialog
	    JOptionPane.showMessageDialog(frame,
	        "::" + response + " ");
	   
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ftp2 frame = new Ftp2( psw ,  user);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 */
	public Ftp2( String psw , String user) throws IOException {
		
		this.psw=psw;
		this.user=user;
		
		Ftp ftp = new Ftp(user,psw);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 776, 575);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 250, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		ftp.connect();
		
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setBounds(552, 87, -6, 286);
		contentPane.add(splitPane);
		
		JButton btnNewButton = new JButton("QUIT");
		btnNewButton.setForeground(new Color(25, 25, 112));
		btnNewButton.setBackground(Color.PINK);
		btnNewButton.setFont(new Font("Forte", Font.PLAIN, 16));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				show2("connection closed");
				ftp.quit();
				
			}
		});
		btnNewButton.setBounds(530, 491, 217, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("PWD");
		btnNewButton_1.setForeground(new Color(25, 25, 112));
		btnNewButton_1.setBackground(Color.PINK);
		btnNewButton_1.setFont(new Font("Forte", Font.PLAIN, 16));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String response;
					response=ftp.pwd();
					show(response);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_1.setBounds(10, 327, 213, 23);
		contentPane.add(btnNewButton_1);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(10, 197, 213, 20);
		contentPane.add(textField_3);
		
		JButton btnNewButton_2 = new JButton("CWD");
		btnNewButton_2.setBackground(Color.PINK);
		btnNewButton_2.setForeground(new Color(25, 25, 112));
		btnNewButton_2.setFont(new Font("Forte", Font.PLAIN, 16));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String txt= textField_3.getText();
				String r;
				try {
					r=ftp.cwd(txt);
					show(r);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_2.setBounds(6, 234, 217, 23);
		contentPane.add(btnNewButton_2);
		
		
		
		
		JButton btnNewButton_3 = new JButton("LIST");
		btnNewButton_3.setForeground(new Color(25, 25, 112));
		btnNewButton_3.setBackground(Color.PINK);
		btnNewButton_3.setFont(new Font("Forte", Font.PLAIN, 16));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String l;
					l=ftp.list();
					show(l);
						
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		btnNewButton_3.setBounds(530, 327, 217, 23);
		contentPane.add(btnNewButton_3);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "JPanel title", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(509, 129, 13, 397);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		
		
		JSeparator separator = new JSeparator();
		separator.setBounds(6, 16, 1, 261);
		panel_1.add(separator);
		
		textField = new JTextField();
		textField.setBounds(279, 284, 202, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton_4 = new JButton("download");
		btnNewButton_4.setForeground(new Color(25, 25, 112));
		btnNewButton_4.setBackground(Color.PINK);
		btnNewButton_4.setFont(new Font("Forte", Font.PLAIN, 16));
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					String file=textField.getText();
					try {
						
						ftp.download(file);
						
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				
			}
		});
		btnNewButton_4.setBounds(279, 327, 202, 23);
		contentPane.add(btnNewButton_4);
		
		textField_1 = new JTextField();
		textField_1.setBounds(279, 451, 202, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton_5 = new JButton("Upload");
		btnNewButton_5.setForeground(new Color(25, 25, 112));
		btnNewButton_5.setBackground(Color.PINK);
		btnNewButton_5.setFont(new Font("Forte", Font.PLAIN, 16));
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String file=textField_1.getText();
				try {
					
				ftp.upload(file);
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
			}
		});
		btnNewButton_5.setBounds(279, 491, 202, 23);
		contentPane.add(btnNewButton_5);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "JPanel title", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(245, 129, 14, 397);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 11, -6, 281);
		panel.add(separator_1);
		
		JLabel lblNewLabel_2 = new JLabel("YOUR FILE TRANSFER APPLICATION");
		lblNewLabel_2.setBackground(new Color(240, 255, 255));
		lblNewLabel_2.setFont(new Font("Bradley Hand ITC", Font.BOLD, 28));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(60, 11, 646, 65);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Change your working directory");
		lblNewLabel_3.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Bradley Hand ITC", Font.BOLD, 18));
		lblNewLabel_3.setBounds(6, 87, 279, 36);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("write down the name of \r\nthe  ");
		lblNewLabel_4.setFont(new Font("Bradley Hand ITC", Font.BOLD, 16));
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setToolTipText("write down the name of \r\nthe directory you want to go to");
		lblNewLabel_4.setBounds(6, 125, 229, 29);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblDirectoryYouWant = new JLabel("directory you want to go to");
		lblDirectoryYouWant.setToolTipText("write down the name of \r\nthe directory you want to go to");
		lblDirectoryYouWant.setHorizontalAlignment(SwingConstants.CENTER);
		lblDirectoryYouWant.setFont(new Font("Bradley Hand ITC", Font.BOLD, 16));
		lblDirectoryYouWant.setBounds(6, 150, 229, 36);
		contentPane.add(lblDirectoryYouWant);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "pwd", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_2.setBounds(0, 262, 253, 14);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(6, 16, 241, 2);
		panel_2.add(separator_2);
		
		JLabel lblShowTheCurrent = new JLabel("Show the current directory");
		lblShowTheCurrent.setVerticalAlignment(SwingConstants.TOP);
		lblShowTheCurrent.setHorizontalAlignment(SwingConstants.CENTER);
		lblShowTheCurrent.setFont(new Font("Bradley Hand ITC", Font.BOLD, 18));
		lblShowTheCurrent.setBounds(0, 282, 254, 29);
		contentPane.add(lblShowTheCurrent);
		
		JLabel lblViewTheList = new JLabel("View the list of files");
		lblViewTheList.setVerticalAlignment(SwingConstants.TOP);
		lblViewTheList.setHorizontalAlignment(SwingConstants.CENTER);
		lblViewTheList.setFont(new Font("Bradley Hand ITC", Font.BOLD, 18));
		lblViewTheList.setBounds(518, 262, 229, 25);
		contentPane.add(lblViewTheList);
		
		JLabel lblDeconnectFromThe = new JLabel("Deconnect from the current ");
		lblDeconnectFromThe.setHorizontalAlignment(SwingConstants.CENTER);
		lblDeconnectFromThe.setFont(new Font("Bradley Hand ITC", Font.BOLD, 18));
		lblDeconnectFromThe.setBounds(530, 419, 232, 25);
		contentPane.add(lblDeconnectFromThe);
		
		JLabel lblDownloadAFile = new JLabel("Download a file");
		lblDownloadAFile.setVerticalAlignment(SwingConstants.TOP);
		lblDownloadAFile.setHorizontalAlignment(SwingConstants.CENTER);
		lblDownloadAFile.setFont(new Font("Bradley Hand ITC", Font.BOLD, 18));
		lblDownloadAFile.setBounds(269, 248, 231, 25);
		contentPane.add(lblDownloadAFile);
		
		JLabel lblUploadAFile = new JLabel("Upload a file");
		lblUploadAFile.setVerticalAlignment(SwingConstants.TOP);
		lblUploadAFile.setHorizontalAlignment(SwingConstants.CENTER);
		lblUploadAFile.setFont(new Font("Bradley Hand ITC", Font.BOLD, 18));
		lblUploadAFile.setBounds(269, 411, 231, 25);
		contentPane.add(lblUploadAFile);
		
		JLabel lblSession = new JLabel("session");
		lblSession.setVerticalAlignment(SwingConstants.TOP);
		lblSession.setHorizontalAlignment(SwingConstants.CENTER);
		lblSession.setFont(new Font("Bradley Hand ITC", Font.BOLD, 18));
		lblSession.setBounds(531, 455, 231, 25);
		contentPane.add(lblSession);
		
		JPanel upload = new JPanel();
		upload.setLayout(null);
		upload.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "upload", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		upload.setBounds(258, 372, 253, 14);
		contentPane.add(upload);
		
		JSeparator separator_4 = new JSeparator();
		separator_4.setBounds(6, 16, 241, 2);
		upload.add(separator_4);
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "deconnect", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_3.setBounds(524, 372, 238, 14);
		contentPane.add(panel_3);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(6, 16, 241, 2);
		panel_3.add(separator_3);
		
		JPanel panel_5 = new JPanel();
		panel_5.setLayout(null);
		panel_5.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Delete", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_5.setBounds(0, 372, 253, 14);
		contentPane.add(panel_5);
		
		JSeparator separator_5 = new JSeparator();
		separator_5.setBounds(6, 16, 241, 2);
		panel_5.add(separator_5);
		
		JLabel lblDeleteAFile = new JLabel("Delete a file");
		lblDeleteAFile.setVerticalAlignment(SwingConstants.TOP);
		lblDeleteAFile.setHorizontalAlignment(SwingConstants.CENTER);
		lblDeleteAFile.setFont(new Font("Bradley Hand ITC", Font.BOLD, 18));
		lblDeleteAFile.setBounds(-1, 411, 254, 29);
		contentPane.add(lblDeleteAFile);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(10, 451, 213, 20);
		contentPane.add(textField_2);
		
		JButton btnDelete = new JButton("DELETE");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String file=textField_2.getText();
				String res;
				try {
					
					res=ftp.deleteFile(file);
					
					show(res);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		
		});
		btnDelete.setForeground(new Color(25, 25, 112));
		btnDelete.setFont(new Font("Forte", Font.PLAIN, 16));
		btnDelete.setBackground(Color.PINK);
		btnDelete.setBounds(10, 491, 213, 23);
		contentPane.add(btnDelete);
		
		JPanel panel_4 = new JPanel();
		panel_4.setLayout(null);
		panel_4.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "  list", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_4.setBounds(519, 219, 253, 14);
		contentPane.add(panel_4);
		
		JSeparator separator_6 = new JSeparator();
		separator_6.setBounds(6, 16, 241, 2);
		panel_4.add(separator_6);
		
		JPanel panel_6 = new JPanel();
		panel_6.setLayout(null);
		panel_6.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "download", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_6.setBounds(258, 219, 253, 14);
		contentPane.add(panel_6);
		
		JSeparator separator_7 = new JSeparator();
		separator_7.setBounds(6, 16, 241, 2);
		panel_6.add(separator_7);
		
		JLabel lblAsciiMode = new JLabel("ASCII mode");
		lblAsciiMode.setVerticalAlignment(SwingConstants.TOP);
		lblAsciiMode.setHorizontalAlignment(SwingConstants.CENTER);
		lblAsciiMode.setFont(new Font("Bradley Hand ITC", Font.BOLD, 18));
		lblAsciiMode.setBounds(531, 150, 231, 25);
		contentPane.add(lblAsciiMode);
		
		JButton btnTypeAscii = new JButton("TYPE ASCII");
		btnTypeAscii.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String resp;
				try {
					resp=ftp.modeASCII();
					show(resp);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnTypeAscii.setForeground(new Color(25, 25, 112));
		btnTypeAscii.setFont(new Font("Forte", Font.PLAIN, 16));
		btnTypeAscii.setBackground(Color.PINK);
		btnTypeAscii.setBounds(530, 185, 217, 23);
		contentPane.add(btnTypeAscii);
		
		JLabel lblPassiveMode = new JLabel("Passive mode");
		lblPassiveMode.setVerticalAlignment(SwingConstants.TOP);
		lblPassiveMode.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassiveMode.setFont(new Font("Bradley Hand ITC", Font.BOLD, 18));
		lblPassiveMode.setBounds(279, 150, 231, 25);
		contentPane.add(lblPassiveMode);
		
		JButton btnPasv = new JButton("PASV");
		btnPasv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String p;
				try {
					ftp.enterPassiveMode();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnPasv.setForeground(new Color(25, 25, 112));
		btnPasv.setFont(new Font("Forte", Font.PLAIN, 16));
		btnPasv.setBackground(Color.PINK);
		btnPasv.setBounds(282, 185, 217, 23);
		contentPane.add(btnPasv);
		
		
		
		
		
		
		
		
		
	}
}
