package object.p2pipcam.bean;

import java.io.Serializable;

import android.graphics.Bitmap;

public class CameraParamsBean implements Serializable {
	private static final long serialVersionUID = -1893938966606638092L;
	
	
	
	private String did;
	private String user;
	private String pwd;
	private String name;
	private int status;
	private Bitmap bmp;
	private int sum;
	private int sum_pic;
	private int kmode;
	private int authority;
	private int mode;
	private boolean selected;
	private boolean isSettingAllowed=true;
	private boolean isSubUser=false;
	
	

	public int getKmode() {
		return kmode;
	}

	public void setKmode(int kmode) {
		this.kmode = kmode;
	}

	public int getSum_pic() {
		return sum_pic;
	}

	public void setSum_pic(int sum_pic) {
		this.sum_pic = sum_pic;
	}

	

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public int getMode() {
		return mode;
	}

	public void setMode(int mode) {
		this.mode = mode;
	}

	public int isAuthority() {
		return authority;
	}

	public void setAuthority(int authority) {
		this.authority = authority;
	}

	public Bitmap getBmp() {
		return bmp;
	}

	public void setBmp(Bitmap bmp) {
		this.bmp = bmp;
	}

	public int getSum() {
		return sum;
	}

	public void setSum(int sum) {
		this.sum = sum;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getDid() {
		return did;
	}

	public void setDid(String did) {
		this.did = did;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "CameraParamsBean [did=" + did + ", user=" + user + ", pwd="
				+ pwd + ", name=" + name + "]";
	}

	public boolean isSettingAllowed() {
		return isSettingAllowed;
	}

	public void setSettingAllowed(boolean isSettingAllowed) {
		this.isSettingAllowed = isSettingAllowed;
	}

	public boolean isSubUser() {
		return isSubUser;
	}

	public void setSubUser(boolean isSubUser) {
		this.isSubUser = isSubUser;
	}

}
