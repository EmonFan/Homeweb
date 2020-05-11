-- Build the docker image
docker build -t homeweb:1.0 .

-- Tag the image
docker images
docker tag image_id emonfan/homeweb:latest

-- Push the image to docker hub
docker push emonfan/homeweb:latest

-- Force rebuild on dockerhost
docker-compose pull homeweb


-- Issue with headless docker installation on Ubuntu
sudo apt-get remove golang-docker-credential-helpers
docker login --username=emonfan
sudo apt-get install docker-compose
docker-compose -f ${USERDIR}/docker/docker-compose.yml up -d
