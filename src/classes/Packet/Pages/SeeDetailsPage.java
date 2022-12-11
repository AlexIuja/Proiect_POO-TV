package classes.Packet.Pages;

import classes.Packet.Movie;

public final class SeeDetailsPage extends SitePage {
    private Movie movie;
    public SeeDetailsPage() {
        super("see details");
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(final Movie movie) {
        this.movie = movie;
    }
}
