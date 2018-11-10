package ru.galkov.raceserver;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;


// singletone bean; выделить а не запускать с каждой проверкой. 
// на самом деле это не совсем безопасность, это быстрое отключение старых версий :)
public class Key {
	private String serverKey = ""; // ключ из файла при получении через new Key(); 
	private final String KEY_FILE = "AccessKey.key";
	private static final String KEY = "xzcv4ewattaswrf";

	
	public Key() {
		readKey();
	}
	
	public String getFileKey() {
		return serverKey;
	}
	
	public boolean chkFileKey (String k1) {
		if (k1.equals(serverKey)) return true;
		else return false;
	}
	
	@Deprecated
	public static boolean chkKey(String key1) {
		if (KEY.equals(key1)) return true;
		else return false; 
	}
	
	@Deprecated
	public static String getKey() {
		return KEY;
	}
	
	private void readKey() {
		ApplicationContext appContext = new ClassPathXmlApplicationContext(new String[] {});
		Resource resource = appContext.getResource(KEY_FILE);
		((ClassPathXmlApplicationContext) appContext).close();
		
		// работали ради этого		
		serverKey = readFile(resource);
	}
	
	
	
	private String readFile (Resource resource) {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = null;
		String line;
		try {
				br = new BufferedReader(new InputStreamReader(resource.getInputStream(), "UTF-8"));
				while ((line = br.readLine()) != null) {  sb.append(line);   }
		}
		catch (IOException e) { e.printStackTrace(); }
	    finally {if(br != null) try { br.close(); } catch (IOException e) { e.printStackTrace(); }}
		return sb.toString();
	}
	
}
