# How to run #

## How to start the servers ##
The servers requires you to have [glassfish](https://javaee.github.io/glassfish/download) installed.

Once glassfish has been downloaded and extracted you can navigate to the directory of it and start the default glassfish domain by typing
```./bin/asadmin start-domain```

You will now find the glassfish admin interface at `localhost:4848`. Once you have navigated to this interface, go to Applications > Deploy and choose the `.war` file we provided. Select `/` as content root, and press deploy
(It can also be deployed to different paths on the server / different ports, but the calculator client won't know how to contact the server).
Both servers come packaged in the same `.war` file and should no be available via port 8080.



## How to access the Calculator service ##
The Calculator service can be accessed via `http://127.0.0.1:8080/services/Calculator` via any SOAP/WSDL client.

We have provided a sample example client packaged in a jar that calls upon this server to perform some simple calculations.
This client can be run as `java -jar calculator_client.jar`.
It will connect to the address specified above (hard-coded) to perform the calculation.

## How to access the URL shortener service ##
The URL shortener service can be accessed at `http://127.0.0.1:8080/rest/url` via any rest/web client.
The service provides all methods as required by the assignment document. 

From here the user can send HTTP requests to the URL given above in order to test the service. 

# Design of the URL shortener #
The URL shortener consists of two main parts, an id generator, and the main service class that handles the requests.

## ID generator ##
One of the requirements of the assignment is to use as short as possible ids.
To figure out what characters we could use for the ids we looked up RFC 3986, the URI syntax.
The specification mentions that only the unreserved characters can be safely used as data in a URI, any others should be "percent encoded" making the URI longer.
So we have made a class `URLID` that generates ids using these unreserved characters.
It will first give us ids of length 1, then 2 and so on, incrementing the length of ids by one every time we run out of ids of shorter lengths.

## The main class ##
The `URLShortener` class form the implementation of the URL shortener.
It is marked as a `Singleton`, so the object gets instantiated once and then reused for every request.
This allows us to store the urls within this object without the need for a separate database.

The (id, URL) pairs are stored in a HashMap.
The hashmap offers O(1) lookups, and handles deletions fairly well.
 
We do not reuse keys that have been deleted previously.
We do this because we prefer people seeing an error page to them getting sent to the wrong page.

Note that we do not deduplicate shortened URLs (give the same ID for multiple requests with the same URL).
If we would combine every request for the same URL on the same ID we can no longer guarantee that when a DELETE request is performed on a certain ID, that the given ID no longer links to the URL, because there might be other requests for that same URL that have not been deleted yet.  
