package Classes.Packet;

import Classes.fileio.MovieInput;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;

public class Movie {
    private String name;
    private int year;
    private int duration;
    private ArrayList<String> genres = new ArrayList<>();
    private ArrayList<String> actors = new ArrayList<>();
    private ArrayList<String> countriesBanned = new ArrayList<>();
    private int numLikes;
    private double rating;
    private int numRatings;
    @JsonIgnore
    private ArrayList<Integer> allRatings = new ArrayList<>();

    public Movie(MovieInput input) {
        name = input.getName();
        year = input.getYear();
        duration = input.getDuration();
        genres.addAll(input.getGenres());
        actors.addAll(input.getActors());
        countriesBanned.addAll(input.getCountriesBanned());
        numLikes = 0;
        numRatings = 0;
    }

    public Movie(Movie input) {
        name = input.getName();
        year = input.getYear();
        duration = input.getDuration();
        genres.addAll(input.getGenres());
        actors.addAll(input.getActors());
        countriesBanned.addAll(input.getCountriesBanned());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public ArrayList<String> getGenres() {
        return genres;
    }

    public void setGenres(ArrayList<String> genres) {
        this.genres = genres;
    }

    public ArrayList<String> getActors() {
        return actors;
    }

    public void setActors(ArrayList<String> actors) {
        this.actors = actors;
    }

    public ArrayList<String> getCountriesBanned() {
        return countriesBanned;
    }

    public void setCountriesBanned(ArrayList<String> countriesBanned) {
        this.countriesBanned = countriesBanned;
    }

    public int getNumLikes() {
        return numLikes;
    }

    public void setNumLikes(int numLikes) {
        this.numLikes = numLikes;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getNumRatings() {
        return numRatings;
    }

    public void setNumRatings(int numRatings) {
        this.numRatings = numRatings;
    }

    public ArrayList<Integer> getAllRatings() {
        return allRatings;
    }

    public void setAllRatings(ArrayList<Integer> allRatings) {
        this.allRatings = allRatings;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "name='" + name + '\'' +
                ", year=" + year +
                ", duration=" + duration +
                ", genres=" + genres +
                ", actors=" + actors +
                ", countriesBanned=" + countriesBanned +
                ", numLikes=" + numLikes +
                ", rating=" + rating +
                ", numRatings=" + numRatings +
                '}';
    }
}
