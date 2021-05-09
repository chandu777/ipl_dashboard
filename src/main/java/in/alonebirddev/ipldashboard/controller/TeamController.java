/**
 * 
 */
package in.alonebirddev.ipldashboard.controller;

import java.time.LocalDate;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.alonebirddev.ipldashboard.model.Match;
import in.alonebirddev.ipldashboard.model.Team;
import in.alonebirddev.ipldashboard.repository.MatchRepository;
import in.alonebirddev.ipldashboard.repository.TeamRepository;

/**
 * @author chandrakant
 *
 */

@RestController
@CrossOrigin
public class TeamController {

	private TeamRepository teamRepositroy;
	private MatchRepository matchRepository;

	public TeamController(TeamRepository teamRepository, MatchRepository matchRepository) {
		this.teamRepositroy = teamRepository;
		this.matchRepository = matchRepository;
	}

	@GetMapping("/team/{teamName}")
	public Team getTeam(@PathVariable String teamName) {
		Team team = this.teamRepositroy.findByTeamName(teamName);
		team.setMatches(this.matchRepository.findLatestMatchesByTeam(teamName, 4));
		return team;
	}
	
	@GetMapping("/team/{teamName}/matches")
	public List<Match> getMatchesByTeamAndYear(@PathVariable String teamName, @RequestParam int year) {
		
		LocalDate startDate = LocalDate.of(year, 1, 1);
		LocalDate endDate = LocalDate.of(year+1, 1, 1);
		
		List<Match> matches = matchRepository.getMatchesByTeamBetweenDates(teamName, startDate, endDate);
		return matches;
	}
}
