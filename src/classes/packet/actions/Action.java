package classes.packet.actions;

import classes.packet.Output;

public interface Action {
    /**
     *
     * @param visitor constructie clasica pentru Visitor Pattern
     * @return
     */
    Output accept(ActionVisitor visitor);
}
