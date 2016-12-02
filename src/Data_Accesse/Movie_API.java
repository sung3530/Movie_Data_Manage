package Data_Accesse;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Scanner;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Movie_API {
	 private  String readUrl(String movieCode) throws Exception { 
          BufferedReader reader = null; 
             try {           	 
                    URL url = new URL(
                            "http://www.kobis.or.kr/kobisopenapi/webservice/rest/movie/" 
                            + "searchMovieInfo.json" 
                            + "?key=7b52993eec0a549720861ffc2a8fc2cc" //인증 
                            + "&movieCd=" + movieCode //무비 코드 입력 
                            ); 
                    reader = new BufferedReader(new InputStreamReader(url.openStream(),"UTF-8")); 
                    StringBuffer buffer = new StringBuffer();  
                    String str;
                    while( (str = reader.readLine()) != null){
                         buffer.append(str); 
                    } 
                    return buffer.toString(); 
                } finally {
                    if (reader != null) 
                        reader.close(); 
             }
      } 
	 public int getRunningTime(String movieCode) throws Exception{ 
         JSONParser jsonparser = new JSONParser();
         JSONObject jsonobject = (JSONObject)jsonparser.parse(readUrl(movieCode)); 
         JSONObject json =  (JSONObject) jsonobject.get("movieInfoResult"); 
         JSONObject enty = (JSONObject)json.get("movieInfo"); 
               return  Integer.parseInt((String) enty.get("showTm")); //무비코드에대한 상영시간 return;
  } 
}
