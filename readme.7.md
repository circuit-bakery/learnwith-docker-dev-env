## Making a build for development

Picking up from part6, we have our app running in a docker environment.

We do have some issues. If we want to see our code running we need to:

1. Start the swarm
2. Build the image
3. Start the containers

If we make a change, we need to:

1. Stop the container
2. Build the image
3. Restart the containers

This is not ideal for developing an app. 

With 3 more pieces information we're able to make the build more suitable for a development environment:

#### 1. Start the containers without setting up a swarm 

Another way of running `docker-compose.yml` is with the command `docker-compose`

    $ docker-compose up

This removes one step from writing code to running the code. We now have:

1. Build the image
2. Start the containers

But every time we make a change we still need to:

1. Stop the container
2. Build the new image
3. Restart the containers 

This has not improved much.

#### 2. Build the image directly from docker-compose

Tell docker-compose.yml to build the Dockerfile in the current directory

    web:
      build: .


Running `docker-compose up` will build the image too. The steps from code to result are now:

1. Start the containers

For a change, we execute the new code by:

1. Stopping the container
2. Restarting the containers

It's (benedict) cumbersome.

#### 3. Bind-mount the sourcecode into the container

To bind-mount the local directory `./` into the container `/app` directory, add to the docker-compose.yml:

    web:
      volumes:
        - './:/app'
 

Our latest code will now always be in the container.

Assuming we have a watch build task running in the container we will always have the latest app running.

The steps from code to result and change to result are now the same:

1. Start the containers

Fin.
