package me.jianpeng.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class LogUser {
	
    private String userid;

    private String username;

    private String userpassword;

    private String usersalt;

    private Integer userlv;

    private Integer state;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createtime;
    
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date updatetime;

    public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

	public String getUserid() {
        return userid;
    }

    public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword == null ? null : userpassword.trim();
    }

    public String getUsersalt() {
        return usersalt;
    }

    public void setUsersalt(String usersalt) {
        this.usersalt = usersalt == null ? null : usersalt.trim();
    }

    public Integer getUserlv() {
        return userlv;
    }

    public void setUserlv(Integer userlv) {
        this.userlv = userlv;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

	@Override
	public String toString() {
		return "LogUser [userid=" + userid + ", username=" + username + ", userpassword=" + userpassword + ", usersalt="
				+ usersalt + ", userlv=" + userlv + ", state=" + state + ", createtime=" + createtime + ", updatetime="
				+ updatetime + "]";
	}
    
    
}