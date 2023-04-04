package job.list.jdbc.service;

import job.list.domen.User;

import java.util.Collection;

public interface UserService {

    User createUser(String name, String lastName, String passport);

    Collection<User> findUserByPassport(String passport);

    Collection<User> findUserByNameAndLastName(String name, String lastName);

    Collection<User> findUserByLastName(String lastName);

    void deleteUserByPassport(String passport);

    User  updateUser(String name, String lastName, String passport);


}
