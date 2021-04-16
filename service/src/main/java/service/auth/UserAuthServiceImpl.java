package service.auth;

import model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.UserRepository;

@Service
@Transactional
public class UserAuthServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws InternalAuthenticationServiceException {
       Account account = userRepository.findByLogin(username);
       return User.builder()
                .username(account.getLogin())
                .password(account.getPassword())
                .roles(account.getRole().name())
                .build();
    }
}
