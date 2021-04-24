/**
 * 
 */
package in.alonebirddev.ipldashboard.repository;

import org.springframework.data.repository.CrudRepository;

import in.alonebirddev.ipldashboard.model.Team;

/**
 * @author chandrakant
 *
 */
public interface TeamRepository extends CrudRepository<Team, Long> {

	Team findByTeamName(String teamName);
}
