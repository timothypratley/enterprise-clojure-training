SOURCES := $(wildcard docs/*.adoc)
HTML := $(patsubst docs/%.adoc, site/%.html, $(SOURCES))
PDF := $(patsubst docs/%.adoc, site/%.pdf, $(SOURCES))
CLOJURE_COURSE_SOURCES := $(wildcard docs/clojure/*.adoc)
CLOJURE_COURSE := $(patsubst docs/clojure/%.adoc, site/clojure/%.html, $(CLOJURE_COURSE_SOURCES))

.PHONY: all clean setup

all: setup $(HTML) $(PDF) $(CLOJURE_COURSE)

clean:
	rm site/*.html site/*.pdf site/clojure/*.html

setup: .bundle

.bundle:
	bundle --path=.bundle/gems --binstubs=.bundle/.bin

site/clojure/%.html: docs/clojure/%.adoc
	bundle exec asciidoctor-revealjs --destination-dir=site/clojure $<

site/%.html: docs/%.adoc
	bundle exec asciidoctor --destination-dir=site $< 

site/%.pdf: docs/%.adoc
	bundle exec asciidoctor-pdf --destination-dir=site $<

