package com.mage.park.common.bo;

import java.util.Map;

import com.mage.database.NotDbField;

public class PageInfo {
	
    private String page_id;

    private String page_code;

    private String page_name;

    private String page_url;

    private String page_icon;

    private String page_element;

    private Integer parent_id;

    private Integer sno;

    private Integer state;

    private String desc;
    
    private Map<String, Object> pageEle;

    public String getPage_id() {
		return page_id;
	}

	public void setPage_id(String page_id) {
		this.page_id = page_id;
	}

	public String getPage_code() {
		return page_code;
	}

	public void setPage_code(String page_code) {
		this.page_code = page_code;
	}

	public String getPage_name() {
		return page_name;
	}

	public void setPage_name(String page_name) {
		this.page_name = page_name;
	}

	public String getPage_url() {
		return page_url;
	}

	public void setPage_url(String page_url) {
		this.page_url = page_url;
	}

	public String getPage_icon() {
		return page_icon;
	}

	public void setPage_icon(String page_icon) {
		this.page_icon = page_icon;
	}

	public String getPage_element() {
		return page_element;
	}

	public void setPage_element(String page_element) {
		this.page_element = page_element;
	}

	public Integer getParent_id() {
		return parent_id;
	}

	public void setParent_id(Integer parent_id) {
		this.parent_id = parent_id;
	}

	public Integer getSno() {
        return sno;
    }

    public void setSno(Integer sno) {
        this.sno = sno;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc == null ? null : desc.trim();
    }

    @NotDbField
	public Map<String, Object> getPageEle() {
		return pageEle;
	}

	public void setPageEle(Map<String, Object> pageEle) {
		this.pageEle = pageEle;
	}
    
    
}