package Classes.Packet.Actions;

import Classes.Packet.Output;

public interface Action {
    Output accept(ActionVisitor visitor);
}
