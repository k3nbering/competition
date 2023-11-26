package com.example.competition.Controller;

import com.example.competition.Entity.Player;
import com.example.competition.Entity.Schedule;
import com.example.competition.Entity.Team;
import com.example.competition.Repository.PlayerRepository;
import com.example.competition.Repository.ScheduleRepository;
import com.example.competition.Repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RequestMapping("/admin")
@Controller
public class AdminController {
    @Autowired
    PlayerRepository playerRepository;
    @Autowired
    TeamRepository teamRepository;
    @Autowired
    ScheduleRepository scheduleRepository;

    @RequestMapping("/players")
    private String players(Model model){
        Iterable<Player> players = playerRepository.findAll();
        model.addAttribute("players", players);
        return "admin/index";
    }
    @RequestMapping("/player/{id}")
    private String odom(@PathVariable Integer id){
        Optional<Player> optional = playerRepository.findById(id);
        Player player = optional.get();
        player.setAccepted(true);
        playerRepository.save(player);
        return "redirect:/admin/players";
    }
    @RequestMapping("/teams")
    private String teams(Model model){
        Iterable<Team> teams = teamRepository.findAll();
        model.addAttribute("teams", teams);
        return "admin/teams";
    }
    @GetMapping("/teams/adplayer/{teamid}")
    private String adPlayer(@PathVariable Integer teamid, Model model){
        List<Player> players = new ArrayList<>();
        playerRepository.findAll().forEach(x -> {
            if (x.getTeam() == null){
                players.add(x);
            }
        });
        model.addAttribute("players", players);
        model.addAttribute("teamid", teamid);
        return "admin/adplayer";
    }
    @PostMapping("/teams/adplayer/{teamid}")
    private String addPlayerToTeam(@PathVariable Integer teamid, @RequestParam(required = false) List<Integer> pids){
        Team team = teamRepository.findById(teamid).get();
        Iterable<Player> players = playerRepository.findAllById(pids);

        for (Player player : players){
            player.setTeam(team);
            team.getPlayers().add(player);
        }

        teamRepository.save(team);

        return "redirect:/admin/teams";
    }
    @GetMapping("/newteam")
    private String add (){
        return "admin/newteam";
    }

    @PostMapping("/newteam")
    private String newteam(@RequestParam String name){
        Team team = new Team();
        team.setName(name);
        teamRepository.save(team);
        return "redirect:/teams";
    }
    @GetMapping("/schedule")
    private String newschedule(Model model){
        Iterable<Schedule> schedules = scheduleRepository.findAll();
        Iterable<Team> teams = teamRepository.findAll();

        model.addAttribute("schedules", schedules);
        model.addAttribute("teams", teams);
        return "admin/schedule";
    }
    @PostMapping("/schedule")
    private String nsch(@RequestParam Integer team1,
                        @RequestParam Integer team2,
                        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date matchtime,
                        Model model){

        Team firstteam = teamRepository.findById(team1).get();
        Team secondteam = teamRepository.findById(team2).get();
        Schedule schedule = new Schedule();
        schedule.setDate(matchtime);
        schedule.setTeamFirst(firstteam);
        schedule.setTeamSecond(secondteam);
        scheduleRepository.save(schedule);

        Iterable<Schedule> schedules = scheduleRepository.findAll();
        Iterable<Team> teams = teamRepository.findAll();

        model.addAttribute("schedules", schedules);
        model.addAttribute("teams", teams);
        return "admin/schedule";
    }

}
