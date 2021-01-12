FROM node:12-alpine
WORKDIR /app
COPY package*.json ./
COPY public public
COPY server.js ./
COPY node_modules node_modules
EXPOSE 8000
CMD [ "node", "server.js" ]

LABEL maintainer="yauhen2012@gmail.com"
