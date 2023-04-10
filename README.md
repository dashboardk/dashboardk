# dashboardk
MonoRepo of a complete Devops Dashboard that fetches details from multiple sources and display it


To run the program, use the commands below:

```bash
$ ./gradlew build         # builds the client/backend
$ ./gradlew :backend:run  # runs the backend server
```

After starting the backend, you can visit the website at `http://127.0.0.1:8080`.

If you're working on the client and would prefer a hot-reloading instance, you can use:

```bash
./gradlew :client:run
```

Then you can reach the client at `http://127.0.0.1:3000`.

## Docker

First, you'll need to build the `webapp` Docker image. However, before you can, you must first build the app. This can
all be done by following the commands below:

```bash
$ ./gradlew build
$ docker build -t webapp .
```

Once the image is done, start the web server and MySQL database with:

```bash
$ docker-compose up
```
