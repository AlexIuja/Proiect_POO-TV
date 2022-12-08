package Classes.Packet.Actions;

import Classes.Packet.Output;
import Classes.Packet.Site;
import Classes.fileio.ActionInput;

public class Watch implements Action{
    private String type;
    private String page;
    private String feature;
    private String objectType;
    private String movie;
    private Site site;

    public Watch(ActionInput input, Site site) {
        type = input.getType();
        page = input.getPage();
        feature = input.getFeature();
        objectType = input.getObjectType();
        movie = input.getMovie();
        this.site = site;
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    public String getMovie() {
        return movie;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }


    @Override
    public Output accept(ActionVisitor visitor) {
        return visitor.visit(this, site);
    }
}
