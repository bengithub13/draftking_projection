package com.draftking.springbatch.itemWriter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.draftking.dao.HomeStatsAllYearsDAO;
import com.draftking.dao.PlayersTableDAO;
import com.draftking.domain.PlayersTable;
import com.draftking.springbatch.model.DraftKingCSVModel;

@Component("playersTableItemWriter")
public class PlayersTableItemWriter implements ItemWriter<DraftKingCSVModel> {
	// @Autowired

	@PersistenceContext(unitName = "restProjectPU")
	private EntityManager em;
	private Set<PlayersTable> allPLayers;
	@Resource(name = "homeStatsAllYearsDAO")
	HomeStatsAllYearsDAO homeStatsAllYearsDAO;
	@Resource(name = "playersTableDAO")
	//@Autowired
	private PlayersTableDAO playersTableDao;
	private List<PlayersTable> allPlayersList;

	public void write(List<? extends DraftKingCSVModel> draftKingCSVPLayers)
			throws Exception {
		// TODO Auto-generated method stub
		getAllPlayers();
		for (DraftKingCSVModel draftKingCSVPlayer : draftKingCSVPLayers) {
			PlayersTable playersTable = new PlayersTable();
			mapPlayersTableDomain(playersTable, draftKingCSVPlayer);
			if (!(playerExist(playersTable))) {
				em.persist(playersTable);
			}
		}

	}

	private void mapPlayersTableDomain(PlayersTable playersTable,
			DraftKingCSVModel draftKingCSVModel) {
		playersTable.setPlayer_name(draftKingCSVModel.getName());
		playersTable.setPosition(draftKingCSVModel.getPosition());
		playersTable.setTeam(draftKingCSVModel.getTeamAbbrev());
	}

	private void getAllPlayers() {
		allPlayersList = playersTableDao.findAllPlayers();
		allPLayers = allPlayersList.isEmpty() ? new HashSet() : new HashSet(allPlayersList);
	}

	private boolean playerExist(PlayersTable currentPlayer) {
		if (allPlayersList.contains(currentPlayer))
		return true;
		else 
			return false;
	}
}
