def buildApp(){
 echo "building the application ${NEW_VERSION}"
 echo " deploying with ${SERVER_CREDENTIALS}"
 sh "${SERVER_CREDENTIALS}"
 sh "mvn install"
}

def testApp(){
 echo 'testing the application ...'
}

def deployApp(){
 echo "deploying the application: ${params.VERSION}"
 withCredentials([usernamePassword(credentials: 'services-github', usernameVariable: USER, passwordVariable: PWD)])
 {
  sh "some script ${USER} ${PWD}"
 }
}
return this
