package com.hh.aws.security;

import com.hh.aws.model.User;
import com.hh.aws.repository.UserRepository;
import com.hh.aws.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Authenticate a user from the database.
 */
@Component("userDetailsService")
public class UserModelDetailsService implements UserDetailsService {
   @Autowired
   private UserService userService;

   @Override
   public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      User user = userService.findUserByName(username);
      if (user == null) {
         throw new UsernameNotFoundException("该用户不存在");
      }
      // 用户权限列表，根据用户拥有的权限标识与如 @PreAuthorize("hasAuthority('sys:menu:view')") 标注的接口对比，决定是否可以调用接口
      Set<String> permissions = userService.findPermissions(username);
      List<GrantedAuthority> grantedAuthorities = permissions.stream().map(GrantedAuthorityImpl::new).collect(Collectors.toList());
      return new JwtUserDetails(username, user.getPassword(), grantedAuthorities);
   }
}
