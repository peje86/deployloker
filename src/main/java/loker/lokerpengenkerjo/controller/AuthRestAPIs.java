// package loker.lokerpengenkerjo.controller;

// import java.util.HashSet;
// import java.util.Set;
// import java.util.List;
// import java.util.Optional;

// import javax.validation.Valid;
 
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.Authentication;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.web.bind.annotation.CrossOrigin;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.RequestMethod;
// import org.springframework.web.bind.annotation.ResponseBody;
// import org.springframework.web.bind.annotation.RestController;

// import org.springframework.security.access.prepost.PreAuthorize;
// import org.springframework.web.bind.annotation.GetMapping;


 
// import loker.lokerpengenkerjo.messages.request.LoginForm;
// import loker.lokerpengenkerjo.messages.request.SignUpForm;
// import loker.lokerpengenkerjo.messages.response.JwtResponse;
// import loker.lokerpengenkerjo.model.Role;
// import loker.lokerpengenkerjo.model.RoleName;
// import loker.lokerpengenkerjo.model.User;
// // import loker.lokerpengenkerjo.model.Lokers;
// // import loker.lokerpengenkerjo.model.Perusahaans;
// import loker.lokerpengenkerjo.repository.RoleRepository;
// import loker.lokerpengenkerjo.repository.UserRepository;
// // import loker.lokerpengenkerjo.repository.LokerRepository;
// // import loker.lokerpengenkerjo.repository.PerusahaanRepository;
// import loker.lokerpengenkerjo.security.jwt.JwtProvider;
// import loker.lokerpengenkerjo.security.services.UserPrinciple;


// @CrossOrigin(origins = "*", maxAge = 3600)
// @RestController
// @RequestMapping("/loker")
// public class AuthRestAPIs {
 
//     @Autowired
//     AuthenticationManager authenticationManager;
 
//     @Autowired
//     UserRepository userRepository;
 
//     @Autowired
//     RoleRepository roleRepository;

//     // @Autowired
//     // LokerRepository lokerRepository;

//     // @Autowired
//     // PerusahaanRepository perusahaanRepository;
 
//     @Autowired
//     PasswordEncoder encoder;
 
//     @Autowired
//     JwtProvider jwtProvider;
 
// //auth user signup,signin


//     @PostMapping("/signin")
//     public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {
 
//         Authentication authentication = authenticationManager.authenticate(
//                 new UsernamePasswordAuthenticationToken(
//                         loginRequest.getUsername(),
//                         loginRequest.getPassword()
//                 )
//         );
 
//         SecurityContextHolder.getContext().setAuthentication(authentication);
 
//         String jwt = jwtProvider.generateJwtToken(authentication);
//         return ResponseEntity.ok(new JwtResponse(jwt));
//     }
 
//     @PostMapping("/signup")
//     public ResponseEntity<String> registerUser(@Valid @RequestBody SignUpForm signUpRequest) {
//         if(userRepository.existsByUsername(signUpRequest.getUsername())) {
//             return new ResponseEntity<String>("Fail -> Username is already taken!",
//                     HttpStatus.BAD_REQUEST);
//         }
 
//         if(userRepository.existsByEmail(signUpRequest.getEmail())) {
//             return new ResponseEntity<String>("Fail -> Email is already in use!",
//                     HttpStatus.BAD_REQUEST);
//         }
 
//         // Creating user's account
//         User user = new User(signUpRequest.getName(), signUpRequest.getUsername(),
//                 signUpRequest.getEmail(), encoder.encode(signUpRequest.getPassword()));
 
//         Set<String> strRoles = signUpRequest.getRole();
//         Set<Role> roles = new HashSet<>();
 
//         strRoles.forEach(role -> {
//           switch(role) {
//           case "admin":
//             Role adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
//                   .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
//             roles.add(adminRole);
            
//             break;
//           case "usera":
//                 Role useraRole = roleRepository.findByName(RoleName.ROLE_USERA)
//                   .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
//                 roles.add(useraRole);
                
//             break;
//             case "userb":
//             Role userbRole = roleRepository.findByName(RoleName.ROLE_USERB)
//               .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
//             roles.add(userbRole);
            
//         break;
//           default:
//               Role userRole = roleRepository.findByName(RoleName.ROLE_PEGAWAI)
//                   .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
//               roles.add(userRole);              
//           }
//         });
        
//         user.setRoles(roles);
//         userRepository.save(user);
 
//         return ResponseEntity.ok().body("User registered successfully!");
//     }


  
// /////////////////////////////////////////////////////end auth////////////


// // memilih role user

// @RequestMapping(value = "/info", 
// produces = "application/json",
// method = RequestMethod.GET)
// @ResponseBody
// public Object currentUserName(Authentication authentication) {
// return authentication.getPrincipal();
// }


// //4. merubah data pake PUT user

// @PutMapping("/updateuser/{id}")//tipe biomahasisa
// @PreAuthorize(" hasRole('USERB') or hasRole('ADMIN')")
// public User replaceUser(@RequestBody User newUser, @PathVariable Long id){
//     return userRepository.findById(id)
//     .map(user->{
//         user.setName(newUser.getName());
//         user.setId(newUser.getId());
//         return userRepository.save(newUser);
//     }).orElseGet(()->{
//         newUser.setId(id);
//         return userRepository.save(newUser);
//     });
    
// }




// }