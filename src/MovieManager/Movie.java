package MovieManager;

import java.util.Comparator;

public class Movie implements Comparable<Movie>, Cloneable{
	private int rank;
	private String movieName;
	private String playDate;
	private double predictRate;
	private int runningTime;
	private int expectedAudience;
	private double percentageChange;
	
	public Movie(){
		this.rank=0;
		this.percentageChange=0.0;
	}
	
	public double getPredictRate() {
		return predictRate;
	}
	public void setPredictRate(double predictRate) {
		this.predictRate = predictRate;
	}
	public int getRunningTime() {
		return runningTime;
	}
	public void setRunningTime(int runningTime) {
		this.runningTime = runningTime;
	}
	public int getExpectedAudience() {
		return expectedAudience;
	}
	public void setExpectedAudience(int expectedAudience) {
		this.expectedAudience = expectedAudience;
	}
	public String getMovieName() {
		return movieName;
	}
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	public String getPlayDate() {
		return playDate;
	}
	public void setPlayDate(String playDate) {
		this.playDate = playDate;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public static Comparator<Movie> MovieNameComparator=new Comparator<Movie>(){//매출점유율 순으로 soritng할때 쓰는 함수

		@Override
		public int compare(Movie arg0, Movie arg1) {
			// TODO Auto-generated method stub
			double arg0_rate=arg0.getPredictRate();
			double arg1_rate=arg1.getPredictRate();
			if(arg0_rate>arg1_rate)
				return -1;
			else if(arg0_rate<arg1_rate)
				return 1;
			
			return 0; 
		}
		
		
	};
	
	@Override
	public int compareTo(Movie cmp_movie) {
		if(this.predictRate >= cmp_movie.getPredictRate()){
			return -1;
		}
		else
			return 1;
	}
	public Movie clone() throws CloneNotSupportedException{
		Movie movie =(Movie)super.clone();
		movie.predictRate = this.predictRate;
		movie.movieName = this.movieName;
		movie.expectedAudience = this.expectedAudience;
		movie.runningTime = this.runningTime;
		return movie;
	}

	public double getPercentageChange() {
		return percentageChange;
	}

	public void setPercentageChange(double percentageChange) {
		this.percentageChange = percentageChange;
	}
}
