package service.user;

import model.Account;
import model.Subject;

import java.util.List;

public interface UserService {

    void saveUser(Account account);

    List<Subject> getAllSubjectsByAccount(Account account);

    Account getCurrentAccount();

    void accountUpdate(Account account);

    Account getAccount(String login);



}
