## Vertx Docker Integration

I am now using what I learned to make my own docker files

I've swapped out the example python app for an example vertx app the connects to mysql 

Using the updated docker file, build the app into a container:  

`$ docker build --tag=vertxdocker:0.0.1 .`


Run the container and browse http://localhost:4000  

`$ docker run -p 4000:8888 vertxdocker:0.0.1`


Searching the web I found a resource that shows how to include an mysql: https://docs.docker.com/samples/library/mysql/

Initialise the swarm:

`$ docker swarm init`

Deploy the app:

`$ docker stack deploy -c docker-compose.yml vertxdev`

We're now at the exact same place we were with the example app. Instead of python we're running vertx. Instead of redis we're running mysql.

It's not quite good for java/vertx/mysql development but it's a good start.

