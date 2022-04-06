/**
 * ϵͳ����ȫ�ֱ���
 */
package object.p2pipcam.system;

import java.util.ArrayList;
import java.util.List;

import info.lamatricexiste.network.Network.HostBean;
import object.p2pipcam.bean.CameraParamsBean;

/**
 * @author zhaogenghuai
 * @creation 2012-12-17����9:30:57
 */
public class SystemValue {
	public static ArrayList<CameraParamsBean> arrayList = new ArrayList<CameraParamsBean>();
	
	public static  List<HostBean> _hosts = new ArrayList<>();
	
	public static String deviceName = null;
	public static String usrName = null;
	public static String devicePass = null;
	public static String deviceId = null;
	public static int checkSDStatu = 0;
	public static int pictChange = 0;
	public static int NOTI = 0;
	public static boolean ISRUN = false;
	public static int TAG_CAMERLIST = 0;
	public static String SystemSerVer = "PFTDLREKSQHYEILVPAPDSYLNLKHXIBLTPNELAUHUASEHAVEESUARSWAOPELPLMLXSXPGSQLOPHLQPASTLRPILKPDLNLS-$$";
	//public static String SystemSerVer = "ATTDASPCSUSQAREOSTPAPESVAYLKSWPNLOPDHYHUEIASLTEETAPKAOPFLMLXLRPGSQSULNLQPAPELOLKLULP-$$";

	
}
