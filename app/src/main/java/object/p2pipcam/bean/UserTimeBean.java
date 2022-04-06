package object.p2pipcam.bean;

import java.io.Serializable;

public class UserTimeBean implements Serializable {
	private static final long serialVersionUID = 1L;
	// get_users_schedule.cgi?user_schedule_enable=1&user_schedule_mode=1&user_schedule_startday=1
	// &user_schedule_endday=1&user_schedule_starttime_min=1&user_schedule_endtime_min=1
	private int user_schedule_enable;
	private int user_schedule_mode;
	private int user_schedule_startday;
	private int user_schedule_endday;
	private int user_schedule_starttime_min;
	private int user_schedule_endtime_min;

	public int getUser_schedule_enable() {
		return user_schedule_enable;
	}

	public void setUser_schedule_enable(int user_schedule_enable) {
		this.user_schedule_enable = user_schedule_enable;
	}

	public int getUser_schedule_mode() {
		return user_schedule_mode;
	}

	public void setUser_schedule_mode(int user_schedule_mode) {
		this.user_schedule_mode = user_schedule_mode;
	}

	public int getUser_schedule_startday() {
		return user_schedule_startday;
	}

	public void setUser_schedule_startday(int user_schedule_startday) {
		this.user_schedule_startday = user_schedule_startday;
	}

	public int getUser_schedule_endday() {
		return user_schedule_endday;
	}

	public void setUser_schedule_endday(int user_schedule_endday) {
		this.user_schedule_endday = user_schedule_endday;
	}

	public int getUser_schedule_starttime_min() {
		return user_schedule_starttime_min;
	}

	public void setUser_schedule_starttime_min(int user_schedule_starttime_min) {
		this.user_schedule_starttime_min = user_schedule_starttime_min;
	}

	public int getUser_schedule_endtime_min() {
		return user_schedule_endtime_min;
	}

	public void setUser_schedule_endtime_min(int user_schedule_endtime_min) {
		this.user_schedule_endtime_min = user_schedule_endtime_min;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
