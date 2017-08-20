# Bird-Registry

Prerequisites
=============
1. Install and run mongo server in default port
2. Install maven build tool

Running testcases
=================
1. Open terminal
2. Navigate to project folder where pom.xml is exist
3. Run command 'mvn test -Dtarget.env=test'
  - target.env is used in pom.xml->profiles
  - target.env specified as test, so that test resource files will be used
  - test resource file contains testdatabase name, which wont interrupt main database during testing phase
  
 Building & running application
 ==============================

 1. Open terminal
 2. Navigate to project folder where pom.xml is exist
 3. Run command 'mvn package -Dmaven.test.skip=true -Dtarget.env=main'
  - target.env is specified as main, so that main resource files will be used
  - testing is skipped in this environment
 4. Run command 'java -jar target/Bird-Registry-0.0.1-SNAPSHOT.jar server config.yml' to run the application
  - config.yml contains information about server port
  - Application will run at http://localhost:9090
