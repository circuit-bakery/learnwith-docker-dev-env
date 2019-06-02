## My first Docker Container

[Source](https://docs.docker.com/get-started/part2/)

#### Build and tag the docker image in the docker file

`$ docker build --tag=friendlyhello .`

_use_ `--tag=friendlyhello:v0.0.1` _to have a specific version number_

#### View the images in your machine’s local Docker image registry

`$ docker image ls`

#### Run the app, mapping your machine’s port 4000 to the container’s published port 80

`$ docker run -p 4000:80 friendlyhello`

You should now be able to browse to http://localhost:4000

_use -d to run in detached mode_

##### Close the detached image

Find the container id  
`$ docker container ls`

Stop the container using the id 
`$ docker container stop <containerId>`

