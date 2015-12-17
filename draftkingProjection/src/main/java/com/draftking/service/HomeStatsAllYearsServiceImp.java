package com.draftking.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.draftking.dao.HomeStatsAllYearsDAO;
import com.draftking.domain.HomeStatsAllYears;
import com.draftking.dto.PlayerStatsAllYearsDTO;
import com.draftking.dto.PlayersStatsAllYearsDTO;
import com.draftking.utility.Mapper;



@Service("HomeStatsAllYears")
public class HomeStatsAllYearsServiceImp implements HomeStatsAllYearsService {
	@Resource(name = "homeStatsAllYearsDAO")
	HomeStatsAllYearsDAO homeStatsAllYearsDAO;
	@Autowired
	Mapper<HomeStatsAllYears, PlayerStatsAllYearsDTO> homeStatsAllYearsMapper;
	
	
	@Transactional(rollbackFor=Exception.class)
	
	public PlayersStatsAllYearsDTO getAllYearsStats() {
		List<HomeStatsAllYears> playersList = homeStatsAllYearsDAO
				.findAllPlayers();
		return new PlayersStatsAllYearsDTO(setAllPlayersDTO(playersList));
	}

	
	
	public PlayersStatsAllYearsDTO getAllYearsStatsByPlayer(String player) {
		List<HomeStatsAllYears> playersList = homeStatsAllYearsDAO
				.findByPlayer(player);
		return new PlayersStatsAllYearsDTO(setAllPlayersDTO(playersList));
	}

	//@Transactional(rollbackFor=Exception.class)
	private List<PlayerStatsAllYearsDTO> setAllPlayersDTO(
			List<HomeStatsAllYears> playersList) {
		// TODO Auto-generated method stub
		List<PlayerStatsAllYearsDTO> allPlayersDTO = new ArrayList<PlayerStatsAllYearsDTO>();
		// map domain to each DTO
		for (HomeStatsAllYears hSay : playersList) {
			PlayerStatsAllYearsDTO playerDTO = new PlayerStatsAllYearsDTO();
			homeStatsAllYearsMapper.map(hSay, playerDTO);
			allPlayersDTO.add(playerDTO);
		}
		return allPlayersDTO;
	}
}
