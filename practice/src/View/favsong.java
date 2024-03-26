package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import core.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.music;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

public class favsong extends JFrame {

	private JPanel contentPane;
	private JTextField pwbox;
	JPanel[] mpanelarr = new JPanel[99];

	/**
	 * Create the frame.
	 */
	JFrame parentform;
	public favsong(JFrame parentform) {
		this.parentform = parentform;
		setTitle("\uC120\uD638 POP SONG");
		SQLExecutor ex = new SQLExecutor();
		String filepath = System.getProperty("user.dir");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 594, 491);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		JLabel lblPopSong = new JLabel("\uC120\uD638 POP SONG");
		lblPopSong.setFont(new Font("굴림", Font.PLAIN, 23));
		lblPopSong.setBounds(198, 10, 168, 47);
		contentPane.add(lblPopSong);
		
		JLabel lblNewLabel = new JLabel("\uC120\uD0DD");
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 14));
		lblNewLabel.setBounds(12, 62, 57, 33);
		contentPane.add(lblNewLabel);
		
		pwbox = new JTextField();
		pwbox.setBounds(52, 68, 155, 21);
		contentPane.add(pwbox);
		pwbox.setColumns(10);
		
		List<Integer> pwlist = new ArrayList<>();
		ImageIcon reseticon = new ImageIcon(filepath+"/src/datafiles/초기화.png");
		JLabel lblNewLabel_1 = new JLabel(imagesizeset(reseticon, 47, 47));
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				pwlist.clear();
				onPwReset();
			}
		});
		
		ImageIcon homeimage = new ImageIcon(filepath+"/src/datafiles/메인.png");
		JLabel homelabel = new JLabel(imagesizeset(homeimage, 127, 47));
		homelabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				PWSend(pwlist);
			}
		});
		homelabel.setBounds(410, 10, 127, 47);
		contentPane.add(homelabel);
		
		lblNewLabel_1.setBounds(224, 55, 47, 47);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("\uC120\uD0DD");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				PWSend(pwlist);
			}
		});
		btnNewButton.setBounds(293, 59, 127, 38);
		contentPane.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(12, 124, 561, 328);
		contentPane.add(scrollPane);
		JPanel musicarea = new JPanel();
		GridLayout gridlayout = new GridLayout(0,5);
		gridlayout.setHgap(5);
		musicarea.setLayout(gridlayout);
		List<music> musiclist = new ArrayList<>();
		
		try {
			ex.Connect();
			ResultSet rs = ex.ExecuteReadQuery("SELECT m_no, m_name, agelimit FROM `music`");
			while(rs.next()) {
				musiclist.add(new music(rs.getInt(1),rs.getString(2),rs.getBoolean(3)));
			}
			rs.close();
		}catch(Exception e) {
			e.printStackTrace();
		}	
		
		
		for(int i=0;i<99;i++) {
			music music = musiclist.get(i);
			mpanelarr[i] = createMusicPanel(music.getNo(), music.getName(), music.getAgelimit());
			int j = i;
			mpanelarr[i].addMouseListener(new MouseListener() {
				
				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseClicked(MouseEvent e) {
					mpanelarr[j].setBorder(BorderFactory.createLineBorder(Color.red));
					pwlist.add(j+1);
					onPwAdd(pwlist);
				}
			});
			musicarea.add(mpanelarr[i]);
		}
		scrollPane.add(musicarea);
		scrollPane.setViewportView(musicarea);
	}
	private ImageIcon imagesizeset(ImageIcon icon, int i, int j) {
		Image img = icon.getImage();
		Image updatedimg = img.getScaledInstance(i, j, Image.SCALE_SMOOTH);
		ImageIcon finalimg = new ImageIcon(updatedimg);
		return finalimg;
	}
	private void PWSend(List<Integer> pwlist) {
		if(pwlist.size()==4) {
			if(parentform.getTitle().equals("로그인")) {
				Logincontroller.pw = pwbox.getText();
				Logincontroller.SendPassword();
			}else {
				RegisterController.pw = pwbox.getText();
				RegisterController.SendPassword();
			}
			setVisible(false);
		}else {
			JOptionPane.showMessageDialog(null, "선호 음원 4개를 선택하세요.","경고",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private JPanel createMusicPanel(int m_no,String m_name, boolean agelimit) {
		JPanel musicpanel = new JPanel(null);
		musicpanel.setPreferredSize(new Dimension(100, 140));
		String filepath = System.getProperty("user.dir");
			
		ImageIcon image = new ImageIcon(filepath+"/src/datafiles/album/"+m_no+".jpg");
		JLabel imagelabel = new JLabel(imagesizeset(image, 100, 100));
		imagelabel.setBounds(1,0,100,100);
		musicpanel.add(imagelabel);	
		
		if(m_name.length()>=17) m_name = m_name.substring(0,17)+"...";
		JLabel mnameLabel = new JLabel(m_name);
		mnameLabel.setBounds(0, 70, 100, 100);
		mnameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		Font namefont = mnameLabel.getFont();
		mnameLabel.setFont(new Font(namefont.getFontName(),namefont.getStyle(),10));
		musicpanel.add(mnameLabel);
		return musicpanel;
	}
	private void onPwAdd(List<Integer> pwlist) {
		StringBuilder sb = new StringBuilder();
		if(pwlist.size()==1) {
			pwbox.setText(String.valueOf(pwlist.get(0)));
		}else {
			for(int i=0;i<pwlist.size();i++) {
				sb.append(String.valueOf(pwlist.get(i)));
				if(i!=pwlist.size()-1) {
					sb.append(",");
				}else {
					pwbox.setText(sb.toString());
				}
			}
		}
	}
	private void onPwReset() {
		pwbox.setText(null);
		for(int i=0;i<99;i++) {
			mpanelarr[i].setBorder(null);
		}
	}
}
