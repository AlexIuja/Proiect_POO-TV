package Classes.Packet.Actions;

import Classes.Packet.Output;
import Classes.Packet.Site;
import Classes.fileio.ActionInput;

public class ChangePage implements Action{
    private String page;
    private String type;
    private String movie;
    private Site site;

    public ChangePage(ActionInput input, Site site) {
        this.page = input.getPage();
        this.type = input.getType();
        if(input.getMovie() != null)
            movie = input.getMovie();
        this.site = site;
    }

    public String getMovie() {
        return movie;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    @Override
    public Output accept(ActionVisitor visitor) {
        return visitor.visit(this, site);
    }

    @Override
    public String toString() {
        return "ChangePage{" +
                "page='" + page + '\'' +
                ", type='" + type + '\'' +
                ", movie='" + movie + '\'' +
                '}';
    }
}
