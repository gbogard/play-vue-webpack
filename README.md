# Play Framework + Vue.js

This is a Play Framework + Vue.js (v2) boilerplate that uses Webpack
to load front-end modules and provide **hot reloading**.

In production mode, it has proper 
long-term caching, **minified and gzip compressed css and js bundles**

It supports SASS and includes Bootstrap 4 by default.

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
The front-end code entry point is located in front/app.js

SASS stylesheets are located in the front/sass folder.

This boilerplate uses Webpack-dev-server to provide hot-reloading during development.

### Production mode

Use `sbt dist` to create zip archive containing your application. This will also trigger
the front-end build.

To launch the front-end build manually you can run `sbt frontEndBuild`

## Configuration

### conf/frontend.conf

#### webpack.port
Allows you to change the port Webpack-dev-server is running on. (default 8080)
