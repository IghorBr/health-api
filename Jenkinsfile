node {
  stage("Clone the project") {
    git branch: 'main', url: 'https://github.com/IghorBr/health-api.git'
  }

  stage("Compilation") {
    sh "./mvnw clean install -DskipTests"
  }

  stage("Tests and Deployment") {
    stage("Runing unit tests") {
      sh "./mvnw test -Punit"
    }
    stage("Deployment") {
      sh 'mvn spring-boot:run'
    }
  }
}