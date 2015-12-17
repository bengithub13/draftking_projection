package com.draftking.dao;

import java.util.List;

import com.draftking.domain.HomeStatsAllYears;
import com.draftking.domain.PlayersTable;

public interface PlayersTableDAO  extends GenericDAO<PlayersTable> {
public List<PlayersTable> findByPlayer(String Name);
public List<PlayersTable> findAllPlayers();
}


