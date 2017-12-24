# Play Framework + Vue.js starter

This is a modern Play Framework + Vue.js (v2) boilerplate that uses Webpack
to load front-end modules and provide **hot reloading**.

In production mode, it has proper 
long-term caching, **minified and gzip-compressed css and js bundles**

It supports SASS and includes Bootstrap 4 by default. (Bootstrap may easily be 
removed or replaced though).

It works out-of-the-box.

## How to use

[Download the project](https://github.com/gbogard/play-vue-webpack/archive/master.zip).

Install the front-end dependencies.

```
sbt frontEndBuild
```

Launch the project

```
sbt ~run
```
The front-end code entry point is located in `front/app.js`. This is where you can import your modules
and declare your Vue components.

SASS stylesheets are located in the `front/sass` folder.

The main stylesheet is `front/sass/style.scss`. It's imported by default.

Vue single-file components are located in `front/src/components`.

This boilerplate uses Webpack-dev-server to provide hot-reloading during development.

The Play application is in the `app` folder. The `FrontController` is responsible for
serving the index page, with dev or production bundles depending on the current environment.

### Production mode

Use `sbt dist` to create zip archive containing your application. This will also trigger
the front-end build.

To launch the front-end build manually you can run `sbt frontEndBuild`.

In production mode, CSS and JS are split into two different files. Thanks to gzip compression,
the default js bundle is less than 23kb.

#### Long-term caching

Production bundles have a unique hash in their name, allowing long-term caching without any
risk of keeping an old build in the browser's cache.

The `lib/WebpackBuildFile` object is responsible for determining the full 
name of the CSS and JS bundles.

## Configuration

### conf/frontend.conf

#### webpack.port
Allows you to change the port Webpack-dev-server is running on. (default 8080)

## Contribute

Contribution are welcome if you know how to improve this project.
