package Classes.Packet;

import Classes.fileio.CredentialsInput;
import Classes.fileio.UserInput;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;

public class User {

    private CredentialsInput credentials;
    private int tokensCount;
    private int numFreePremiumMovies;
    private ArrayList<Movie> purchasedMovies = new ArrayList<>();
    private ArrayList<Movie> watchedMovies = new ArrayList<>();
    private ArrayList<Movie> likedMovies = new ArrayList<>();
    private ArrayList<Movie> ratedMovies = new ArrayList<>();
    @JsonIgnore
    private ArrayList<Movie> allowedMovies = new ArrayList<>();

    public User() {
        tokensCount = 0;
        numFreePremiumMovies = 15;
    }


    public User(UserInput input) {
        credentials = input.getCredentials();
        tokensCount = 0;
        numFreePremiumMovies = 15;
    }

    public User(User input) {
        this.credentials = input.getCredentials();
        tokensCount = 0;
        numFreePremiumMovies = 15;
    }

    public CredentialsInput getCredentials() {
        return credentials;
    }

    public void setCredentials(CredentialsInput credentials) {
        this.credentials = credentials;
    }

    public int getTokensCount() {
        return tokensCount;
    }

    public void setTokensCount(int tokensCount) {
        this.tokensCount = tokensCount;
    }

    public int getNumFreePremiumMovies() {
        return numFreePremiumMovies;
    }

    public void setNumFreePremiumMovies(int numFreePremiumMovies) {
        this.numFreePremiumMovies = numFreePremiumMovies;
    }

    public ArrayList<Movie> getPurchasedMovies() {
        return purchasedMovies;
    }

    public void setPurchasedMovies(ArrayList<Movie> purchasedMovies) {
        this.purchasedMovies = purchasedMovies;
    }

    public ArrayList<Movie> getWatchedMovies() {
        return watchedMovies;
    }

    public void setWatchedMovies(ArrayList<Movie> watchedMovies) {
        this.watchedMovies = watchedMovies;
    }

    public ArrayList<Movie> getLikedMovies() {
        return likedMovies;
    }

    public void setLikedMovies(ArrayList<Movie> likedMovies) {
        this.likedMovies = likedMovies;
    }

    public ArrayList<Movie> getRatedMovies() {
        return ratedMovies;
    }

    public void setRatedMovies(ArrayList<Movie> ratedMovies) {
        this.ratedMovies = ratedMovies;
    }

    public ArrayList<Movie> getAllowedMovies() {
        return allowedMovies;
    }

    public void setAllowedMovies(ArrayList<Movie> allowedMovies) {
        this.allowedMovies = allowedMovies;
    }

    @Override
    public String toString() {
        return "{" +
                "credentials=" + credentials +
                ", tokensCount=" + tokensCount +
                ", numFreePremiumMovies=" + numFreePremiumMovies +
                ", purchasedMovies=" + purchasedMovies +
                ", watchedMovies=" + watchedMovies +
                ", likedMovies=" + likedMovies +
                ", ratedMovies=" + ratedMovies +
                '}';
    }
}
