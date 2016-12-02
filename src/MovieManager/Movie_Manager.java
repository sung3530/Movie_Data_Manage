package MovieManager;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;

import Data_Accesse.DataManager;
import MovieManager.RatioOfDays.GetDayData;

public class Movie_Manager {
	private ArrayList<Movie> movieList;//return할 data
	private GetDayData getDayData = new GetDayData();//토, 일, 월, 화 별로 무비리스트 가져오게끔 함수 실행.
	private SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd"); //날짜 형식
	Calendar cl = Calendar.getInstance();//오늘 날짜 cl에 입력.
	public Movie_Manager(){
		movieList=new ArrayList<Movie>();
	}
	
	public ArrayList<Movie> getMovieList() {
		return movieList;
	}
	public void movie_Reranked(){
		double totalRatio=100.0;
        ArrayList<Movie> boxofficData=getDayData.getDayData(cl.get(Calendar.DAY_OF_WEEK)-1).getMovieData();//각 요일에 맞게 박스오피스에서 data가져온후 같은 영화 명끼리 매출점유율 평균내고 movielist를 리턴
        ArrayList<Movie> predictData=this.predictExpectedMovie();//실시간예매율을 웹크롤링하여 data수집 / movieList 리턴
        for (int i = 0; i < predictData.size(); i++) {
        	totalRatio-=predictData.get(i).getPredictRate();//100%에서 상영예정작 영화들의 예상 매출점유율을 빼준다.
			movieList.add(predictData.get(i));//실시간예매율로 가져온 상영예정작 영화들은 상영중인 영화와 관계없이 배치.
		}
       for (int i = 0; i < boxofficData.size(); i++) {//상위 6개영화만 가져온다.
        	boxofficData.get(i).setPredictRate(boxofficData.get(i).getPredictRate()*totalRatio/(double)100.0);//상영예정작영화 매출점유율을 빼고 남은 점유율에서 상영작들을 집어넣게 비율조절.
        	movieList.add(boxofficData.get(i));
		}
	}
	private ArrayList<Movie> predictExpectedMovie(){//3일뒤의 예정개봉작영화만 찾아서 무비리스트로 가져온다.
		this.cl.add(Calendar.DATE, +3);//3일뒤 날짜 검색.
		String threeLater = format.format(cl.getTime());
		ArrayList<Movie> returnData=new ArrayList<Movie>();
		try {
			ArrayList<Movie> dataTemp= DataManager.getInstance().getWeb_Crawling().Crawl();
			for (int i = 0; i < dataTemp.size(); i++) {
				if(dataTemp.get(i).getPlayDate().equals(threeLater)){
					int expectedAudience= dataTemp.get(i).getExpectedAudience();
					dataTemp.get(i).setExpectedAudience(expectedAudience*5);//일단 예매관객수 곱하기 5배!!
					dataTemp.get(i).setPredictRate(((double)dataTemp.get(i).getExpectedAudience()/(double)this.getDaysAudience((Calendar.DAY_OF_WEEK)-1))*100);
					returnData.add(dataTemp.get(i));
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return returnData;
		
	}
	private int getDaysAudience(int day){//0일요일~6토요일 각 요일마다 평균 관객수
		if(day==0)
			return 894000;
		else if(day==1)
			return 390000;
		else if(day==2)
			return 353000;
		else if(day==3)
			return 454000;
		else if(day==4)
			return 454000;
		else if(day==5)
			return 562000;
		else if(day==6)
			return 980000;
		return 0;
	}
}
