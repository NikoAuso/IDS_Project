package it.unicam.cs.ids.services;

import it.unicam.cs.ids.dto.UserRegistrationDto;
import it.unicam.cs.ids.model.Users;
import it.unicam.cs.ids.repository.POIRepository;
import it.unicam.cs.ids.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private POIRepository poiRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Users create(UserRegistrationDto userRegistrationDto) {
        if (userRepository.findByUsername(userRegistrationDto.getUsername()).isPresent()) {
            throw new RuntimeException("l'utente esiste già.");
        }

        if (!userRegistrationDto.getPassword().equals(userRegistrationDto.getConfirmPassword())) {
            throw new RuntimeException("le password non corrispondono.");
        }

        Users user = new Users(
                userRegistrationDto.getNome(),
                userRegistrationDto.getCognome(),
                userRegistrationDto.getEmail(),
                userRegistrationDto.getUsername(),
                passwordEncoder.encode(userRegistrationDto.getPassword())
        );

        return userRepository.save(user);
    }

    public List<Users> read() {
        return userRepository.findAll();
    }

    public Users read(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("utente non trovato."));
    }

    public Users update(Long id, Users updatedUsers) {
        return userRepository.findById(id).map(user -> {
            user.setUsername(updatedUsers.getUsername());
            user.setPassword(updatedUsers.getPassword());
            return userRepository.save(user);
        }).orElseThrow(() -> new RuntimeException("utente non trovato."));
    }

    public void delete(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        } else {
            throw new RuntimeException("utente non trovato.");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("utente non trovato."));
    }

    public Users addPOIToPreferiti(Long poiId, Users user) {
        user.getPreferiti().add(
                poiRepository.findById(poiId)
                        .orElseThrow(() -> new RuntimeException("POI non trovato."))
        );
        return userRepository.save(user);
    }

    public Users getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (Users) authentication.getPrincipal();
    }
}