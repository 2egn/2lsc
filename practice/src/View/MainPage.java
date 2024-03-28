package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.sun.tools.javac.Main;

import core.Logincontroller;
import core.SQLExecutor;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import model.*;
import java.awt.Dimension;

import java.util.*;
import java.awt.event.MouseAdapter;
import java.awt.Toolkit;

public class MainPage extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainPage frame = new MainPage();
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
	List<music> musiclist = new ArrayList<>();
	JButton loginbtn = new JButton("\uB85C\uADF8\uC778");
	JButton regibtn = new JButton("\uD68C\uC6D0\uAC00\uC785");
	JButton searchbtn = new JButton("\uC74C\uC6D0\uAC80\uC0C9");
	JButton mypagebtn = new JButton("\uB9C8\uC774\uD398\uC774\uC9C0");
	JButton analysisbtn = new JButton("\uBD84\uC11D");
	JPanel[] mpanelarr = new JPanel[99];
	
	GridLayout gridlayout = new GridLayout(0,5);
	public static boolean isLogin = false;
	int[] rank = new int[5];
	JScrollPane scrollPane = new JScrollPane();
	MainPage currentform;
	public MainPage() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainPage.class.getResource("/datafiles/\uB9C8\uC774\uD06C.png")));
		Logincontroller.mainpage = this;
		currentform = this;
		SQLExecutor ex = new SQLExecutor();
		setTitle("\uBA54\uC778");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 601, 433);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("SKILL MUSIC(OLD POP SONG)");
		lblNewLabel.setFont(new Font("휴먼둥근헤드라인", Font.BOLD, 18));
		lblNewLabel.setBounds(150, 10, 361, 55);
		contentPane.add(lblNewLabel);
		
		loginbtn.setBounds(12, 60, 110, 42);
		contentPane.add(loginbtn);
		loginbtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(isLogin) {
					isLogin=false;
					LoginInitiated();
				}else {
					new Login(currentform).setVisible(true);
					setVisible(false);
				}
			}
		});
		regibtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(isLogin) {
					new UserInfo(currentform).setVisible(true);
				}else {
					new RegiPage(currentform).setVisible(true);
				}
				setVisible(false);
			}
		});
		
		
		
		regibtn.setBounds(128, 60, 110, 42);
		contentPane.add(regibtn);
		searchbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new SearchMusic(currentform).setVisible(true);
				setVisible(false);
			}
		});
		searchbtn.setEnabled(false);
		searchbtn.setBounds(241, 60, 110, 42);
		contentPane.add(searchbtn);	
		mypagebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new MyPage(currentform).setVisible(true);;
			}
		});
		
		mypagebtn.setEnabled(false);
		mypagebtn.setBounds(354, 60, 110, 42);
		contentPane.add(mypagebtn);
		
		analysisbtn.setBounds(463, 60, 110, 42);
		contentPane.add(analysisbtn);
		
		
		scrollPane.setBounds(12, 112, 561, 272);
		contentPane.add(scrollPane);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		gridlayout.setHgap(5);
	
		
		try {
			ex.Connect();
			ResultSet rs = ex.ExecuteReadQuery("SELECT m_no, m_name, agelimit FROM `music`");
			while(rs.next()) {
				musiclist.add(new music(rs.getInt(1),rs.getString(2),rs.getBoolean(3)));
			}
			rs.close();
			rs = ex.ExecuteReadQuery("SELECT music.m_no, COUNT(playlist.m_no) AS listen_count"
					+ " FROM music"
					+ " INNER JOIN playlist ON music.m_no = playlist.m_no"
					+ " GROUP BY music.m_no"
					+ " ORDER BY listen_count DESC"
					+ " LIMIT 5;");
			int rankindex = 0;
			while(rs.next()) {
				rank[rankindex++] = rs.getInt(1);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}	
		
		createMusicArea();        
		
	}
	
	public void createMusicArea() {
		JPanel musicarea = new JPanel();
		musicarea.setLayout(gridlayout);
		for(int i=0;i<99;i++) {
			music music = musiclist.get(i);
			if (music.getAgelimit()&&user.age<19) continue;
			mpanelarr[i] = createMusicPanel(music.getNo(), music.getName(), music.getAgelimit(), rank);
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
					// TODO Auto-generated method stub
					if(isLogin) {
						new musicdetail(j+1,currentform,currentform).setVisible(true);
						setVisible(false);
					}
					
				}
			});
			musicarea.add(mpanelarr[i]);
		}
		scrollPane.add(musicarea);
		scrollPane.setViewportView(musicarea);
	}
	private JPanel createMusicPanel(int m_no,String m_name, boolean agelimit, int[] rank) {
		JPanel musicpanel = new JPanel(null);
		musicpanel.setPreferredSize(new Dimension(100, 140));
		String filepath = System.getProperty("user.dir");
		
		if(agelimit) {
			ImageIcon limitimage = new ImageIcon(filepath+"/src/datafiles/19금.png");
			JLabel limitlabel = new JLabel(imagesizeset(limitimage, 30, 30));
			limitlabel.setBounds(70,0,30,30);
			musicpanel.add(limitlabel);
		}
		
		int index = -1;
		for(int i=0;i<rank.length;i++) {
			if(rank[i]==m_no) index = i+1;
		}
		if(index>0) {
			JLabel ranknumlabel = new JLabel(Integer.toString(index));
			ranknumlabel.setForeground(Color.white);
			ranknumlabel.setBounds(12,2,30,30);
			musicpanel.add(ranknumlabel);
			ImageIcon rankimage = new ImageIcon(filepath+"/src/datafiles/yellowcircle.png");
			JLabel rankimagelabel = new JLabel(imagesizeset(rankimage, 30, 30));
			rankimagelabel.setBounds(0,0,30,30);
			musicpanel.add(rankimagelabel);
		}
		
		ImageIcon image = new ImageIcon(filepath+"/src/datafiles/album/"+m_no+".jpg");
		JLabel imagelabel = new JLabel(imagesizeset(image, 100, 100));
		imagelabel.setBounds(0,0,100,100);
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
	private ImageIcon imagesizeset(ImageIcon icon, int i, int j) {
		Image img = icon.getImage();
		Image updatedimg = img.getScaledInstance(i, j, Image.SCALE_SMOOTH);
		ImageIcon finalimg = new ImageIcon(updatedimg);
		return finalimg;
	}
	public void LoginInitiated() {
		if(isLogin) {
			loginbtn.setText("로그아웃");
			regibtn.setText("회원정보");
			searchbtn.setEnabled(true);
			mypagebtn.setEnabled(true);
			createMusicArea();
		}else {
			loginbtn.setText("로그인");
			regibtn.setText("회원가입");
			searchbtn.setEnabled(false);
			mypagebtn.setEnabled(false);
			user.Init();
			createMusicArea();
		}
	}
}
