FROM tomcat:9.0
ADD build/libs/freemarkerGradle-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/freemarker.war
CMD ["catalina.sh", "run"]
# install dependencies
RUN apt-get update && apt-get install -y \
      curl \
      npm \
      nodejs \
      git;
#RUN apt-get install ia32-libs-gtk
#RUN /sbin/ldconfig -v
RUN npm install npm@latest -g
#RUN npm install puppeteer
RUN  apt-get update \
     && apt-get install -y wget gnupg ca-certificates \
     && wget -q -O - https://dl-ssl.google.com/linux/linux_signing_key.pub | apt-key add - \
     && sh -c 'echo "deb [arch=amd64] http://dl.google.com/linux/chrome/deb/ stable main" >> /etc/apt/sources.list.d/google.list' \
     && apt-get update \
     # We install Chrome to get all the OS level dependencies, but Chrome itself
     # is not actually used as it's packaged in the node puppeteer library.
     # Alternatively, we could could include the entire dep list ourselves
     # (https://github.com/puppeteer/puppeteer/blob/master/docs/troubleshooting.md#chrome-headless-doesnt-launch-on-unix)
     # but that seems too easy to get out of date.
     && apt-get install -y google-chrome-stable \
     && rm -rf /var/lib/apt/lists/* \
     && wget --quiet https://raw.githubusercontent.com/vishnubob/wait-for-it/master/wait-for-it.sh -O /usr/sbin/wait-for-it.sh \
     && chmod +x /usr/sbin/wait-for-it.sh
ADD package.json /usr/local/tomcat
ADD package-lock.json /usr/local/tomcat
ADD index.js /usr/local/tomcat
ADD columnsPartha1Template.ftl /usr/local/tomcat
RUN npm install
RUN npm install fs
