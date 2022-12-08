package Classes.Packet.Pages;

import java.util.ArrayList;

public abstract class SitePage {
    private String pageName;
    private ArrayList<SitePage> allowedPagesToChange;

    public SitePage(String pageName) {
        this.pageName = pageName;
    }

    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    public ArrayList<SitePage> getAllowedPagesToChange() {
        return allowedPagesToChange;
    }

    public void setAllowedPagesToChange(ArrayList<SitePage> allowedPagesToChange) {
        this.allowedPagesToChange = allowedPagesToChange;
    }
}
