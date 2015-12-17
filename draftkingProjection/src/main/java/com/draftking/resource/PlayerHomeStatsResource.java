package com.draftking.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.draftking.dto.PlayersStatsAllYearsDTO;
@Path("/playerhomestats")

@Produces({"application/xml", "application/json"})
public interface PlayerHomeStatsResource {
	//http://localhost:8282/draftking/services/playerhomestats/all
	
	    @GET
		@Path("/all")
	    @Produces("application/json")
	    PlayersStatsAllYearsDTO getAllPlayersHomeStats();
	 
		
		@GET		   
		@Produces("application/xml")    
		  @Path("/{player}")
		    PlayersStatsAllYearsDTO getAllPLayersHomeStats(@PathParam("player") String playerName);
		
		 

}
