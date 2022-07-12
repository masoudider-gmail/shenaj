package shenaj.shenajtask.service.serviceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import shenaj.shenajtask.domain.AppUser;
import shenaj.shenajtask.repository.UserRepository;
import shenaj.shenajtask.service.UserService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class UserDetailServiceImp implements UserService, UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        AppUser appUser = repository
                .findAppUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Name Not Found"));

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(appUser.getRole()));

        return new User(
                appUser.getUsername(),
                appUser.getPassword(),
                authorities
        );
    }

    @Override
    public AppUser save(AppUser user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return repository.save(user);
    }

    @Override
    public List<AppUser> allUsers() {
        return repository.findAll();
    }

    @Override
    public void deleteUser(int id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<AppUser> findByUserName(String userName) {
        return repository.findAppUserByUsername(userName);
    }
}
