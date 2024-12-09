//package com.sd.sls.config;
//
///*
// * @Author: Nikunj Panchal
// */
//
//import com.sd.sls.user.dao.IUserDAO;
//import com.sd.sls.user.model.CustomUserDetails;
//import com.sd.sls.user.model.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//
//@Configuration
//public class ApplicationConfig {
//
//    @Autowired
//    private IUserDAO userDAO;
//
//    @Autowired
//    private UserDetailsService userDetailsService;
//
//    @Bean
//    public UserDetailsService userDetailsService() {
//        return username -> {
//            User user = userDAO.findUserByEmail(username);
//            if (user == null) {
//                throw new UsernameNotFoundException("User not found with email: " + username);
//            }
//            return new CustomUserDetails(user);
//        };
//
////        return username -> (org.springframework.security.core.userdetails.UserDetails) userDAO.findUserByEmail(username);
//
////		return new UserDetailsService() {
////			@Override
////			public UserDetails loadUserByUsername (String username) throws UsernameNotFoundException {
////				return null;
////			}
////		};
//    }
//
//    @Bean
//    public AuthenticationProvider authenticationProvider(PasswordEncoder passwordEncoder) {
//        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//        authProvider.setUserDetailsService(userDetailsService);
//        authProvider.setPasswordEncoder(passwordEncoder());
//
//        return authProvider;
//    }
//
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
//        return config.getAuthenticationManager();
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//}
