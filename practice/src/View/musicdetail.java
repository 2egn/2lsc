package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import core.*;
import model.user;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.Window;

import javax.swing.border.LineBorder;


import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Callable;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JProgressBar;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class musicdetail extends JFrame {

	private JPanel contentPane;


	/**
	 * Create the frame.
	 */
	String filepath = System.getProperty("user.dir");
	JProgressBar progressBar = new JProgressBar();
	boolean musicplaying = false;
	public musicdetail(int m_no,MainPage mainform,JFrame parentform) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				parentform.setVisible(true);
			}
		});
		setTitle("\uC74C\uC6D0\uC0C1\uC138\uC815\uBCF4");
		SQLExecutor ex = new SQLExecutor();
		String m_name = "";
		String singer = "";
		String album = "";
		String composer = "";
		String lyricist = "";
		String playtime = "";
		String soundquality = "";
		
		
		try {
			ex.Connect();
			ResultSet rs = ex.ExecuteReadQuery("SELECT m_name, singer, album, composer, lyricist,playtime,soundquality FROM music WHERE m_no="+m_no);
			while(rs.next()) {
				m_name = rs.getString(1);
				singer = rs.getString(2);
				album = rs.getString(3);
				composer = rs.getString(4);
				lyricist = rs.getString(5);
				playtime = rs.getString(6);
				soundquality = rs.getString(7);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 639, 442);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\uC74C\uC6D0\uC0C1\uC138\uC815\uBCF4");
		lblNewLabel.setFont(new Font("±¼¸²", Font.PLAIN, 19));
		lblNewLabel.setBounds(193, 10, 135, 43);
		contentPane.add(lblNewLabel);
		
		JLabel musicnameLabel = new JLabel(m_name);	
		musicnameLabel.setFont(new Font("±¼¸²", Font.PLAIN, 18));
		musicnameLabel.setBounds(12, 51, 497, 43);
		contentPane.add(musicnameLabel);
		
		ImageIcon homeimage = new ImageIcon(filepath+"/src/datafiles/¸ÞÀÎ.png");
		JLabel homeimagelabel = new JLabel(imagesizeset(homeimage, 108, 43));
		homeimagelabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mainform.setVisible(true);
				dispose();
			}
		});
		homeimagelabel.setBounds(503, 51, 108, 43);
		contentPane.add(homeimagelabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(-10, 94, 644, 336);
		contentPane.add(panel);
		panel.setLayout(null);
		
		ImageIcon musicimg = new ImageIcon(filepath+"/src/datafiles/album/"+m_no+".jpg");
		JLabel musicimglabel = new JLabel(imagesizeset(musicimg, 162, 156));
		musicimglabel.setBounds(29, 33, 162, 156);
		panel.add(musicimglabel);
		
		JLabel lblNewLabel_2 = new JLabel("\uC544\uD2F0\uC2A4\uD2B8");
		lblNewLabel_2.setFont(new Font("±¼¸²Ã¼", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(233, 33, 65, 29);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("\uC791\uC0AC");
		lblNewLabel_2_1.setFont(new Font("±¼¸²Ã¼", Font.PLAIN, 14));
		lblNewLabel_2_1.setBounds(233, 59, 65, 29);
		panel.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_2_2 = new JLabel("\uC791\uACE1");
		lblNewLabel_2_2.setFont(new Font("±¼¸²Ã¼", Font.PLAIN, 14));
		lblNewLabel_2_2.setBounds(233, 87, 65, 29);
		panel.add(lblNewLabel_2_2);
		
		JLabel lblNewLabel_2_3 = new JLabel("\uC568\uBC94");
		lblNewLabel_2_3.setFont(new Font("±¼¸²Ã¼", Font.PLAIN, 14));
		lblNewLabel_2_3.setBounds(233, 114, 65, 29);
		panel.add(lblNewLabel_2_3);
		
		JLabel lblNewLabel_2_4 = new JLabel("\uC7AC\uC0DD\uC2DC\uAC04");
		lblNewLabel_2_4.setFont(new Font("±¼¸²Ã¼", Font.PLAIN, 14));
		lblNewLabel_2_4.setBounds(233, 142, 65, 29);
		panel.add(lblNewLabel_2_4);
		
		JLabel lblNewLabel_2_5 = new JLabel("\uC74C\uC9C8");
		lblNewLabel_2_5.setFont(new Font("±¼¸²Ã¼", Font.PLAIN, 14));
		lblNewLabel_2_5.setBounds(233, 168, 65, 29);
		panel.add(lblNewLabel_2_5);
		
		JLabel artistlabel = new JLabel(singer);
		artistlabel.setBounds(317, 40, 286, 15);
		panel.add(artistlabel);
		
		JLabel composerlabel = new JLabel(composer);
		composerlabel.setBounds(317, 66, 286, 15);
		panel.add(composerlabel);
		
		JLabel lyricistlabel = new JLabel(lyricist);
		lyricistlabel.setBounds(317, 94, 286, 15);
		panel.add(lyricistlabel);
		
		JLabel albumlabel = new JLabel(album);
		albumlabel.setBounds(317, 121, 286, 15);
		panel.add(albumlabel);
		
		JLabel playtimelabel = new JLabel(playtime);
		playtimelabel.setBounds(317, 149, 286, 15);
		panel.add(playtimelabel);
		
		JLabel soundqualitylabel = new JLabel(soundquality);
		soundqualitylabel.setBounds(317, 175, 286, 15);
		panel.add(soundqualitylabel);
		
		ImageIcon listenimg = new ImageIcon(filepath+"/src/datafiles/Àç»ýÁßÁö.png");
		ImageIcon listeningimg = new ImageIcon(filepath+"/src/datafiles/Àç»ýÁß.png");
		JLabel listenimgLabel = new JLabel(imagesizeset(listenimg, 35, 35));
		listenimgLabel.setBounds(596, 275, 35, 35);
		panel.add(listenimgLabel);
		
		
		JButton takeBtn = new JButton("\uB2F4\uAE30");
		takeBtn.setBounds(201, 211, 97, 23);
		panel.add(takeBtn);
		
		JButton ListenBtn = new JButton("\uB4E3\uAE30");
		ListenBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ex.Connect();
					System.out.println(m_no);
					System.out.println(user.no);
					ResultSet rs = ex.ExecuteReadQuery("SELECT * FROM oldpopsong.playlist WHERE m_no="+m_no+" AND u_no="+user.no);
					if(!rs.next()) {
						JOptionPane.showMessageDialog(null, "À½¿ø ´ã±â¸¦ ¸ÕÀú ½ÇÇàÇØ ÁÖ¼¼¿ä.","°æ°í",JOptionPane.ERROR_MESSAGE);
						return;
					}
					if(musicplaying==false){
						ListenBtn.setText("¸ØÃã");
						listenimgLabel.setIcon(imagesizeset(listeningimg, 35, 35));
						musicplaying=true;
					}else {
						ListenBtn.setText("µè±â");
						listenimgLabel.setIcon(imagesizeset(listenimg, 35, 35));
						musicplaying=false;
					}
				}catch(Exception ex) {
					ex.printStackTrace();
				}
				
			}
		});
		ListenBtn.setBounds(310, 211, 97, 23);
		panel.add(ListenBtn);
		
		
		progressBar.setBounds(12, 287, 584, 23);
		panel.add(progressBar);
		
		
	}
	private ImageIcon imagesizeset(ImageIcon icon, int i, int j) {
		Image img = icon.getImage();
		Image updatedimg = img.getScaledInstance(i, j, Image.SCALE_SMOOTH);
		ImageIcon finalimg = new ImageIcon(updatedimg);
		return finalimg;
	}
	
}
