package shenaj.shenajtask.service;

import shenaj.shenajtask.domain.AppUser;

import java.util.List;
import java.util.Optional;

public interface UserService {
    AppUser save(AppUser user);

    List<AppUser> allUsers();

    void deleteUser(int id);

    Optional<AppUser> findByUserName(String userName);
}
