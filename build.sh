#!/bin/sh

set -e 

asciidoctor -a toc manual.adoc
asciidoc --backend slidy slides.adoc
