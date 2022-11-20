package be.intecbrussel.JUnitLab_3.service;

import be.intecbrussel.JUnitLab_3.model.User;
import be.intecbrussel.JUnitLab_3.model.UserProfile;
import be.intecbrussel.JUnitLab_3.repository.UserProfileRepository;
import be.intecbrussel.JUnitLab_3.repository.UserRepository;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private UserProfileRepository profileRepository;

    @Override
    public Optional<User> retrieveUserByEmail(String email, String password) {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email cannot be empty");
        }

        if (!email.contains(".") || !email.contains("@")) {
            throw new IllegalArgumentException("Must be valid email");
        }

        User searchUser = new User();
        searchUser.setEmail(email);
        searchUser.setPassword(password);

        Optional<User> foundUser = userRepository.findOne(Example.of(searchUser));

        if (foundUser.isEmpty()) {
            throw new NullPointerException("User Not found");
        }

        return Optional.of(foundUser.get());
    }

    @Override
    public Optional<User> retrieveUserByPhone(String phone, String password) {

        String regex = "^\\+(?:[0-9] ?){6,14}[0-9]$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phone);

        if (!matcher.matches()) {
            throw new IllegalArgumentException("Phone number must be valid");
        }

        UserProfile searchUserProfile = new UserProfile();
        searchUserProfile.setPhoneNumber(phone);

        User searchUser = new User();
        searchUser.setProfile(searchUserProfile);
        searchUser.setPassword(password);

        Optional<User> foundUser = userRepository.findOne(Example.of(searchUser));

        if (foundUser.isEmpty()) {
            throw new NullPointerException("User Not found");
        }

        return Optional.of(foundUser.get());
    }

    @Override
    public List<User> retrieveAllUsersInPages(Integer pageNo, Integer pageSize) {

        if (pageNo <= 0 || pageSize <= 0) {
            throw new IllegalArgumentException("Page Number or size cannot be 0");
        }

        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by("name"));
        Page<User> pagedResult = userRepository.findAll(paging);
        List<User> userList = null;

        if (pagedResult.isEmpty()) {
            throw new NullPointerException("page Not found");
        }
        userList = pagedResult.getContent();

        return userList;
    }

    @Override
    public Long createUser(User user) {
        User newUser = new User();
        newUser.setId(user.getId());
        userRepository.save(user);
        return newUser.getId();
    }

    @Override
    public Long updateUserEmailById(Long userId, String email) {

        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email cannot be empty");
        }

        if (!email.contains(".") || !email.contains("@")) {
            throw new IllegalArgumentException("Must be valid email");
        }

        User searchUser = new User();
        searchUser.setId(userId);

        Optional<User> foundUser = userRepository.findOne(Example.of(searchUser));


        if (foundUser.isEmpty()) {
            throw new NullPointerException("User not found");
        }

        User user = foundUser.get();
        user.setEmail(email);

        return user.getId();
    }

    @Override
    public Long updateUserPhoneById(Long userId, String phone) {

        String regex = "^\\+(?:[0-9] ?){6,14}[0-9]$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phone);

        if (!matcher.matches()) {
            throw new IllegalArgumentException("Phone number must be valid");
        }

        User searchUser = new User();
        searchUser.setId(userId);

        Optional<User> foundUser = userRepository.findOne(Example.of(searchUser));


        if (foundUser.isEmpty()) {
            throw new NullPointerException("User not found");
        }

        User user = foundUser.get();
        UserProfile profile = user.getProfile();
        profile.setPhoneNumber(phone);

        return user.getId();
    }
}
