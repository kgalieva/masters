package model;

import javax.persistence.*;

@Entity
public class Invite {

    @EmbeddedId
    private InvitePK key;

    @Enumerated(value = EnumType.ORDINAL)
    private InviteType type;

    public InviteType getType() {
        return type;
    }

    public void setType(InviteType type) {

        this.type = type;
    }

    public InvitePK getKey() {
        return key;
    }

    public void setKey(InvitePK key) {
        this.key = key;
    }
}
