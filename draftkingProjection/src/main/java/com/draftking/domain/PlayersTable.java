package com.draftking.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

@Entity


@NamedQueries({
	@NamedQuery(name="com.draftking.domain.PlayersTable.findByPlayer",query="from PlayersTable a where a.player_name=?"),	
	@NamedQuery(name="com.draftking.domain.PlayersTable.findAllPlayers",query="from PlayersTable a")})


@Table(name = "PLAYERS_TABLE")


public class PlayersTable extends AbstractDomain {

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((player_name == null) ? 0 : player_name.hashCode());
		result = prime * result
				+ ((position == null) ? 0 : position.hashCode());
		result = prime * result + ((team == null) ? 0 : team.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
//		if (!super.equals(obj))
//			return false;
		if (!(obj instanceof PlayersTable))
			return false;
		PlayersTable other = (PlayersTable) obj;
		if (player_name == null) {
			if (other.player_name != null)
				return false;
		} else if (!player_name.equals(other.player_name))
			return false;
		if (position == null) {
			if (other.position != null)
				return false;
		} else if (!position.equals(other.position))
			return false;
		if (team == null) {
			if (other.team != null)
				return false;
		} else if (!team.equals(other.team))
			return false;
		return true;
	}

	private static final long serialVersionUID = 2l;
	@Column(name="PLAYER_NAME")
	private String player_name;
	@Column(name="TEAM")
	private String team;
	@Column(name="POSITION")
	private String position;

	public String getPlayer_name() {
		return player_name;
	}

	public void setPlayer_name(String player_name) {
		this.player_name = player_name;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

}
