package com.livixa.apacam.client.response.isdatasyncedwithserver;

import java.io.Serializable;
import java.util.ArrayList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import com.livixa.apacam.client.response.tariff_energy.ShTariffResult;

public class ShResult_Uploaded_Images implements Serializable {

	
	
	@SerializedName("sh_pictures")
	@Expose
	private ArrayList<String> picturePaths;
	
	
	public ArrayList<String> getPicturePaths() {
		return picturePaths;
	}

	public void setPicturePaths(ArrayList<String> picturePaths) {
		this.picturePaths = picturePaths;
	}
	
}
