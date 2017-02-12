package com.mage.park.lot.bo;

import java.util.Date;

public class Notice {
	
    private String notice_id;

    private String notice_title;

    private String notice_content;

    private Date notice_time;

    private Integer state;

    private String notice_type;

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

	public String getNotice_id() {
		return notice_id;
	}

	public void setNotice_id(String notice_id) {
		this.notice_id = notice_id;
	}

	public String getNotice_title() {
		return notice_title;
	}

	public void setNotice_title(String notice_title) {
		this.notice_title = notice_title;
	}

	public String getNotice_content() {
		return notice_content;
	}

	public void setNotice_content(String notice_content) {
		this.notice_content = notice_content;
	}

	public Date getNotice_time() {
		return notice_time;
	}

	public void setNotice_time(Date notice_time) {
		this.notice_time = notice_time;
	}

	public String getNotice_type() {
		return notice_type;
	}

	public void setNotice_type(String notice_type) {
		this.notice_type = notice_type;
	}
}