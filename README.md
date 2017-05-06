# Play Framework + Vue.js

This is a Play Framework + Vue.js boilerplate that uses Webpack
to load front-end modules and provide **hot reloading**.

## How to use

Clone the project.

Install the front-end dependencies.

```
cd front && npm install
```

Launch the project

```
sbt ~run
```

## Configuration

### conf/frontend.conf

#### webpack.port
Allows you to change the port Webpack-dev-server is running on. (default 8080)

#### npm.path
Allow you to change the path to the npm executable

