package com.ozone.user.component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ozone.component.datamodel.Employee;


@Service
public class GetUserDetailsService implements UserDetailsService {

    @Autowired
	private EmployeeRepository employeeRepository;
    
	public GetUserDetailsService(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	public GetUserDetailsService() {
	}

	@SuppressWarnings("unchecked")
	public UserDetails loadUserByUsername(String oganizationEmailId) throws UsernameNotFoundException {
		
		User user = null ;
		//boolean isMobileNumber = Pattern.matches("^[0-9]{10}$", userName);
		boolean isEmailId = Pattern.matches("^(.+)@(.+)$", oganizationEmailId);

		if (isEmailId) {
			Employee employee = employeeRepository.findByEmailId(oganizationEmailId);
			
			if (employee == null)
				throw new UsernameNotFoundException("Invalid Credentials" + oganizationEmailId);
			
			if(employee != null) {
				user = new User(employee.getOrganizationEmailId(), employee.getPassword(),
						(Collection<? extends GrantedAuthority>) Collections.unmodifiableCollection(new ArrayList<Object>()));
			}			
			return User.withUserDetails(user).build();
		
		}  else {
			throw new RuntimeException("Invalid input");
		}

	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
