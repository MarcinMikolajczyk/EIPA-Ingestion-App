# EIPA Ingestion App

App returns updated electricity charging point. Program using spring webflux and spring integration frameworks.
In application.properties file you can configure:

`export-data=` link with dynamic data

`export-data-token=` user token

`repeat-rate=` time intervals between request to server

## Updated Points

### GET

The application returns a json file with data at the API event address:

- `/api/updated-points`

