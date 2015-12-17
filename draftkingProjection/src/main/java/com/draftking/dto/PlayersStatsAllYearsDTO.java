package com.draftking.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name="Players")
public class PlayersStatsAllYearsDTO {


	private List<PlayerStatsAllYearsDTO> playersStatsAllYears;

	public PlayersStatsAllYearsDTO(){
		
	}
	
	public PlayersStatsAllYearsDTO(List<PlayerStatsAllYearsDTO> playerStatsAllYears){
		this.playersStatsAllYears=playerStatsAllYears;
	}
	//@XmlElementWrapper(name = "players")
	@XmlElement(name="Player")
	public List<PlayerStatsAllYearsDTO> getPlayers(){
		return playersStatsAllYears;
	}

	public void setPlayers(List<PlayerStatsAllYearsDTO> playersStatsAllYears){
		this.playersStatsAllYears=playersStatsAllYears;
	}

	
}
