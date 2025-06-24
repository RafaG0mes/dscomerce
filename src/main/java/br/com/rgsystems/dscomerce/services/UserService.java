package br.com.rgsystems.dscomerce.services;

import br.com.rgsystems.dscomerce.dto.UserDTO;
import br.com.rgsystems.dscomerce.entities.Role;
import br.com.rgsystems.dscomerce.entities.User;
import br.com.rgsystems.dscomerce.projections.UserDetailProjection;
import br.com.rgsystems.dscomerce.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    protected User authenticated(){
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Jwt jwtPrincipal = (Jwt) authentication.getPrincipal();
            String username = jwtPrincipal.getClaim("username");
            return userRepository.findByEmail(username).get();
        }
        catch (Exception e){
            throw new UsernameNotFoundException("Email not found");
        }
    }

    @Transactional(readOnly = true)
    public UserDTO getMe(){
        User user = authenticated();
        return new UserDTO(user);
    }
}
