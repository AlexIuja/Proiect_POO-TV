package classes.packet.actions;

import classes.packet.Movie;
import classes.packet.Output;
import classes.packet.pages.SeeDetailsPage;
import classes.packet.Site;
import classes.packet.User;
import classes.fileio.CredentialsInput;

import java.util.ArrayList;
import java.util.Comparator;

public final class ActionVisitorImpl implements ActionVisitor {
    private ArrayList<Movie> moviesAllowedInCountry = new ArrayList<>();
    public ArrayList<Movie> getMoviesAllowedInCountry() {
        return moviesAllowedInCountry;
    }

    public void setMoviesAllowedInCountry(final ArrayList<Movie> moviesAllowedInCountry) {
        this.moviesAllowedInCountry = moviesAllowedInCountry;
    }
    @Override
    public Output visit(final ChangePage changePage, final Site site) {
        Output output = new Output();
        for (int i = 0; i < site.getCurrentPage().getAllowedPagesToChange().size(); i++) {
            if (changePage.getPage().equals(site.getCurrentPage()
                    .getAllowedPagesToChange().get(i).getPageName())) {
                if (changePage.getPage().equals("login")) {
                    site.setCurrentPage(site.getAvailablePages().get(Site.LOGIN_ID));
                    return null;
                } else if (changePage.getPage().equals("register")) {
                    site.setCurrentPage(site.getAvailablePages().get(Site.REGISTER_ID));
                    return null;
                } else if (changePage.getPage().equals("logout")) {
                    site.setCurrentPage(site.getAvailablePages().get(Site.HOMEPAGENEAUT_ID));
                    site.setCurrentUser(null);
                    return null;
                } else if (changePage.getPage().equals("movies")) {
                    site.setCurrentPage(site.getAvailablePages().get(Site.MOVIES_ID));
                    output.setCurrentUser(site.getCurrentUser());
                    ArrayList<Movie> currMovies = new ArrayList<>();
                    for (int j = 0; j < site.getMoviesIn().size(); j++) {
                        if (!site.getMoviesIn().get(j).getCountriesBanned()
                                .contains(site.getCurrentUser().getCredentials().getCountry())) {
                            currMovies.add(site.getMoviesIn().get(j));
                        }
                    }
                    site.getCurrentUser().setAllowedMovies(currMovies);
                    setMoviesAllowedInCountry(currMovies);
                    output.setCurrentMoviesList(currMovies);
                    output.setError(null);
                    return output;
                } else if (changePage.getPage().equals("upgrades")) {
                    site.setCurrentPage(site.getAvailablePages().get(Site.UPGRADES_ID));
                    return null;
                } else if (changePage.getPage().equals("see details")) {
                    for (int j = 0; j < site.getCurrentUser()
                            .getAllowedMovies().size(); j++) {
                        if (site.getCurrentUser().getAllowedMovies().get(j)
                                .getName().equals(changePage.getMovie())) {
                            site.setCurrentPage(site.getAvailablePages().get(Site.SEEDETAILS_ID));
                            ((SeeDetailsPage) site.getAvailablePages().get(Site.SEEDETAILS_ID))
                                    .setMovie(site.getCurrentUser().getAllowedMovies().get(j));
                            Output outputSeeDetails = new Output();
                            ArrayList<Movie> outputSeeDetailsCurrMovie = new ArrayList<>();
                            outputSeeDetailsCurrMovie.add(site.getCurrentUser()
                                    .getAllowedMovies().get(j));
                            outputSeeDetails.setError(null);
                            outputSeeDetails.setCurrentUser(site.getCurrentUser());
                            outputSeeDetails.setCurrentMoviesList(outputSeeDetailsCurrMovie);
                            return outputSeeDetails;
                        }
                    }
                }
            }
        }
        output.setError("Error");
        output.setCurrentUser(null);
        output.getCurrentMoviesList().clear();
        return output;
    }

    @Override
    public Output visit(final BuyPrem buyPrem, final Site site) {
        Output output = new Output();
        int tokens = site.getCurrentUser().getTokensCount();
        if (site.getCurrentPage().equals(site.getAvailablePages().get(Site.UPGRADES_ID))) {
            if (tokens >= Site.PREM_PRICE) {
                site.getCurrentUser().getCredentials().setAccountType("premium");
                site.getCurrentUser().setTokensCount(tokens - Site.PREM_PRICE);
                return null;
            }
        }
        output.setError("Error");
        output.setCurrentUser(null);
        output.getCurrentMoviesList().clear();
        return output;
    }

    @Override
    public Output visit(final BuyTokens buyTokens, final Site site) {
        Output output = new Output();
        int balance = Integer.parseInt(site.getCurrentUser().getCredentials().getBalance());
        if (site.getCurrentPage().equals(site.getAvailablePages().get(Site.UPGRADES_ID))) {
            if (balance >= buyTokens.getCount()) {
                site.getCurrentUser().setTokensCount(buyTokens.getCount());
                site.getCurrentUser().getCredentials()
                        .setBalance(String.valueOf(balance - buyTokens.getCount()));
                return null;
            }
        }
        output.setError("Error");
        output.setCurrentUser(null);
        output.getCurrentMoviesList().clear();
        return output;
    }

    @Override
    public Output visit(final Filter filter, final Site site) {
        Output output = new Output();
        site.getCurrentUser().setAllowedMovies(moviesAllowedInCountry);
        ArrayList<Movie> currMovies = new ArrayList<>();
        if (site.getCurrentPage().equals(site.getAvailablePages().get(Site.MOVIES_ID))) {
            if (filter.getContains() != null) {
                for (int i = 0; i < site.getCurrentUser().getAllowedMovies().size(); i++) {
                    if (filter.getContains().getActors() != null
                            && filter.getContains().getGenre() == null) {
                        if (site.getCurrentUser().getAllowedMovies().get(i)
                                .getActors().containsAll(filter.getContains().getActors())) {
                            currMovies.add(site.getCurrentUser().getAllowedMovies().get(i));
                        }
                    }
                    if (filter.getContains().getActors() == null
                            && filter.getContains().getGenre() != null) {
                        if (site.getCurrentUser().getAllowedMovies().get(i)
                                .getGenres().containsAll(filter.getContains().getGenre())) {
                            currMovies.add(site.getCurrentUser().getAllowedMovies().get(i));
                        }
                    }
                    if (filter.getContains().getActors() != null
                            && filter.getContains().getGenre() != null) {
                        if (site.getCurrentUser().getAllowedMovies().get(i)
                                .getActors().containsAll(filter.getContains().getActors())
                                && site.getCurrentUser().getAllowedMovies().get(i)
                                .getActors().containsAll(filter.getContains().getGenre())) {
                            currMovies.add(site.getCurrentUser().getAllowedMovies().get(i));
                        }
                    }
                }
            } else {
                currMovies.addAll(site.getCurrentUser().getAllowedMovies());
            }
            if (filter.getSort() != null) {
                if (filter.getSort().getRating() != null
                        && filter.getSort().getRating().equals("decreasing")) {
                    currMovies.sort(Comparator.comparing(Movie::getRating).reversed());
                }
                if (filter.getSort().getRating() != null
                        && filter.getSort().getRating().equals("increasing")) {
                    currMovies.sort(Comparator.comparing(Movie::getRating));
                }
                if (filter.getSort().getDuration() != null
                        && filter.getSort().getDuration().equals("decreasing")) {
                    currMovies.sort(Comparator.comparing(Movie::getDuration).reversed());
                }
                if (filter.getSort().getDuration() != null
                        && filter.getSort().getDuration().equals("increasing")) {
                    currMovies.sort(Comparator.comparing(Movie::getDuration));
                }

            }
            site.getCurrentUser().setAllowedMovies(currMovies);
            output.setCurrentUser(site.getCurrentUser());
            output.setCurrentMoviesList(currMovies);
            output.setError(null);
            return output;
        }
        output.setCurrentUser(null);
        output.setError("Error");
        output.getCurrentMoviesList().clear();
        return output;
    }

    @Override
    public Output visit(final Like like, final Site site) {
        Output output = new Output();
        if (site.getCurrentPage().equals(site.getAvailablePages().get(Site.SEEDETAILS_ID))) {
            if (!(like.getMovie() != null && !"".equals(like.getMovie()))) {
                like.setMovie(((SeeDetailsPage) site.getAvailablePages()
                        .get(Site.SEEDETAILS_ID)).getMovie().getName());
            }
            for (int i = 0; i < site.getCurrentUser().getWatchedMovies().size(); i++) {
                if (site.getCurrentUser().getWatchedMovies()
                        .get(i).getName().equals(like.getMovie())) {
                    site.getCurrentUser().getLikedMovies()
                            .add(site.getCurrentUser().getWatchedMovies().get(i));
                    site.getCurrentUser().getWatchedMovies().get(i)
                            .setNumLikes(site.getCurrentUser()
                                    .getWatchedMovies().get(i).getNumLikes() + 1);
                    output.setCurrentUser(site.getCurrentUser());
                    output.setError(null);
                    ArrayList<Movie> outputLike = new ArrayList<>();
                    outputLike.add(site.getCurrentUser().getWatchedMovies().get(i));
                    output.setCurrentMoviesList(outputLike);
                    return output;
                }
            }
        }
        output.setCurrentUser(null);
        output.setError("Error");
        output.getCurrentMoviesList().clear();
        return output;
    }

    @Override
    public Output visit(final Login login, final Site site) {
        Output output = new Output();
        if (site.getCurrentPage().equals(site.getAvailablePages().get(Site.LOGIN_ID))) {
            for (int i = 0; i < site.getUsersIn().size(); i++) {
                if (site.getUsersIn().get(i).getCredentials().getName().equals(login.getName())) {
                    if (site.getUsersIn().get(i).getCredentials()
                            .getPassword().equals(login.getPassword())) {
                        site.setCurrentPage(site.getAvailablePages().get(Site.HOMEPAGEAUT_ID));
                        site.setCurrentUser(site.getUsersIn().get(i));
                        output.setError(null);
                        output.setCurrentUser(site.getUsersIn().get(i));
                        output.getCurrentMoviesList().clear();
                        return output;
                    }
                }
            }
            site.setCurrentPage(site.getAvailablePages().get(Site.HOMEPAGENEAUT_ID));
        }
        output.setError("Error");
        output.setCurrentUser(null);
        output.getCurrentMoviesList().clear();
        return output;
    }

    @Override
    public Output visit(final Purchase purchase, final Site site) {
        Output output = new Output();
        if (site.getCurrentPage().equals(site.getAvailablePages().get(Site.SEEDETAILS_ID))) {
            if (!(purchase.getMovie() != null && !"".equals(purchase.getMovie()))) {
                purchase.setMovie(((SeeDetailsPage)
                        site.getAvailablePages().get(Site.SEEDETAILS_ID)).getMovie().getName());
            }
            for (int i = 0; i < site.getCurrentUser().getAllowedMovies().size(); i++) {
                if (site.getCurrentUser().getAllowedMovies().get(i)
                        .getName().equals(purchase.getMovie())) {
                    if (site.getCurrentUser().getCredentials().getAccountType().equals("premium")) {
                        if (site.getCurrentUser().getNumFreePremiumMovies() > 0) {
                            int numFreePremMovies = site.getCurrentUser().getNumFreePremiumMovies();
                            site.getCurrentUser().setNumFreePremiumMovies(numFreePremMovies - 1);
                            output.setError(null);
                            output.setCurrentUser(site.getCurrentUser());
                            site.getCurrentUser().getPurchasedMovies()
                                    .add(site.getCurrentUser().getAllowedMovies().get(i));
                            ArrayList<Movie> outputPurchaseCurrMovie = new ArrayList<>();
                            outputPurchaseCurrMovie.add(site.getCurrentUser()
                                    .getAllowedMovies().get(i));
                            output.setCurrentMoviesList(outputPurchaseCurrMovie);
                            return output;
                        } else {
                            if (site.getCurrentUser().getTokensCount() >= 2) {
                                int tokensCount = site.getCurrentUser().getTokensCount();
                                site.getCurrentUser().setTokensCount(tokensCount - 2);
                                site.getCurrentUser().getPurchasedMovies()
                                        .add(site.getCurrentUser().getAllowedMovies().get(i));
                                output.setError(null);
                                output.setCurrentUser(site.getCurrentUser());
                                ArrayList<Movie> outputPurchaseCurrMovie = new ArrayList<>();
                                outputPurchaseCurrMovie.add(site.getCurrentUser()
                                        .getAllowedMovies().get(i));
                                output.setCurrentMoviesList(outputPurchaseCurrMovie);
                                return output;
                            }
                        }
                    } else if (site.getCurrentUser().getCredentials()
                            .getAccountType().equals("standard")) {
                        if (site.getCurrentUser().getTokensCount() > 1) {
                            int tokensCountStandard = site.getCurrentUser().getTokensCount();
                            site.getCurrentUser().setTokensCount(tokensCountStandard - 2);
                            site.getCurrentUser().getPurchasedMovies().add(site
                                    .getCurrentUser().getAllowedMovies().get(i));
                            output.setError(null);
                            output.setCurrentUser(site.getCurrentUser());
                            ArrayList<Movie> outputPurchaseCurrMovie = new ArrayList<>();
                            outputPurchaseCurrMovie.add(site.getCurrentUser()
                                    .getAllowedMovies().get(i));
                            output.setCurrentMoviesList(outputPurchaseCurrMovie);
                            return output;
                        }
                    }
                }
            }
        }
        output.setError("Error");
        output.getCurrentMoviesList().clear();
        output.setCurrentUser(null);
        return output;
    }

    @Override
    public Output visit(final Rate rate, final Site site) {
        Output output = new Output();
        if (site.getCurrentPage().equals(site.getAvailablePages().get(Site.SEEDETAILS_ID))) {
            if (!(rate.getMovie() != null && !"".equals(rate.getMovie()))) {
                rate.setMovie(((SeeDetailsPage) site.getAvailablePages()
                        .get(Site.SEEDETAILS_ID)).getMovie().getName());
            }
            for (int i = 0; i < site.getCurrentUser().getWatchedMovies().size(); i++) {
                if (site.getCurrentUser().getWatchedMovies().get(i)
                        .getName().equals(rate.getMovie())) {
                    if (rate.getRate() <= Site.SEEDETAILS_ID && rate.getRate() >= 0) {
                        //fara niciun misto, nu are niciun sens, dar se potrivea valoarea
                        // si imi era lene sa fac alta constanta
                        site.getCurrentUser().getWatchedMovies().get(i)
                                .setNumRatings(site.getCurrentUser().getWatchedMovies().get(i)
                                        .getNumRatings() + 1);
                        site.getCurrentUser().getWatchedMovies().get(i)
                                .getAllRatings().add(rate.getRate());
                        int ratingSum = 0;
                        for (int j = 0; j < site.getCurrentUser().getWatchedMovies()
                                .get(i).getAllRatings().size(); j++) {
                            ratingSum += site.getCurrentUser().getWatchedMovies()
                                    .get(i).getAllRatings().get(j);
                        }
                        site.getCurrentUser().getWatchedMovies().get(i)
                                .setRating(((double) ratingSum) / site.getCurrentUser()
                                        .getWatchedMovies().get(i).getNumRatings());
                        site.getCurrentUser().getRatedMovies()
                                .add(site.getCurrentUser().getWatchedMovies().get(i));
                        output.setCurrentUser(site.getCurrentUser());
                        output.setError(null);
                        ArrayList<Movie> outputRateCurrMovie = new ArrayList<>();
                        outputRateCurrMovie.add(site.getCurrentUser().getWatchedMovies().get(i));
                        output.setCurrentMoviesList(outputRateCurrMovie);
                        return output;
                    }
                }
            }
        }
        output.getCurrentMoviesList().clear();
        output.setError("Error");
        output.setCurrentUser(null);
        return output;
    }

    @Override
    public Output visit(final Register register, final Site site) {
        Output output = new Output();
        if (site.getCurrentPage().equals(site.getAvailablePages().get(2))) {
            for (int i = 0; i < site.getUsersIn().size(); i++) {
                if (register.getCredentials().getName()
                        .equals(site.getUsersIn().get(i).getCredentials().getName())) {
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
            site.setCurrentPage(site.getAvailablePages().get(Site.HOMEPAGEAUT_ID));
            site.setCurrentUser(site.getUsersIn().get(site.getUsersIn().size() - 1));
            output.setCurrentUser(site.getCurrentUser());
            output.setError(null);
            return output;
        }
        output.setError("Error");
        output.setCurrentUser(null);
        output.getCurrentMoviesList().clear();
        return output;
    }

    @Override
    public Output visit(final Search search, final Site site) {
        Output output = new Output();
        if (site.getCurrentPage().equals(site.getAvailablePages().get(Site.MOVIES_ID))) {
            ArrayList<Movie> movies = new ArrayList<>(site.getCurrentUser().getAllowedMovies());
            for (int i = 0; i < movies.size(); i++) {
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
        output.getCurrentMoviesList().clear();
        return output;
    }

    @Override
    public Output visit(final Watch watch, final Site site) {
        Output output = new Output();
        if (site.getCurrentPage().equals(site.getAvailablePages().get(Site.SEEDETAILS_ID))) {
            if (!(watch.getMovie() != null && !"".equals(watch.getMovie()))) {
                watch.setMovie(((SeeDetailsPage) site.
                        getAvailablePages().get(Site.SEEDETAILS_ID)).getMovie().getName());
            }
            for (int i = 0; i < site.getCurrentUser().getPurchasedMovies().size(); i++) {
                if (site.getCurrentUser().getPurchasedMovies().get(i)
                        .getName().equals(watch.getMovie())) {
                    site.getCurrentUser().getWatchedMovies()
                            .add(site.getCurrentUser().getPurchasedMovies().get(i));
                    output.setError(null);
                    output.setCurrentUser(site.getCurrentUser());
                    ArrayList<Movie> outputWatchCurrMovie = new ArrayList<>();
                    outputWatchCurrMovie.add(site.getCurrentUser().getPurchasedMovies().get(i));
                    output.setCurrentMoviesList(outputWatchCurrMovie);
                    return output;
                }
            }
        }
        output.setError("Error");
        output.setCurrentUser(null);
        output.getCurrentMoviesList().clear();
        return output;
    }

}
