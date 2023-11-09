package com.forum.Cyberia;

import com.forum.Cyberia.models.Board;
import com.forum.Cyberia.models.Post;
import com.forum.Cyberia.models.User;
import com.forum.Cyberia.models.enums.Role;
import com.forum.Cyberia.repositories.BoardRepository;
import com.forum.Cyberia.repositories.PostRepository;
import com.forum.Cyberia.repositories.UserRepository;
import com.forum.Cyberia.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.Calendar;

@Configuration
public class Init implements CommandLineRunner {
    @Autowired
    private UserService userService;
    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private PostRepository postRepository;

    @Override
    public void run(String... args) throws Exception {
        Board b1 = new Board("lit");
        Board b2 = new Board("philo");
        Board b3 = new Board("lat");

        boardRepository.saveAll(Arrays.asList(b1, b2, b3));

        User u1 = new User("lvizf", "lvizf@navi.com", "1234", Calendar.getInstance().getTime(), Role.ADMIN, null);
        User u2 = new User("Policarpo", "quaresma@bol.com", "brasilacimadetudo", Calendar.getInstance().getTime(), Role.USER, "Amante do Brasil e estudante da lingua tupi.");

        Post p1 = new Post(null, "Aliquid carmen", "Quis amat valeat. Pereat qui nescit amare. Bis tanto pereat quisquis amare vetat.", Calendar.getInstance().toInstant(), u1, b3);


        userService.insert(u1);
        userService.insert(u2);
        postRepository.save(p1);
    }
}
