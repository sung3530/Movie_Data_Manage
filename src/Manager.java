
import MovieManager.Movie_Manager;

public class Manager {
	private Movie_Manager movie_Manager;
	private Time_Manager time_Manager;
	
	public Manager(){
		movie_Manager=new Movie_Manager();
		time_Manager = new Time_Manager();
	}
	
	public void makeTable(){
		movie_Manager.movie_Reranked();//�� �Լ��� �����Ű�� movie_Manager�� movieList�ȿ� ���ϴ� ������ ����ȴ�.
		
	}

	
}
