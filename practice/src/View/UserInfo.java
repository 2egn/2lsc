package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import core.RegisterController;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.Icon;
import java.awt.Window.Type;
import javax.swing.JButton;

public class UserInfo extends JFrame {

	private JPanel contentPane;


	/**
	 * Create the frame.
	 */
	String filepath = System.getProperty("user.dir");
	private JTextField namebox;
	private JTextField textField;
	private JTextField textField_1;
	public JTextField pwbox;
	public UserInfo(MainPage parentform) {
		UserInfo currentform = this;
		RegisterController.Init();
		setTitle("\uD68C\uC6D0\uC815\uBCF4");
		setIconImage(Toolkit.getDefaultToolkit().getImage(UserInfo.class.getResource("/datafiles/\uB9C8\uC774\uD06C.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 327);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		ImageIcon mainimg = new ImageIcon(filepath+"/src/datafiles/메인.png");
		JLabel lblNewLabel = new JLabel(imagesizeset(mainimg, 96, 47));
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				parentform.setVisible(true);
				dispose();
			}
		});
		lblNewLabel.setBounds(338, 0, 96, 47);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\uD68C\uC6D0\uC815\uBCF4");
		lblNewLabel_1.setFont(new Font("굴림", Font.PLAIN, 25));
		lblNewLabel_1.setBounds(163, 10, 118, 47);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("\uC774\uB984");
		lblNewLabel_2.setBounds(45, 76, 57, 15);
		contentPane.add(lblNewLabel_2);
		
		namebox = new JTextField();
		namebox.setBounds(108, 67, 220, 32);
		contentPane.add(namebox);
		namebox.setColumns(10);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setColumns(10);
		textField.setBounds(108, 109, 220, 32);
		contentPane.add(textField);
		
		JLabel lblNewLabel_2_1 = new JLabel("\uC0DD\uB144\uC6D4\uC77C");
		lblNewLabel_2_1.setBounds(45, 118, 57, 15);
		contentPane.add(lblNewLabel_2_1);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setBounds(108, 149, 220, 32);
		contentPane.add(textField_1);
		
		JLabel lblNewLabel_2_2 = new JLabel("ID");
		lblNewLabel_2_2.setBounds(45, 158, 57, 15);
		contentPane.add(lblNewLabel_2_2);
		
		pwbox = new JTextField();
		pwbox.setEditable(false);
		pwbox.setColumns(10);
		pwbox.setBounds(108, 193, 220, 32);
		contentPane.add(pwbox);
		
		JLabel lblNewLabel_2_3 = new JLabel("PW");
		lblNewLabel_2_3.setBounds(45, 202, 57, 15);
		contentPane.add(lblNewLabel_2_3);
		
		ImageIcon calenderimg = new ImageIcon(filepath+"/src/datafiles/달력.png");
		JLabel lblNewLabel_3 = new JLabel(imagesizeset(calenderimg, 34, 34));
		lblNewLabel_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new SelectDate(parentform).setVisible(true);
				setVisible(false);
			}
		});
		lblNewLabel_3.setBounds(343, 109, 34, 34);
		contentPane.add(lblNewLabel_3);
		
		ImageIcon pwimg = new ImageIcon(filepath+"/src/datafiles/마이크.png");
		JLabel pwlabel = new JLabel(imagesizeset(pwimg, 34, 34));
		pwlabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new SelectDate(parentform).setVisible(true);
				setVisible(false);
			}
		});
		pwlabel.setBounds(343, 191, 34, 34);
		contentPane.add(pwlabel);
		
		JButton regibtn = new JButton("\uC815\uBCF4\uC218\uC815");
		regibtn.setBounds(149, 235, 146, 38);
		contentPane.add(regibtn);
	}
	private ImageIcon imagesizeset(ImageIcon icon, int i, int j) {
		Image img = icon.getImage();
		Image updatedimg = img.getScaledInstance(i, j, Image.SCALE_SMOOTH);
		ImageIcon finalimg = new ImageIcon(updatedimg);
		return finalimg;
	}
}
