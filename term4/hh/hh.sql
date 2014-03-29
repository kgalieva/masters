
CREATE TABLE category (
    id bigint NOT NULL,
    name character varying(255)
);
ALTER TABLE public.category OWNER TO postgres;


CREATE TABLE company (
    id bigint NOT NULL,
    city character varying(255),
    email character varying(255),
    name character varying(255),
    description character varying(1000),
    password character varying(255),
    salt character varying(255)
);
ALTER TABLE public.company OWNER TO postgres;


CREATE TABLE cv (
    id bigint NOT NULL,
    education character varying(255),
    experience character varying(255),
    text character varying(1000),
    title character varying(255),
    user_id bigint
);
ALTER TABLE public.cv OWNER TO postgres;


CREATE TABLE cv_category (
    cv_id bigint NOT NULL,
    category_id bigint NOT NULL
);
ALTER TABLE public.cv_category OWNER TO postgres;


CREATE TABLE invite (
    type integer,
    cv_id bigint NOT NULL,
    vacancy_id bigint NOT NULL
);
ALTER TABLE public.invite OWNER TO postgres;

CREATE TABLE users (
    id bigint NOT NULL,
    city character varying(255),
    email character varying(255),
    gender integer,
    name character varying(255),
    password character varying(255),
    phone character varying(255),
    salt character varying(255)
);
ALTER TABLE public.users OWNER TO postgres;

CREATE TABLE vacancy (
    id bigint NOT NULL,
    city character varying(255),
    description character varying(1000),
    experience integer,
    salary numeric(19,2),
    title character varying(255),
    category_id bigint,
    company_id bigint
);
ALTER TABLE public.vacancy OWNER TO postgres;


INSERT INTO category (id, name) VALUES (1, 'Работа с персоналом');
INSERT INTO category (id, name) VALUES (2, 'IT');
INSERT INTO category (id, name) VALUES (3, 'Логистика');

INSERT INTO cv (id, education, experience, text, title, user_id) VALUES (1, 'высшее', 3, '
Сентябрь 2012 — по настоящее время (1 год 7 месяцев)
Казанский (Приволжский) федеральный университет (Казань) — Образовательные учреждения
Системный администратор
Сопровождение Linux/POSIX совместимых систем, bash/perl/python скриптинг
Июнь 2011 — август 2012 (1 год 3 месяца)
Казанский (Приволжский) федеральный университет (Казань, kpfu.ru) — Образовательные учреждения
Техник
поддержка и сопровождение пользователей ПК', 'Программист-разработчик', 1);
INSERT INTO cv (id, education, experience, text, title, user_id) VALUES (2, 'среднее', 1, 'Обо мне: Имею опыт работы водитель-экспедитор. Хорошо знаю город. Имею л/с транспорт. Езжу аккуратно. Права кат в C. Имею опыт управления грузовым а/м газель. Экспедиторские работы знаю. Полная занятость. Разрешение на работу: Россия', 'Водитель', 1);

INSERT INTO cv_category (cv_id, category_id) VALUES (2, 3);
INSERT INTO cv_category (cv_id, category_id) VALUES (1, 2);


INSERT INTO invite (type, cv_id, vacancy_id) VALUES (0, 1, 2);


INSERT INTO users (id, city, email, gender, name, password, phone, salt) VALUES (1, 'Казань', 'alex@gmail.com', 0, 'Александр Семенов', 'alex', '2121212', 'alex');

INSERT INTO company(id, city, email, name, description, password, salt) VALUES (1, 'Москва', 'bill@microsoft.com', 'MICROSOFT Corporation', 'Microsoft – не просто мировой лидер в производстве программного обеспечения, предоставлении услуг и разработке Интернет-технологий. Не секрет, что слова “Microsoft” и “Windows” знают даже дети. С помощью продуктов и технологий Microsoft в разных уголках земного шара люди решают свои задачи: ведут бизнес, учатся, ходят за покупками, общаются с друзьями, творят.','P@$$w0rd', '123');

INSERT INTO vacancy (id, city, description, experience, salary, title, category_id, company_id) VALUES (1, 'Москва', 'Высшее техническое образование
Знания методологий структурного и объектно-ориентированного программирования, умение их использовать на практике
Знание основ реляционных БД
Знание одного из высокоуровневых языков программирования (C#, Си, Паскаль, Java)
Технический английский (хороший письменный, приемлемый устный)', 3, 123455.00, 'Инженер-разработчик / Software Engineer ', 2, 1);
INSERT INTO vacancy (id, city, description, experience, salary, title, category_id, company_id) VALUES (2, 'Казань', 'Требуется ведущий аналитик', 5, 12345.00, 'Аналитик отдела продаж', 2, 1);
INSERT INTO vacancy (id, city, description, experience, salary, title, category_id, company_id) VALUES (3, 'Москва', 'BA/BS degree
Administrative experience supporting multiple locations and/or businesses
Reporting and analytical experience
Strong organizational skills
Very high attention to detail
Ability to drive processes
Good written and verbal communication skills
Ability to prioritize work from multiple clients in a high volume environment
Integrity to manage confidential information
Proficiency in Microsoft Office applications, good level of Excel knowledge (pivot tables; data validation; Lookup&Reference; What-If Analysis)
Fluent English
Interpersonal Awareness', 5, 12345.00, 'Staffing Associate', 1, 1);



--
-- TOC entry 1816 (class 2606 OID 157663)
-- Dependencies: 162 162 1941
-- Name: category_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY category
    ADD CONSTRAINT category_pkey PRIMARY KEY (id);


--
-- TOC entry 1818 (class 2606 OID 157671)
-- Dependencies: 163 163 1941
-- Name: company_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY company
    ADD CONSTRAINT company_pkey PRIMARY KEY (id);


--
-- TOC entry 1814 (class 2606 OID 157658)
-- Dependencies: 161 161 1941
-- Name: cv_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY cv
    ADD CONSTRAINT cv_pkey PRIMARY KEY (id);


--
-- TOC entry 1820 (class 2606 OID 157676)
-- Dependencies: 164 164 164 1941
-- Name: invite_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY invite
    ADD CONSTRAINT invite_pkey PRIMARY KEY (cv_id, vacancy_id);


--
-- TOC entry 1824 (class 2606 OID 157695)
-- Dependencies: 167 167 1941
-- Name: users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- TOC entry 1822 (class 2606 OID 157684)
-- Dependencies: 165 165 1941
-- Name: vacancy_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY vacancy
    ADD CONSTRAINT vacancy_pkey PRIMARY KEY (id);


--
-- TOC entry 1827 (class 2606 OID 157706)
-- Dependencies: 1821 165 164 1941
-- Name: fk_2fgyr9kjv6i8j0yavf73ve433; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY invite
    ADD CONSTRAINT fk_2fgyr9kjv6i8j0yavf73ve433 FOREIGN KEY (vacancy_id) REFERENCES vacancy(id);


--
-- TOC entry 1831 (class 2606 OID 157726)
-- Dependencies: 166 161 1813 1941
-- Name: fk_657k0el30mfwv4qe8u0kli22i; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cv_category
    ADD CONSTRAINT fk_657k0el30mfwv4qe8u0kli22i FOREIGN KEY (cv_id) REFERENCES cv(id);


--
-- TOC entry 1830 (class 2606 OID 157721)
-- Dependencies: 1815 166 162 1941
-- Name: fk_bw417f4jltlg654wqk337ytmd; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cv_category
    ADD CONSTRAINT fk_bw417f4jltlg654wqk337ytmd FOREIGN KEY (category_id) REFERENCES category(id);


--
-- TOC entry 1828 (class 2606 OID 157711)
-- Dependencies: 1815 165 162 1941
-- Name: fk_dh2klx4qx4nik2sw9dwmgc6n0; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY vacancy
    ADD CONSTRAINT fk_dh2klx4qx4nik2sw9dwmgc6n0 FOREIGN KEY (category_id) REFERENCES category(id);


--
-- TOC entry 1829 (class 2606 OID 157716)
-- Dependencies: 163 1817 165 1941
-- Name: fk_kdipb7wk536afi05k7ry8cvod; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY vacancy
    ADD CONSTRAINT fk_kdipb7wk536afi05k7ry8cvod FOREIGN KEY (company_id) REFERENCES company(id);


--
-- TOC entry 1825 (class 2606 OID 157696)
-- Dependencies: 167 1823 161 1941
-- Name: fk_n6x3soua7g9nuhkfud47meis5; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cv
    ADD CONSTRAINT fk_n6x3soua7g9nuhkfud47meis5 FOREIGN KEY (user_id) REFERENCES users(id);


--
-- TOC entry 1826 (class 2606 OID 157701)
-- Dependencies: 164 161 1813 1941
-- Name: fk_s065144c6812b7toa7pcbsm9h; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY invite
    ADD CONSTRAINT fk_s065144c6812b7toa7pcbsm9h FOREIGN KEY (cv_id) REFERENCES cv(id);


--
-- TOC entry 1946 (class 0 OID 0)
-- Dependencies: 5
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2014-03-29 06:26:46 MSK

--
-- PostgreSQL database dump complete
--

