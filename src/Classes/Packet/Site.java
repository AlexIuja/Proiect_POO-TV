package Classes.Packet;

import Classes.Packet.Actions.*;
import Classes.Packet.Pages.*;
import Classes.fileio.ActionInput;
import Classes.fileio.MovieInput;
import Classes.fileio.UserInput;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.util.ArrayList;
import java.util.Arrays;

public class Site {
    private ArrayList<User> usersIn = new ArrayList<>();
    private ArrayList<Movie> moviesIn = new ArrayList<>();
    private ArrayList<Action> actionsIn = new ArrayList<>();
    private ArrayList<SitePage> availablePages = new ArrayList<>();
    private SitePage currentPage;
    private User currentUser;


    public Site(ArrayList<UserInput> usersInput, ArrayList<MovieInput> moviesInput, ArrayList<ActionInput> actionsInput) {
        for(UserInput input : usersInput)
            usersIn.add(new User(input));
        for(MovieInput input : moviesInput)
            moviesIn.add(new Movie(input));
        for(ActionInput input : actionsInput)
            if(input.getType().equals("change page"))
                actionsIn.add(new ChangePage(input, this));
            else if(input.getFeature().equals("login"))
                actionsIn.add(new Login(input, this));
            else if(input.getFeature().equals("register"))
                actionsIn.add(new Register(input, this));
            else if(input.getFeature().equals("search"))
                actionsIn.add(new Search(input, this));
            else if(input.getFeature().equals("filter"))
                actionsIn.add(new Filter(input, this));
            else if(input.getFeature().equals("purchase"))
                actionsIn.add(new Purchase(input, this));
            else if(input.getFeature().equals("watch"))
                actionsIn.add(new Watch(input, this));
            else if(input.getFeature().equals("like"))
                actionsIn.add(new Like(input, this));
            else if(input.getFeature().equals("rate"))
                actionsIn.add(new Rate(input, this));
            else if(input.getFeature().equals("buy premium account"))
                actionsIn.add(new BuyPrem(input, this));
            else if(input.getFeature().equals("buy tokens"))
                actionsIn.add(new BuyTokens(input, this));

        availablePages.add(new HomepageNeautentificat());
        availablePages.add(new LoginPage());
        availablePages.add(new RegisterPage());
        availablePages.add(new HomepageAutentificat());
        availablePages.add(new MoviesPage());
        availablePages.add(new SeeDetailsPage());
        availablePages.add(new UpgradesPage());
        availablePages.add(new LogoutPage());
        availablePages.get(0).setAllowedPagesToChange(new ArrayList<>(Arrays.asList(availablePages.get(1), availablePages.get(2))));
        availablePages.get(3).setAllowedPagesToChange(new ArrayList<>(Arrays.asList(availablePages.get(4), availablePages.get(6), availablePages.get(7))));
        availablePages.get(4).setAllowedPagesToChange(new ArrayList<>(Arrays.asList(availablePages.get(3), availablePages.get(5), availablePages.get(7))));
        availablePages.get(5).setAllowedPagesToChange(new ArrayList<>(Arrays.asList(availablePages.get(3), availablePages.get(4), availablePages.get(6), availablePages.get(7))));
        availablePages.get(6).setAllowedPagesToChange(new ArrayList<>(Arrays.asList(availablePages.get(3), availablePages.get(4), availablePages.get(7))));
        currentPage = availablePages.get(0);
    }

    public void setUsers(ArrayList<UserInput> usersInput) {
        for(UserInput input : usersInput)
            usersIn.add(new User(input));
    }

    public void setMovies(ArrayList<MovieInput> moviesInput) {
        for(MovieInput input : moviesInput)
            moviesIn.add(new Movie(input));
    }

    public ArrayList<Action> getActionsIn() {
        return actionsIn;
    }

    public void setActionsIn(ArrayList<Action> actionsIn) {
        this.actionsIn = actionsIn;
    }

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

    public SitePage getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(SitePage currentPage) {
        this.currentPage = currentPage;
    }

    public ArrayList<SitePage> getAvailablePages() {
        return availablePages;
    }

    public void setAvailablePages(ArrayList<SitePage> availablePages) {
        this.availablePages = availablePages;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }


    public void exec(ArrayList<Action> actions) {
        ActionVisitor visitor = new ActionVisitorImpl();

        for(Action action : actions){
            Output p = action.accept(visitor);
            if(p != null)
                System.out.println(currentPage + " : " + action + " -> " + p);
//            if(action.accept(visitor) == null)
//                System.out.println(currentPage + " : " + action + "->" + action.accept(visitor));
            }
    }
}
