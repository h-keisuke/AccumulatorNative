#!/bin/bash -x

set -eo pipefail

./gradlew clean shadowJar

WORKDIR="$(pwd)/workdir"

mkdir -p "$WORKDIR"

cp build/libs/AccumulatorNative-all.jar "$WORKDIR"
cp src/main/script/bootstrap "$WORKDIR"

COMMAND=$(cat <<-EOF
  yum update -y && yum install -y gcc glibc-devel zlib-devel tar gzip &&  \
  curl -sL https://github.com/graalvm/graalvm-ce-builds/releases/download/vm-20.3.0/graalvm-ce-java11-linux-amd64-20.3.0.tar.gz --output - | tar zx &&  \
  ./graalvm-ce-java11-20.3.0/bin/gu install native-image &&  \
  ./graalvm-ce-java11-20.3.0/bin/native-image --no-fallback -jar AccumulatorNative-all.jar accumulator-native
EOF
)

docker run -v "$WORKDIR":/workdir -w /workdir --rm amazonlinux:2 sh -c "$COMMAND"

cd "$WORKDIR"
chmod 755 bootstrap accumulator-native
zip accumulator-native.zip bootstrap accumulator-native