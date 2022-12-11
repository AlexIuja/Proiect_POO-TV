package Classes.Packet.Actions;

import Classes.Packet.Output;
import Classes.Packet.Site;
import Classes.fileio.ActionInput;

public class Rate implements Action{
    private String type;
    private String feature;
    private String objectType;
    private String movie;
    private int rate;

    private Site site;

    public Rate(ActionInput input, Site site) {
        type = input.getType();
        feature = input.getFeature();
        objectType = input.getObjectType();
        movie = input.getMovie();
        rate = input.getRate();
        this.site = site;
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

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    @Override
    public Output accept(ActionVisitor visitor) {
        return visitor.visit(this, site);
    }

    @Override
    public String toString() {
        return "Rate{" +
                "feature='" + feature + '\'' +
                ", movie='" + movie + '\'' +
                ", rate=" + rate +
                '}';
    }
}
