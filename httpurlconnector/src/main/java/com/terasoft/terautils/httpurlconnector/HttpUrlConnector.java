package com.terasoft.terautils.httpurlconnector;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

public final class HttpUrlConnector {
	
	private final String USER_AGENT = "Mozilla/5.0";
	
	public String sendGet(String url) throws ClientProtocolException, IOException
	{			
		return sendGet(url,null);
	}
	
	public String sendGet(String url, ParameterList parameters) throws ClientProtocolException, IOException
	{	
		HttpClient client = HttpClients.createDefault();
		HttpGet request = null;		
		
		if( parameters == null )
		{
			request = new HttpGet(url);
		}else
		{
			List<NameValuePair> params = new ArrayList<NameValuePair>();		
			for (Parameter parameter : parameters.getParams()) {
				params.add(new BasicNameValuePair(parameter.getName(), parameter.getValue()));
			}
			String paramString = URLEncodedUtils.format(params, "utf-8");
			if( url.contains("?") )		
				request = new HttpGet(url+paramString);
			else
				request = new HttpGet(url+"?"+paramString);			
		}
		
		request.addHeader("User-Agent", USER_AGENT);
		
		HttpResponse response = client.execute(request);
		BufferedReader rd = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent()));
		
		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}
		
		return result.toString();
	}
	
	public String sendPost(String url) throws ClientProtocolException, IOException
	{
		return sendPost(url, null);
	}

	public String sendPost(String url, ParameterList parameters) throws ClientProtocolException, IOException
	{	
		HttpClient client = HttpClients.createDefault();
		HttpPost request = new HttpPost(url);
		
		if(parameters != null)
		{
			List<NameValuePair> params = new ArrayList<NameValuePair>();		
			for (Parameter parameter : parameters.getParams()) {
				params.add(new BasicNameValuePair(parameter.getName(), parameter.getValue()));
			}
							
			request.setEntity(new UrlEncodedFormEntity(params));				
		}
		
		request.addHeader("User-Agent", USER_AGENT);
		
		HttpResponse response = client.execute(request);
		BufferedReader rd = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent()));
		
		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}
		
		return result.toString();
	}

	public String sendPut(String url) throws ClientProtocolException, IOException
	{
		return sendPut(url, null);
	}
		
	public String sendPut(String url, ParameterList parameters) throws ClientProtocolException, IOException
	{	
		HttpClient client = HttpClients.createDefault();		
		HttpPut request = new HttpPut(url);
		
		if(parameters != null)
		{
			List<NameValuePair> params = new ArrayList<NameValuePair>();		
			for (Parameter parameter : parameters.getParams()) {
				params.add(new BasicNameValuePair(parameter.getName(), parameter.getValue()));
			}
							
			request.setEntity(new UrlEncodedFormEntity(params));				
		}
		
		request.addHeader("User-Agent", USER_AGENT);
		
		HttpResponse response = client.execute(request);
		BufferedReader rd = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent()));
		
		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}
		
		return result.toString();
	}
	
	public String sendPatch(String url) throws ClientProtocolException, IOException
	{
		return sendPatch(url, null);
	}
		
	public String sendPatch(String url, ParameterList parameters) throws ClientProtocolException, IOException
	{	
		HttpClient client = HttpClients.createDefault();		
		HttpPatch request = new HttpPatch(url);
		
		if(parameters != null)
		{
			List<NameValuePair> params = new ArrayList<NameValuePair>();		
			for (Parameter parameter : parameters.getParams()) {
				params.add(new BasicNameValuePair(parameter.getName(), parameter.getValue()));
			}
							
			request.setEntity(new UrlEncodedFormEntity(params));				
		}
		
		request.addHeader("User-Agent", USER_AGENT);
		
		HttpResponse response = client.execute(request);
		BufferedReader rd = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent()));
		
		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}
		
		return result.toString();
	}

	
	public final class Parameter
	{
		private final String name;
		private final String value;
		
		public Parameter(String name, String value) {
			this.name = name;
			this.value = value;
		}

		public String getName() {
			return name;
		}
		
		public String getValue() {
			return value;
		}
		
	}
	
	public final class ParameterList
	{
		private List<Parameter> params = new ArrayList<Parameter>();		
		public void addParameter(Parameter parameter)
		{
			this.params.add(parameter);
		}
		
		public List<Parameter> getParams()
		{
			return this.params;
		}
	}	
		
}
