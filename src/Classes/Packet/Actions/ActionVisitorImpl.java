package Classes.Packet.Actions;

import Classes.Packet.Movie;
import Classes.Packet.Output;
import Classes.Packet.Site;
import Classes.Packet.User;
import Classes.fileio.CredentialsInput;

import java.util.ArrayList;
import java.util.Comparator;

public class ActionVisitorImpl implements ActionVisitor{
    @Override
    public Output visit(ChangePage changePage, Site site) {
        Output output = new Output();
        for(int i = 0; i < site.getCurrentPage().getAllowedPagesToChange().size(); i++)
            if(changePage.getPage().equals(site.getCurrentPage().getAllowedPagesToChange().get(i).getPageName())) {
                if(changePage.getPage().equals("login")) {
                    site.setCurrentPage(site.getAvailablePages().get(1));
                    return null;
                }
                else if(changePage.getPage().equals("register")) {
                    site.setCurrentPage(site.getAvailablePages().get(2));
                    return null;
                }
                else if(changePage.getPage().equals("logout")) {
                    site.setCurrentPage(site.getAvailablePages().get(0));
                    site.setCurrentUser(null);
                    return null;
                }
                else if(changePage.getPage().equals("movies")) {
                    site.setCurrentPage(site.getAvailablePages().get(4));
                    output.setCurrentUser(site.getCurrentUser());
                    ArrayList<Movie> currMovies = new ArrayList<>();
                    for(int j = 0; j < site.getMoviesIn().size(); j++) {
                        if(!site.getMoviesIn().get(j).getCountriesBanned().contains(site.getCurrentUser().getCredentials().getCountry()))
                            currMovies.add(site.getMoviesIn().get(j));
                    }
                    site.getCurrentUser().setAllowedMovies(currMovies);
                    output.setCurrentMoviesList(currMovies);
                    output.setError(null);
                    return output;
                }
                else if(changePage.getPage().equals("upgrades")) {
                    site.setCurrentPage(site.getAvailablePages().get(6));
                    return null;
                }
            }
        output.setError("Error");
        output.setCurrentUser(null);
        output.setCurrentMoviesList(null);
        return output;
    }

    @Override
    public Output visit(BuyPrem buyPrem, Site site) {
        Output output = new Output();
        int tokens = site.getCurrentUser().getTokensCount();
        if(site.getCurrentPage().equals(site.getAvailablePages().get(6))) {
            if(tokens >= 10) {
                site.getCurrentUser().getCredentials().setAccountType("premium");
                site.getCurrentUser().setTokensCount(tokens - 10);
                return null;
            }
        }
        output.setError("Error");
        output.setCurrentUser(null);
        output.setCurrentMoviesList(null);
        return output;
    }

    @Override
    public Output visit(BuyTokens buyTokens, Site site) {
        Output output = new Output();
        int balance = site.getCurrentUser().getCredentials().getBalance();
        if(site.getCurrentPage().equals(site.getAvailablePages().get(6))) {
            if(balance >= buyTokens.getCount()) {
                site.getCurrentUser().setTokensCount(buyTokens.getCount());
                site.getCurrentUser().getCredentials().setBalance(balance - buyTokens.getCount());
                return null;
            }
        }
        output.setError("Error");
        output.setCurrentUser(null);
        output.setCurrentMoviesList(null);
        return output;
    }

    @Override
    public Output visit(Filter filter, Site site) {
        Output output = new Output();
        ArrayList<Movie> currMovies = new ArrayList<>();
        if(site.getCurrentPage().equals(site.getAvailablePages().get(4))) {
            if (filter.getContains() != null){
                for (int i = 0; i < site.getCurrentUser().getAllowedMovies().size(); i++) {
                    if(filter.getContains().getActors() != null && filter.getContains().getGenre() == null) {
                        if(site.getCurrentUser().getAllowedMovies().get(i).getActors().containsAll(filter.getContains().getActors()))
                            currMovies.add(site.getCurrentUser().getAllowedMovies().get(i));
                    }
                    if(filter.getContains().getActors() == null && filter.getContains().getGenre() != null) {
                        if(site.getCurrentUser().getAllowedMovies().get(i).getGenres().containsAll(filter.getContains().getGenre()))
                            currMovies.add(site.getCurrentUser().getAllowedMovies().get(i));
                    }
                    if(filter.getContains().getActors() != null && filter.getContains().getGenre() != null) {
                        if(site.getCurrentUser().getAllowedMovies().get(i).getActors().containsAll(filter.getContains().getActors()) && site.getCurrentUser().getAllowedMovies().get(i).getActors().containsAll(filter.getContains().getActors()))
                            currMovies.add(site.getCurrentUser().getAllowedMovies().get(i));
                    }
                }
            }
            else {
                currMovies.addAll(site.getCurrentUser().getAllowedMovies());
            }
            if(filter.getSort() != null) {
                if(filter.getSort().getRating() != null && filter.getSort().getRating().equals("decreasing"))
                    currMovies.sort(Comparator.comparing(Movie::getRating).reversed());
                if(filter.getSort().getRating() != null && filter.getSort().getRating().equals("increasing"))
                    currMovies.sort(Comparator.comparing(Movie::getRating));
                if(filter.getSort().getDuration() != null && filter.getSort().getDuration().equals("decreasing"))
                    currMovies.sort(Comparator.comparing(Movie::getDuration).reversed());
                if(filter.getSort().getDuration() != null && filter.getSort().getDuration().equals("increasing"))
                    currMovies.sort(Comparator.comparing(Movie::getDuration));

            }
            output.setCurrentUser(site.getCurrentUser());
            output.setCurrentMoviesList(currMovies);
            output.setError("null");
            return output;
        }
        output.setCurrentUser(null);
        output.setError("Error");
        output.setCurrentMoviesList(null);
        return output;
    }

    @Override
    public Output visit(Like like, Site site) {
        return null;
    }

    @Override
    public Output visit(Login login, Site site) {
        Output output = new Output();
        if(site.getCurrentPage().equals(site.getAvailablePages().get(1))) {
            for(int i = 0; i < site.getUsersIn().size(); i++) {
                if(site.getUsersIn().get(i).getCredentials().getName().equals(login.getName())) {
                    if(site.getUsersIn().get(i).getCredentials().getPassword().equals(login.getPassword())) {
                        site.setCurrentPage(site.getAvailablePages().get(3));
                        site.setCurrentUser(site.getUsersIn().get(i));
                        output.setError(null);
                        output.setCurrentUser(site.getUsersIn().get(i));
                        output.setCurrentMoviesList(null);
                        return output;
                    }
                }
            }
            site.setCurrentPage(site.getAvailablePages().get(0));
        }
        output.setError("Error");
        output.setCurrentUser(null);
        output.setCurrentMoviesList(null);
        return output;
    }

    @Override
    public Output visit(Purchase purchase, Site site) {
        return null;
    }

    @Override
    public Output visit(Rate rate, Site site) {
        return null;
    }

    @Override
    public Output visit(Register register, Site site) {
        Output output = new Output();
        if(site.getCurrentPage().equals(site.getAvailablePages().get(2))) {
            for(int i = 0; i < site.getUsersIn().size(); i++) {
                if(register.getCredentials().getName().equals(site.getUsersIn().get(i).getCredentials().getName())) {
                    output.setError("Error");
                    output.setCurrentUser(null);
                    output.setCurrentMoviesList(null);
                    site.setCurrentPage(site.getAvailablePages().get(0));
                    return output;
                }
            }
            User newUser = new User();
            CredentialsInput credentials = new CredentialsInput();
            credentials.setName(register.getCredentials().getName());
            credentials.setPassword(register.getCredentials().getPassword());
            credentials.setAccountType(register.getCredentials().getAccountType());
            credentials.setCountry(register.getCredentials().getCountry());
            credentials.setBalance(register.getCredentials().getBalance());
            newUser.setCredentials(credentials);
            site.getUsersIn().add(newUser);
            site.setCurrentPage(site.getAvailablePages().get(3));
            site.setCurrentUser(site.getUsersIn().get(site.getUsersIn().size() - 1));
            output.setCurrentUser(site.getCurrentUser());
            output.setError(null);
            return output;
        }
        output.setError("Error");
        output.setCurrentUser(null);
        output.setCurrentMoviesList(null);
        return output;
    }

    @Override
    public Output visit(Search search, Site site) {
        Output output = new Output();
        if(site.getCurrentPage().equals(site.getAvailablePages().get(4))) {
            ArrayList<Movie> movies = new ArrayList<>(site.getCurrentUser().getAllowedMovies());
            for(int i = 0; i < movies.size(); i++) {
                if (!movies.get(i).getName().startsWith(search.getStartsWith())) {
                    movies.remove(i);
                    i--;
                }
            }
            output.setCurrentMoviesList(movies);
            output.setError(null);
            output.setCurrentUser(site.getCurrentUser());
            return output;
        }
        output.setError("Error");
        output.setCurrentUser(null);
        output.setCurrentMoviesList(null);
        return output;
    }

    @Override
    public Output visit(Watch watch, Site site) {
        return null;
    }
}
