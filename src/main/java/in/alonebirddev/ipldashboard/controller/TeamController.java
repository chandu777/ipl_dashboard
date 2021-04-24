/**
 * 
 */
package in.alonebirddev.ipldashboard.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import in.alonebirddev.ipldashboard.model.Team;
import in.alonebirddev.ipldashboard.repository.MatchRepository;
import in.alonebirddev.ipldashboard.repository.TeamRepository;

/**
 * @author chandrakant
 *
 */

@RestController
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
}
