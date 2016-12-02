package MovieManager.RatioOfDays;

import java.util.HashMap;

public class GetDayData {//�߻����丮����
	private HashMap<Integer,DayData> dataFactory = new HashMap<Integer,DayData>();
	
	public GetDayData(){
		this.dataFactory.put(0, new SundayData());
		this.dataFactory.put(1, new MondayData());
		this.dataFactory.put(2, new TuesdayData());
		this.dataFactory.put(6, new SaturdayData());
		this.dataFactory.put(0,new ExceptDays());
	}
	public DayData getDayData(int day){
		if(day==0||day==1||day==2||day==6){//0�Ͽ��� 1������ 2ȭ���� 6 �����
			return this.dataFactory.get(day);//�������� �̿��� ��ȯ
			
		}
		return dataFactory.get(0);
	}
}
