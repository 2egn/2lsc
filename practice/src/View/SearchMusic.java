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
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.Image;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import java.awt.GridLayout;
import core.*;
import model.music;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
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
	
	public SearchMusic(JFrame parentform) {

		setIconImage(Toolkit.getDefaultToolkit().getImage(SearchMusic.class.getResource("/datafiles/\uB9C8\uC774\uD06C.png")));
		setTitle("\uC74C\uC6D0\uAC80\uC0C9");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		lblNewLabel.setFont(new Font("����", Font.BOLD, 30));
		lblNewLabel.setBounds(206, 10, 156, 28);
		contentPane.add(lblNewLabel);
		
		ImageIcon homeimage = new ImageIcon(filepath+"/src/datafiles/����.png");
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
		lblNewLabel_1.setFont(new Font("����", Font.PLAIN, 17));
		lblNewLabel_1.setBounds(12, 57, 70, 28);
		contentPane.add(lblNewLabel_1);
		
		
		
		
		textField = new JTextField();
		textField.setBounds(181, 60, 112, 23);
		contentPane.add(textField);
		textField.setColumns(10);
		
		ImageIcon searchicon = new ImageIcon(filepath+"/src/datafiles/�˻�.png");
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
		column = column.equals("����")?"m_name":"singer";
		sort = sort.equals("��������")?"ASC":"DESC";
		try {
			ex.Connect();
			ResultSet rs = ex.ExecuteReadQuery("SELECT m_no,m_name,agelimit FROM music order by "+column+" "+sort);
			while(rs.next()) {
				musiclist.add(new music(rs.getInt(1),rs.getString(2),rs.getBoolean(3)));
			}
			ex.Close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return musiclist;
	}
	private List<music> getMusicList(String column, String sort, String searchvalue){
		List<music> musiclist = new ArrayList<>();
		column = column.equals("����")?"m_name":"singer";
		sort = sort.equals("��������")?"ASC":"DESC";
		try {
			ex.Connect();
			ResultSet rs = ex.ExecuteReadQuery("SELECT m_no,m_name,agelimit FROM music WHERE m_name LIKE '%"+searchvalue+"%' order by "+column+" "+sort);
			while(rs.next()) {
				musiclist.add(new music(rs.getInt(1),rs.getString(2),rs.getBoolean(3)));
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
			JPanel musicpanel = createMusicPanel(music.getNo(), music.getName());
			musicarea.add(musicpanel);
			
		}
		scrollPane.add(musicarea);
		scrollPane.setViewportView(musicarea);
	}
	private JPanel createMusicPanel(int no, String name) {
		JPanel musicpanel = new JPanel(null);
		musicpanel.setPreferredSize(new Dimension(100,140));
		ImageIcon image = new ImageIcon(filepath+"/src/datafiles/album/"+no+".jpg"); 
		JLabel imagelabel = new JLabel(imagesizeset(image, 100, 100));
		imagelabel.setBounds(0,0, 100, 100);
		musicpanel.add(imagelabel);
		if(name.length()>=17) name = name.substring(0,17)+"...";
		JLabel namelabel = new JLabel(name);
		namelabel.setBounds(0,100,100,40);
		musicpanel.add(namelabel);
		
		return musicpanel;
	}
}