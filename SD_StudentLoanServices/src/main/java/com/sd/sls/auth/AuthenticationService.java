//package com.sd.sls.auth;
//
//import com.sd.sls.config.JwtService;
//import com.sd.sls.user.dao.IUserDAO;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Service
//public class AuthenticationService {
//
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @Autowired
//    private IUserDAO userDAO;
//
//    @Autowired
//    private JwtService jwtService;
//
//    public AuthenticationResponse register(RegisterRequest request) {
//        return null;
//    }
//
//    public AuthenticationResponse authenticate(AuthenticationRequest request) {
//        authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        request.getEmail(),
//                        request.getPassword()
//                )
//        );
//        var user = userDAO.findUserByEmail(request.getEmail());
//        if (user == null) {
//            throw new UsernameNotFoundException("User not found with email: " + request.getEmail());
//        }
//
//        var jwtToken = jwtService.generateToken(user);
//        return AuthenticationResponse.builder()
//                .token(jwtToken)
//                .build();
//    }
//}
