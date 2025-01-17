

### Prerequisites
* Java 17 OR later

* Maven 3.8.5 OR later

#### How to run the application locally

From *Terminal* run this command:

```
 cd <path_to_cron_parserfolder>

```
  mvn exec:java -Dexec.args="'*/15 0 1,15 * 1-5 /usr/bin/find'"

```

#### How to run the tests locally

From *Terminal* run this command:

```
 cd <path_to_cron_parserfolder>

```
  mvn test

