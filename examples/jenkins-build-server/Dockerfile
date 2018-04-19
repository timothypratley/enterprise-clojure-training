FROM jenkins/jenkins:lts
USER root
RUN wget --directory-prefix /usr/local/bin/ https://raw.github.com/technomancy/leiningen/stable/bin/lein
RUN chmod +x /usr/local/bin/lein 
USER jenkins
