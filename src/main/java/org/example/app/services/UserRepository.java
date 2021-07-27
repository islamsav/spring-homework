package org.example.app.services;

import org.apache.log4j.Logger;
import org.example.web.dto.RegistrationForm;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository implements ProjectRepository<RegistrationForm> {

    private Logger logger = Logger.getLogger(UserRepository.class);
    private List<RegistrationForm> users = new ArrayList<>();

    @Override
    public List<RegistrationForm> retreiveAll() {
        return new ArrayList<>(users);
    }

    @Override
    public void store(RegistrationForm user) {
        logger.info("store new user: " + user.toString());
        user.setId(user.hashCode());
        users.add(user);
    }

    @Override
    public boolean removeItemById(Integer userIdToRemove) {
        return false;
    }

    @Override
    public void removeItemByParam(RegistrationForm item) {
        // implement me
    }
}
