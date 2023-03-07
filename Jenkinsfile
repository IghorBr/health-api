node {
  stage("Clone the project") {
    git branch: 'main', url: 'https://github.com/IghorBr/health-api.git'
  }

  stage("Compilation") {
    bat "./mvnw clean install -DskipTests"
  }

  stage("Tests and Deployment") {
    stage("Runing unit tests") {
      bat "./mvnw test -Punit"
    }
    stage("Deployment") {
      bat './mvnw spring-boot:run'
    }
  }
}