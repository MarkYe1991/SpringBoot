package com.api;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import lombok.Data;

@Data
public class Cmd {
	private String cmd;
	private int timestamp;
	private String version;
	private String ticket;
	private String source;
	private String sign;
	private String secret;
	private Object body;

	public Cmd(){}

	public Cmd(String cmd, String source, String secret, Object body) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
		this.ticket = UUID.randomUUID().toString().toUpperCase();
		this.timestamp = (int)(System.currentTimeMillis() / 1000);

		generatePublicProperties( cmd,  source,  secret, body);
	}

	public Cmd(String cmd, String source, String secret, String ticket, int timestamp, Object body) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
		this.ticket = ticket == null ? UUID.randomUUID().toString().toUpperCase() : ticket;
		this.timestamp = timestamp == 0 ? (int)(System.currentTimeMillis() / 1000): timestamp;
		generatePublicProperties(cmd,  source,  secret, body);
	}

	private void generatePublicProperties(String cmd, String source, String secret,Object body) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
		this.cmd = cmd;
//		this.version = PropertiesUtil.getCommonProperties().getProperty("baiduApiVersion"); //TODO
		this.version = "3";
		this.source = source;
		this.secret = secret;
		this.body = body;


		Class<?> serializerClass = Class.forName(body.getClass().getName() + "Serializer");
		Gson gson = new GsonBuilder()
				.registerTypeAdapter(Cmd.class, new CmdSerializer())
				.registerTypeAdapter(body.getClass(),serializerClass.newInstance())
				.disableHtmlEscaping()
				.create();
		String signJson = gson.toJson(this);
		signJson = signJson.replace("/", "\\/");
		signJson = chinaToUnicode(signJson);
		this.sign = getMD5(signJson);
	}

//	public String generateRequestJson(Cmd cmd) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
//		Class serializerClass = Class.forName(body.getClass().getName() + "Serializer");
//		Gson gson = new GsonBuilder()
//				.registerTypeAdapter(Cmd.class, new CmdSerializer())
//				.registerTypeAdapter(cmd.getBody().getClass(), serializerClass.newInstance())
//				.disableHtmlEscaping()
//				.create();
//		cmd.setSecret(null);
//		String requestJson = gson.toJson(cmd);
//		requestJson = requestJson.replace("/", "\\/");
//		requestJson = chinaToUnicode(requestJson);
//		return requestJson;
//	}

	/**
	 * MD5
	 * @param input
	 * @return
	 */
	public String getMD5(String input) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] messageDigest = md.digest(input.getBytes());
			BigInteger number = new BigInteger(1, messageDigest);
			String hashtext = number.toString(16);
			// Now we need to zero pad it if you actually want the full 32 chars.
			while (hashtext.length() < 32) {
				hashtext = "0" + hashtext;
			}
			return hashtext.toUpperCase();
		}
		catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * @param str
	 * @return
	 */
	public String chinaToUnicode(String str){
		String result="";
		for (int i = 0; i < str.length(); i++){
			int chr1 = str.charAt(i);
			if(chr1>=19968&&chr1<=171941){//锟斤拷锟街凤拷围 \u4e00-\u9fa5 (锟斤拷锟斤拷)
				result+="\\u" + Integer.toHexString(chr1);
			}else{
				result+=str.charAt(i);
			}
		}
		return result;
	}

}
