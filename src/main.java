import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.json.simple.parser.ParseException;

import Data_Accesse.DataManager;
import MovieManager.Movie;
import MovieManager.RatioOfDays.MondayData;
import MovieManager.RatioOfDays.SaturdayData;

public class main {

		
	public static void main(String args[]) throws UnsupportedEncodingException, IOException, ParseException{
		try {
			SaturdayData mondayData= new SaturdayData();
			ArrayList<Movie> test=mondayData.getMovieData();
			for (int i = 0; i < test.size(); i++) {
				System.out.println(test.get(i).getMovieName()+"+"+test.get(i).getPredictRate());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		 /*StringBuffer buffer;
	         BufferedReader reader = null; 
	             try {           	 
	                    URL url = new URL(
	                    		"http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json"
	                            + "?key=d1228fff198216f548292cf93b9c2c41" //���� 
	                            + "&targetDt=" + "20161125" // ��ȸ��¥  
	                            ); 
	                    reader = new BufferedReader(new InputStreamReader(url.openStream(),"UTF-8")); //�����ڵ�� �Ѿ�� ���� ��
	                     buffer = new StringBuffer();  
	                    String str;
	                    while( (str = reader.readLine()) != null){
	                         buffer.append(str); 
	                    } 
	                   
	                } finally {
	                    if (reader != null) 
	                        reader.close(); 
	             } 

	         JSONParser jsonparser = new JSONParser();
	         JSONObject jsonobject = (JSONObject)jsonparser.parse(buffer.toString()); 
	         JSONObject json =  (JSONObject) jsonobject.get("boxOfficeResult"); 
	         JSONArray array = (JSONArray)json.get("dailyBoxOfficeList"); 
	         ArrayList<Movie> movieList = new ArrayList<Movie>();
	         for(int i = 0 ; i < array.size(); i++){ 
	                JSONObject entity = (JSONObject)array.get(i); 
	                Movie movie = new Movie();
	                movie.setExpectedAudience(0);//��������� timeManager���� ����ϴ� ����
	                movie.setPlayDate(null);//������
	                movie.setMovieName((String)entity.get("movieNm"));//��ȭ��
	                movie.setPredictRate(Double.parseDouble((String)entity.get("salesShare")));//����� ����������
	                 //��ȭ �󿵽ð�(�ڽ����ǽ�API������ �󿵽ð� ������ �����Ƿ� ��ȭ������API���� �󿵽ð��� �����´�)
	                movieList.add(movie);
	                }
	         for (int i = 0; i < movieList.size(); i++) {
				System.out.println(movieList.get(i).getMovieName()+"+"+movieList.get(i).getPredictRate());
			}
	         */
	}
}
