import groovy.json.JsonSlurper
def json = new JsonSlurper().parseText(readFileFromWorkspace('.jenkins/config.json'))

def job_name      = '_tmpl'
def job_desc      = 'template for jobs'
def job_branches  = ['master', 'develop']

job {
  name "${json.jenkins.folder}/${job_name}"
  description job_desc

  if (json.jenkins.labels != null) {
    label json.jenkins.labels
  }

  scm {
    git {
      remote {
        github json.git.url
        credentials json.git.creds
      }

      job_branches.each { git_branch ->
        branch git_branch
      }
    }
  }

  wrappers {
    timestamps()
    buildName '#${BUILD_NUMBER} ${GIT_BRANCH} ${GIT_REVISION,length=10}'
  }

  steps {}

  publishers {}
}
