import { React, useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import { MatchDetailCard } from '../components/MatchDetailCard';
import { MatchSmallCard } from '../components/MatchSmallCard';


export const MatchPage = () => {

  const [matches, setMatches] = useState([]);
  const {teamName, year} = useParams();

  useEffect(
    () => {
      const fetchMatches = async () => {
        const response = await fetch(`http://localhost:8080/team/${teamName}/matches?year=${year}`);
        const data = await response.json();
        setMatches(data);
      };
      fetchMatches();
    }, [teamName]
  );

  return (
    <div className="MatchPage">
      {
        matches.map(match => <MatchDetailCard teamName={teamName} match={match}/>)
      }
    </div>
  );
}
