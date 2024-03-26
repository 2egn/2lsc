package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import core.Logincontroller;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	public JTextField pwbox;


		/**
	 * Create the frame.
	 */
	public Login(MainPage parentform) {
		setTitle("\uB85C\uADF8\uC778");
		Logincontroller.Init();
		Login currentform = this;
		Logincontroller.login = currentform;
		String filepath = System.getProperty("user.dir");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\uB85C\uADF8\uC778");
		lblNewLabel.setFont(new Font("±º∏≤", Font.PLAIN, 23));
		lblNewLabel.setBounds(176, 10, 79, 47);
		contentPane.add(lblNewLabel);	
		
		JLabel lblId = new JLabel("ID");
		lblId.setFont(new Font("±º∏≤", Font.PLAIN, 23));
		lblId.setBounds(51, 72, 38, 47);
		contentPane.add(lblId);
		
		JLabel lblPw = new JLabel("PW");
		lblPw.setFont(new Font("±º∏≤", Font.PLAIN, 23));
		lblPw.setBounds(51, 129, 38, 47);
		contentPane.add(lblPw);
		
		textField = new JTextField();
		textField.setBounds(124, 78, 229, 42);
		contentPane.add(textField);
		textField.setColumns(10);
		
		pwbox = new JTextField();
		pwbox.setText("38,71,82,86");
		pwbox.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new favsong(currentform).setVisible(true);
				setVisible(false);
			}
		});
		pwbox.setEditable(false);
		pwbox.setColumns(10);
		pwbox.setBounds(124, 129, 229, 42);
		contentPane.add(pwbox);
		
		JButton btnNewButton = new JButton("\uB85C\uADF8\uC778");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Logincontroller.id = textField.getText();
				Logincontroller.pw = pwbox.getText();
				try {
					Logincontroller.TryLogin();
				}catch (Exception ex) {
					ex.printStackTrace();
				}
				
			}
		});
		btnNewButton.setBounds(124, 182, 229, 47);
		contentPane.add(btnNewButton);
		
		ImageIcon homeimage = new ImageIcon(filepath+"/src/datafiles/∏ﬁ¿Œ.png");	
		JLabel homelabel = new JLabel(imagesizeset(homeimage, 108, 43));
		homelabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				parentform.setVisible(true);
				dispose();
			}
		});
		homelabel.setBounds(295, 10, 127, 47);
		contentPane.add(homelabel);
	}
	private ImageIcon imagesizeset(ImageIcon icon, int i, int j) {
		Image img = icon.getImage();
		Image updatedimg = img.getScaledInstance(i, j, Image.SCALE_SMOOTH);
		ImageIcon finalimg = new ImageIcon(updatedimg);
		return finalimg;
	}
}
