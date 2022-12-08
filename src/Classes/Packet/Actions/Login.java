package Classes.Packet.Actions;

import Classes.Packet.Output;
import Classes.Packet.Site;
import Classes.fileio.ActionInput;

public class Login implements Action{
    private String type;
    private String page;
    private String feature;
    private String name;
    private String password;
    private Site site;

    public Login(ActionInput input, Site site) {
        type = input.getType();
        page = input.getPage();
        feature = input.getFeature();
        name = input.getCredentials().getName();
        password = input.getCredentials().getPassword();
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

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
}
