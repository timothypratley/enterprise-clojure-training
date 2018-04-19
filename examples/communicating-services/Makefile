build:
	cd customer-data-service && $(MAKE)
	cd insurance-policy-service && $(MAKE)
	cd insurance-policy-application-processor && $(MAKE)

up:
	docker-compose up -d && docker-compose logs -f

up-prod:
	docker-compose -f docker-compose-prod.yml up -d && docker-compose logs -f

stop:
	docker-compose stop
