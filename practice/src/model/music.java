package model;

public class music {
	int m_no;
	String m_name;
	boolean agelimit;
	String singer;
	public music(int m_no, String m_name, boolean agelimit) {
		this.m_no = m_no;
		this.m_name = m_name;
		this.agelimit = agelimit;
	}
	public music(int m_no, String m_name, String singer) {
		this.m_no = m_no;
		this.m_name = m_name;
		this.singer = singer;
	}
	public int getNo() {
		return m_no;
	}
	public String getName() {
		return m_name;
	}
	public boolean getAgelimit() {
		return agelimit;
	}
	public String getSinger() {
		return singer;
	}
}
