package ru.kpfu.itis.login.dao;

import ru.kpfu.itis.login.model.Credentials;

import java.util.List;

public interface CredentialsDao {

    public void add(Credentials credentials);

    public void update(Credentials credentials);

    public void delete(Long id);

    public List findAll();

    public Credentials findByPrimaryKey(Long id) ;
}
