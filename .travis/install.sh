#!/usr/bin/env sh

if [ "$TRAVIS_BRANCH" = "master" ] && [ "$TRAVIS_PULL_REQUEST" = "false" ];
then
    echo $enc_key | gpg --passphrase-fd 0 secret.gpg.gpg
fi