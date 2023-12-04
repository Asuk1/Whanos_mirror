folder('Whanos base images') {
    displayName('Whanos base images')
    description('Folder for Whanos project')
}

freeStyleJob("Whanos base images/WHANOS BASE IMAGES BUILD JOBS") {
    steps {
        buildImageForLanguage('c')
        buildImageForLanguage('python')
        buildImageForLanguage('java')
        buildImageForLanguage('javascript')
        buildImageForLanguage('befunge')
    }
    post {
        always {
            cleanWs()
        }
    }
}

freeStyleJob("LINK PROJECTS") {
    parameters {
        string(name: 'PROJECT_NAME', defaultValue: 'whanos', description: 'Name of the project')
        string(name: 'REPOSITORY_URL', defaultValue: ' ', description: 'URL of the repository')
        string(name: 'KUBE_CONFIG_PATH', defaultValue: ' ', description: 'Path to the kube config file')
        string(name: 'DOCKER', defaultValue: ' ', description: 'Docker image name')
        string(name: 'ANSIBLE', defaultValue: ' ', description: 'Ansible image name')

    }
    steps {
       job(projectName) {
        displayName("${projectName} Job")

        triggers {
            pollSCM('H/1 * * * *')
        }

        parameters {
            string(name: 'REPOSITORY_URL', defaultValue: repositoryUrl, description: 'Repository URL')
            string(name: 'KUBE_CONFIG_PATH', defaultValue: kubeConfigPath, description: 'Path to Kubernetes config file')
            string(name: 'DOCKER_IMAGE', defaultValue: dockerImage, description: 'Docker image name')
            string(name: 'ANSIBLE_IMAGE', defaultValue: ansibleImage, description: 'Ansible image name')
        }

        environment {
            REPOSITORY_URL = repositoryUrl
            KUBE_CONFIG_PATH = kubeConfigPath
            DOCKER_IMAGE = dockerImage
            ANSIBLE_IMAGE = ansibleImage
        }

        stages {
            stage('Containerize_Application') {
                steps {
                    script {
                        // Idk what to do here now
                    }
                }
            }

            stage('Deploy_to_Kubernetes') {
                when {
                    expression { KUBE_CONFIG_PATH != '' }
                }
                steps {
                    script {
                        // Idk what to do here now
                    }
                }
            }
            stage('Deploy_to_Docker') {
                when {
                    expression { DOCKER_IMAGE != '' }
                }
                steps {
                    script {
                        // Idk what to do here now
                    }
                }
            }
            stage('Deploy_to_Ansible') {
                when {
                    expression { ANSIBLE_IMAGE != '' }
                }
                steps {
                    script {
                        // Idk what to do here now
                    }
                }
            }
        }
    }
    }
    post {
        always {
            cleanWs()
        }
    }
}

freeStyleJob("JOBS CREATED BY THE link-project JOB/Projects") {
    job(projectName) {
        displayName("${projectName} Job")
        folder('Projects')

        triggers {
            pollSCM('H/1 * * * *')
        }

        parameters {
            string(name: 'REPOSITORY_URL', defaultValue: repositoryUrl, description: 'Repository URL')
            string(name: 'KUBE_CONFIG_PATH', defaultValue: kubeConfigPath, description: 'Path to Kubernetes config file')
            string(name: 'DOCKER_IMAGE', defaultValue: dockerImage, description: 'Docker image name')
            string(name: 'ANSIBLE_IMAGE', defaultValue: ansibleImage, description: 'Ansible image name')
        }
        environment {
            REPOSITORY_URL = repositoryUrl
            KUBE_CONFIG_PATH = kubeConfigPath
            DOCKER_IMAGE = dockerImage
            ANSIBLE_IMAGE = ansibleImage
        }
        steps {
            script {
                // void for now wiating for more info
            }
        }
    }
    post {
        always {
            cleanWs()
        }
    }
}