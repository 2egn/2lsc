package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import core.*;
import model.user;

import javax.swing.JTree;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class MyPage extends JFrame {
	SQLExecutor ex = new SQLExecutor();
	private JPanel contentPane;
	private String filepath = System.getProperty("user.dir");
	private JTable playlisttable;
	public MyPage(JFrame mainform) {
		
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 693, 491);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(12, 132, 653, 310);
		contentPane.add(tabbedPane);
		tabbedPane.setVisible(false);
		
		
		JPanel playlist = new JPanel(null);
		
		
		JLabel namelabel = new JLabel("\uD64D\uAE38\uB3D9 \uB2D8");
		namelabel.setFont(new Font("굴림", Font.PLAIN, 24));
		namelabel.setBounds(12, 71, 119, 31);
		contentPane.add(namelabel);
		
		JLabel namelabel_1 = new JLabel("\uB9C8\uC774\uD398\uC774\uC9C0");
		namelabel_1.setFont(new Font("굴림", Font.PLAIN, 24));
		namelabel_1.setBounds(281, 10, 149, 31);
		contentPane.add(namelabel_1);
		
		ImageIcon homeicon = new ImageIcon(filepath+"/src/datafiles/메인.png");
		JLabel homelabel = new JLabel(imagesizeset(homeicon,97,31));
		homelabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mainform.setVisible(true);
				dispose();
			}
		});
		homelabel.setBounds(568, 27, 97, 31);
		contentPane.add(homelabel);
		
		JButton userinfolabel = new JButton("\uD68C\uC6D0 \uC815\uBCF4\uC218\uC815");
		userinfolabel.setBounds(556, 68, 109, 42);
		contentPane.add(userinfolabel);
		
		
		
		
		makePLTable();
	}
	public ImageIcon imagesizeset(ImageIcon img,int i,int j) {
		Image tempimg = img.getImage();
		Image convimg = tempimg.getScaledInstance(i, j, Image.SCALE_SMOOTH);
		return new ImageIcon(convimg);
	}
	private void makePLTable() {
		Object[][] PLArr = new Object[10000][4];
		playlisttable = new JTable();
		playlisttable.setBounds(12, 99, 648, 281);
		
		try {
			ex.Connect();
			
			ResultSet rs = ex.ExecuteReadQuery("SELECT playlist.m_no, singer, m_name "
					+ "FROM oldpopsong.music INNER JOIN oldpopsong.playlist "
					+ "ON music.m_no = playlist.m_no "
					+ "WHERE playlist.u_no = "+user.no);
			int rowno = 0;
			
			while(rs.next()) {
				PLArr[rowno][0] = rowno+1;
				ImageIcon albumimg = new ImageIcon(filepath+"/src/datafiles/album/"+rs.getInt(1)+".jpg");
				JLabel albumlabel = new JLabel(imagesizeset(albumimg, 60, 40));
				PLArr[rowno][1] = albumlabel;
				PLArr[rowno][2] = rs.getString(2);
				PLArr[rowno][3] = rs.getString(3);
				rowno++;
			}
			ex.Close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		playlisttable.setModel(new DefaultTableModel(
				PLArr,
				new String[] {
					"No", "음원 사진", "아티스트", "음원 제목"
				}
			));
		playlisttable.getColumnModel().getColumn(1).setPreferredWidth(108);
		playlisttable.getColumnModel().getColumn(1).setCellRenderer(null);
		playlisttable.getColumnModel().getColumn(2).setPreferredWidth(224);
		playlisttable.getColumnModel().getColumn(3).setPreferredWidth(214);
		contentPane.add(playlisttable);
	}
}
