
FROM alpine:3.11

# Install OpenJDK 11
RUN apk add openjdk11

# Install Maven 3.6.3
RUN apk add --no-cache curl tar bash
ARG MAVEN_VERSION=3.6.3
ARG USER_HOME_DIR="/root"
RUN mkdir -p /usr/share/maven && \
curl -fsSL http://apache.osuosl.org/maven/maven-3/$MAVEN_VERSION/binaries/apache-maven-$MAVEN_VERSION-bin.tar.gz | tar -xzC /usr/share/maven --strip-components=1 && \
ln -s /usr/share/maven/bin/mvn /usr/bin/mvn
ENV MAVEN_HOME /usr/share/maven
ENV MAVEN_CONFIG "$USER_HOME_DIR/.m2"
#ENV MAVEN_OPTS="-XX:+TieredCompilation -XX:TieredStopAtLevel=1"

# Copy source to Image
RUN mkdir -p /usr/flatter
WORKDIR /usr/flatter
# Copy pom.xml
COPY pom.xml /usr/flatter
# Copy /src directory
COPY src /usr/flatter/src
# Execute build with Test
RUN mvn clean install

WORKDIR /usr/flatter/target
CMD ["sh", "-c", "java -jar flatter.jar"]
# Expose Server on port 10000
EXPOSE 10000
