package me.jianpeng.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class LogContent {
	
    private String contentid;

    private Integer recommend;

    private String typeid;
    
    private String typeval;

    private String title;

	private String tag;

    private String summary;

    private Integer state;

    private String userid;

    private String username;

    private String body;
    
    private Integer click;
    
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date updatetime;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createtime;

    public String getContentid() {
		return contentid;
	}

	public void setContentid(String contentid) {
		this.contentid = contentid;
	}

    public Integer getRecommend() {
        return recommend;
    }

    public void setRecommend(Integer recommend) {
        this.recommend = recommend;
    }

    public String getTypeid() {
		return typeid;
	}

	public void setTypeid(String typeid) {
		this.typeid = typeid;
	}
	
	public String getTypeval() {
		return typeval;
	}

	public void setTypeval(String typeval) {
		this.typeval = typeval;
	}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag == null ? null : tag.trim();
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary == null ? null : summary.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Integer getClick() {
        return click;
    }

    public void setClick(Integer click) {
        this.click = click;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body == null ? null : body.trim();
    }

	@Override
	public String toString() {
		return "LogContent [contentid=" + contentid + ", recommend=" + recommend + ", typeid=" + typeid + ", typeval="
				+ typeval + ", title=" + title + ", tag=" + tag + ", summary=" + summary
				+ ", state=" + state + ", userid=" + userid + ", username=" + username + ", updatetime=" + updatetime
				+ ", createtime=" + createtime + ", click=" + click + "]";
	}
    
    
}