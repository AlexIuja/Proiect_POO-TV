package classes.Packet.Actions;

import classes.Packet.Output;

public interface Action {
    /**
     *
     * @param visitor constructie clasica pentru Visitor Pattern
     * @return
     */
    Output accept(ActionVisitor visitor);
}
