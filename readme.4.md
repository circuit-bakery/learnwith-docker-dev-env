## Swarm Clusters and Docker Machine basics

[Source](https://docs.docker.com/get-started/part4/)

you deploy this application onto a cluster, running it on multiple machines. Multi-container, multi-machine applications are made possible by joining multiple machines into a “Dockerized” cluster called a swarm.

A swarm is a group of machines that are running Docker and joined into a cluster. After that has happened, you continue to run the Docker commands you’re used to, but now they are executed on a cluster by a swarm manager. The machines in a swarm can be physical or virtual. After joining a swarm, they are referred to as nodes.

Swarm managers are the only machines in a swarm that can execute your commands, or authorize other machines to join the swarm as workers. Workers are just there to provide capacity and do not have the authority to tell any other machine what it can and cannot do.

The basic concept is simple enough: run `docker swarm init` to enable swarm mode and make your current machine a swarm manager, then run `docker swarm join` on other machines to have them join the swarm as workers.

create a couple of VMs using docker-machine, using the VirtualBox driver:

`$ docker-machine create --driver virtualbox myvm1`  
`$ docker-machine create --driver virtualbox myvm2`

List the VMs and get their IP addresses

`$ docker-machine ls`

To start a machine that’s stopped, run:

`$ docker-machine start <machine-name>`


You can send commands to your VMs using `docker-machine ssh`. Instruct myvm1 to become a swarm manager with `docker swarm init`.

`$ docker-machine ssh myvm1 "docker swarm init --advertise-addr 192.168.99.100"`

_Always run docker swarm init and docker swarm join with port 2377 (the swarm management port), or no port at all and let it take the default._

The response to `docker swarm init` contains a pre-configured `docker swarm join` command

Run it on other machines you wish to join the swarm

`$ docker-machine ssh myvm2 "docker swarm join  --token <token> <ip>:2377"`


Run `docker node ls` on the manager to view the nodes in this swarm:

`$ docker-machine ssh myvm1 "docker node ls"`




#### Leaving a swarm

If you want to start over, you can run docker swarm leave from each node.


#### Deploy your app on the swarm cluster

Run `docker-machine env <vm-name>` to get the command to configure your shell to talk to a vm

`$ docker-machine env myvm1`

```
# Run this command to configure your shell:
# eval $(docker-machine env myvm1)
```

`$ eval $(docker-machine env myvm1)`


blah blah blah.... I skipped this bit because I get it and I wanted to it all locally

The VM needs an image available to it and I skipped the push the image to dockerhub

I can always come back to it if I need it. I don't need to share my images at this point and I only want to use it on my
machine so pushing it to a hub is not yet needed



