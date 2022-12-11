package Classes.Packet.Actions;

import Classes.Packet.Output;
import Classes.Packet.Site;
import Classes.fileio.ActionInput;

public class BuyPrem implements Action{
    private String type;
    private String feature;
    private Site site;

    public BuyPrem(ActionInput input, Site site) {
        type = input.getType();
        feature = input.getFeature();
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

    @Override
    public Output accept(ActionVisitor visitor) {
        return visitor.visit(this, site);
    }

    @Override
    public String toString() {
        return "BuyPrem{" +
                "feature='" + feature + '\'' +
                '}';
    }
}
