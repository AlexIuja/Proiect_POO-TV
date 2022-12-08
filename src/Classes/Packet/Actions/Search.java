package Classes.Packet.Actions;

import Classes.Packet.Output;
import Classes.Packet.Site;
import Classes.fileio.ActionInput;

public class Search implements Action{
    private String type;
    private String page;
    private String feature;
    private String startsWith;
    private Site site;

    public Search(ActionInput input, Site site) {
        type = input.getType();
        page = input.getPage();
        feature = input.getFeature();
        startsWith = input.getStartsWith();
        this.site = site;
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

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public String getStartsWith() {
        return startsWith;
    }

    public void setStartsWith(String startsWith) {
        this.startsWith = startsWith;
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
}
