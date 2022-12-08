package Classes.Packet.Actions;

import Classes.Packet.Output;
import Classes.Packet.Site;

public interface ActionVisitor {
    Output visit(ChangePage changePage, Site site);
    Output visit(BuyPrem buyPrem, Site site);
    Output visit(BuyTokens buyTokens, Site site);
    Output visit(Filter filter, Site site);
    Output visit(Like like, Site site);
    Output visit(Login login, Site site);
    Output visit(Purchase purchase, Site site);
    Output visit(Rate rate, Site site);
    Output visit(Register register, Site site);
    Output visit(Search search, Site site);
    Output visit(Watch watch, Site site);
}
