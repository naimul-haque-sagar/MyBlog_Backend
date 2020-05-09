package myBrain.service;

import myBrain.model.AppUser;
import myBrain.model.AppUserGroup;
import myBrain.repository.AppUserGroupRepository;
import myBrain.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private AppUserGroupRepository appUserGroupRepository;
	
    @Autowired
    private AppUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = userRepository.findByUserName(username).orElseThrow(() ->
                new UsernameNotFoundException("No user found " + username));
        
        List<AppUserGroup> list=appUserGroupRepository.findByUsername(username);
        
        return new org.springframework.security.core.userdetails.User(user.getUserName(),
                user.getPassword(),
                true, true, true, true,
                getAuthorities(list));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(List<AppUserGroup> list) {
        if(list==null) {
        	return Collections.emptySet();
        }
        
        Set<SimpleGrantedAuthority> set=new HashSet<>();
        for(AppUserGroup x:list) {
        	set.add(new SimpleGrantedAuthority(x.getUser_group()));
        }
        return set;
    }
}
