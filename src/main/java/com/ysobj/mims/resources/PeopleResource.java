package com.ysobj.mims.resources;

import io.dropwizard.jersey.params.LongParam;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ysobj.mims.core.People;
import com.ysobj.mims.jdbi.PeopleDao;

@Path("/people")
@Produces(MediaType.APPLICATION_JSON)
public class PeopleResource {
	private PeopleDao peopleDao;
	public PeopleResource(PeopleDao peopleDao) {
		this.peopleDao = peopleDao;
	}
	@GET
	@Path("/{people}")
	public People find(@PathParam("people") LongParam peopleId){
		People people = peopleDao.findById(peopleId.get());
		System.out.println(people);
		return people;
	}
}
