package dao;

import dao.impl.CVDaoImpl;

public class Factory {

    private static CVDao cvDao;
    private static Factory instance;

    private Factory() {
    }

    public static synchronized Factory getInstance() {
        if (instance == null) {
            instance = new Factory();
        }
        return instance;
    }

    public CVDao getCvDao() {
        if (cvDao == null) {
            cvDao = new CVDaoImpl();
        }
        return cvDao;
    }
}