package com.delorean.jeopardy;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import android.util.Log;

public class HttpComm {
	private HttpClient httpClient;
	
	public HttpComm() {
		httpClient = new DefaultHttpClient();
	}
	
	public String postLogin(String url, String username, String password) {
		HttpPost httpPost = new HttpPost(url);
		
		// add the data
		List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>();
		nameValuePair.add(new BasicNameValuePair("username", username));
		nameValuePair.add(new BasicNameValuePair("password", password));
		
		// encode the post data in order to convert all string data into valid url format.
		try {
			httpPost.setEntity(new UrlEncodedFormEntity(nameValuePair));
		} catch(UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		// make the http request
		try {
		    HttpResponse response = httpClient.execute(httpPost);
		    // print the response
		    Log.d("Http Response:", response.toString());
			return EntityUtils.toString(response.getEntity());
			
		} catch (ClientProtocolException e) {
		    e.printStackTrace(); 
		    return null;
		} catch (IOException e) {
		    e.printStackTrace();
		    return null;
		}

	}
	
	public String postNewUser(String url, String username, String password) {
		HttpPost httpPost = new HttpPost(url);
		
		// add the data
		List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>();
		nameValuePair.add(new BasicNameValuePair("username", username));
		nameValuePair.add(new BasicNameValuePair("password", password));
		
		// encode the post data in order to convert all string data into valid url format.
		try {
			httpPost.setEntity(new UrlEncodedFormEntity(nameValuePair));
		} catch(UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		// make the http request
		try {
		    HttpResponse response = httpClient.execute(httpPost);
		    // print the response
		    Log.d("Http Response:", response.toString());
			return EntityUtils.toString(response.getEntity());
			
		} catch (ClientProtocolException e) {
		    e.printStackTrace(); 
		    return null;
		} catch (IOException e) {
		    e.printStackTrace();
		    return null;
		}

	}


	public String getQuestion(String url, String questionID) {
		HttpGet httpGet = new HttpGet(url + "/" + questionID);
		
		// add the data
		List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>();
		nameValuePair.add(new BasicNameValuePair("questionID", questionID));

		// make the http request
		try {
		    HttpResponse response = httpClient.execute(httpGet);
		    // print the response
		    Log.d("Http Response:", response.toString());
			return EntityUtils.toString(response.getEntity());
			
		} catch (ClientProtocolException e) {
		    e.printStackTrace(); 
		    return null;
		} catch (IOException e) {
		    e.printStackTrace();
		    return null;
		}

	}






}
