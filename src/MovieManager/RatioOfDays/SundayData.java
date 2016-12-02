package MovieManager.RatioOfDays;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import Data_Accesse.DataManager;
import MovieManager.Movie;

public class SundayData implements DayData{

	@Override
	public ArrayList<Movie> getMovieData() {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub();
				cl.add(Calendar.DATE, -1);//어제 날짜구하기
		        String yesterday = format.format(cl.getTime());
				cl.add(Calendar.DATE, -1);//2일전 날짜구하기
				String twoDaysAgo = format.format(cl.getTime());
				cl.add(Calendar.DATE, -1);//3일전 날짜구하기
				String threeDaysAgo = format.format(cl.getTime());
				
				try {
					ArrayList<Movie> yesterdayTemp = DataManager.getInstance().getBoxOffice_API().getData(yesterday);
					ArrayList<Movie> twodaysTemp = DataManager.getInstance().getBoxOffice_API().getData(twoDaysAgo);
					ArrayList<Movie> threeDaysTemp = DataManager.getInstance().getBoxOffice_API().getData(threeDaysAgo);
					//하루 의 박스오피스 상위 랭킹 6위까지 긁어온다.
					ArrayList<Movie> newData = new ArrayList<Movie>();//return할 데이타이다.
					boolean check=true;//영화 중복체크 변수
					double sum=0.0;
					for (int i = 0; i < yesterdayTemp.size(); i++) { //어제랑 그저께 영화 비교해서 같은 영화명끼리 매출점유율 덧셈.
						for (int j = 0; j < twodaysTemp.size(); j++) {
							if(yesterdayTemp.get(i).getMovieName().equals(twodaysTemp.get(j).getMovieName())){
								Movie temp=yesterdayTemp.get(i);
								temp.setPercentageChange(yesterdayTemp.get(i).getPredictRate()-twodaysTemp.get(j).getPredictRate());
								temp.setRank(1);
								newData.add(temp);
							}
						}
					}
					for (int i = 0; i < twodaysTemp.size(); i++) { //어제랑 그저께 영화 비교해서 같은 영화명끼리 매출점유율 덧셈.
						for (int j = 0; j < threeDaysTemp.size(); j++) {
							if(threeDaysTemp.get(j).getMovieName().equals(twodaysTemp.get(i).getMovieName())){
								Movie temp=twodaysTemp.get(i);
								temp.setPercentageChange(twodaysTemp.get(i).getPredictRate()-threeDaysTemp.get(j).getPredictRate());
								temp.setRank(1);
								for (int k = 0; k < newData.size(); k++) {
									if(newData.get(k).getMovieName().equals(temp.getMovieName())){
										newData.get(k).setPercentageChange(newData.get(k).getPercentageChange()+temp.getPercentageChange());
										newData.get(k).setRank(newData.get(k).getRank()+temp.getRank());
										check=false;
									}
								}
								if(check)
									newData.add(temp);
								check=true;
							}
						}
					}
					for (int i = 0; i < newData.size(); i++) {
						newData.get(i).setPercentageChange(newData.get(i).getPercentageChange()/newData.get(i).getRank());
					}
					
					for (int i = 0; i < yesterdayTemp.size(); i++) {
						for (int j = 0; j < newData.size(); j++) {
							if(yesterdayTemp.get(i).getMovieName().equals(newData.get(j).getMovieName()))
								yesterdayTemp.get(i).setPredictRate(yesterdayTemp.get(i).getPredictRate()+newData.get(j).getPercentageChange());	
						}				
						sum+=yesterdayTemp.get(i).getPredictRate();
					}
					
					for (int i = 0; i < yesterdayTemp.size(); i++) {
						yesterdayTemp.get(i).setPredictRate(yesterdayTemp.get(i).getPredictRate()/sum);
					}
					return yesterdayTemp;
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return null;
	}
}
