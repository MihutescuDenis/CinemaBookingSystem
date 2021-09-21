package Code;

public class Seats {
    private Integer id;
    private String movieTitle;
    private boolean[] seats = new boolean[20];

    public Seats(Integer id, String movieTitle, boolean[] seats) {
        this.id = id;
        this.movieTitle = movieTitle;
        this.seats = seats;
    }

    public Seats() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public boolean[] getSeats() {
        return seats;
    }

    public void setSeats(boolean[] seats) {
        this.seats = seats;
    }
}
