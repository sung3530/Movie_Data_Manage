package MovieManager.RatioOfDays;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import Data_Accesse.DataManager;
import MovieManager.Movie;

public class TuesdayData implements DayData {

	@Override
	public ArrayList<Movie> getMovieData() {
		// TODO Auto-generated method stub

		cl.add(Calendar.DATE, -1);//���� ��¥���ϱ�
        String yesterday = format.format(cl.getTime());
		cl.add(Calendar.DATE, -1);//2���� ��¥���ϱ�
		String twoDaysAgo = format.format(cl.getTime());
		cl.add(Calendar.DATE, -1);//3���� ��¥���ϱ�
        String threeDaysAgo = format.format(cl.getTime());
		cl.add(Calendar.DATE, -1);//4���� ��¥���ϱ�
		String fourthDaysAgo = format.format(cl.getTime());
		cl.add(Calendar.DATE, -1);//5���� ��¥���ϱ�
		String fifthDaysAgo = format.format(cl.getTime());   
		
		try {
			ArrayList<Movie> yesterdayTemp = DataManager.getInstance().getBoxOffice_API().getData(yesterday);
			ArrayList<Movie> twodaysTemp = DataManager.getInstance().getBoxOffice_API().getData(twoDaysAgo);
			ArrayList<Movie> threeDaysTemp = DataManager.getInstance().getBoxOffice_API().getData(threeDaysAgo);
			ArrayList<Movie> fourthDaysTemp = DataManager.getInstance().getBoxOffice_API().getData(fourthDaysAgo);
			ArrayList<Movie> fifthDaysTemp = DataManager.getInstance().getBoxOffice_API().getData(fifthDaysAgo);
			ArrayList<Movie> newData = new ArrayList<Movie>();
			boolean check=true;
			double sum=0.0;
			for (int i = 0; i < yesterdayTemp.size(); i++) { //������ ������ ��ȭ ���ؼ� ���� ��ȭ������ ���������� ����.
				for (int j = 0; j < twodaysTemp.size(); j++) {
					if(yesterdayTemp.get(i).getMovieName().equals(twodaysTemp.get(j).getMovieName())){
						Movie temp=yesterdayTemp.get(i);
						temp.setPercentageChange(yesterdayTemp.get(i).getPredictRate()-twodaysTemp.get(j).getPredictRate());
						temp.setRank(1);
						newData.add(temp);
					}
				}
			}
	
			for (int i = 0; i < twodaysTemp.size(); i++) { //������ ������ ��ȭ ���ؼ� ���� ��ȭ������ ���������� ����.
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
			for (int i = 0; i < threeDaysTemp.size(); i++) { //������ ������ ��ȭ ���ؼ� ���� ��ȭ������ ���������� ����.
				for (int j = 0; j < fourthDaysTemp.size(); j++) {
					if(threeDaysTemp.get(i).getMovieName().equals(fourthDaysTemp.get(j).getMovieName())){
						Movie temp=threeDaysTemp.get(i);
						temp.setPercentageChange(threeDaysTemp.get(i).getPredictRate()-fourthDaysTemp.get(j).getPredictRate());
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
			for (int i = 0; i < fourthDaysTemp.size(); i++) { //������ ������ ��ȭ ���ؼ� ���� ��ȭ������ ���������� ����.
				for (int j = 0; j < fifthDaysTemp.size(); j++) {
					if(fourthDaysTemp.get(i).getMovieName().equals(fifthDaysTemp.get(j).getMovieName())){
						Movie temp=fourthDaysTemp.get(i);
						temp.setPercentageChange(fourthDaysTemp.get(i).getPredictRate()-fifthDaysTemp.get(j).getPredictRate());
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