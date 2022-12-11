package Classes.Packet.Pages;

import Classes.Packet.Movie;

public class SeeDetailsPage extends SitePage{
    private Movie movie;
    public SeeDetailsPage() {
        super("see details");
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
}
