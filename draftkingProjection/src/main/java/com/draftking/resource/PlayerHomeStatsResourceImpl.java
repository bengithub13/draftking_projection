package com.draftking.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.draftking.dto.PlayersStatsAllYearsDTO;
import com.draftking.service.HomeStatsAllYearsServiceImp;

@Controller("playerHomeStatsResource")
public class PlayerHomeStatsResourceImpl implements PlayerHomeStatsResource {

@Autowired
HomeStatsAllYearsServiceImp homeStatsAllYearsService;

	
	public PlayersStatsAllYearsDTO getAllPlayersHomeStats() {
		// TODO Auto-generated method stub
		return homeStatsAllYearsService.getAllYearsStats();
	}

	
	public PlayersStatsAllYearsDTO getAllPLayersHomeStats(String playerName) {
		// TODO Auto-generated method stub
		return homeStatsAllYearsService.getAllYearsStatsByPlayer(playerName);
	}

}



