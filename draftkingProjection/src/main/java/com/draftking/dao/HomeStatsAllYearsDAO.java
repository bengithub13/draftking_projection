package com.draftking.dao;

import java.util.List;

import com.draftking.domain.HomeStatsAllYears;



	public interface HomeStatsAllYearsDAO extends GenericDAO<HomeStatsAllYears> {
		public List<HomeStatsAllYears> findByPlayer(String player);
		public List<HomeStatsAllYears> findAllPlayers();
		
	}