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

    @RequestMapping("/invite/{id}")
    public void invite(@PathVariable Long id) {
        System.out.println(id);
        /*TODO реализуйте сохранение приглашения.
            Для простоты все приглашения высылаются на вакансию с id = 1
            Если есть желание, можно реализовать диалоговое окно выбора вакансии
            и передавать в контроллер оба id.
         */
    }
}
