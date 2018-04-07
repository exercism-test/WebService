package urlshortener;

import javax.ejb.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;

@Path ("/url")
@Singleton
public class URLShortener {
	private HashMap<String, URL> urls;
	private URLID idGenerator;
	private String currentID;

	public URLShortener () {
		urls = new HashMap<>();
		idGenerator = new URLID();
		currentID = idGenerator.first();
	}

	@GET
	@Path ("/{id}")
	public Response getID (@PathParam ("id") String id) {
		URL result = urls.get(id);

		if (result == null) {
			return Response.status(Status.NOT_FOUND).build();
		} else {
			try {
				return Response.status(Status.MOVED_PERMANENTLY).location(result.toURI()).build();
			} catch (URISyntaxException e) {
				return Response.status(Status.INTERNAL_SERVER_ERROR).build();
			}
		}
	}

	@PUT
	@Path ("/{id}")
	//@Consumes(MediaType.TEXT_PLAIN)
	public Response putID (@PathParam ("id") String id, String url) {
		URL result;
		try {
			result = new URL(url);
		} catch (MalformedURLException e) {
			return Response.status(Status.BAD_REQUEST).build();
		}

		if (urls.replace(id, result) == null) {
			return Response.status(Status.NOT_FOUND).build();
		} else {
			return Response.ok().build();
		}
	}

	@DELETE
	@Path ("/{id}")
	public Response deleteID (@PathParam ("id") String id) {
		if (urls.remove(id) == null) {
			return Response.status(Status.NOT_FOUND).build();
		} else {
			return Response.status(Status.NO_CONTENT).build();
		}
	}

	@GET
	@Path ("/")
	public String getAll () {
		return urls.toString();
	}

	@POST
	@Path ("/")
	public Response postURL (String url) {
		URL toAdd;
		try {
			toAdd = new URL(url);
		} catch (MalformedURLException e) {
			return Response.status(Status.BAD_REQUEST).build();
		}

		String id = currentID;
		currentID = idGenerator.next(currentID);

		urls.put(id, toAdd);
		return Response.status(Status.CREATED).entity(id).build();
	}

	@DELETE
	@Path ("/")
	public Response clearURLs () {
		urls.clear();
		return Response.status(Status.NO_CONTENT).build();
	}

}
