package com.draftking.dto;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import javax.xml.bind.annotation.XmlElement;

//@XmlRootElement(name = "Player")

public class PlayerStatsAllYearsDTO {
	private String PlayerName;
	private String homeAvg;
	private String awayAvg;
	private String homeCount;
	private String awayCount;
	private DecimalFormat decimalFormat = new DecimalFormat("###.###");

	// @XmlElementWrapper(name="PLayer")

	@XmlElement(name = "player_name")
	public String getPlayerName() {
		return this.PlayerName;
	}

	public void setPlayerName(String PlayerName) {
		this.PlayerName = PlayerName;
	}

	@XmlElement(name = "home_avg")
	public String getHomeAvg() {
		return this.homeAvg;
	}

	public void setHomeAvg(BigDecimal homeAvg) {
		this.homeAvg = decimalFormat.format(homeAvg);
	}

	@XmlElement(name = "away_avg")
	public String getAwayAvg() {
		return this.awayAvg;
	}

	public void setAwayAvg(BigDecimal awayAvg) {
		this.awayAvg = decimalFormat.format(awayAvg);
	}

	@XmlElement(name = "home_count")
	public String getHomeCount() {
		return this.homeCount;
	}

	public void setHomeCount(Integer homeCount) {
		this.homeCount = homeCount.toString();
	}

	@XmlElement(name = "away_count")
	public String getAwayCount() {
		return this.awayCount;
	}

	public void setAwayCount(Integer awayCount) {
		this.awayCount = awayCount.toString();
	}

}
