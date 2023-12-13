FROM jenkins/jenkins:lts
COPY --from=docker /usr/local/bin/docker /usr/local/bin/docker


ENV JAVA_OPTS -Djenkins.install.runSetupWizard=false
ENV CASC_JENKINS_CONFIG /var/jenkins_home/main.yml

USER jenkins
ENV ADMIN_PASSWORD ${ADMIN_PASSWORD}

COPY jenkins/plugins.txt /usr/share/jenkins/ref/plugins.txt
RUN jenkins-plugin-cli -f /usr/share/jenkins/ref/plugins.txt

COPY jenkins/bash.sh /var/jenkins_home/bash.sh

COPY jenkins/main.yml /var/jenkins_home/main.yml
COPY jenkins/job_dsl.groovy /var/jenkins_home/job_dsl.groovy

COPY ["images/", "/var/jenkins_home/workspace/images/"]