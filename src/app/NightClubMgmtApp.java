package app;

import java.util.ArrayList;

public class NightClubMgmtApp extends ClubAbstractEntity {
    ArrayList<ClubAbstractEntity>clubbers;

    @Override
    public boolean match(String key) {
        return false;
    }

    @Override
    public boolean validateDate() {
        return false;
    }

    @Override
    public void commit() {

    }

    @Override
    public void rollBack() {

    }
}
