package com.securityservice.service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.securityservice.dao.HospitalUserRepository;
import com.securityservice.dto.HospitalUserDTO;
import com.securityservice.entity.HospitalUser;
import com.securityservice.entity.Roles;
import com.securityservice.util.Constants;

@Service
public class HospitalUserServiceImpl implements HospitalUserService, Constants, UserDetailsService {

	@Autowired
	HospitalUserRepository hospitalUserRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<SimpleGrantedAuthority> roles = null;
		HospitalUser hospitalUser = hospitalUserRepository.findByUsername(username);
		if (Objects.nonNull(hospitalUser)) {
			roles = Arrays.asList(new SimpleGrantedAuthority(hospitalUser.getRole().getUserRole()));
			return new User(hospitalUser.getUsername(), hospitalUser.getPassword(), roles);
		} else
			throw new UsernameNotFoundException(USER_NOT_FOUND_WITH_NAME + username);
	}

	@Override
	public void saveUser(HospitalUserDTO hospitalUserDTO) {
		HospitalUser hospitalUser = new HospitalUser();
		Roles roleOfUser = hospitalUserDTO.getRole().equals(Roles.ROLE_ADMIN.getUserRole()) ? Roles.ROLE_ADMIN
				: Roles.ROLE_USER;
		hospitalUser.setUsername(hospitalUserDTO.getUsername());
		hospitalUser.setPassword(hospitalUserDTO.getPassword());
		hospitalUser.setRole(roleOfUser);
		hospitalUserRepository.save(hospitalUser);

	}

}
