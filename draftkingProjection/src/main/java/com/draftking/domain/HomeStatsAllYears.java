package com.draftking.domain;

import java.math.BigDecimal;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

@Entity
@Cacheable(false)
@NamedQueries({
	@NamedQuery(name="com.draftking.domain.HomeStatsAllYears.findByPlayer",query="from HomeStatsAllYears a where a.player=?"),	
	@NamedQuery(name="com.draftking.domain.HomeStatsAllYears.findAllPlayers",query="from HomeStatsAllYears a")})


@Table(name = "HOME_STATS_ALL_YEARS_VIEW_2")
public class HomeStatsAllYears extends AbstractDomain {

	private static final long serialVersionUID = 2l;
	@Column(name = "PLAYER")
	private String player;
	@Column(name = "HOME_AVG")
	private BigDecimal homeAvg;
	@Column(name = "AWAY_AVG")
	private BigDecimal awayAvg;
	@Column(name = "Home_Games")
	private Integer homeGames;
	@Column(name = "Away_Games")
	private Integer awayGames;

	public String getPlayer() {
		return this.player;
	}

	public void setPlayer(String player) {
		this.player=player;
	}

	public BigDecimal getHomeAvg() {
		return this.homeAvg;
	}
	public void setHomeAvg(BigDecimal homeAvg) {
		this.homeAvg=homeAvg;
	}

	public BigDecimal getAwayAvg() {
		return this.awayAvg;
	}
	public void setAwayAvg(BigDecimal awayAvg) {
		this.awayAvg=awayAvg;
	}


	public Integer getHomeGames() {
		return this.homeGames;
	}

	public Integer getAwayGames() {
		return this.awayGames;
	}

}
