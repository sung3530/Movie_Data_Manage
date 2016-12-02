package MovieManager.RatioOfDays;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import MovieManager.Movie;

public interface DayData {
	SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
    Calendar cl = Calendar.getInstance();
    public ArrayList<Movie> getMovieData();
}
