package repository;

import config.DataSourceTestConfig;
import config.PersistenceConfig;
import model.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.SQLException;

import static org.junit.Assert.*;
import static repository.fixture.TestConstants.InviteConstants.INVITE_TYPE;
import static repository.fixture.TestData.standardCV;
import static repository.fixture.TestData.standardInvite;
import static repository.fixture.TestData.standardVacancy;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DataSourceTestConfig.class, PersistenceConfig.class})
public class InviteRepositoryTest {

    @Autowired
    private InviteRepository inviteRepository;
    @Autowired
    private CVRepository cvRepository;
    @Autowired
    private VacancyRepository vacancyRepository;

    private Invite createInvite() {
        Invite invite = standardInvite();
        CV cv = standardCV();
        Vacancy vacancy = standardVacancy();
        cvRepository.save(cv);
        vacancyRepository.save(vacancy);
        invite.setKey(new InvitePK(vacancy, cv));
        return invite;
    }

    @Test
    public void testFindAll() throws SQLException {
        Invite newInvite = createInvite();
        inviteRepository.save(newInvite);
        Iterable<Invite> inviteList = inviteRepository.findAll();
        assertNotNull(inviteList);
        assertTrue(inviteList.iterator().hasNext());
        for(Invite invite: inviteList) {
            assertNotNull(invite);
            assertNotNull(invite.getType());
            assertNotNull(invite.getKey());
            assertNotNull(invite.getKey().getCv());
            assertNotNull(invite.getKey().getVacancy());
        }
    }

    @Test
    public void testCRUD(){
        Invite invite = createInvite();
        inviteRepository.save(invite);
        invite = inviteRepository.findOne(invite.getKey());
        assertEquals(invite.getType(), INVITE_TYPE);
        invite.setType(InviteType.INVITE);
        inviteRepository.save(invite);
        assertEquals(inviteRepository.findOne(invite.getKey()).getType(), InviteType.INVITE);
        inviteRepository.delete(invite);
        assertNull(inviteRepository.findOne(invite.getKey()));
    }
}
