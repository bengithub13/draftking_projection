package com.draftking.service;

import com.draftking.dto.PlayersStatsAllYearsDTO;



public interface HomeStatsAllYearsService {
public PlayersStatsAllYearsDTO getAllYearsStats( );
public PlayersStatsAllYearsDTO getAllYearsStatsByPlayer(String player);
}