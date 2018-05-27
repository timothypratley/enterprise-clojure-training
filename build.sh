#!/bin/sh

set -e

asciidoctor --attribute=toc --destination-dir=site manual/*.adoc slides/*.adoc
