package com.draftking.springbatch.mapper;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;

import com.draftking.springbatch.model.DraftKingCSVModel;

@Component("draftkingPlayersFieldSetMapper")
public class DraftKingPlayersFieldSetMapper implements FieldSetMapper<DraftKingCSVModel>{

	
	public DraftKingCSVModel mapFieldSet(FieldSet fieldSet) throws BindException {
	DraftKingCSVModel draftKingCsvModel = new DraftKingCSVModel();
	draftKingCsvModel.setAvgPointsPerGame(fieldSet.readFloat("avgPointsPerGame"));
	draftKingCsvModel.setName(fieldSet.readString("name"));
	draftKingCsvModel.setGameInfo(fieldSet.readString("gameInfo"));
	draftKingCsvModel.setPosition(fieldSet.readString("position"));
	draftKingCsvModel.setSalary(fieldSet.readString("salary"));
	draftKingCsvModel.setTeamAbbrev(fieldSet.readString("teamAbrev"));
		return draftKingCsvModel;
	}

}
