package it.unicam.cs.ids.services;

import it.unicam.cs.ids.dto.UserRegistrationDto;
import it.unicam.cs.ids.model.Users;
import it.unicam.cs.ids.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

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
                userRegistrationDto.getPassword()
        );

        return userRepository.save(user);
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
}