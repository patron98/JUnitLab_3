package be.intecbrussel.JUnitLab_3.service;

import be.intecbrussel.JUnitLab_3.model.User;
import be.intecbrussel.JUnitLab_3.repository.UserRepository;

import java.util.List;
import java.util.Optional;


public interface UserService{

    // TODO: Gebruik hier constraint validation voor de email-adres
    public Optional<User> retrieveUserByEmail(String email, String password);

    // TODO: Gebruik hier REGEX-validatie voor de phonenummer
    public Optional<User> retrieveUserByPhone(String phone, String password);

    // TODO: pageNo en pageSize kunnen geen negative of zero values kregen.
    public List<User> retrieveAllUsersInPages(Integer pageNo, Integer pageSize);

    public Long createUser(User user);

    // TODO: Gebruik hier constraint validation voor de email-adres
    public Long updateUserEmailById(Long userId, String email);

    // TODO: Gebruik hier REGEX-validatie voor de phonenumber
    public Long updateUserPhoneById(Long userId, String phone);
}
