package Classes.Packet;


import java.util.ArrayList;

public class Output {
    private String error;
    private ArrayList<Movie> currentMoviesList = new ArrayList<>();
    private User currentUser;

    public Output() {
        error = null;
        currentUser = null;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public ArrayList<Movie> getCurrentMoviesList() {
        return currentMoviesList;
    }

    public void setCurrentMoviesList(ArrayList<Movie> currentMoviesList) {
        this.currentMoviesList = currentMoviesList;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    @Override
    public String toString() {
        return "Output{" +
                "error='" + error + '\'' +
                ", currentMoviesList=" + currentMoviesList +
                ", currentUser=" + currentUser +
                '}';
    }
}
