package Classes.Packet;

import java.util.ArrayList;

public class Site {
    private ArrayList<User> usersIn;
    private ArrayList<Movie> moviesIn;



    public ArrayList<User> getUsersIn() {
        return usersIn;
    }

    public void setUsersIn(ArrayList<User> usersIn) {
        this.usersIn = usersIn;
    }

    public ArrayList<Movie> getMoviesIn() {
        return moviesIn;
    }

    public void setMoviesIn(ArrayList<Movie> moviesIn) {
        this.moviesIn = moviesIn;
    }
}
