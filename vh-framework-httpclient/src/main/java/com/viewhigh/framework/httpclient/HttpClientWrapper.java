/**
 * 版权所属：东软望海科技有限公司
 * 作者：张晓明 
 * 版本：V1.0
 * 创建日期：2018年7月17日
 * 修改日期：2018年7月17日
 */
package com.viewhigh.framework.httpclient;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/**
 * @Description  
 * @author zhangxm 
 * @version v1.0
 * @since 2018年7月17日
 */
@Configuration
public class HttpClientWrapper {
	
	//用户代理  默认 谷歌浏览器 
	private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.18 Safari/537.36";

	@Autowired
	private HttpClientConfig httpClientConfig;
	
	/**
	 * 发送get 表单请求
	 * @param url
	 * @return
	 */
	public String doGet(String url){
		return doGet(url, null, null, Consts.UTF_8, null);
	}
	
	/**
	 * 发送get 表单请求
	 * @param url
	 * @param callBack
	 * @return
	 */
	public String doGet(String url, HttpErrorCallBack callBack){
		return doGet(url, null, callBack);
	}
	
	/**
	 * 发送get 表单请求
	 * @param url
	 * @param params
	 * @param callBack
	 * @return
	 */
	public String doGet(String url, Map<String,String> params,  HttpErrorCallBack callBack){
		return doGet(url, params, null, Consts.UTF_8, callBack);
	}
	
	/**
	 * 发送get 表单请求
	 * @param url  路径
	 * @param params  参数
	 * @param header  请求头
	 * @param charset  编码
	 * @param callBack  回调
	 * @return
	 */
	public String doGet(String url, Map<String,String> params, Map<String, String> header, Charset charset, HttpErrorCallBack callBack){
		if(params != null){
			//拼接参数
			StringBuffer sb = new StringBuffer();
			for(Map.Entry<String, String> entry : params.entrySet()){
				sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
			}
			//去掉最后的&
			if(sb.length()>0){
				sb = new StringBuffer(sb.substring(0, sb.length()-1));
				if(url.indexOf("?") > -1){
					url = url + "&" + sb.toString();
				}else{
					url = url + "?" + sb.toString();
				}
			}
		}
		//初始化请求
		CloseableHttpClient httpClient = initHttpClient();
		HttpGet httpGet = initHttpGet(url);
		httpGet.setConfig(initRequestConfig());
		
		if(header != null){
			initHttpRequestBase(httpGet, header);
		}
		
		try {
			//执行请求
			HttpResponse httpResponse = httpClient.execute(httpGet);
			if(httpResponse.getStatusLine().getStatusCode() == 200){
				return EntityUtils.toString(httpResponse.getEntity(), charset);
			}else{
				//其他code 进行回调
				if(callBack != null){
					callBack.callback(httpResponse.getStatusLine().getStatusCode(), httpResponse, null);
				}
			}
		} catch (Exception e) {
			//出现异常的时候 关闭连接  并返回回调接口
			try {
				httpClient.close();
				if(callBack != null){
					callBack.callback(-1, null, e);
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		return "";
	}
	
	/**
	 * 发送 post 表单请求
	 * @param url
	 * @param params
	 * @return
	 */
	public String doPost(String url, Map<String,String> params){
		return doPost(url, params, null, Consts.UTF_8, null, initRequestConfig());
	}
	
	/**
	 * 发送 post 表单请求
	 * @param url
	 * @param params
	 * @return
	 */
	public String doPost(String url, Map<String,String> params,RequestConfig config){
		return doPost(url, params, null, Consts.UTF_8, null, config);
	}
	
	/**
	 * 发送 post 表单请求
	 * @param url
	 * @param params
	 * @param callBack
	 * @return
	 */
	public String doPost(String url, Map<String,String> params,  HttpErrorCallBack callBack){
		return doPost(url, params, null, Consts.UTF_8, callBack, initRequestConfig());
	}
	
	/**
	 * 发送 post 表单请求
	 * @param url  路径
	 * @param params  参数
	 * @param header  请求头
	 * @param charset  编码
	 * @param callBack  回调
	 * @return
	 */
	public String doPost(String url, Map<String,String> params, Map<String, String> header, Charset charset, HttpErrorCallBack callBack,RequestConfig config){
		
		//初始化
		CloseableHttpClient httpClient = initHttpClient();
		HttpPost httpPost = initHttpPost(url);
		httpPost.setConfig(config);
		
		if(header != null){
			initHttpRequestBase(httpPost, header);
		}
		
		//组装参数
		List<NameValuePair> list = new ArrayList<NameValuePair>();
		for(Map.Entry<String, String> entry : params.entrySet()){
			list.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
		}
		
		try {
			//执行请求
			httpPost.setEntity(new UrlEncodedFormEntity(list, Consts.UTF_8));
			HttpResponse httpResponse = httpClient.execute(httpPost);
			if(httpResponse.getStatusLine().getStatusCode() == 200){
				return EntityUtils.toString(httpResponse.getEntity(), charset);
			}else{
				//其他code 进行回调
				if(callBack != null){
					callBack.callback(httpResponse.getStatusLine().getStatusCode(), httpResponse, null);
				}
			}
		} catch (Exception e) {
			//出现异常的时候 关闭连接  并返回回调接口
			try {
				httpClient.close();
				if(callBack != null){
					callBack.callback(-1, null , e);
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
		return "";
	}
	
	/**
	 * 发送 post json请求
	 * @param url
	 * @param jsonContent
	 * @return
	 */
	public String doPostJson(String url, String jsonContent){
		return doPostJson(url, jsonContent, null, Consts.UTF_8, null, initRequestConfig());
	}
	
	/**
	 * 发送 post json请求
	 * @param url
	 * @param jsonContent
	 * @return
	 */
	public String doPostJson(String url, String jsonContent, RequestConfig config){
		return doPostJson(url, jsonContent, null, Consts.UTF_8, null, config);
	}
	
	/**
	 * 发送 post json请求
	 * @param url
	 * @param jsonContent
	 * @param callBack
	 * @return
	 */
	public String doPostJson(String url, String jsonContent,  HttpErrorCallBack callBack){
		return doPostJson(url, jsonContent, null, Consts.UTF_8, callBack, initRequestConfig());
	}
	
	
	/**
	 * 发送 post json请求
	 * @param url  路径
	 * @param params  参数
	 * @param header  请求头
	 * @param charset  编码
	 * @param callBack  回调
	 * @return
	 */
	public String doPostJson(String url, String jsonContent, Map<String, String> header, Charset charset, HttpErrorCallBack callBack){
		return doPostJson(url, jsonContent, header, charset, callBack, initRequestConfig());
	}
	/**
	 * 发送 post json请求
	 * @param url  路径
	 * @param params  参数
	 * @param header  请求头
	 * @param charset  编码
	 * @param callBack  回调
	 * @return
	 */
	public String doPostJson(String url, String jsonContent, Map<String, String> header, Charset charset, HttpErrorCallBack callBack, RequestConfig config){
		
		//初始化
		HttpPost httpPost = initHttpPost(url);
		CloseableHttpClient httpClient = initHttpClient();
		
		httpPost.setConfig(config);
		
		if(header != null){
			initHttpRequestBase(httpPost, header);
		}
		
		//json参数
		StringEntity entity = new StringEntity(jsonContent, charset);
		entity.setContentType("application/json");
		
		try {
			//执行请求
			httpPost.setEntity(entity);
			HttpResponse httpResponse = httpClient.execute(httpPost);
			if(httpResponse.getStatusLine().getStatusCode() == 200){
				return EntityUtils.toString(httpResponse.getEntity(), charset);
			}else{
				//其他code 进行回调
				if(callBack != null){
					callBack.callback(httpResponse.getStatusLine().getStatusCode(), httpResponse,null);
				}
			}
		} catch (Exception e) {
			//出现异常的时候 关闭连接  并返回回调接口
			try {
				httpClient.close();
				if(callBack != null){
					callBack.callback(-1, null,e);
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
		return "";
	}
	
	
	/**
	 * 上传文件
	 * @param url
	 * @param params
	 * @param fileMap
	 * @return
	 */
	public String uploadFile(String url, Map<String, String> params, Map<String, File> fileMap) {
		return uploadFile(url, params, fileMap, null);
	}

	/**
	 * 上传文件
	 * @param url
	 * @param params
	 * @param fileMap
	 * @param header
	 * @return
	 */
	public String uploadFile(String url, Map<String, String> params, Map<String, File> fileMap, Map<String, String> header) {
		String result = null;

		CloseableHttpClient httpClient = initHttpClient();
		HttpPost httpPost = initHttpPost(url);
		
		if(header != null){
			initHttpRequestBase(httpPost, header);
		}

		MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
		multipartEntityBuilder.setCharset(Charset.forName("UTF-8"));
		multipartEntityBuilder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
		// 设置表单参数
		if (params != null && params.size() > 0) {
			Set<String> keySet = params.keySet();
			for (Iterator<String> iterator = keySet.iterator(); iterator.hasNext();) {
				String key = iterator.next();
				StringBody stringBody = new StringBody(params.get(key), ContentType.TEXT_PLAIN);
				multipartEntityBuilder.addPart(key, stringBody);
			}

		}
		// 设置上传的文件参数
		if (fileMap != null && fileMap.size() > 0) {
			Set<String> keySet = fileMap.keySet();
			for (Iterator<String> iterator = keySet.iterator(); iterator.hasNext();) {
				String key = iterator.next();
				FileBody fileBody = new FileBody(fileMap.get(key), ContentType.APPLICATION_OCTET_STREAM);
				multipartEntityBuilder.addPart(key, fileBody);
			}
		}
		httpPost.setEntity(multipartEntityBuilder.build());
		try {
			HttpResponse execute = httpClient.execute(httpPost);
			HttpEntity entity = execute.getEntity();
			result = EntityUtils.toString(entity, Charset.forName("UTF-8"));
		} catch (Exception e) {
			//出现异常的时候 关闭连接  并返回回调接口
			try {
				httpClient.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 下载文件
	 * @param url
	 * @param desFilePath
	 * @param params
	 * @return
	 */
	public File downloadFile(String url, String desFilePath, Map<String, String> params) {
		return downloadFile(url, new File(desFilePath), params);
	}

	/**
	 * 下载文件
	 * @param url
	 * @param file
	 * @param params
	 * @return
	 */
	public File downloadFile(String url, File file, Map<String, String> params) {
		CloseableHttpClient httpClient = initHttpClient();
		HttpPost httpPost = initHttpPost(url);

		BufferedInputStream bufferedInputStream = null;
		BufferedOutputStream bufferedOutputStream = null;
		try {
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			if (params != null) {
				Set<String> keySet = params.keySet();
				for (String key : keySet) {
					nvps.add(new BasicNameValuePair(key, params.get(key)));
				}
			}
			httpPost.setEntity(new UrlEncodedFormEntity(nvps));

			InputStream inputStream = httpClient.execute(httpPost).getEntity().getContent();
			bufferedInputStream = new BufferedInputStream(inputStream);
			bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
			byte[] buff = new byte[1024 * 1024];
			int length = 0;
			while ((length = bufferedInputStream.read(buff)) != -1) {
				bufferedOutputStream.write(buff, 0, length);
				bufferedOutputStream.flush();
			}
		} catch (Exception e) {
			//出现异常的时候 关闭连接  并返回回调接口
			try {
				httpClient.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} finally {
			if (bufferedInputStream != null) {
				try {
					bufferedInputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (bufferedOutputStream != null) {
				try {
					bufferedOutputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return file;
	}
	
	
	
	/**
	 * 初始化请求头
	 * @param base
	 * @param header
	 */
	private void initHttpRequestBase(HttpRequestBase base, Map<String, String> header){
		for(Map.Entry<String, String> entry : header.entrySet()){
			base.addHeader(entry.getKey(), entry.getValue());
		}
	}
	
	
	/**
	 * 设置请求配置  超时时间
	 * @return
	 */
	private RequestConfig initRequestConfig(){
		return RequestConfig.custom()
				.setConnectTimeout(httpClientConfig.getConnectTimeout())
				.setConnectionRequestTimeout(httpClientConfig.getConnectRequestTimeout())
				.setRedirectsEnabled(httpClientConfig.getRedirectEnabled())
				.build();
	}
	
	/**
	 * 初始化 一个 CloseableHttpClient
	 * @return
	 */
	private CloseableHttpClient initHttpClient(){
		return HttpClients.createDefault();
	}
	
	/**
	 * 初始化一个 httpGet
	 * @param url
	 * @return
	 */
	private HttpGet initHttpGet(String url){
		HttpGet httpGet = new HttpGet(url);
		httpGet.addHeader("User-Agent", USER_AGENT);
		return httpGet;
	}
	
	/**
	 * 初始化一个 httpPost
	 * @param url
	 * @return
	 */
	private HttpPost initHttpPost(String url){
		HttpPost httpPost = new HttpPost(url);
		httpPost.addHeader("User-Agent", USER_AGENT);
		return httpPost;
	}
	
}
