package br.com.rgsystems.dscomerce.services;

import br.com.rgsystems.dscomerce.entities.Role;
import br.com.rgsystems.dscomerce.entities.User;
import br.com.rgsystems.dscomerce.projections.UserDetailProjection;
import br.com.rgsystems.dscomerce.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<UserDetailProjection> userDetail = userRepository.searchUserAndRoleByEmail(username);
        if(userDetail.size() == 0){
            throw new UsernameNotFoundException("User not found");
        }
        User user = new User();

        user.setEmail(username);
        user.setPassword(userDetail.get(0).getPassword());
        for (UserDetailProjection projection : userDetail){
            user.addRole(new Role(projection.getRoleId(), projection.getAuthority()));
        }
        return user;
    }
}
