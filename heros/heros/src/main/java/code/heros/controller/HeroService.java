package code.heros.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import code.heros.model.Hero;
import code.heros.model.HeroRepository;
import code.heros.UserNotFoundException;

@Service
public class HeroService {

    @Autowired
    private HeroRepository heroRepository;

    public void verifyUser(String username) throws UserNotFoundException {
        Optional<Hero> hero = heroRepository.findByName(username);
        if (!hero.isPresent()) {
            throw new UserNotFoundException("Utilisateur non trouvé");
        }
    }
}