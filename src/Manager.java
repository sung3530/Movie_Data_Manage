
import MovieManager.Movie_Manager;

public class Manager {
	private Movie_Manager movie_Manager;
	private Time_Manager time_Manager;
	
	public Manager(){
		movie_Manager=new Movie_Manager();
		time_Manager = new Time_Manager();
	}
	//ㅋㅋㅋㅋ	
public void makeTable(){
		movie_Manager.movie_Reranked();//이 함수를 실행시키면 movie_Manager의 movieList안에 원하는 값들이 저장된다.
		
	}

	
}
