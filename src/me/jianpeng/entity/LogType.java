package me.jianpeng.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class LogType {
	
    private String typeid;

    private String typeval;

    private Integer state;
    
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date updatetime;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createtime;

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

	public String getTypeid() {
        return typeid;
    }

    public void setTypeid(String typeid) {
        this.typeid = typeid == null ? null : typeid.trim();
    }

    public String getTypeval() {
        return typeval;
    }

    public void setTypeval(String typeval) {
        this.typeval = typeval == null ? null : typeval.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

	@Override
	public String toString() {
		return "LogType [typeid=" + typeid + ", typeval=" + typeval + ", state=" + state + ", updatetime=" + updatetime
				+ ", createtime=" + createtime + "]";
	}
}