package florie.photofoot.service;

import florie.photofoot.mapper.UserInfoMapper;
import florie.photofoot.model.CustomUserDetails;
import florie.photofoot.model.UserInfo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private UserInfoMapper uiMapper;
    public CustomUserDetailsService(UserInfoMapper uiMapper) {
        this.uiMapper = uiMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo ui = uiMapper.findByUsername(username);
        if (null == ui) {
            throw new UsernameNotFoundException(username + " Not Found.");
        }
        return new CustomUserDetails(ui);
    }
}
