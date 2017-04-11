package com.viki.json;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Scanner;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class NumberofObject {

	public static int n=0,t=0;
	
	public static void NumbersOfResponseObject(int p) throws JSONException, MalformedURLException, IOException{

		  for(int page=1;page<=p;page++)
		  {		
			  String URL="http://api.viki.io/v4/videos.json?app=100250a&per_page=10&page=";
			  StringBuilder builder = new StringBuilder();
			  builder.append(URL);
			  builder.append(page);
			  
			  JSONObject json = new JSONObject(IOUtils.toString(new URL(builder.toString()), Charset.forName("UTF-8")));
			  //System.out.println(json.toString());

			  Boolean more =json.getBoolean("more");
			  /*if value of "more" is negative. Then it is out of scope*/
			  if(!more)
				  break;
			  /*Get the response objects and saved as Array*/
			  JSONArray arr = json.getJSONArray("response");
			  for (int i = 0; i < arr.length(); i++)
				  arr.get(i);
			  Iterator<?> i = arr.iterator();
			  /*Each response object has flags objects and each flags object has the hd*/
			  while (i.hasNext()) {
				  JSONObject innerObj = (JSONObject) i.next();
				  if(innerObj.getJSONObject("flags").getBoolean("hd"))
				  {
					  n++;
				  }
				  else{
					  t++;
				  }  
			  }
		  }
		  
		  System.out.println("The number of objects have flags:hd set to true is "+n);
		  System.out.println("The number of objects have flags:hd set to false is "+t);
	}


	public static void main(String[] args) throws IOException {
		
		System.out.println("Enter how many pages you require: ");
		Scanner scanner = new Scanner(System.in);
		int p = scanner.nextInt();
		NumbersOfResponseObject(p);
	}
	
}
