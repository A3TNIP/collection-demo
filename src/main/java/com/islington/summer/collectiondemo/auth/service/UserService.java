package com.islington.summer.collectiondemo.auth.service;

import com.islington.summer.collectiondemo.auth.dto.RegisterDto;
import com.islington.summer.collectiondemo.model.Role;
import com.islington.summer.collectiondemo.model.UserEntity;
import com.islington.summer.collectiondemo.repository.CustomRoleRepository;
import com.islington.summer.collectiondemo.repository.RoleRepository;
import com.islington.summer.collectiondemo.repository.UserRepository;
import com.islington.summer.collectiondemo.service.BaseService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service(value = "userService")
public class UserService extends BaseService<UserEntity> implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CustomRoleRepository roleRepository;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, CustomRoleRepository roleRepository) {
        super(userRepository);
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new org.springframework.security.core.userdetails.User(username, user.getPassword(), getAuthority(user));
    }

    private Set<SimpleGrantedAuthority> getAuthority(UserEntity userEntity) {
        return userEntity.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName().replace(" ", "_").toUpperCase()))
                .collect(Collectors.toSet());
    }

    public UserEntity register(RegisterDto registerDto) {
        var password = passwordEncoder.encode(registerDto.getPassword());

        UserEntity user = new UserEntity();
        user.setName(registerDto.getName());
        user.setEmail(registerDto.getEmail());
        user.setPassword(password);

        Set<Role> roles = new HashSet<>(roleRepository.findAllRolesByNameList(registerDto.getRoles()));
        user.setRoles(roles);

        return super.save(user);
    }
}
