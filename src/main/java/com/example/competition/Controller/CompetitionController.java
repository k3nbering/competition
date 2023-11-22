package com.example.competition.Controller;

import com.example.competition.Config.PasswordEncoderConfig;
import com.example.competition.DTO.PlayerDTO;
import com.example.competition.Entity.Player;
import com.example.competition.Entity.Schedule;
import com.example.competition.Entity.Team;
import com.example.competition.Entity.User;
import com.example.competition.Repository.*;
import com.example.competition.Security.UserDetailsImpl;
import jakarta.persistence.JoinColumn;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CompetitionController {
    @Autowired
    ScheduleRepository scheduleRepository;

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PlayerRepository playerRepository;

    @RequestMapping({"/", "/schedule"})
    private String schedule(Model model){
        Iterable<Schedule> schedules = scheduleRepository.findAll();
        model.addAttribute("schedules", schedules);
        return "schedule";
    }

    @RequestMapping("/teams")
    private String teams(Model model){
        Iterable<Team> teams = teamRepository.findAll();
        model.addAttribute("teams", teams);
        return "teams";
    }

    @GetMapping("/singin")
    private String newuser(){
        return "singin";
    }

    @PostMapping("/singin")
    private String singin(@RequestParam String login, @RequestParam String password){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        User user = new User();
        user.setPassword(passwordEncoder.encode(password));
        user.setLogin(login);
        user.setRoles(roleRepository.findByName("user"));
        userRepository.save(user);
        return "redirect:/";
    }

    @RequestMapping("/manualLogout")
    private String customLogout(Model models, HttpServletRequest request) throws ServletException
    {
        request.logout();
        return "redirect:/";
    }

    @GetMapping("/profile")
    private String profileIn(Authentication authentication, Model model){
        UserDetailsImpl user = (UserDetailsImpl) authentication.getPrincipal();
        Player player = playerRepository.findByUserId(user.getUserId()).orElseGet(Player::new);
        model.addAttribute("player", player);
        return "profile";
    }

    @PostMapping("/profile")
    private String profile(Authentication authentication, Model model, PlayerDTO playerDTO){
        UserDetailsImpl user = (UserDetailsImpl) authentication.getPrincipal();
        Player player = playerRepository.findByUserId(user.getUserId()).orElseGet(Player::new);
        player.setName(playerDTO.getName());
        player.setAge(playerDTO.getAge());
        player.setGender(playerDTO.getGender());
        player.setHeight(playerDTO.getHeight());
        player.setWeight(playerDTO.getWeight());
        player.setInfo(playerDTO.getInfo());
        player.setUser(userRepository.findById(user.getUserId()).get());
        playerRepository.save(player);
        model.addAttribute("player", player);
        return "profile";
    }

}
