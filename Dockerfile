# Using an OpenJdk jdk image because I want to use it for development not just running it
FROM openjdk:8u212-jdk

# Set the working directory to /app
WORKDIR /app

# Copy the current directory contents into the container at /app
COPY . /app

# Do a single gradle wrapper initialise on build rather than mutlple on deploy (mutliple replicas)
RUN ["./gradlew", "clean"]

# Make port 80 available to the world outside this container
EXPOSE 8888

# Run app.py when the container launches
CMD ["./gradlew", "run"]
