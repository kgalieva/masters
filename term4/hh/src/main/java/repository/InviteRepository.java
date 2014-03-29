package repository;

import model.Invite;
import model.InvitePK;
import org.springframework.data.repository.CrudRepository;

public interface InviteRepository extends CrudRepository<Invite, InvitePK> {
}
