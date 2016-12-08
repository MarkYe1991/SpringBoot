package com.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import javax.servlet.ServletRequest;

import com.api.Cmd;
import com.api.Message;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;

public class StarBucksUtil {

	public static boolean isLegalSign(ServletRequest request) throws ClassNotFoundException, IllegalAccessException, InstantiationException  {
		Cmd originCmd = getOriginCmd(request);

//		String compareSource = PropertiesUtil.getCommonProperties().getProperty("baidu_source");
//		String compareSecret = PropertiesUtil.getCommonProperties().getProperty("baidu_secret");
		String compareSource = "baidu_source";
		String compareSecret = "baidu_secret";

		Cmd compareCmd = new Cmd(originCmd.getCmd(), compareSource, compareSecret, originCmd.getTicket(),
				originCmd.getTimestamp(), originCmd.getBody());
		return compareCmd.getSign().equals(originCmd.getSign());
	}

	public static Cmd getOriginCmd(ServletRequest request) {

		Cmd originCmd = new Gson().fromJson(getBodyString(request), Cmd.class);
		@SuppressWarnings("rawtypes")
		LinkedTreeMap dataMap = (LinkedTreeMap) ((LinkedTreeMap) originCmd.getBody()).get("data");

		Message messageBody = new Message((String) dataMap.get("sender"), (String) dataMap.get("content"));
		originCmd.setBody(messageBody);

		return originCmd;
	}

	public static String getBodyString(ServletRequest request) {
		StringBuilder sb = new StringBuilder();
		InputStream inputStream = null;
		BufferedReader reader = null;
		try {
			inputStream = request.getInputStream();
			reader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
			String line = "";
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return sb.toString();
	}

}
