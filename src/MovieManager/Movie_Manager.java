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
	private ArrayList<Movie> movieList;//return�� data
	private GetDayData getDayData = new GetDayData();//��, ��, ��, ȭ ���� ���񸮽�Ʈ �������Բ� �Լ� ����.
	private SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd"); //��¥ ����
	Calendar cl = Calendar.getInstance();//���� ��¥ cl�� �Է�.
	public Movie_Manager(){
		movieList=new ArrayList<Movie>();
	}
	
	public ArrayList<Movie> getMovieList() {
		return movieList;
	}
	public void movie_Reranked(){
		double totalRatio=100.0;
        ArrayList<Movie> boxofficData=getDayData.getDayData(cl.get(Calendar.DAY_OF_WEEK)-1).getMovieData();//�� ���Ͽ� �°� �ڽ����ǽ����� data�������� ���� ��ȭ ���� ���������� ��ճ��� movielist�� ����
        ArrayList<Movie> predictData=this.predictExpectedMovie();//�ǽð��������� ��ũ�Ѹ��Ͽ� data���� / movieList ����
        for (int i = 0; i < predictData.size(); i++) {
        	totalRatio-=predictData.get(i).getPredictRate();//100%���� �󿵿����� ��ȭ���� ���� ������������ ���ش�.
			movieList.add(predictData.get(i));//�ǽð��������� ������ �󿵿����� ��ȭ���� ������ ��ȭ�� ������� ��ġ.
		}
       for (int i = 0; i < boxofficData.size(); i++) {//���� 6����ȭ�� �����´�.
        	boxofficData.get(i).setPredictRate(boxofficData.get(i).getPredictRate()*totalRatio/(double)100.0);//�󿵿����ۿ�ȭ ������������ ���� ���� ���������� ���۵��� ����ְ� ��������.
        	movieList.add(boxofficData.get(i));
		}
	}
	private ArrayList<Movie> predictExpectedMovie(){//3�ϵ��� ���������ۿ�ȭ�� ã�Ƽ� ���񸮽�Ʈ�� �����´�.
		this.cl.add(Calendar.DATE, +3);//3�ϵ� ��¥ �˻�.
		String threeLater = format.format(cl.getTime());
		ArrayList<Movie> returnData=new ArrayList<Movie>();
		try {
			ArrayList<Movie> dataTemp= DataManager.getInstance().getWeb_Crawling().Crawl();
			for (int i = 0; i < dataTemp.size(); i++) {
				if(dataTemp.get(i).getPlayDate().equals(threeLater)){
					int expectedAudience= dataTemp.get(i).getExpectedAudience();
					dataTemp.get(i).setExpectedAudience(expectedAudience*5);//�ϴ� ���Ű����� ���ϱ� 5��!!
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
	private int getDaysAudience(int day){//0�Ͽ���~6����� �� ���ϸ��� ��� ������
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
