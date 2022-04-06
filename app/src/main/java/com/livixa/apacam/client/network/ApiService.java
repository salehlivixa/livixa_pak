package com.livixa.apacam.client.network;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.JsonObject;
import com.kisafa.user.profile.UpdateProfileResponse;

import com.livixa.apacam.client.activity.LoginActivity;

import com.livixa.apacam.client.response.ServerResponse;
import com.livixa.apacam.client.response.deletion.DeletionResponse;
import com.livixa.apacam.client.response.isdatasyncedwithserver.Association_Response;
import com.livixa.apacam.client.response.isdatasyncedwithserver.DataSynced_With_Server;
import com.livixa.apacam.client.response.isdatasyncedwithserver.ShCamera;
import com.livixa.apacam.client.response.isdatasyncedwithserver.ShUserCamera;
import com.livixa.apacam.client.response.isdatasyncedwithserver.Sync_DataRequestToServer;
import com.livixa.apacam.client.response.isdatasyncedwithserver.Uploaded_Pictures_Response;
import com.livixa.apacam.client.response.login.LoginResponse;
import com.livixa.apacam.client.response.register.RegisterResponse;
import com.livixa.apacam.client.response.request.RequestResponse;
import com.livixa.apacam.client.response.tariff_energy.Watage_Response;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.QueryMap;

public interface ApiService {

	// User Login
	@FormUrlEncoded
	@POST(AppWebServices.API_LOGIN)
	Call<LoginResponse> login(@FieldMap Map<String, String> params);

	// User Register
	@Multipart
	@POST(AppWebServices.API_REGISTER)
	Call<RegisterResponse> register(@Part MultipartBody.Part image, @PartMap Map<String, String> params, @Part("profile_image")  RequestBody photo);
	
	
	@Multipart
	@POST(AppWebServices.API_REGISTER)
	Call<RegisterResponse> register( @PartMap() Map<String, RequestBody> partMap,@Part MultipartBody.Part file);
	
	
	@FormUrlEncoded
	@POST(AppWebServices.API_REGISTER)
	Call<RegisterResponse> register(@FieldMap Map<String, String> params);

	// User Forgot Password
	@FormUrlEncoded
	@POST(AppWebServices.API_FORGOT)
	Call<RequestResponse> forgot(@FieldMap Map<String, String> params);
	
	
	@FormUrlEncoded
	@POST(AppWebServices.API_WAITAGE)
	Call<Watage_Response> getWatageDetails(@FieldMap Map<String, String> params);
	
	@FormUrlEncoded
	@POST(AppWebServices.API_CHANGE_PASS)
	Call<RequestResponse> changePassword(@FieldMap Map<String, String> params);

	// User Logout
	@FormUrlEncoded
	@POST(AppWebServices.API_LOGOUT)
	Call<RequestResponse> logout(@FieldMap Map<String, String> params);
	
	@FormUrlEncoded
	@POST(AppWebServices.API_DELETE)
	Call<DeletionResponse> delete(@FieldMap Map<String, String> params);
	
	
	
	@POST(AppWebServices.API_SYNCED_WITH_SERVER)
	Call<DataSynced_With_Server> upload2server(@Body Sync_DataRequestToServer param);
	
	@FormUrlEncoded
	@POST(AppWebServices.API_SYNCED_WITH_SERVER)
	Call<DataSynced_With_Server> upload2server(@Field("session") String Session);
	
	@FormUrlEncoded
	@POST(AppWebServices.API_SYNCED_WITH_SERVER)
	Call<DataSynced_With_Server> upload2server(@FieldMap Map<String, String> params);
	
	
	/*@FormUrlEncoded
	@POST(AppWebServices.API_EDIT_PROFILE)
	Call<LoginResponse> editProfile(@FieldMap Map<String, String> params);*/
	
	
	@Multipart
	@POST(AppWebServices.API_EDIT_PROFILE)
	Call<UpdateProfileResponse> editProfile(@PartMap Map<String, String> params,@Part MultipartBody.Part image, @Part("profile_image") RequestBody name);
	
	@Multipart
	@POST(AppWebServices.API_EDIT_PROFILE)
	Call<UpdateProfileResponse> editProfile( @PartMap() Map<String, RequestBody> partMap,@Part MultipartBody.Part file);
	
	@FormUrlEncoded
	@POST(AppWebServices.API_SYNCED_WITH_SERVER)
	Call<DataSynced_With_Server> upload2server(
			@Field("session") String Session,
			@Field("rooms") ArrayList<String> rooms,
			@Field("user_rooms") ArrayList<String> user_rooms,
			@Field("cameras") ArrayList<ShCamera> cameras,
			@Field("user_cameras") ArrayList<ShUserCamera> user_cameras,
			@Field("switches")  ArrayList<String> switches,
			@Field("buttons")  ArrayList<String> buttons,
			@Field("moods")  ArrayList<String> moods,
			@Field("button_moods") ArrayList<String> button_moods
			);
	
	
	@FormUrlEncoded
	@POST(AppWebServices.API_GET_SYNCED_DATA_FROM_SERVER)
	Call<DataSynced_With_Server> get_sync_data_from_server(@FieldMap Map<String, String> params);

	
	
	// User Login
		@FormUrlEncoded
		@POST(AppWebServices.API_SUB_USER_CAM_ASSO)
		Call<Association_Response> sub_User_Cam_Asso(@FieldMap Map<String, String> params);
		
		
		
		
		@POST(AppWebServices.API_UPLOAD_PICTURES)
		Call<Uploaded_Pictures_Response> uploadImagesToServer( @Field("session")String session,@Body MultipartBody filePart);
		
		@POST(AppWebServices.API_UPLOAD_PICTURES)
		Call<Uploaded_Pictures_Response> uploadImagesToServer( @Body MultipartBody filePart);
		
		@FormUrlEncoded
		@POST(AppWebServices.API_CHANGE_CURRENCY)
		Call<RequestResponse> changeCurrency(@FieldMap Map<String, String> params);
		
		
		@FormUrlEncoded
		@POST(AppWebServices.API_ADD_SWITCH_MOODS)
		Call<RequestResponse> add_edit_switch(@FieldMap Map<String, String> params);
	

}
