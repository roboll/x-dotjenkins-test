import groovy.json.JsonSlurper
def json = new JsonSlurper().parseText(readFileFromWorkspace('.jenkins/jenkins.json'))

folder {
  name json.jenkins.folder
}