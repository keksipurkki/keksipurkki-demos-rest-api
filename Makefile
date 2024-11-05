IMAGE_NAME:=$(shell basename $(CURDIR))

app/target/app.jar:
	mvn clean verify -pl app -Pnative

build: app/target/app.jar
	docker build -t $(IMAGE_NAME) -f docker/Dockerfile --build-arg APP_JAR=$< .

build-native: app/target/app.jar
	docker build -t $(IMAGE_NAME) -f docker/Dockerfile.native --build-arg APP_JAR=$< .

clean:
	mvn clean
