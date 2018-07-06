### Car Service Restful API 

It is a Maven Project based on Spring MVC framework.

#### Run postgres container locally 

```
docker pull postgres
docker run --name dealerDB -e POSTGRES_DB=car_dev -e POSTGRES_USER=admin -e POSTGRES_PASSWORD=password -p 5432:5432 -d postgres
```

#### Run migration

    mvn compile flyway:migrate -P unit -Ddb_url=${url} -Ddb_password=${password} -Ddb_username=${username}

you can also add that into maven settings.xml

```$xml
	<profiles>
		<profile>
			<id>unit</id>
			<properties>
				<db_url>localhost:5432/car_unit</db_url>
				<db_username>xxxx</db_username>
				<db_password>xxxx</db_password>
			</properties>
		</profile>
	</profiles>
```

#### Unit Test

```
mvn compile test -Dspring.profiles.active=unit
```

#### Configuration information (The config file is located in ./src/main/resources/META-INF/)

```
mvn compile -Dspring.profiles.active=dev
mvn compile -Dspring.profiles.active=unit
```

#### Package Command

```
mvn compile package -DoutputDirectory=./target
```