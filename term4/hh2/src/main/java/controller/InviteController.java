package controller;

import model.Invite;
import model.InvitePK;
import model.InviteType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import repository.InviteRepository;

@RestController
public class InviteController {
    InviteRepository inviteRepository;

    @Autowired
    public InviteController(InviteRepository inviteRepository) {
        this.inviteRepository = inviteRepository;
    }

    @RequestMapping("/invite/{id}")
    public void invite(@PathVariable Long id) {
        /*TODO реализуйте сохранение приглашения.
            Для простоты все приглашения высылаются на вакансию с id = 1
            Если есть желание, можно реализовать диалоговое окно выбора вакансии
            и передавать в контроллер оба id.
         */
        Invite invite = new Invite();
        InvitePK pk = new InvitePK(1L, id);
        invite.setKey(pk);
        invite.setType(InviteType.INVITE);
        inviteRepository.save(invite);
    }

    @RequestMapping("/response/{id}")
    public void response(@PathVariable Long id) {
        /*TODO реализуйте сохранение приглашения.
            Для простоты все приглашения высылаются на вакансию с id = 1
            Если есть желание, можно реализовать диалоговое окно выбора вакансии
            и передавать в контроллер оба id.
         */
        Invite invite = new Invite();
        InvitePK pk = new InvitePK(id, 1L);
        invite.setKey(pk);
        invite.setType(InviteType.RESPONSE);
        inviteRepository.save(invite);
    }
}
