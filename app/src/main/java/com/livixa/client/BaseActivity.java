package com.livixa.client;

import object.p2pipcam.nativecaller.NativeCaller;

import java.util.Locale;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.graphics.Matrix;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;
import com.livixa.apacam.client.appconfig.AppKeys;
import com.livixa.apacam.client.base.KisafaApplication;
import com.livixa.apacam.services.Sync_Service;

public class BaseActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
		
	}
	
	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {

		
		
		
		
		super.onConfigurationChanged(newConfig);
		
		upDateLanguageonConfigurationChange();
		
	}
	
	
	
	private void upDateLanguageonConfigurationChange()
	{
		
		String  tempLanguage="en";
	    if(KisafaApplication.currentAppLanguage.equals(AppKeys.LANGUAGES.ENGLISH))
		{
	    	tempLanguage="en";
	    	
	    	
		}
	    else
	    {
	    	tempLanguage="ar";
	    	
	    	
	    }
	 
	 try
		{
			Locale locale = new Locale(tempLanguage);
			Locale.setDefault(locale);
			Configuration config = getApplicationContext().getResources().getConfiguration();
			config.locale = locale;
			getBaseContext().getResources().updateConfiguration(config,
			      getBaseContext().getResources().getDisplayMetrics());
			
		
			
		}catch(Exception ex)
		{
			ex.toString();
		}
	 
		
	}

	

	public void showToast(String content) {
		Toast.makeText(this, content, Toast.LENGTH_SHORT).show();
	}

	public void showToast(int rid) {
		Toast.makeText(this, getResources().getString(rid), Toast.LENGTH_SHORT)
				.show();
	}

	public void showToastLong(int rid) {
		Toast.makeText(this, getResources().getString(rid), Toast.LENGTH_LONG)
				.show();
	}

	public String returnString(int rid) {
		return getResources().getString(rid);
	}
	public static boolean hasSdcard() {

		String status = Environment.getExternalStorageState();
		if (status.equals(Environment.MEDIA_MOUNTED)) {
			return true;
		} else {
			return false;
		}
	}
	
	
	/****
	 * �˳�ȷ��dialog
	 * */
	public void showSureDialog(Context context) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setIcon(R.drawable.app);
		builder.setTitle(getResources().getString(R.string.exit));
				//+ getResources().getString(R.string.app_name));
		builder.setMessage(R.string.exit_chenxu_show);
		builder.setPositiveButton(R.string.str_ok,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						// Process.killProcess(Process.myPid());
						finish();
					}
				});
		builder.setNegativeButton(R.string.str_cancel, null);
		builder.show();
	}
	public void StartPPPP(String did, String user, String pwd) {
		String server = "AVTDHXIDAULKEHHYPLHUPFSWEEARTAPKPNSTPDPGAOLNHXEISQSXLPPAASEHLQLXARLUSULKLVPEHUSTHWHZEEIAPDIHPHIEAOIFESQLNENLRPALOEGEJERIBHYLKEIEPHUHXEKEEEHEOEL-$$";
		String serTag = did.substring(0, 3);
		if (serTag.equalsIgnoreCase("ESS")) {
			server = "SVLXLRLQLKSTLUPFHUIAPLEELVLPIHIBIEAOIFEMSQPDENELPALOHWHZERLNEOHYLKEPEIHUHXEGEJEEEKEH-$$";
			Log.d("server", "server-ESS");
		} else if (serTag.equalsIgnoreCase("PSD")) {
			Log.d("server", "server-PSD");
			server = "EJTDICSTSQPDPGATPALNEMLMLKHXTASVPNAWSZHUEHPLPKEEARSYLOAOSTLVLULXLQPISQPFPJPAPDLSLRLKLNLPLT-$$";
		} else if (serTag.equalsIgnoreCase("NIP")
				|| serTag.equalsIgnoreCase("MCI")
				|| serTag.equalsIgnoreCase("MSE")
				|| serTag.equalsIgnoreCase("MDI")
				|| serTag.equalsIgnoreCase("MIC")
				|| serTag.equalsIgnoreCase("MSI")
				|| serTag.equalsIgnoreCase("MTE")
				|| serTag.equalsIgnoreCase("MUI")
				|| serTag.equalsIgnoreCase("WBT")) {
			Log.d("server", "server-NEO");
			server = "ATTDASPCSUSQAREOSTPAPESVAYLKSWPNLOPDHYHUEIASLTEETAPKAOPFLMLXLRPGSQSULNLQPAPELOLKLULP-$$";
		} else if (serTag.equalsIgnoreCase("HDT")
				|| serTag.equalsIgnoreCase("DFT")
				|| serTag.equalsIgnoreCase("DFZ")
				|| serTag.equalsIgnoreCase("AJT")) {
			Log.d("server", "server-AJT");
			server = "ATTDSXIASQSUSTEKPASVAZLKTAPCPNPHAUHUPEPDSWEEPFTBAOPKLULXLRPGSQLOLNLQPALPPLLKLVLM-$$";
		} else if (serTag.equalsIgnoreCase("CPT")
				|| serTag.equalsIgnoreCase("PIP")) {
			Log.d("server", "server-CPTCAM");
			server = "LPTDAVIASQLOSTEKPAHYSSLKSYPIPNSXAUHUEIPDSWEEASPCAOPHLVLXLRPGSQSULNLQPAPELMLKLSLO-$$";
		} else if (serTag.equalsIgnoreCase("JDF")) {
			Log.d("server", "server-JDF");
			server = "PFTDIBTASQLNSZPAHXLOELLKHYSSEHPNAVPKHUARPJEESTEISXAOASPCSULXPHLUSQPDLTPALNPELRLKLOLMLP-$$";
		} else if (serTag.equalsIgnoreCase("MEY")) {
			Log.d("server", "server-MEY");
			server = "EJTDLOATSQHYLNPAEIHXPJLKEHLTLUPNAVPGHUASSXAREESTPILSAOPDSVPFLXPHLQSQSUPELVPALPLMLKLOLNLR-$$";
		} else if (serTag.equalsIgnoreCase("MIL")) {
			Log.d("server", "server-MIL");
			server = "SVTDLRSWSQSUIBPEPAHXAYLMLKEHTAARPNELPGHULOPFAVEESTSXPKAOHYEIPDLXPHLQSQASLPSUPAPELOLSLKLNLRLU-$$";
		} else if (serTag.equalsIgnoreCase("PSD")) {
			Log.d("server", "server-PSD");
			server = "EJTDICSTSQPDPGATPALNEMLMLKHXTASVPNAWSZHUEHPLPKEEARSYLOAOSTLVLULXLQPISQPFPJPAPDLSLRLKLNLPLT-$$";
		} else if (serTag.equalsIgnoreCase("RSZ")) {
			Log.d("server", "server-RSZ");
			server = "ASTDHXEHSUSQELPAARTBSYLKSTSVLQPNPDLNPEHUAVEEHXPLPIAOEHPFSXLXARSTLOSQPHPAPDLVLSLKLNLPLR-$$";
		} else if (serTag.equalsIgnoreCase("JWE")
				|| serTag.equalsIgnoreCase("WNS")
				|| serTag.equalsIgnoreCase("TSD")
				|| serTag.equalsIgnoreCase("HWA")
				|| serTag.equalsIgnoreCase("OPC")) {
			Log.d("server", "server-WNS");
			server = "SVTDIBEKSQEIAUPFPALVPJLKASPCSYPNELSWHUSUAVHXEEEHARLPAOPESXSTLXPHPGSQLOLQPIPAPDLMLTLKLNLSLR-$$";
		} else if (serTag.equalsIgnoreCase("WXO")
				|| serTag.equalsIgnoreCase("WXH")) {
			Log.d("server", "server-WNS");
			server = "LPLXLRSULKPEEOHULSPGEEPDICHZIHIBLOAOEMLQSQIALNPAHYHXENERELEILKHWHUEJEGEEEHEK-$$";
		} else if (serTag.equalsIgnoreCase("EST")
				|| serTag.equalsIgnoreCase("CTW")) {
			Log.d("server", "server-EST");
			server = "PFTDSTAXSWSQPDASEPPASUPELULKLNSZLPPNHXPJPGHUEHLOAZEEHYEITBAOARLMASLXSTLTLQSQPDSUPLPAPELOLVLKLNLR-$$";
		} else if (serTag.equalsIgnoreCase("PIX")
				|| serTag.equalsIgnoreCase("ZES")
				|| serTag.equalsIgnoreCase("IPC")) {
			Log.d("server", "server-PIX");
			server = "EJTDAVAUSQLOHYPDPAEISWPCLKLNPLHXPNSXPGHUASEHPHEEARATSVAOSUPFLULXLRLQSQPELOLVPASTLPPDLKLNLM-$$";
		} else if (serTag.equalsIgnoreCase("DYN")
				|| serTag.equalsIgnoreCase("PAR")) {
			Log.d("server", "server-PAR");
			server = "LPTDHXEKSQHZEHPAARAVLKSTEJAUPNPDSWHUATLNEEHXSXAOEHSVPGLXARLQSQPFSTPAPDPHLKLNLPLR-$$";
		} else if (serTag.equalsIgnoreCase("HTS")) {
			Log.d("server", "server-HTS");
			server = "SVTDPDLNPHSQEITAPAHXPFASLKSWPNEHARLRHUSUPKEESTLPPEAOPGLXLQLOSQLVPIPAPDLSPCLKLNLULM-$$";
		} else if (serTag.equalsIgnoreCase("TWS")) {
			Log.d("server", "server-TWS");
			server = "LPTDIBSWSQEILMHWPALTSTLKPDELASPNAVPGHUSUEGAQEEAYSXAOLNTASSLXPHLQSQPEPCLMPAPKLOLKLRLU-$$";
		} else if (serTag.equalsIgnoreCase("XXC") 
				|| serTag.equalsIgnoreCase("XXK")
				) {
			Log.d("server", "server-XXC");
			server = "PFTDELEKSQLOAVHYPAEIHXASLKSYPCPNSXAUHUSUSWPIEEPELPEHAOARPGLULXPHLQSQLOSTLRPAPDLVLMLKLNLSLT-$$";
		} else if (serTag.equalsIgnoreCase("PTP")
				|| serTag.equalsIgnoreCase("PHP")) {
			Log.d("server", "server-PTP");
			//server = "AVTDHXIDAULKEHHYPLHUPFSWEEARTAPKPNSTPDPGAOLNHXEISQSXLPPAASEHLQLXARLUSULKLVPEHUSTHWHZEEIAPDIHPHIEAOIFESQLNENLRPALOEGEJERIBHYLKEIEPHUHXEKEEEHEOEL-$$";
			server = "PFLXEHEOEILKIFASHULPEEARHZHWIHPHLQAOSUSTLRSQPEPDIAPALOEJEGERIBEKLKHYEIEPHULNEMHXEEEHELEN-$$";
		} else if (serTag.equalsIgnoreCase("HVC")) {
			Log.d("server", "server-HVCnew");
			server = "PFTDSXSWSQSZEIPAASPCLKLNAYHXPNPHPGHUEHTAPJEESUARAOSTLPPKLXLRLQSQLTPEPALOLMLKPDLULN-$$";
		} else if (serTag.equalsIgnoreCase("ZLA")) {
			Log.d("server", "server-WBJ");
			server = "SVTDEHARELSQHYAZPASTAVLKPDEIPFPNLNHXASHUEHSULQEELSSXAOTBPLLXARSTPHSQPELVPAPDLRLKLNLOLP-$$";
		} else if (serTag.equalsIgnoreCase("MHK")
				|| serTag.equalsIgnoreCase("EPC")) {
			Log.d("server", "server-MHK");
			server = "ATTDLREKSQEIIBELPAHXAUASLKSUPESVPNAVSWHUEHPLPFEELVLMAOARLPSTLXSXPGSQPKPHPAPDLULOLKLNLQLR-$$";
		} else if (serTag.equalsIgnoreCase("GOS")) {
			Log.d("server", "server-GOS");
			server = "LPTDSXSWSQLOPGLQPAHYPHLRLKSTPITAPNIBIAHUEIEKAUEEASELAVAOSUPDPKLXSXSWSQPEPGLQPALOPHLRLKLNLSLU-$$";
		} else if (serTag.equalsIgnoreCase("NTP")) {
			Log.d("server", "server-ƽ̨NTP");
			server = "HZTDHYSTTASQSXSWPAEJEGLKEIAQPJPNASPDPKHUPHPGEEATSSAOSUSVPFLXPELNLUSQLRLQPALPPCLKLOLMLT-$$";
		} else if (serTag.equalsIgnoreCase("IVT")) {
			Log.d("server", "server-IVT");
			server = "PFTDAUPESQEMLOPASXPKLKHYSTAWPNSWEIHUSYASEEPGPDAOLPPHLXLQSUSQPIPEPALRLULKLOLNLS-$$";
		} else if (serTag.equalsIgnoreCase("ZEO")) {
			Log.d("server", "server-ZEO");
			server = "ATTDSXLQSQEIIAAZPAASPKLKARSVPCPNPHEKHUSTTBPLEESUAUPFAOPDLSSWLXLRPGSQPELQLVPALOLULKLNLPLM-$$";
		} else if (serTag.equalsIgnoreCase("KSC")) {
			Log.d("server", "server-ƽ̨KLX");
			server = "SVTDSXSWSQPELNTBPAHXLSLOLKEHARSTPNPHPGHUPDPLPFEELNHXAOEHPJPCLXLRLQSQARLVLPPASTPDLKLNLTLM-$$";
		} else if (serTag.equalsIgnoreCase("BLM")) {
			Log.d("server", "server-ƽ̨BLM");
			server = "PFTDSXEKSQPEEGSTPALOHYLKEILPPNPHAUHUASAQSSEESUSWPJAOPDPLPILXLRPGSQPEPCLMPALOLQLTLKLNLVLS-$$";
		} else if (serTag.equalsIgnoreCase("OBJ")
				|| serTag.equalsIgnoreCase("SIP")
				|| serTag.equalsIgnoreCase("ESN")
				|| serTag.equalsIgnoreCase("ZLD")
				|| serTag.equalsIgnoreCase("TCM")
				|| serTag.equalsIgnoreCase("IPC")
				|| serTag.equalsIgnoreCase("BSI")
				|| serTag.equalsIgnoreCase("GKW")
				|| serTag.equalsIgnoreCase("MEG")) {
			Log.d("server", "server-ƽ̨BLM");
			server = "SVTDEHAYLOSQTBHYPAARPCPFLKLQSTPNPDTAEIHUPLASEEPIPKAOSUPESXLXPHLUSQLVLSPALNLTLRLKLOLMLP-$$";
		} else if (serTag.equalsIgnoreCase("XTS")
				|| serTag.equalsIgnoreCase("XTB")
				|| serTag.equalsIgnoreCase("UID")
				|| serTag.equalsIgnoreCase("AID")
				|| serTag.equalsIgnoreCase("ZSK")
				|| serTag.equalsIgnoreCase("MGW")) {
			Log.d("server", "server-ƽ̨BLM");
			server = "HZLXPHLQLKSYTAHUSULRHWEEPDPKPIIHLNLUPEAOEPLOSQHXENEJPAHYIALSERIBEKLKICIEHUEIELEGEEEHEOEM-$$";
		} else {
			Log.d("server", "server-OBJ");
		}
		Log.d("test", "server:" + serTag);
		NativeCaller.StartPPPP(did, user, pwd,server);
	}

}
