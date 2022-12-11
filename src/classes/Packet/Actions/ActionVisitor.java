package classes.Packet.Actions;

import classes.Packet.Output;
import classes.Packet.Site;

public interface ActionVisitor {
    /**
     *
     * @param changePage actiunea de Change Page pe site-ul site
     * @param site
     * @return
     */
    Output visit(ChangePage changePage, Site site);

    /**
     *
     * @param buyPrem
     * @param site
     * @return
     */
    Output visit(BuyPrem buyPrem, Site site);

    /**
     *
     * @param buyTokens
     * @param site
     * @return
     */
    Output visit(BuyTokens buyTokens, Site site);

    /**
     *
     * @param filter
     * @param site
     * @return
     */
    Output visit(Filter filter, Site site);

    /**
     *
     * @param like
     * @param site
     * @return
     */
    Output visit(Like like, Site site);

    /**
     *
     * @param login
     * @param site
     * @return
     */
    Output visit(Login login, Site site);

    /**
     *
     * @param purchase
     * @param site
     * @return
     */
    Output visit(Purchase purchase, Site site);

    /**
     *
     * @param rate
     * @param site
     * @return
     */
    Output visit(Rate rate, Site site);

    /**
     *
     * @param register
     * @param site
     * @return
     */
    Output visit(Register register, Site site);

    /**
     *
     * @param search
     * @param site
     * @return
     */
    Output visit(Search search, Site site);

    /**
     *
     * @param watch
     * @param site
     * @return
     */
    Output visit(Watch watch, Site site);
}
