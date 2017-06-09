package com.atguigu.survey.entities.manager;

public class Res {
    private Integer resId;

    private String servletPath;

    private Boolean publicStatus;

    private Integer resCode;

    private Integer resPos;
    
    public Res() {
    	
	}

    public Res(Integer resId, String servletPath, Boolean publicStatus, Integer resCode, Integer resPos) {
		super();
		this.resId = resId;
		this.servletPath = servletPath;
		this.publicStatus = publicStatus;
		this.resCode = resCode;
		this.resPos = resPos;
	}

	@Override
	public String toString() {
		return "Res [resId=" + resId + ", servletPath=" + servletPath + ", publicStatus=" + publicStatus + ", resCode="
				+ resCode + ", resPos=" + resPos + "]";
	}

	public Integer getResId() {
        return resId;
    }

    public void setResId(Integer resId) {
        this.resId = resId;
    }

    public String getServletPath() {
        return servletPath;
    }

    public void setServletPath(String servletPath) {
        this.servletPath = servletPath == null ? null : servletPath.trim();
    }

    public Boolean getPublicStatus() {
        return publicStatus;
    }

    public void setPublicStatus(Boolean publicStatus) {
        this.publicStatus = publicStatus;
    }

    public Integer getResCode() {
        return resCode;
    }

    public void setResCode(Integer resCode) {
        this.resCode = resCode;
    }

    public Integer getResPos() {
        return resPos;
    }

    public void setResPos(Integer resPos) {
        this.resPos = resPos;
    }
}