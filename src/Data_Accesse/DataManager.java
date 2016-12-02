package Data_Accesse;

public class DataManager {//BoxOffice_API, Movie_API, Web_Crawlling 을 관리하는 class
	private static DataManager dataManager = new DataManager();	//싱클턴패턴
	private BoxOffice_API boxOffice_API;
	private Web_Crawling web_Crawling;
	private Movie_API movie_API;
	
	public static DataManager getInstance(){
		return dataManager;
	}
	
	DataManager(){
		boxOffice_API=new BoxOffice_API();
		web_Crawling=new Web_Crawling();
		movie_API= new Movie_API();
	}
	public Movie_API getMovie_Accesse(){
		return this.movie_API;
	}
	public BoxOffice_API getBoxOffice_API(){
		return this.boxOffice_API;
		
	}
	public Web_Crawling getWeb_Crawling(){
		return this.web_Crawling;
	}
	
}
