package com.kh.notice.vo;

import java.sql.Timestamp;

public class NoticeVo {
	
	private int no;
	private String title;
	private String content;
	private String writer;
	private String cnt;
	private Timestamp enrollDate;
	private String status;
	
	
	public NoticeVo() {
		
	}
	
	public NoticeVo(int no, String title, String content, String writer, String cnt, Timestamp enrollDate, String status) {
		super();
		this.no = no;
		this.title = title;
		this.content = content;
		this.writer = writer;
		this.cnt = cnt;
		this.enrollDate = enrollDate;
		this.status = status;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getCnt() {
		return cnt;
	}

	public void setCnt(String cnt) {
		this.cnt = cnt;
	}

	public Timestamp getEnrollDate() {
		return enrollDate;
	}

	public void setEnrollDate(Timestamp enrollDate) {
		this.enrollDate = enrollDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "NoticeVo [no=" + no + ", title=" + title + ", content=" + content + ", writer=" + writer + ", cnt="
				+ cnt + ", enrollDate=" + enrollDate + ", status=" + status + "]";
	}
	
	
	
	
	
}
