## Stacks and Multiple Services

[Source](https://docs.docker.com/get-started/part5/)

I am starting to mix my own learning into the tutorial now.

Let's get to the point where we've deployed what we've got and move from there.

Initialise the swarm:

`$ docker swarm init`

Deploy it:

`$ docker stack deploy -c docker-compose.yml getstartedlab`

http://localhost:4000 should be responding.

Back to the tutorial...

Adding a visualiser

I copied the file `docker-compose.yml` to a new file to `docker-compose-visualiser.yml` and added to the services:

```
  visualizer:
    image: dockersamples/visualizer:stable
    ports:
      - "8080:8080"
    volumes:
      - "/var/run/docker.sock:/var/run/docker.sock"
    deploy:
      placement:
        constraints: [node.role == manager]
    networks:
      - webnet
```

Deploy over (redeploy) with the new file but keeping the same appname:

`$ docker stack deploy -c docker-compose-visualiser.yml getstartedlab`

Browsing http://localhost:8080 should now bring up a visualiser

_Note:_ Always read what you copy and paste. Notice what's different, notice what's the same.

This has a volumes, a deploy placement constraint, and a network which is the same. I am going to forget about those for
 now but people generally don't put things in there for no reason. Does network mean they will be able to speak to each other?
 If I change it will it not work? Volume... I can only guess it's for storage... this will probably be where I change my
 make it respond to source code locally. What does the constraint do. I think it's for multiple machines and means it 
 has to run on specific node. Either way none of these things are really important right now. Sometimes I will play with 
 and change these values and see if they break or change behaviour. I am moving on for now but I guarantee they will be 
 needed in more advanced cases. 

#### Adding redis

I copied the file `docker-compose-visualiser.yml` to a new file to `docker-compose-visualiser-redis.yml` and added redis:

```
  redis:
    image: redis
    ports:
      - "6379:6379"
    volumes:
      - "/home/docker/data:/data"
    deploy:
      placement:
        constraints: [node.role == manager]
    command: redis-server --appendonly yes
    networks:
      - webnet
```

Deploy over again:

`$ docker stack deploy -c docker-compose-visualiser-redis.yml getstartedlab`

Browsing http://localhost:8080 should now bring up a visualiser

Turns out my guesses volumes and constraints were correct:

_Most importantly, there are a couple of things in the redis specification that make data persist between deployments of this stack:_

```
redis always runs on the manager, so it’s always using the same filesystem.  
redis accesses an arbitrary directory in the host’s file system as `/data` inside the container, which is where Redis stores data._
```

_Together, this is creating a “source of truth” in your host’s physical filesystem for the Redis data. Without this, Redis would store its data in /data inside the container’s filesystem, which would get wiped out if that container were ever redeployed._ 

Note: I have removed the volume section from the redis yml file because I don't really care about persisting the data at this point. This would mean making sure these paths exists and are writeable by docker. I will need to do something of the sort when I do the real thing though

Browsing http://localhost:4000 you should now see the hit counter ticking up. This means redis and the python service are talking to each other.

#### Getting started tutorial finished

I now have a basic idea of what I need to do. I feel I can start but there will still be some key pieces missing.

