package model;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class InvitePK implements Serializable {

        private static final long serialVersionUID = 1L;

        @ManyToOne
        @JoinColumn(name = "vacancy_id")
        private Vacancy vacancy;

        @ManyToOne
        @JoinColumn(name = "cv_id")
        private CV cv;

        public InvitePK() {
        }

        public InvitePK(Long vacancyId, Long cvId) {
            this.vacancy = new Vacancy(vacancyId);
            this.cv = new CV(cvId);
        }

        public InvitePK(Vacancy vacancy, CV cv) {
            this.vacancy = vacancy;
            this.cv = cv;
        }

        public Vacancy getVacancy() {
            return vacancy;
        }

        public void setVacancy(Vacancy vacancy) {
            this.vacancy = vacancy;
        }

        public CV getCv() {
            return cv;
        }

        public void setCv(CV cv) {
            this.cv = cv;
        }
    }