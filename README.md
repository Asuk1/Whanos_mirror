# Whanos_mirror
Whannos_mirror


#################### FOLDERS ####################
folder('Whanos base images') {
    displayName('Whanos base images')
    description('Folder for Whanos project')
}

folder('Projects') {
    displayName('Projects')
    description('Folder for Whanos project')
}

#################### BASE IMAGES ####################

#Base images for c
freeStyleJob("Whanos base images/whanos-c") {
    steps {
        shell('docker build . -t whanos-c -f /images/whanos-c/Dockerfile.base')
        shell('docker tag whanos-c localhost:5000/whanos-c')
        shell('docker push localhost:5000/whanos-c')
        shell('docker pull localhost:5000/whanos-c')
        shell('docker rm whanos-c')
    }
}

#Base images for ansible
freeStyleJob("Whanos base images/whanos-ansible") {
    steps {
        shell('docker build . -t whanos-ansible -f /images/whanos-ansible/Dockerfile.base')
        shell('docker tag whanos-ansible localhost:5000/whanos-ansible')
        shell('docker push localhost:5000/whanos-ansible')
        shell('docker pull localhost:5000/whanos-ansible')
        shell('docker rm whanos-ansible')
    }
}

#Base images for docker
freeStyleJob("Whanos base images/whanos-docker") {
    steps {
        shell('docker build . -t whanos-docker -f /images/whanos-docker/Dockerfile.base')
        shell('docker tag whanos-docker localhost:5000/whanos-docker')
        shell('docker push localhost:5000/whanos-docker')
        shell('docker pull localhost:5000/whanos-docker')
        shell('docker rm whanos-docker')
    }
}

#Base images for java
freeStyleJob("Whanos base images/whanos-java") {
    steps {
        shell('docker build . -t  whanos-java -f /images/whanos-java/Dockerfile.base')
        shell('docker tag whanos-java localhost:5000/whanos-java')
        shell('docker push localhost:5000/whanos-java')
        shell('docker pull localhost:5000/whanos-java')
        shell('docker rm whanos-java')
    }
}

#Base images for javascript
freeStyleJob("Whanos base images/whanos-javascript") {
    steps {
        shell('docker build . -t whanos-javascript -f  /images/whanos-javascript/Dockerfile.base')
        shell('docker tag whanos-javascript localhost:5000/whanos-javascript')
        shell('docker push localhost:5000/whanos-javascript')
        shell('docker pull localhost:5000/whanos-javascript')
        shell('docker rm whanos-javascript')
    }
}

#Base images for befunge
freeStyleJob("Whanos base images/whanos-befunge") {
    steps {
        shell('docker build . -t whanos-befunge -f /images/whanos-befunge/Dockerfile.base')
        shell('docker tag whanos-befunge localhost:5000/whanos-befunge')
        shell('docker push localhost:5000/whanos-befunge')
        shell('docker pull localhost:5000/whanos-befunge')
        shell('docker rm whanos-befunge')
    }
}

#Base images for python
freeStyleJob("Whanos base images/whanos-python") {
    steps {
        shell('docker build . -t whanos-python -f /images/whanos-python/Dockerfile.base')
        shell('docker tag whanos-python localhost:5000/whanos-python')
        shell('docker push localhost:5000/whanos-python')
        shell('docker pull localhost:5000/whanos-python')
        shell('docker rm whanos-python')
    }

}


#################### PROJECTS ####################

#Project for link project with paremeter of github deploy and take your bash to execute your job
freeStyleJob("LINK PROJECTS") {
    parameters {
        stringParam('GITHUB_NAME', null, 'GitHub repository url for the job')
        stringParam('DISPLAY_NAME', null, 'Display name for the job')
    }
    steps {
        dsl {
            text('''
            freeStyleJob("Projects/$DISPLAY_NAME") {
                wrappers {
                    preBuildCleanup()
                }
                scm {
                    github("$GITHUB_NAME")
                }
                triggers {
                    scm('H/1 * * * *')
                }
                steps {
                        shell("/var/jenkins_home/bash.sh $DISPLAY_NAME\")
                }
            }
            ''')
        }
    }
}

####### HOW TO USE JENKINS ##############

Go to link project and set up your repository name in in the DISPLAY NAME by the name of your repository without the @github and name the test you want to execute
Then go to project to see the job created by your link project that take your whanos folder to execute the job based on the language of your repository


####### HOW TO RUN ##############

LANG=C.UTF-8 ansible-playbook ansible-playbook.yml -i inventory.ini

Do root@51.15.244.85
After this do the docker command to clean up your container
docker rm -f $(docker ps -aq)

Verify that the bash.sh in jenkins have still some permission. If no do a chmod 777 on it 

Build with docker compose up -d --build

Go to http://51.15.244.85:8080/ and enjoy