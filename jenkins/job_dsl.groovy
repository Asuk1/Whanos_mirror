folder('Whanos base images') {
    displayName('Whanos base images')
    description('Folder for Whanos project')
}

folder('Projects') {
    displayName('Projects')
    description('Folder for Whanos project')
}

freeStyleJob("Whanos base images/whanos-c") {
    steps {
        shell('docker build . -t whanos-c -f /images/whanos-c/Dockerfile.base')
        shell('docker tag whanos-c localhost:5000/whanos-c')
        shell('docker push localhost:5000/whanos-c')
        shell('docker pull localhost:5000/whanos-c')
        shell('docker rm whanos-c')
    }
}

freeStyleJob("Whanos base images/whanos-ansible") {
    steps {
        shell('docker build . -t whanos-ansible -f /images/whanos-ansible/Dockerfile.base')
        shell('docker tag whanos-ansible localhost:5000/whanos-ansible')
        shell('docker push localhost:5000/whanos-ansible')
        shell('docker pull localhost:5000/whanos-ansible')
        shell('docker rm whanos-ansible')
    }
}

freeStyleJob("Whanos base images/whanos-docker") {
    steps {
        shell('docker build . -t whanos-docker -f /images/whanos-docker/Dockerfile.base')
        shell('docker tag whanos-docker localhost:5000/whanos-docker')
        shell('docker push localhost:5000/whanos-docker')
        shell('docker pull localhost:5000/whanos-docker')
        shell('docker rm whanos-docker')
    }
}

freeStyleJob("Whanos base images/whanos-java") {
    steps {
        shell('docker build . -t  whanos-java -f /images/whanos-java/Dockerfile.base')
        shell('docker tag whanos-java localhost:5000/whanos-java')
        shell('docker push localhost:5000/whanos-java')
        shell('docker pull localhost:5000/whanos-java')
        shell('docker rm whanos-java')
    }
}

freeStyleJob("Whanos base images/whanos-javascript") {
    steps {
        shell('docker build . -t whanos-javascript -f  /images/whanos-javascript/Dockerfile.base')
        shell('docker tag whanos-javascript localhost:5000/whanos-javascript')
        shell('docker push localhost:5000/whanos-javascript')
        shell('docker pull localhost:5000/whanos-javascript')
        shell('docker rm whanos-javascript')
    }
}

freeStyleJob("Whanos base images/whanos-befunge") {
    steps {
        shell('docker build . -t whanos-befunge -f /images/whanos-befunge/Dockerfile.base')
        shell('docker tag whanos-befunge localhost:5000/whanos-befunge')
        shell('docker push localhost:5000/whanos-befunge')
        shell('docker pull localhost:5000/whanos-befunge')
        shell('docker rm whanos-befunge')
    }
}

freeStyleJob("Whanos base images/whanos-python") {
    steps {
        shell('docker build . -t whanos-python -f /images/whanos-python/Dockerfile.base')
        shell('docker tag whanos-python localhost:5000/whanos-python')
        shell('docker push localhost:5000/whanos-python')
        shell('docker pull localhost:5000/whanos-python')
        shell('docker rm whanos-python')
    }

}

freeStyleJob("LINK PROJECTS") {
    parameters {
        stringParam('GITHUB_NAME', null, 'GitHub repository url for the job')
        stringParam('DISPLAY_NAME', null, 'Display name for the job')
        credentialsParam('SSH_PRIVATE_KEY') {
            type('com.cloudbees.jenkins.plugins.sshcredentials.impl.BasicSSHUserPrivateKey')
        }
    }
    steps {
        dsl {
            text('''
            freeStyleJob("Projects/$DISPLAY_NAME") {
                wrappers {
                    preBuildCleanup()
                }
                scm {
                    git {
                        remote {
                            url("$GITHUB_NAME")
                            credentials("$SSH_PRIVATE_KEY")
                        }
                    }
                }
                triggers {
                    scm('H/1 * * * *')
                }
                steps {
                    shell("/var/jenkins_home/bash.sh $DISPLAY_NAME")
                }
            }
            ''')
        }
    }
}
