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

public class BoxOffice_API {//박스오피스 순위 가져오는 클래스이다. 10위까지 가져온다.
	
	 private  String readUrl(String date) throws Exception { 
         BufferedReader reader = null; 
             try {           	 
                    URL url = new URL(
                    		"http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json"
                            + "?key=d1228fff198216f548292cf93b9c2c41" //인증 
                            + "&targetDt=" + date // 조회날짜  
                            ); 
                    reader = new BufferedReader(new InputStreamReader(url.openStream(),"UTF-8")); //유니코드로 넘어온 것을 변
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
                movie.setExpectedAudience(0);//예상관객수 timeManager에서 사용하는 변수
                movie.setPlayDate(null);//개봉일
                movie.setMovieName((String)entity.get("movieNm"));//영화명
                movie.setPredictRate(Double.parseDouble((String)entity.get("salesShare")));//현재는 매출점유율
                movie.setRunningTime(DataManager.getInstance().getMovie_Accesse().getRunningTime((String)entity.get("movieCd")));
                //영화 상영시간(박스오피스API에서는 상영시간 제공이 없으므로 영화상세정보API에서 상영시간을 가져온다)
                movieList.add(movie);
                }
         return movieList;
  } 
}
