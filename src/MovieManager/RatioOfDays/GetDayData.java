package MovieManager.RatioOfDays;

import java.util.HashMap;

public class GetDayData {//추상팩토리패턴
	private HashMap<Integer,DayData> dataFactory = new HashMap<Integer,DayData>();
	
	public GetDayData(){
		this.dataFactory.put(0, new SundayData());
		this.dataFactory.put(1, new MondayData());
		this.dataFactory.put(2, new TuesdayData());
		this.dataFactory.put(6, new SaturdayData());
		this.dataFactory.put(0,new ExceptDays());
	}
	public DayData getDayData(int day){
		if(day==0||day==1||day==2||day==6){//0일요일 1월요일 2화요일 6 토요일
			return this.dataFactory.get(day);//다형성을 이용한 반환
			
		}
		return dataFactory.get(0);
	}
}
