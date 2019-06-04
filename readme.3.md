## Docker compose basics and Services

In a distributed application, different pieces of the app are called “services”. For example, if you imagine a video sharing site, it probably includes a service for storing application data in a database, a service for video transcoding in the background after a user uploads something, a service for the front-end, and so on.

Services are really just “containers in production.” A service only runs one image, but it codifies the way that image runs—what ports it should use, how many replicas of the container should run so the service has the capacity it needs, and so on. Scaling a service changes the number of container instances running that piece of software, assigning more computing resources to the service in the process.

Luckily it’s very easy to define, run, and scale services with the Docker platform -- just write a docker-compose.yml file.

#### Running It

Before we can use the `docker stack deploy` command we first run:

`$ docker swarm init`



Now let’s run it. You need to give your app a name. Here, it is set to getstartedlab:

`$ docker stack deploy -c docker-compose.yml getstartedlab`


Get the service ID for the one service in our application:

`$ docker service ls`

Alternatively, you can run `docker stack services`, followed by the name of your stack. The following example command lets you view all services associated with the `getstartedlab` stack:

`$ docker stack services getstartedlab`

A single container running in a service is called a task. Tasks are given unique IDs that numerically increment, up to the number of replicas you defined in docker-compose.yml. List the tasks for your service:

`$ docker service ps getstartedlab_web`

Tasks also show up if you just list all the containers on your system, though that is not filtered by service:

`docker container ls`


You should now be able to browse to http://localhost:4000. Refreshing should hit a different container

To view all tasks of a stack, you can run `docker stack ps` followed by your app name, as shown in the following example:

`$ docker stack ps getstartedlab`


You can scale the app by changing the replicas value in docker-compose.yml, saving the change, and re-running the docker stack deploy command from above. Docker performs an in-place update, no need to tear the stack down first or kill any containers.



Take the app down with docker stack rm:

`$ docker stack rm getstartedlab`

Take down the swarm.

`$ docker swarm leave --force`
