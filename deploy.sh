#!/bin/bash
set -e
cd $(dirname $0)
./build.sh
cd site
git init
git add .
git commit -m "Deploy to GitHub Pages"
git push --force --quiet "git@github.com:timothypratley/enterprise-clojure-training.git" master:gh-pages
rm -fr .git
