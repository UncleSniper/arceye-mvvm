GRADLE=./gradlew $(J7RTOPT) $(AEDEPOPT)
J7RTOPT=-Pjava7RTPath=/usr/lib/jvm/icedtea-bin-7/jre/lib/rt.jar
AEDEPOPT=-ParceyeDependBase=/home/cloud/arceye/ -ParceyeDependPath=/build/libs/

.SILENT:
.PHONY: all jar clean new

all: jar

jar:
	$(GRADLE) assemble

clean:
	$(GRADLE) clean

new: clean jar
