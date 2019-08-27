#!/usr/bin/env sh

if [ "$TRAVIS_BRANCH" = "master" ] && [ "$TRAVIS_PULL_REQUEST" = "false" ];
then
    openssl aes-256-cbc -K $encrypted_e66af61e534c_key -iv $encrypted_e66af61e534c_iv -in B27871CF38A3D5179C445ABC95002DCA39516032.gpg.enc -out B27871CF38A3D5179C445ABC95002DCA39516032.gpg -d
fi