	package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Toolkit;
import java.awt.Font;
import java.awt.Image;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JToolTip;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import java.awt.GridLayout;
import core.*;
import model.music;
import model.user;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
public class SearchMusic extends JFrame {

	private JPanel contentPane;


	/**
	 * Create the frame.
	 */
	SQLExecutor ex = new SQLExecutor();
	String filepath = System.getProperty("user.dir");
	private JTextField textField;
	JScrollPane scrollPane = new JScrollPane();
	GridLayout gridlayout = new GridLayout(0,5);
	JFrame parentform;
	JFrame currentform;
	public SearchMusic(JFrame parentform) {
		this.currentform = this;
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				parentform.setVisible(true);
			}
		});
		this.parentform = parentform;
		setIconImage(Toolkit.getDefaultToolkit().getImage(SearchMusic.class.getResource("/datafiles/\uB9C8\uC774\uD06C.png")));
		setTitle("\uC74C\uC6D0\uAC80\uC0C9");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 576, 526);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JComboBox sortstandardcombo = new JComboBox();
		sortstandardcombo.setModel(new DefaultComboBoxModel(new String[] {"\uC624\uB984\uCC28\uC21C", "\uB0B4\uB9BC\uCC28\uC21C"}));
		JComboBox searchstandardcombo = new JComboBox();
		searchstandardcombo.setModel(new DefaultComboBoxModel(new String[] {"\uC81C\uBAA9", "\uC544\uD2F0\uC2A4\uD2B8"}));
		
		searchstandardcombo.setBounds(94, 61, 77, 23);
		contentPane.add(searchstandardcombo);
		searchstandardcombo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createMusicArea(getMusicList((String)searchstandardcombo.getSelectedItem(), (String)sortstandardcombo.getSelectedItem()));
			}
		});
		
		sortstandardcombo.setBounds(456, 91, 77, 23);
		contentPane.add(sortstandardcombo);
		sortstandardcombo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createMusicArea(getMusicList((String)searchstandardcombo.getSelectedItem(), (String)sortstandardcombo.getSelectedItem()));
			}
		});
		createMusicArea(getMusicList((String)searchstandardcombo.getSelectedItem(), (String)sortstandardcombo.getSelectedItem()));
			
		JLabel lblNewLabel = new JLabel("\uC74C\uC6D0\uAC80\uC0C9");
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 30));
		lblNewLabel.setBounds(206, 10, 156, 28);
		contentPane.add(lblNewLabel);
		
		ImageIcon homeimage = new ImageIcon(filepath+"/src/datafiles/메인.png");
		JLabel homelabel = new JLabel(imagesizeset(homeimage, 112, 46));
		homelabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
				parentform.setVisible(true);
			}
		});
		homelabel.setBounds(421, 0, 112, 46);
		contentPane.add(homelabel);
		
		JLabel lblNewLabel_1 = new JLabel("\uAC80\uC0C9\uAE30\uC900");
		lblNewLabel_1.setFont(new Font("굴림", Font.PLAIN, 17));
		lblNewLabel_1.setBounds(12, 57, 70, 28);
		contentPane.add(lblNewLabel_1);
		
		
		
		
		textField = new JTextField();
		textField.setBounds(181, 60, 112, 23);
		contentPane.add(textField);
		textField.setColumns(10);
		
		ImageIcon searchicon = new ImageIcon(filepath+"/src/datafiles/검색.png");
		JLabel serachlabel = new JLabel(imagesizeset(searchicon, 25, 25));
		serachlabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(!textField.getText().isEmpty()) {
					createMusicArea(getMusicList((String)searchstandardcombo.getSelectedItem(), (String)sortstandardcombo.getSelectedItem(),textField.getText()));
				}else {
					createMusicArea(getMusicList((String)searchstandardcombo.getSelectedItem(), (String)sortstandardcombo.getSelectedItem()));
				}
			}
		});
		serachlabel.setBounds(305, 57, 25, 25);
		contentPane.add(serachlabel);
		
		JLabel lblNewLabel_2 = new JLabel("\uC815\uB82C\uAE30\uC900");
		lblNewLabel_2.setBounds(387, 95, 57, 15);
		contentPane.add(lblNewLabel_2);
		
		
		scrollPane.setBounds(12, 143, 536, 334);
		contentPane.add(scrollPane);
	}
	private ImageIcon imagesizeset(ImageIcon tempicon, int i, int j) {
		Image tempimg = tempicon.getImage();
		Image convimg = tempimg.getScaledInstance(i, j, Image.SCALE_SMOOTH);
		return new ImageIcon(convimg);
	}
	
	private List<music> getMusicList(String column, String sort){
		List<music> musiclist = new ArrayList<>();
		column = column.equals("제목")?"m_name":"singer";
		sort = sort.equals("오름차순")?"ASC":"DESC";
		try {
			ex.Connect();
			ResultSet rs = ex.ExecuteReadQuery("SELECT m_no,m_name,agelimit,singer FROM music order by "+column+" "+sort);
			while(rs.next()) {
				if(user.age<19&&rs.getBoolean(3)) continue;
				musiclist.add(new music(rs.getInt(1),rs.getString(2),rs.getString(4)));
			}
			ex.Close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return musiclist;
	}
	private List<music> getMusicList(String column, String sort, String searchvalue){
		List<music> musiclist = new ArrayList<>();
		column = column.equals("제목")?"m_name":"singer";
		sort = sort.equals("오름차순")?"ASC":"DESC";
		try {
			ex.Connect();
			ResultSet rs = ex.ExecuteReadQuery("SELECT m_no,m_name,agelimit,singer FROM music WHERE m_name LIKE '%"+searchvalue+"%' order by "+column+" "+sort);
			while(rs.next()) {
				if(user.age<19&&rs.getBoolean(3)) continue;
				musiclist.add(new music(rs.getInt(1),rs.getString(2),rs.getString(4)));
			}
			ex.Close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return musiclist;
	}
	private void createMusicArea(List<music> musiclist) {
		gridlayout.setHgap(5);
		JPanel musicarea = new JPanel(gridlayout);
		
		
		for(int i=0;i<musiclist.size();i++) {
			music music = musiclist.get(i);
			musicarea.add(createMusicPanel(music.getNo(), music.getName(),music.getSinger()));
		}
		scrollPane.add(musicarea);
		scrollPane.setViewportView(musicarea);
	}
	private JPanel createMusicPanel(int no, String name,String singer) {
		JPanel musicpanel = new JPanel(null);
		musicpanel.setPreferredSize(new Dimension(100,140));
		
		ImageIcon downIcon = new ImageIcon(filepath+"/src/datafiles/담기.png");
		JLabel downlabel = new JLabel(imagesizeset(downIcon, 30, 30));
		downlabel.setBounds(70,70,30,30);
		downlabel.setVisible(false);
		downlabel.setToolTipText("<html>아티스트:"+singer+"<br>제목:"+name+"</html>");
		downlabel.addMouseListener(new MouseListener() {
			
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
				downlabel.setVisible(false);
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				downlabel.setVisible(true);
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(null, "음원이 저장되었습니다.","정보",JOptionPane.INFORMATION_MESSAGE);
				try {
					ex.Connect();
					ex.ExecuteUpdateQuery("INSERT INTO playlist(u_no,m_no) VALUES("+user.no+","+no+")");
					ex.Close();
				}catch(Exception exc) {
					exc.printStackTrace();
				}
			}
		});
		musicpanel.add(downlabel);
		
		ImageIcon image = new ImageIcon(filepath+"/src/datafiles/album/"+no+".jpg"); 
		JLabel imagelabel = new JLabel(imagesizeset(image, 100, 100));
		imagelabel.setBounds(0,0, 100, 100);
		imagelabel.setToolTipText("<html>아티스트:"+singer+"<br>제목:"+name+"</html>");
		imagelabel.addMouseListener(new MouseListener() {
			
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
				downlabel.setVisible(false);
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				downlabel.setVisible(true);
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				new musicdetail(no, (MainPage)parentform,currentform).setVisible(true);
				setVisible(false);
			}
		});
		musicpanel.add(imagelabel);
		
		
		if(name.length()>=17) name = name.substring(0,17)+"...";
		JLabel namelabel = new JLabel(name);
		namelabel.setBounds(0,100,100,40);
		musicpanel.add(namelabel);
		
		return musicpanel;
	}
}
