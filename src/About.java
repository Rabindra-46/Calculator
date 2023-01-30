
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.UIManager;
/**
 * Pattern Find Assigment
 * @author Rabindra Jaiswal
 * Version 1.0
 */
public class About extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel CPane;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					About frame = new About();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create about frame.
	 */
	public About() {
		setTitle("About");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(220, 110, 390, 300); 
		CPane = new JPanel();
		CPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		CPane.setLayout(new BorderLayout(0, 0));
		setContentPane(CPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.red);
		CPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setBackground(UIManager.getColor("RadioButtonMenuItem.selectionBackground"));
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnExit.setBounds(250, 190, 100, 30);
		panel.add(btnExit);
		
		JLabel lblNewLabel_0 = new JLabel("<html>This Program Compares pattern of a different files/directory"
				+ " as the requirement of the Assigment</html>");
		lblNewLabel_0.setForeground(Color.black);
		lblNewLabel_0.setBounds(10, 10, 300, 260);
		panel.add(lblNewLabel_0);
		
		JLabel lblNewLabel_1 = new JLabel("Author Name : Rabindra Jaiswal");
		lblNewLabel_1.setForeground(Color.black);
		lblNewLabel_1.setBounds(10, 185, 240, 20);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("@ Copyright 2020");
		lblNewLabel_2.setForeground(Color.black);
		lblNewLabel_2.setBounds(10, 195, 210, 30);
		panel.add(lblNewLabel_2);
	}
}
