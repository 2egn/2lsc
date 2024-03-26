	package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

public class SelectDate extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	String filepath = System.getProperty("user.dir");
	public SelectDate(JFrame parentform) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(SelectDate.class.getResource("/datafiles/\uB9C8\uC774\uD06C.png")));
		setTitle("\uB0A0\uC9DC \uC120\uD0DD");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 503, 421);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\uB0A0\uC9DC \uC120\uD0DD");
		lblNewLabel.setFont(new Font("±º∏≤", Font.PLAIN, 28));
		lblNewLabel.setBounds(181, 10, 127, 33);
		contentPane.add(lblNewLabel);
		
		ImageIcon homeimage = new ImageIcon(filepath+"/src/datafiles/∏ﬁ¿Œ.png");
		JLabel homelabel = new JLabel("");
		homelabel.setBounds(379, 0, 96, 47);
		contentPane.add(homelabel);
	}
	private ImageIcon imagesizeset(ImageIcon icon, int i, int j) {
		Image img = icon.getImage();
		Image updatedimg = img.getScaledInstance(i, j, Image.SCALE_SMOOTH);
		ImageIcon finalimg = new ImageIcon(updatedimg);
		return finalimg;
	}
}
