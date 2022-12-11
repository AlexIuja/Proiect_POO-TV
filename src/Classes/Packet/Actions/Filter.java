package Classes.Packet.Actions;

import Classes.Packet.Output;
import Classes.Packet.Site;
import Classes.fileio.ActionInput;
import Classes.fileio.ContainsInput;
import Classes.fileio.SortInput;

public class Filter implements Action{
    private String type;
    private String feature;
    private SortInput sort;
    private ContainsInput contains;
    private Site site;

    public Filter(ActionInput input, Site site) {
        type = input.getType();
        feature = input.getFeature();
        sort = input.getFilters().getSort();
        contains = input.getFilters().getContains();
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

    public SortInput getSort() {
        return sort;
    }

    public void setSort(SortInput sort) {
        this.sort = sort;
    }

    public ContainsInput getContains() {
        return contains;
    }

    public void setContains(ContainsInput contains) {
        this.contains = contains;
    }


    @Override
    public Output accept(ActionVisitor visitor) {
        return visitor.visit(this, site);
    }


    @Override
    public String toString() {
        return "Filter{" +
                "feature='" + feature + '\'' +
                ", sort=" + sort +
                ", contains=" + contains +
                '}';
    }
}
