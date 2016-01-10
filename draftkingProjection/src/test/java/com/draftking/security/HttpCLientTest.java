package com.draftking.security;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.json.simple.JSONObject;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.draftking.json.AllPlayersStatsJSON;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class HttpCLientTest {

	private HttpClient client = HttpClientBuilder.create().build();
	private Cookie jSessionIDCookie;
	private List<Cookie> cookies;
	private Logger logger= LoggerFactory.getLogger(HttpCLientTest.class);

	private enum Http_Mode {
		GET, POST
	};

	ResponseHandler<JSONObject> rh = new ResponseHandler<JSONObject>() {
	
		public JSONObject handleResponse(final HttpResponse response)
				throws IOException {
			StatusLine statusLine = response.getStatusLine();
			HttpEntity entity = response.getEntity();
			if (statusLine.getStatusCode() >= 300) {
				throw new HttpResponseException(statusLine.getStatusCode(),
						statusLine.getReasonPhrase());
			}
			if (entity == null) {
				throw new ClientProtocolException(
						"Response contains no content");
			}
			//convert from JSON to Java objects.
			Gson gson = new GsonBuilder().create();
			ContentType contentType = ContentType.getOrDefault(entity);
			Charset charset = contentType.getCharset();
			if (entity.getContentLength() == 0)
				return null;
			Reader reader = new InputStreamReader(entity.getContent());
			String jsonString=gson.fromJson(reader, JSONObject.class).toJSONString();
			AllPlayersStatsJSON players= gson.fromJson(jsonString, AllPlayersStatsJSON.class);
			return gson.fromJson(reader, JSONObject.class);
		}
	};

	@Test
	public void test() {
		String url = "http://100.12.28.216:8181/draftking/j_spring_security_check";
		HashMap<String, String> authenticationMap = new HashMap<String, String>();
		//the j_username and j_password are form data.  contenttype =application/
		authenticationMap.put("j_username", "benpoon");
		authenticationMap.put("j_password", "pass1");
		http_execute(url, authenticationMap, null, Http_Mode.POST);

		String url2 = "http://100.12.28.216:8181/draftking/services/playerhomestats/all";
		http_execute(url2, authenticationMap, jSessionIDCookie, Http_Mode.GET);
	
	}

	/**
	 * This Method makes the actual http post call.
	 * 
	 * @param url
	 * @param requestParameters
	 * @param jSessionIdCookie
	 */

	private void http_execute(String url,
			HashMap<String, String> requestParameters, Cookie jSessionIdCookie,
			Http_Mode http_mode) {
		JSONObject jobj = null;

		CookieHandler.setDefault(new CookieManager());
		CookieStore cookieStore = new BasicCookieStore();
		if (!(jSessionIdCookie == null)) {
			cookieStore.addCookie(jSessionIdCookie);
		}
		HttpClientContext context = HttpClientContext.create();
		context.setCookieStore(cookieStore);

		HttpPost post = new HttpPost(url);
		HttpGet get = new HttpGet(url);
		List<BasicNameValuePair> urlParameters = new ArrayList<BasicNameValuePair>();
		for (Map.Entry<String, String> entry : requestParameters.entrySet()) {
			urlParameters.add(new BasicNameValuePair(entry.getKey(), entry
					.getValue()));
		}
		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(urlParameters,
				Consts.UTF_8);
		post.setEntity(entity);

		try {
			switch (http_mode) {
			case POST:
				jobj = client.execute(post, rh, context);
				break;
			case GET:
				jobj = client.execute(get, rh, context);
			}

			if (!(jobj == null)){
				logger.debug(jobj.toString());
			
			
			}
			}

		catch (HttpResponseException e) {
			// TODO Auto-generated catch block
			System.out.print("HttpResponseException");
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			System.out.print("ClientProtocolException");
			e.printStackTrace();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		jSessionIDCookie = getJsessionIDCookie(context.getCookieStore()
				.getCookies());
	}

	/**
	 * this method iterates through list of cookies and extracts Jsessionid
	 * cookie
	 * 
	 * @param cookieList
	 * @return
	 */

	private Cookie getJsessionIDCookie(List<Cookie> cookieList) {
		for (Cookie cookie : cookieList) {
			if (cookie.getName().equals("JSESSIONID"))
				return cookie;
		}
		return null;

	}

}
