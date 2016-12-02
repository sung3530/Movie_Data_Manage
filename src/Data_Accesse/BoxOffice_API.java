package Data_Accesse;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.Box;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import MovieManager.Movie;

public class BoxOffice_API {//�ڽ����ǽ� ���� �������� Ŭ�����̴�. 10������ �����´�.
	
	 private  String readUrl(String date) throws Exception { 
         BufferedReader reader = null; 
             try {           	 
                    URL url = new URL(
                    		"http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json"
                            + "?key=d1228fff198216f548292cf93b9c2c41" //���� 
                            + "&targetDt=" + date // ��ȸ��¥  
                            ); 
                    reader = new BufferedReader(new InputStreamReader(url.openStream(),"UTF-8")); //�����ڵ�� �Ѿ�� ���� ��
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
	 public ArrayList<Movie> getData(String date) throws Exception{ 
         JSONParser jsonparser = new JSONParser();
         JSONObject jsonobject = (JSONObject)jsonparser.parse(readUrl(date)); 
         JSONObject json =  (JSONObject) jsonobject.get("boxOfficeResult"); 
         JSONArray array = (JSONArray)json.get("dailyBoxOfficeList"); 
         ArrayList<Movie> movieList = new ArrayList<Movie>();
         for(int i = 0 ; i < 6; i++){ 
                JSONObject entity = (JSONObject)array.get(i); 
                Movie movie = new Movie();
                movie.setExpectedAudience(0);//��������� timeManager���� ����ϴ� ����
                movie.setPlayDate(null);//������
                movie.setMovieName((String)entity.get("movieNm"));//��ȭ��
                movie.setPredictRate(Double.parseDouble((String)entity.get("salesShare")));//����� ����������
                movie.setRunningTime(DataManager.getInstance().getMovie_Accesse().getRunningTime((String)entity.get("movieCd")));
                //��ȭ �󿵽ð�(�ڽ����ǽ�API������ �󿵽ð� ������ �����Ƿ� ��ȭ������API���� �󿵽ð��� �����´�)
                movieList.add(movie);
                }
         return movieList;
  } 
}
