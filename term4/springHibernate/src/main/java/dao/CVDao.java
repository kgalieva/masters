package dao;

import model.CV;

import java.util.List;

public interface CVDao extends CRUDDao<CV> {

    public List<CV> findCVsByOwner(Long owner);
}
