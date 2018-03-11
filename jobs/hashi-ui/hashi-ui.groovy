def hashiui_envs = [
            'prod':'10.0.2.15',
            'nonprod':'127.0.0.1'
            ]

for (env in hashiui_envs) {
      pipelineJob("nomad_deploy_${env.key}_hashi-ui") {
          parameters {
          stringParam("nomad_url", "${env.value}", 'the URL of the Nomad HTTP API endpoint')
      }

      definition{
          cpsScm {
              scm {
                  git {
                      branches('master')
                      remote {
                          url('https://github.com/chongsk/nomadfiles.git')
                      }
                  }
              }
              scriptPath("./jobs/hashi-ui/Jenkinsfile")
            }
        }
    }
}
