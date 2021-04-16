package service.user;

import model.Account;
import model.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import repository.UserRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder encoder ) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    @Override
    public void saveUser(Account account) {
        account.setPassword(encoder.encode(account.getPassword()));
        userRepository.save(account);
        log.info("Пользователь сохранен!");
    }

    @Override
    public List<Subject> getAllSubjectsByAccount(Account account) {
        return account.getSubjects();
    }

    @Override
    public Account getCurrentAccount() {
        return userRepository.findByLogin(getNameCurrentUser());
    }

    @Override
    public void accountUpdate(Account account) {
        userRepository.save(account);
        log.info("Пользователь обновлен!");
    }

    @Override
    public Account getAccount(String login) {
        return userRepository.findByLogin(login);
    }

    public String getNameCurrentUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }
}
