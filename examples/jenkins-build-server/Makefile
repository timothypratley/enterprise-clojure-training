build:
	docker build -t lein-jenkins .

run:
	docker run -p 8080:8080 -p 50000:50000 -v jenkins_home:/var/jenkins_home lein-jenkins

clean:
	docker rm -v lein-jenkins 
