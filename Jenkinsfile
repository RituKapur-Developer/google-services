pipeline{
 agent any{
 parameters{
  //string(name:'VERSION', defaultValue:'', description: 'version to deploy')
  choice(name:'VERSION', choices: ['1.1.0', '1.1.1', '2.0.0', '2.1.1'], description:'')
  booleanParam(name:'executeTests', defaultValue: true, description:'')
 }
 environment{
  //accessible in all the code below
  NEW_VERSION = '1.1.0'
  SERVER_CREDENTIALS = credentials('services-github')
 }
  stages{
   stage("build"){
   when{
    expression{
    //BRANCH_NAME is an environment variable, i.e., always available in Jenkinsfile
     BRANCH_NAME == 'master' || 'summer-2020'
     //&& CODE_CHANGES == true
    }
   }
    steps{
     echo " building the application version ${NEW_VERSION}"
     echo " deploying with ${SERVER_CREDENTIALS}"
     sh "${SERVER_CREDENTIALS}"
     sh "mvn install"
     //sh "gradle build"
    }
   }
   stage("test"){
   when{
    expression{
      
    //BRANCH_NAME is an environment variable, i.e., always available in Jenkinsfile
     BRANCH_NAME == 'master' && params.executeTests
     //) && CODE_CHANGES == true
    }
   }
    steps{
     echo 'testing the application ...'
    }
   }
   stage("review"){
   when{
    expression{
    //BRANCH_NAME is an environment variable, i.e., always available in Jenkinsfile
     BRANCH_NAME == 'master'
     //) && CODE_CHANGES == true
    }
   }
    steps{
     echo 'reviewing the application ...'
    }
   }
   stage("deploy"){
   when{
    expression{
    //BRANCH_NAME is an environment variable, i.e., always available in Jenkinsfile
     BRANCH_NAME == 'master'
    }
   }
    steps{
     echo "deploying the application: ${params.VERSION}"
     withCredentials([usernamePassword(credentials: 'services-github', usernameVariable: USER, passwordVariable: PWD)])
     {
      sh "some script ${USER} ${PWD}"
     }
    }
   }
  }
 }
}
