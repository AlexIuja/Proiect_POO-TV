package Classes.Packet.Actions;

import Classes.Packet.Output;
import Classes.Packet.Site;
import Classes.fileio.ActionInput;
import Classes.fileio.CredentialsInput;

public class Register implements Action{
    private String type;
    private String feature;
    private CredentialsInput credentials;
    private Site site;

    public Register(ActionInput input, Site site) {
        type = input.getType();
        feature = input.getFeature();
        credentials = input.getCredentials();
        this.site = site;
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
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

    public CredentialsInput getCredentials() {
        return credentials;
    }

    public void setCredentials(CredentialsInput credentials) {
        this.credentials = credentials;
    }


    @Override
    public Output accept(ActionVisitor visitor) {
        return visitor.visit(this, site);
    }

    @Override
    public String toString() {
        return "Register{" +
                "feature='" + feature + '\'' +
                '}';
    }
}
